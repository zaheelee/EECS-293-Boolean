package jsa70.lexer;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest
{
    @org.junit.jupiter.api.Test
    void of()
    {
        Token t1 = Token.of(Token.Type.ID, "abcdefg");
        assertEquals(t1.getType(), Token.Type.ID);
        assertEquals(t1.getData(), "abcdefg");

        Token t2 = Token.of(Token.Type.ID, "abcdefg");
        assertEquals(t1, t2);

        Token t3 = Token.of(Token.Type.ID, "notequal");
        assertNotEquals(t1, t3);
    }

}