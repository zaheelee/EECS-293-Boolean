package jsa70.lexer;

import java.util.Map;
import java.util.Optional;

public class Token
{

    //TODO reorder all of these things so they are in some kind of logical place


    private final Type TYPE;
    private final Optional<String> DATA;

    //TODO consider renaming
    private static Map<Builder, Token> map;

    //TODO
    //that returns a token of the given type and with the given ancillary data.
    //If a token already exists with the same type and data, this method returns
    //the previously created token. Otherwise, the instantiates a new token.
    //Furthermore, if the token type does not support ancillary data,
    //the second argument is silently ignored.
    public static Token of(Type type, String data)
    {
        return null;
    }

    private Token(Type type, Optional<String> data)
    {
        this.TYPE = type;
        this.DATA = data;
    }

    public Type getType()
    {
        return TYPE;
    }

    public String getData()
    {
        if(DATA.isPresent())
        {
            return DATA.get();
        }

        return null;
    }

    public enum Type
    {
        //TODO CHANGE LATER/ADD THE REST OF THE ENUM VALUES
        //TODO look up regexes and see what the assignment meant by this
        NOT("not", false);

        private final String pattern;
        private final Boolean hasData;

        Type(String pattern, Boolean hasData)
        {
            this.pattern = pattern;
            this.hasData = hasData;
        }
    }


    private static class Builder
    {
        private final Type TYPE;
        private final Optional<String> DATA;

        Builder(Type type, Optional<String> data)
        {
            //TODO do I want to check if the type is null? can an enum be null?
            this.TYPE = type;
            this.DATA = data;
        }

        private Token build()
        {
            return new Token(this.TYPE, this.DATA);
        }
    }
}
