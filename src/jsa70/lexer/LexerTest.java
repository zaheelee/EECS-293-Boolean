package jsa70.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest
{
    private String testStr;
    private Lexer lex;
    private Set<Token.Type> validTypes;
    private Set<Token.Type> invalidTypes;

    @Test
    void hasNext()
    {
        try
        {
            assertTrue(lex.hasNext());
        }
        catch (Exception e)
        {
            System.out.println("Breaking Exception is:");
            System.out.println(e.toString());
            assertTrue(false);
        }
    }

    @Test
    void next_getFirstToken()
    {
        LocationalToken lt = null;
        try
        {
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
        }
        catch (Exception e)
        {
            System.out.println("Breaking Exception is:");
            System.out.println(e.toString());
            assertTrue(false);
        }
    }

    @Test
    void next_getAllTokens()
    {
        LocationalToken lt = null;
        try
        {
            // "one"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("one"));

            // " "
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "and"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.AND);

            // " "
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "two"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("two"));

            // " "
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "or"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.OR);

            // "("
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.OPEN);

            // "three"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("three"));

            // " "
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "not"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.NOT);

            // " "
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "four"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("four"));

            // ")"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.CLOSE);

            // " "
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "5"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.NUMBER);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("5"));

            // "+"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.BINARYOP);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("+"));

            // "six"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("six"));

            // "-"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.BINARYOP);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("-"));

            // " "
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "7"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.NUMBER);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("7"));

            // "*"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.BINARYOP);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("*"));

            // "eight"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("eight"));

            // "/"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.BINARYOP);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("/"));

            // "9"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.NUMBER);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("9"));

            // " "
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "ten"
            assertTrue(lex.hasNext());
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("ten"));
        }
        catch (Exception e)
        {
            System.out.println("Breaking Exception is:");
            System.out.println(e.toString());
            assertTrue(false);
        }
    }

    @Test
    void nextValid_ValidInputs()
    {
        String validStr = "words and or stuff";
        Lexer validLex = new Lexer(validStr);
        try
        {
            Optional<LocationalToken> optional = validLex.nextValid(validTypes, invalidTypes);
            assertTrue(optional.isPresent());
            assertTrue(optional.get().getType() == Token.Type.ID);

            optional = validLex.nextValid(validTypes, invalidTypes);
            assertTrue(optional.isPresent());
            assertTrue(optional.get().getType() == Token.Type.AND);

            optional = validLex.nextValid(validTypes, invalidTypes);
            assertTrue(optional.isPresent());
            assertTrue(optional.get().getType() == Token.Type.OR);

            optional = validLex.nextValid(validTypes, invalidTypes);
            assertTrue(optional.isPresent());
            assertTrue(optional.get().getType() == Token.Type.ID);
        }
        catch (Exception e)
        {
            System.out.println("Breaking Exception is:");
            System.out.println(e.toString());
            assertTrue(false);
        }
    }

    @Test
    void nextValid_InvalidInputs()
    {
        String invalidStr = "words 5 and (or) stuff";
        Lexer invalidLex = new Lexer(invalidStr);
        try
        {
            Optional<LocationalToken> optional = invalidLex.nextValid(validTypes, invalidTypes);
            assertTrue(optional.isPresent());
            assertTrue(optional.get().getType() == Token.Type.ID);

            optional = invalidLex.nextValid(validTypes, invalidTypes);
        }
        catch (ParserException e)
        {
            assertTrue(e.getErrorCode().equals(ParserException.ErrorCode.INVALID_TOKEN));
        }
    }

    @BeforeEach
    void setUp()
    {
        testStr = "one and two or(three not four) 5+six- 7*eight/9   ten";
        lex = new Lexer(testStr);

        validTypes = new HashSet<>(Arrays.asList(Token.Type.ID, Token.Type.AND, Token.Type.OR));
        invalidTypes = new HashSet<>(Arrays.asList(Token.Type.NUMBER, Token.Type.OPEN, Token.Type.CLOSE));
    }
}