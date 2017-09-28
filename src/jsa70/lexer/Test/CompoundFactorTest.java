package jsa70.lexer.Test;

import jsa70.lexer.src.CompoundFactor;
import jsa70.lexer.src.DisjunctiveLexer;
import jsa70.lexer.src.ParserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompoundFactorTest
{
    String string1;
    String string2;
    String string3;
    DisjunctiveLexer lex1;
    DisjunctiveLexer lex2;
    DisjunctiveLexer lex3;

    @BeforeEach
    void setUp()
    {
        string1 = "(one and (not two and three))";
        string2 = "(one and )";
        string3 = "five";

        lex1 = new DisjunctiveLexer(string1);
        lex2 = new DisjunctiveLexer(string2);
        lex3 = new DisjunctiveLexer(string3);
    }

    @Test
    void build_validInputs()
    {
        try
        {
            CompoundFactor compFactor = CompoundFactor.build(lex1.nextValid().get(), lex1);
            assertTrue(true);
        }
        catch(ParserException e)
        {
            assertTrue(false);
        }
    }

    @Test
    void build_invalidInputs()
    {
        try
        {
            CompoundFactor compFactor = CompoundFactor.build(lex2.nextValid().get(), lex2);
            assertTrue(false);
        }
        catch(ParserException e)
        {
            assertTrue(true);
        }
    }
}