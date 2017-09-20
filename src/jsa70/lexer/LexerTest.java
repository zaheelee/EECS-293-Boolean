package jsa70.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest
{
    //private String testStr;

    @Test
    void hasNext()
    {
    }

    @Test
    void next_getFirstToken()
    {
        String testStr = "one and two or (three not four) 5 + six - 7 * eight / 9   ten";
        Lexer lex = new Lexer(testStr);
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
        String testStr = "one and two or(three not four) 5+six- 7*eight/9   ten";
        Lexer lex = new Lexer(testStr);
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
    void nextValid()
    {
    }

    @BeforeEach
    void setUp()
    {
        //testStr = "one and two or (three not four) 5 + six - 7 * eight / 9   ten";
    }
}