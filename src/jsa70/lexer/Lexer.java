package jsa70.lexer;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer
{
    private final Matcher MATCHER;
    private final int INPUT_LENGTH;

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
    }

    public boolean hasNext()
    {
        return currentLocation != INPUT_LENGTH;
    }

    public LocationalToken next()
            throws ParserException
    {
        if(!MATCHER.find(currentLocation))
        {
            throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);
        }

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

    public Optional<LocationalToken> nextValid(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes)
            throws ParserException
    {
        if(!MATCHER.find(currentLocation))
        {
            throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);
        }

        LocationalToken locationalToken;
        Token.Type type;
        int possibleNewLocation = -1;

        // The 0th group refers to all groups
        for(int i = 1; i <= MATCHER.groupCount(); i++)
        {
            if(MATCHER.group(i) != null)
            {
                locationalToken = getLocationalTokenFromGroup(i);
                type = getTypeFromGroupRegex(i);

                if(validTypes.contains(type))
                {
                    currentLocation = MATCHER.end(i);
                    return Optional.of(locationalToken);
                }
                else if(invalidTypes.contains(type))
                {
                    throw new ParserException(locationalToken, ParserException.ErrorCode.INVALID_TOKEN);
                }
                else if(possibleNewLocation < 0)
                {
                    possibleNewLocation = MATCHER.end(i);
                }
            }
        }

        //if no valid tokens are found
        currentLocation = possibleNewLocation;
        return Optional.empty();
    }

    private LocationalToken getLocationalTokenFromGroup(int group)
    {
        Token token = Token.of(getTypeFromGroupRegex(group), MATCHER.group(group));
        return new LocationalToken(token, currentLocation);
    }

    private Token.Type getTypeFromGroupRegex(int group)
    {
        return Token.Type.values()[group - 1];
    }
}
