package seedu.souschef.logic.parser;

import static seedu.souschef.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Pattern;

import seedu.souschef.logic.CommandHistory;
import seedu.souschef.logic.commands.Command;
import seedu.souschef.logic.parser.exceptions.ParseException;
import seedu.souschef.model.ModelSet;

/**
 * Parses user input.
 */
public class AppContentParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param modelSet
     * @param userInput full user input string
     * @param history
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(ModelSet modelSet, String userInput, CommandHistory history) throws ParseException {
        String context = history.getContext();
        if (userInput.charAt(0) == '-') {
            return new UniversalParser().parseCommand(history, userInput);
        } else if (context == null || context.equals("Recipe")) {
            return new RecipeParser().parseCommand(modelSet.getRecipeModel(), userInput);
        } else if (context.equals("Ingredient")) {
            return new IngredientParser().parseCommand(modelSet.getIngredientModel(), userInput);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
