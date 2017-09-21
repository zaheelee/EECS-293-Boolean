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
            lex.next();
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
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("one"));

            // " "
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "and"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.AND);

            // " "
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "two"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("two"));

            // " "
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "or"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.OR);

            // "("
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.OPEN);

            // "three"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("three"));

            // " "
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "not"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.NOT);

            // " "
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "four"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("four"));

            // ")"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.CLOSE);

            // " "
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "5"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.NUMBER);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("5"));

            // "+"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.BINARYOP);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("+"));

            // "six"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("six"));

            // "-"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.BINARYOP);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("-"));

            // " "
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "7"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.NUMBER);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("7"));

            // "*"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.BINARYOP);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("*"));

            // "eight"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.ID);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("eight"));

            // "/"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.BINARYOP);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("/"));

            // "9"
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.NUMBER);
            assertTrue(lt.getData().isPresent() && lt.getData().get().equals("9"));

            // " "
            lt = lex.next();
            assertTrue(lt.getType() == Token.Type.WHITESPACE);

            // "ten"
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

            //whitespace is neither valid nor invalid
            optional = validLex.nextValid(validTypes, invalidTypes);
            assertTrue(!optional.isPresent());

            optional = validLex.nextValid(validTypes, invalidTypes);
            assertTrue(optional.isPresent());
            assertTrue(optional.get().getType() == Token.Type.AND);

            //whitespace is neither valid nor invalid
            optional = validLex.nextValid(validTypes, invalidTypes);
            assertTrue(!optional.isPresent());

            optional = validLex.nextValid(validTypes, invalidTypes);
            assertTrue(optional.isPresent());
            assertTrue(optional.get().getType() == Token.Type.OR);

            //whitespace is neither valid nor invalid
            optional = validLex.nextValid(validTypes, invalidTypes);
            assertTrue(!optional.isPresent());

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

            //whitespace is neither valid nor invalid
            optional = invalidLex.nextValid(validTypes, invalidTypes);
            assertTrue(!optional.isPresent());

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