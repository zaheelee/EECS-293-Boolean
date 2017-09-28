package jsa70.lexer.src;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer
{
    private final Matcher MATCHER;
    private final int INPUT_LENGTH;

    private static Pattern tokenPatterns;



    static
    {
        tokenPatterns = Pattern.compile(Token.Type.getGroup());
    }


    public Lexer(String input)
    {
        MATCHER = tokenPatterns.matcher(input);
        INPUT_LENGTH = input.length();
    }

    public boolean hasNext()
    {
        return MATCHER.find();
    }

    // Apparently this is not supposed to increment, which is listed nowhere in the assignment,
    // but that is what a TA said so I guess I'll do it.
    public LocationalToken next()
            throws ParserException
    {
        LocationalToken locationalToken = null;

        // The 0th group refers to all groups
        for(int i = 1; i <= MATCHER.groupCount(); i++)
        {
            if(MATCHER.group(i) != null)
            {
                locationalToken = getLocationalTokenFromGroup(i);
                break;
            }
        }
        ParserException.verify(Optional.ofNullable(locationalToken));
        return locationalToken;
    }

    public Optional<LocationalToken> nextValid(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes)
            throws ParserException
    {
        LocationalToken locationalToken;

        while(hasNext())
        {
            locationalToken = next();
            if(validTypes.contains(locationalToken.getType()))
            {
                return Optional.of(locationalToken);
            }
            else if(invalidTypes.contains(locationalToken.getType()))
            {
                throw new ParserException(locationalToken, ParserException.ErrorCode.INVALID_TOKEN);
            }
        }
        return Optional.empty();
    }

    private LocationalToken getLocationalTokenFromGroup(int group)
    {
        Token token = Token.of(getTypeFromGroupRegex(group), MATCHER.group(group));
        return new LocationalToken(token, MATCHER.start(group));
    }

    private Token.Type getTypeFromGroupRegex(int group)
    {
        return Token.Type.values()[group - 1];
    }
}
