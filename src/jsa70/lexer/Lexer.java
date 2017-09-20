package jsa70.lexer;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer
{
    private final Matcher matcher;
    private final int INPUT_LENGTH;

    private static Pattern tokenPatterns;

    private int currentLocation;


    static
    {
        tokenPatterns = Pattern.compile(Token.Type.getGroup());
    }


    public Lexer(String input)
    {
        matcher = tokenPatterns.matcher(input);
        INPUT_LENGTH = input.length();
        currentLocation = 0;
    }

    public boolean hasNext()
    {
        return currentLocation != INPUT_LENGTH;
    }

    public LocationalToken next()
            throws ParserException
    {
        LocationalToken locationalToken = null;
        if(!matcher.find(currentLocation))
        {
            ParserException.verify(Optional.ofNullable(locationalToken));
        }
        // The 0th group refers to all groups
        for(int i = 1; i <= matcher.groupCount(); i++)
        {
            if(matcher.group(i) != null)
            {
                locationalToken = getLocationalTokenFromGroup(i);
                currentLocation = matcher.end(i);
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
        Token token = Token.of(Token.Type.values()[group - 1], matcher.group(group));
        return new LocationalToken(token, currentLocation);
    }
}
