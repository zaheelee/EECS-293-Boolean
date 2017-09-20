package jsa70.lexer;

import javax.swing.text.html.parser.Parser;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer
{
    //TODO figure out how to init
    private final Matcher MATCHER;
    private final int INPUT_LENGTH;

    //TODO figure out how to init
    private static Pattern tokenPatterns;

    private int currentLocation;


    static
    {
        tokenPatterns = Pattern.compile(Token.Type.getGroup());
    }


    public Lexer(String input)
    {
        MATCHER = tokenPatterns.matcher(input);
        INPUT_LENGTH = input.length();
        currentLocation = 0;

        //TODO I don't know where this will go, but eventually I will need to know that the way to get
        // the value of an enum index I need to use AND.ordinal() (or use a for each loop and call .ordinal()
        // on the value of the enum)
    }

    public boolean hasNext()
    {
        return currentLocation != INPUT_LENGTH;
    }

    public LocationalToken next()
            throws ParserException
    {
        if(!hasNext())
        {
            //TODO either throw some kind of index out of bounds exception, or figure out if you implemented hasNext wrong
        }

        MATCHER.find(currentLocation);
        LocationalToken locationalToken = null;
        // The 0th group refers to all groups
        for(int i = 1; i <= MATCHER.groupCount(); i++)
        {
            if(MATCHER.group(i) != null)
            {
                locationalToken = getLocationalTokenFromGroup(i);
                currentLocation = MATCHER.end(i);
                break;
            }
        }
        ParserException.verify(Optional.ofNullable(locationalToken));
        return locationalToken;
    }

    //TODO
    public Optional<LocationalToken> nextValid(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes)
            throws ParserException
    {
        return null;
    }

    private LocationalToken getLocationalTokenFromGroup(int group)
    {
        Token token = Token.of(Token.Type.values()[group - 1], MATCHER.group(group));
        return new LocationalToken(token, currentLocation);
    }
}
