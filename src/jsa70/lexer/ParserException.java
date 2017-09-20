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
        TRAILING_INPUT;
    }

}
