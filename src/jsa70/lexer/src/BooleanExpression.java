package jsa70.lexer.src;

public class BooleanExpression
{
    public static void main(String [] args)
    {
        try
        {
            DisjunctiveLexer lex = new DisjunctiveLexer(args[0]);
            CompoundFactor compFactor = CompoundFactor.build(lex.nextValid().get(), lex);
            System.out.println(compFactor.conjunctiveRepresentation().getRepresentation());
        }
        catch (ParserException e)
        {
            System.out.println(e.getErrorCode());
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Please specify a boolean expression");
        }
    }
}
