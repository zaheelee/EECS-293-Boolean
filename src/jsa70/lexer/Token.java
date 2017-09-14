package jsa70.lexer;

import java.util.*;

public final class Token
{
    private final Type TYPE;
    private final Optional<String> DATA;

    private static Map<Builder, Token> m_allTokens = new HashMap<>();


    private Token(Type type, Optional<String> data)
    {
        this.TYPE = type;
        this.DATA = data;
    }

    //Checks if a Token with specified info exists and returns it.
    //If it does not exist, the Token is created, then returned.
    public static Token of(Type type, String data)
    {
        Optional<String> opData;
        if(hasTypeData(type))
        {
            opData = Optional.of(data);
        }
        else
        {
            opData = Optional.empty();
        }

        Builder builder = new Builder(type, opData);

        if(m_allTokens.containsKey(builder))
        {
            return m_allTokens.get(builder);
        }

        Token token = builder.build();
        m_allTokens.put(builder, token);

        return token;
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

    //Checks if a type is expecting data
    private static boolean hasTypeData(Type type)
    {
        for(Type t : Type.values())
        {
            if(type.equals(t))
            {
                return t.hasData();
            }
        }
        return false;
    }


    public enum Type
    {
        NOT("(not)", false),
        AND("(and)", false),
        OR("(or)", false),
        OPEN("\\(", false),
        CLOSE("\\)", false),
        ID("[a-z]+", true),
        NUMBER("[-]?\\d+", true),
        BINARYOP("[\\+\\-\\*\\/]", true),
        WHITESPACE("\\s+", false);

        private final String pattern;
        private final Boolean hasData;

        Type(String pattern, Boolean hasData)
        {
            this.pattern = pattern;
            this.hasData = hasData;
        }

        public String getPattern()
        {
            return pattern;
        }

        public Boolean hasData()
        {
            return hasData;
        }
    }


    private static class Builder
    {
        private final Type TYPE;
        private final Optional<String> DATA;

        Builder(Type type, Optional<String> data)
        {
            this.TYPE = type;
            this.DATA = data;
        }

        private Token build()
        {
            return new Token(this.TYPE, this.DATA);
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }

            Builder builder = (Builder) o;

            if (TYPE != builder.TYPE)
            {
                return false;
            }
            return DATA != null ? DATA.equals(builder.DATA) : builder.DATA == null;
        }

        @Override
        public int hashCode()
        {
            int result = TYPE.hashCode();
            result = 31 * result + (DATA != null ? DATA.hashCode() : 0);
            return result;
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Token token = (Token) o;

        if (TYPE != token.TYPE)
        {
            return false;
        }
        return DATA != null ? DATA.equals(token.DATA) : token.DATA == null;
    }

    @Override
    public int hashCode()
    {
        int result = TYPE.hashCode();
        result = 31 * result + (DATA != null ? DATA.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Token{" +
                "TYPE=" + TYPE +
                ", DATA=" + DATA +
                '}';
    }
}
