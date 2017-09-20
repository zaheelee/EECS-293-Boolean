package jsa70.lexer;

import java.util.Optional;

public final class ParserException extends Exception
{
    private final ErrorCode errorCode; //why on earth does this not use all caps?
    private final int location;

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

    //TODO
    public static void verify(Optional<LocationalToken> token)
    {
        if(!token.isPresent())
        {
            //throw a TOKEN_EXPECTED exception
        }
    }

    //TODO
    public static void verifyEnd(Optional<LocationalToken> token)
    {
        if(token.isPresent())
        {
            //throw a TRAILING_INPUT exception
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

    //TODO
    public enum ErrorCode
    {
        TOKEN_EXPECTED,
        INVALID_TOKEN,
        TRAILING_INPUT;
    }

}
