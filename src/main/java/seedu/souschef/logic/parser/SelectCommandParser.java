package seedu.souschef.logic.parser;

import static seedu.souschef.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.souschef.commons.core.index.Index;
import seedu.souschef.logic.commands.SelectCommand;
import seedu.souschef.logic.parser.exceptions.ParseException;
import seedu.souschef.model.Model;

/**
 * Parses input arguments and creates a new SelectCommand object
 */
public class SelectCommandParser implements CommandParser<SelectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SelectCommand
     * and returns an SelectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SelectCommand parseRecipe(Model model, String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SelectCommand(model, index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE), pe);
        }
    }

    @Override
    public SelectCommand parseIngredient(Model model, String args) throws ParseException {
        return null;
    }
}
