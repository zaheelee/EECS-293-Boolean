package jsa70.lexer.src;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class DisjunctiveLexer
{
    static public final Set<Token.Type> VALID_TOKENS;
    static public final Set<Token.Type> INVALID_TOKENS;

    private Lexer lexer;

    static
    {
        VALID_TOKENS = new HashSet<>(
                Arrays.asList(Token.Type.AND, Token.Type.ID, Token.Type.NOT, Token.Type.OPEN, Token.Type.CLOSE));
        INVALID_TOKENS = new HashSet<>(
                Arrays.asList(Token.Type.OR, Token.Type.NUMBER, Token.Type.BINARYOP));
    }

    DisjunctiveLexer(String input)
    {
        lexer = new Lexer(input);
    }

    public Optional<LocationalToken> nextValid()
            throws ParserException
    {
        return lexer.nextValid(VALID_TOKENS, INVALID_TOKENS);
    }
}
