package jsa70.lexer.src;

import java.util.*;

public final class Token
{
    private final Type TYPE;
    private final Optional<String> DATA;

    private static Map<Builder, Token> allTokens = new HashMap<>();


    private Token(Type type, Optional<String> data)
    {
        this.TYPE = type;
        this.DATA = data;
    }

    //Checks if a Token with specified info exists and returns it.
    //If it does not exist, the Token is created, then returned.
    public static Token of(Type type, String data)
    {
        Optional<String> optionalData;
        if(type.hasData())
        {
            optionalData = Optional.of(data);
        }
        else
        {
            optionalData = Optional.empty();
        }

        Builder builder = new Builder(type, optionalData);

        if(allTokens.containsKey(builder))
        {
            return allTokens.get(builder);
        }

        Token token = builder.build();
        allTokens.put(builder, token);

        return token;
    }

    public Type getType()
    {
        return TYPE;
    }

    public Optional<String> getData()
    {
        return DATA;
    }


    public enum Type
    {
        NOT("not", false, false, Optional.empty()),
        AND("and", false, true, Optional.of(ParserException.ErrorCode.AND_EXPECTED)),
        OR("or", false, true, Optional.empty()),
        OPEN("\\(", false, false, Optional.of(ParserException.ErrorCode.OPEN_EXPECTED)),
        CLOSE("\\)", false, false, Optional.of(ParserException.ErrorCode.CLOSE_EXPECTED)),
        ID("[a-z]+", true, false, Optional.of(ParserException.ErrorCode.ID_EXPECTED)),
        NUMBER("[-]?\\d+", true, false, Optional.empty()),
        BINARYOP("[\\+\\-\\*\\/]", true, false, Optional.empty()),
        WHITESPACE("\\s+", false, false, Optional.empty());

        private final String pattern;
        private final Boolean hasData;
        private Boolean isComplex;
        private Optional<ParserException.ErrorCode> errorCode;

        Type(String pattern, Boolean hasData, Boolean isComplex, Optional<ParserException.ErrorCode> errorCode)
        {
            this.pattern = pattern;
            this.hasData = hasData;
            this.isComplex = isComplex;
            this.errorCode = errorCode;
        }

        public String getPattern()
        {
            return pattern;
        }

        public Boolean hasData()
        {
            return hasData;
        }

        public Boolean isComplex()
        {
            return isComplex;
        }

        public Optional<ParserException.ErrorCode> getErrorCode()
        {
            return errorCode;
        }

        public static String getGroup()
        {
            StringBuilder output = new StringBuilder();
            //output.append("(");
            for(Type t : Type.values())
            {
                output.append('(');
                output.append(t.getPattern());
                output.append(')');
                output.append('|');
            }
            output.deleteCharAt(output.length() - 1);
            //output.append(")");
            return output.toString();
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
