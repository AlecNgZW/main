package seedu.souschef.logic.parser.contextparser;

import static seedu.souschef.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.souschef.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.souschef.logic.commands.AddCommand;
import seedu.souschef.logic.commands.AddMealHealthPlanCommand;
import seedu.souschef.logic.commands.Command;
import seedu.souschef.logic.commands.DeleteCommand;
import seedu.souschef.logic.commands.DisplayMealPlanCommand;
import seedu.souschef.logic.commands.EditCommand;
import seedu.souschef.logic.commands.FindCommand;
import seedu.souschef.logic.commands.HelpCommand;
import seedu.souschef.logic.commands.ListCommand;
import seedu.souschef.logic.commands.ShowHealthPlanDetailsCommand;
import seedu.souschef.logic.parser.commandparser.AddCommandParser;
import seedu.souschef.logic.parser.commandparser.DeleteCommandParser;
import seedu.souschef.logic.parser.commandparser.EditCommandParser;
import seedu.souschef.logic.parser.commandparser.FindCommandParser;
import seedu.souschef.logic.parser.exceptions.ParseException;
import seedu.souschef.model.Model;
import seedu.souschef.model.healthplan.HealthPlan;
import seedu.souschef.ui.Ui;

/**
 * Class to parse healthplan logic
 */
public class HealthPlanParser {
    public static final String COMMAND_WORD = "healthplan";
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param healthPlanModel
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command<HealthPlan> parseCommand(Model healthPlanModel, Model mealPlanModel,
                                            String userInput, Ui ui) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case DisplayMealPlanCommand.COMMAND_WORD_SHOW:
            return new DisplayMealPlanCommand<HealthPlan>(ui, "show");
        case DisplayMealPlanCommand.COMMAND_WORD_HIDE:
            return new DisplayMealPlanCommand<HealthPlan>(ui, "hide");
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parseHealthPlan(healthPlanModel, arguments);
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parseHealthPlan(healthPlanModel, arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parseHealthPlan(healthPlanModel, arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parseHealthPlan(healthPlanModel, arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand<HealthPlan>(healthPlanModel);

        case AddMealHealthPlanCommand.COMMAND_WORD:
            return new AddMealHealthPlanCommand<>(healthPlanModel, mealPlanModel, arguments);
        case ShowHealthPlanDetailsCommand.COMMAND_WORD:
            return new ShowHealthPlanDetailsCommand<>(ui, arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }


}
