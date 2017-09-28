package jsa70.lexer.Test;

import jsa70.lexer.src.Token;
import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

class TypeTest
{
    @Test
    void getPattern()
    {
        String s = "word";
        //NOT
        assertFalse(Pattern.matches(Token.Type.NOT.getPattern(), s));
        s = "not";
        assertTrue(Pattern.matches(Token.Type.NOT.getPattern(), s));

        //AND
        assertFalse(Pattern.matches(Token.Type.AND.getPattern(), s));
        s = "and";
        assertTrue(Pattern.matches(Token.Type.AND.getPattern(), s));

        //OR
        assertFalse(Pattern.matches(Token.Type.OR.getPattern(), s));
        s = "or";
        assertTrue(Pattern.matches(Token.Type.OR.getPattern(), s));

        //OPEN
        assertFalse(Pattern.matches(Token.Type.OPEN.getPattern(), s));
        s = "(";
        assertTrue(Pattern.matches(Token.Type.OPEN.getPattern(), s));

        //CLOSE
        assertFalse(Pattern.matches(Token.Type.CLOSE.getPattern(), s));
        s = ")";
        assertTrue(Pattern.matches(Token.Type.CLOSE.getPattern(), s));

        //ID
        assertFalse(Pattern.matches(Token.Type.ID.getPattern(), s));
        s = "THIS SHOULD FAIL";
        assertFalse(Pattern.matches(Token.Type.ID.getPattern(), s));
        s = "success";
        assertTrue(Pattern.matches(Token.Type.ID.getPattern(), s));

        //NUMBER
        assertFalse(Pattern.matches(Token.Type.NUMBER.getPattern(), s));
        s = "12345";
        assertTrue(Pattern.matches(Token.Type.NUMBER.getPattern(), s));
        s = "-12345";
        assertTrue(Pattern.matches(Token.Type.NUMBER.getPattern(), s));

        //BINARYOP
        assertFalse(Pattern.matches(Token.Type.BINARYOP.getPattern(), s));
        s = "+";
        assertTrue(Pattern.matches(Token.Type.BINARYOP.getPattern(), s));
        s = "-";
        assertTrue(Pattern.matches(Token.Type.BINARYOP.getPattern(), s));
        s = "*";
        assertTrue(Pattern.matches(Token.Type.BINARYOP.getPattern(), s));
        s = "/";
        assertTrue(Pattern.matches(Token.Type.BINARYOP.getPattern(), s));

        //WHITESPACE
        assertFalse(Pattern.matches(Token.Type.WHITESPACE.getPattern(), s));
        s = " ";
        assertTrue(Pattern.matches(Token.Type.WHITESPACE.getPattern(), s));
    }

}