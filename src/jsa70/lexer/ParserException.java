package jsa70.lexer;

import java.util.Optional;

public final class ParserException extends Exception
{
    private final ErrorCode errorCode; //why on earth does this not use all caps?
    private final int location;

    public static final long serialVersionUID = 293L;

    ParserException(ErrorCode errorCode)
    {
        this.errorCode = errorCode;
        location = -1;
    }

    ParserException(LocationalToken token, ErrorCode errorCode)
    {
        this.errorCode = errorCode;
        location = token.getLocation();
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
        return errorCode;
    }

    public int getLocation()
    {
        return location;
    }

    public enum ErrorCode
    {
        TOKEN_EXPECTED,
        INVALID_TOKEN,
        TRAILING_INPUT;
    }

}
