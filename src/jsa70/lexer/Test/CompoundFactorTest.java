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
    String string4;
    String string5;
    DisjunctiveLexer lex1;
    DisjunctiveLexer lex2;
    DisjunctiveLexer lex3;
    DisjunctiveLexer lex4;
    DisjunctiveLexer lex5;

    @BeforeEach
    void setUp()
    {
        string1 = "(one and (not two and three))";
        string2 = "(one and )";
        string3 = "five";
        string4 = "(one and two)";
        string5 = "(one and (two and three))";

        lex1 = new DisjunctiveLexer(string1);
        lex2 = new DisjunctiveLexer(string2);
        lex3 = new DisjunctiveLexer(string3);
        lex4 = new DisjunctiveLexer(string4);
        lex5 = new DisjunctiveLexer(string5);
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

    @Test
    void conjunctiveRepresentation_validInputs_singleLayer()
    {
        try
        {
            CompoundFactor compFactor = CompoundFactor.build(lex4.nextValid().get(), lex4);
            assertEquals(compFactor.conjunctiveRepresentation().getRepresentation(), "(not one or not two)");
        }
        catch (ParserException e)
        {
            assertTrue(false);
        }
    }

    @Test
    void conjunctiveRepresentation_validInputs_nested()
    {
        try
        {
            CompoundFactor compFactor = CompoundFactor.build(lex5.nextValid().get(), lex5);
            assertEquals(compFactor.conjunctiveRepresentation().getRepresentation(), "(not one or (not two or not three))");
        }
        catch (ParserException e)
        {
            assertTrue(false);
        }
    }
}