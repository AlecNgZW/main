package seedu.souschef.logic.parser.commandparser;

import static java.util.Objects.requireNonNull;
import static seedu.souschef.commons.core.Messages.MESSAGE_DELETE_HEALTHPLAN_USAGE;
import static seedu.souschef.commons.core.Messages.MESSAGE_DELETE_INGREDIENT_USAGE;
import static seedu.souschef.commons.core.Messages.MESSAGE_DELETE_RECIPE_USAGE;
import static seedu.souschef.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.souschef.commons.core.index.Index;
import seedu.souschef.logic.commands.DeleteCommand;
import seedu.souschef.logic.parser.ParserUtil;
import seedu.souschef.logic.parser.exceptions.ParseException;
import seedu.souschef.model.Model;
import seedu.souschef.model.healthplan.HealthPlan;
import seedu.souschef.model.recipe.Recipe;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements CommandParser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns an DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand<Recipe> parseRecipe(Model model, String args) throws ParseException {
        try {
            Index targetIndex = ParserUtil.parseIndex(args);
            requireNonNull(model);
            List<Recipe> lastShownList = model.getFilteredList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_DELETE_RECIPE_USAGE));
            }
            Recipe toDelete = lastShownList.get(targetIndex.getZeroBased());

            return new DeleteCommand<>(model, toDelete);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_DELETE_INGREDIENT_USAGE), pe);
        }
    }


    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns an DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand<HealthPlan> parseHealthPlan(Model model, String args) throws ParseException {
        try {
            Index targetIndex = ParserUtil.parseIndex(args);
            requireNonNull(model);
            List<HealthPlan> lastShownList = model.getFilteredList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        MESSAGE_DELETE_HEALTHPLAN_USAGE));
            }
            HealthPlan toDelete = lastShownList.get(targetIndex.getZeroBased());

            return new DeleteCommand<>(model, toDelete);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_DELETE_HEALTHPLAN_USAGE), pe);
        }
    }


}
