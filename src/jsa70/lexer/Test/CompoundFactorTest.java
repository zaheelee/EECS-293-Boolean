package jsa70.lexer.Test;

import jsa70.lexer.src.CompoundFactor;
import jsa70.lexer.src.DisjunctiveLexer;
import jsa70.lexer.src.ParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompoundFactorTest
{
    DisjunctiveLexer dLex;


    @Test
    void build_validInputs()
    {
        dLex = new DisjunctiveLexer("(one and (not two and three))");

        try
        {
            CompoundFactor.build(dLex.nextValid().get(), dLex);
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
        dLex = new DisjunctiveLexer("(one and )");

        try
        {
            CompoundFactor.build(dLex.nextValid().get(), dLex);
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
        dLex = new DisjunctiveLexer("(one and two)");

        try
        {
            CompoundFactor compFactor = CompoundFactor.build(dLex.nextValid().get(), dLex);
            assertEquals(compFactor.conjunctiveRepresentation().getRepresentation(), "(not one or not two)");

            dLex = new DisjunctiveLexer("(one and not two)");
            compFactor = CompoundFactor.build(dLex.nextValid().get(), dLex);
            assertEquals(compFactor.conjunctiveRepresentation().getRepresentation(), "(not one or two)");
        }
        catch (ParserException e)
        {
            assertTrue(false);
        }
    }

    @Test
    void conjunctiveRepresentation_validInputs_nested()
    {
        dLex = new DisjunctiveLexer("(one and (two and three))");

        try
        {
            CompoundFactor compFactor = CompoundFactor.build(dLex.nextValid().get(), dLex);
            assertEquals(compFactor.conjunctiveRepresentation().getRepresentation(), "(not one or (not two or not three))");

            dLex = new DisjunctiveLexer("(one and not (two and three))");
            compFactor = CompoundFactor.build(dLex.nextValid().get(), dLex);
            assertEquals(compFactor.conjunctiveRepresentation().getRepresentation(), "(not one or (two and three))");
        }
        catch (ParserException e)
        {
            assertTrue(false);
        }
    }
}