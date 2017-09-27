package jsa70.lexer;

import java.util.Optional;

public final class ParserException extends Exception
{
    private final ErrorCode ERROR_CODE;
    private final int LOCATION;

    public static final long serialVersionUID = 293L;

    ParserException(ErrorCode errorCode)
    {
        ERROR_CODE = errorCode;
        LOCATION = -1;
    }

    ParserException(LocationalToken token, ErrorCode errorCode)
    {
        ERROR_CODE = errorCode;
        LOCATION = token.getLocation();
    }

    public static void verify(Optional<LocationalToken> token)
            throws ParserException
    {
        if(!token.isPresent())
        {
            throw new ParserException(ErrorCode.TOKEN_EXPECTED);
        }
    }

    public final static void verify(Token.Type expectedType, LocationalToken token)
            throws ParserException
    {
        if(!expectedType.equals(token.getType()))
        {
            if(expectedType.getErrorCode().isPresent())
            {
                throw new ParserException(token, expectedType.getErrorCode().get());
            }
            // When no specific error is given, throw a more general one
            throw new ParserException(token, ErrorCode.INVALID_TOKEN);
        }
    }

    public static void verifyEnd(Optional<LocationalToken> token)
            throws ParserException
    {
        if(token.isPresent())
        {
            throw new ParserException(token.get(), ErrorCode.TRAILING_INPUT);
        }
    }


    public ErrorCode getErrorCode()
    {
        return ERROR_CODE;
    }

    public int getLocation()
    {
        return LOCATION;
    }

    @Override
    public String toString()
    {
        return "ParserException{" +
                "ERROR_CODE=" + ERROR_CODE +
                ", LOCATION=" + LOCATION +
                '}';
    }

    public enum ErrorCode
    {
        TOKEN_EXPECTED,
        INVALID_TOKEN,
        TRAILING_INPUT,
        AND_EXPECTED,
        OPEN_EXPECTED,
        CLOSE_EXPECTED,
        ID_EXPECTED;
    }

}
