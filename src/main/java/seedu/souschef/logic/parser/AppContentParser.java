package seedu.souschef.logic.parser;

import static seedu.souschef.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Pattern;

import seedu.souschef.logic.CommandHistory;
import seedu.souschef.logic.commands.Command;
import seedu.souschef.logic.parser.contextparser.FavouritesParser;
import seedu.souschef.logic.parser.contextparser.HealthPlanParser;
import seedu.souschef.logic.parser.contextparser.IngredientParser;
import seedu.souschef.logic.parser.contextparser.MealPlannerParser;
import seedu.souschef.logic.parser.contextparser.RecipeParser;
import seedu.souschef.logic.parser.contextparser.UniversalParser;
import seedu.souschef.logic.parser.exceptions.ParseException;
import seedu.souschef.model.ModelSet;
import seedu.souschef.storage.Storage;
import seedu.souschef.storage.StorageManager;
import seedu.souschef.ui.Ui;

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
    public Command parseCommand(ModelSet modelSet, String userInput, CommandHistory history,
                                Storage storage, Ui ui) throws ParseException {
        String context = history.getContext();

        if (storage == null) {
            storage = new StorageManager();
        }

        if (userInput.charAt(0) == '-') {
            return new UniversalParser().parseCommand(history, userInput, ui);
        } else if (context.equals("Meal Planner")) {
            if (storage.getListOfFeatureStorage().size() > 0) {
                storage.setMainFeatureStorage(storage.getListOfFeatureStorage().get(3));
            }
            return new MealPlannerParser()
                .parseCommand(modelSet.getMealPlannerModel(), modelSet.getRecipeModel(), userInput, ui);
        } else if (context.equals("Recipe")) {
            if (storage.getListOfFeatureStorage().size() > 0) {
                storage.setMainFeatureStorage(storage.getListOfFeatureStorage().get(0));
            }
            if (userInput.matches("favourite(\\s|\\S)*")) {
                return new RecipeParser().parseCommand(modelSet.getFavouriteModel(), userInput);
            }
            return new RecipeParser().parseCommand(modelSet.getRecipeModel(), userInput);
        } else if (context.equals("Ingredient")) {
            if (storage.getListOfFeatureStorage().size() > 0) {
                storage.setMainFeatureStorage(storage.getListOfFeatureStorage().get(1));
            }
            return new IngredientParser().parseCommand(modelSet.getIngredientModel(), userInput);
        } else if (context.equals("Health Plan")) {
            if (storage.getListOfFeatureStorage().size() > 0) {
                storage.setMainFeatureStorage(storage.getListOfFeatureStorage().get(2));
            }
            return new HealthPlanParser().parseCommand(modelSet.getHealthPlanModel(), userInput);
        } else if (context.equals("Favourites")) {
            if (storage.getListOfFeatureStorage().size() > 0) {
                storage.setMainFeatureStorage(storage.getListOfFeatureStorage().get(3));
            }
            return new FavouritesParser().parseCommand(modelSet.getFavouriteModel(), userInput);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
