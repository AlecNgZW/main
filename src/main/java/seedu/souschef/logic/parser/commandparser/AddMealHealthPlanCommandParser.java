package seedu.souschef.logic.parser.commandparser;

import static seedu.souschef.commons.core.Messages.MESSAGE_DUPLICATE;
import static seedu.souschef.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.souschef.commons.core.Messages.MESSAGE_INVALID_DAY_INDEX;
import static seedu.souschef.commons.core.Messages.MESSAGE_INVALID_PLAN_INDEX;
import static seedu.souschef.commons.core.Messages.MESSAGE_PREFIX_WITHOUT_VALUE;

import static seedu.souschef.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.souschef.logic.parser.CliSyntax.PREFIX_PLAN;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.souschef.commons.core.LogsCenter;
import seedu.souschef.logic.commands.AddMealHealthPlanCommand;
import seedu.souschef.logic.parser.ArgumentMultimap;
import seedu.souschef.logic.parser.ArgumentTokenizer;
import seedu.souschef.logic.parser.Prefix;
import seedu.souschef.logic.parser.exceptions.ParseException;
import seedu.souschef.model.Model;
import seedu.souschef.model.healthplan.HealthPlan;
import seedu.souschef.model.planner.Day;

/**
 *  parser class for adding days into health plan
 */
public class AddMealHealthPlanCommandParser {

    private static final Logger logger = LogsCenter.getLogger(AddMealHealthPlanCommandParser.class);

    private ArgumentMultimap argMultimap;
    private HealthPlan planToAddTo;
    private Day dayToAdd;
    private Model<HealthPlan> model;
    private int dayIndex;
    private int planIndex;

    /**
     * parser call for healthplans in healthPlan Parser
     */
    public AddMealHealthPlanCommand parseHealthPlan(Model healthPlanModel, Model mealPlanModel, String args)
            throws ParseException {

        this.model = healthPlanModel;

        argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PLAN, PREFIX_DURATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_PLAN, PREFIX_DURATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMealHealthPlanCommand.MESSAGE_USAGE));
        }

        //capture cases when prefix is not followed by a value
        if (argMultimap.getValue(PREFIX_PLAN).get().trim().length() == 0
                || argMultimap.getValue(PREFIX_DURATION).get().trim().length() == 0) {

            throw new ParseException(MESSAGE_PREFIX_WITHOUT_VALUE);
        }


        //check if numeric otherwise throw error
        if (!argMultimap.getValue(PREFIX_PLAN).get().trim().matches("^[0-9]+$")) {
            throw new ParseException(MESSAGE_INVALID_PLAN_INDEX);
        }

        if (!argMultimap.getValue(PREFIX_DURATION).get().trim().matches("^[0-9]+$")) {
            throw new ParseException(MESSAGE_INVALID_DAY_INDEX);
        }


        this.planIndex = Integer.parseInt(argMultimap.getValue(PREFIX_PLAN).get().trim());

        this.dayIndex = Integer.parseInt(argMultimap.getValue(PREFIX_DURATION).get().trim());


        //capture 0 or negative cases and also out of allowed bounds cases (plan)
        int zeroBasedPlanIndex = this.planIndex - 1;
        int zeroBasedDayIndex = this.dayIndex - 1;


        if (zeroBasedPlanIndex < 0
                || zeroBasedPlanIndex >= healthPlanModel.getAppContent().getObservableHealthPlanList().size()) {

            throw new ParseException(MESSAGE_INVALID_PLAN_INDEX);
        }

        if (zeroBasedDayIndex < 0
                || zeroBasedDayIndex >= mealPlanModel.getAppContent().getObservableMealPlanner().size()) {

            throw new ParseException(MESSAGE_INVALID_DAY_INDEX);
        }




        planToAddTo = healthPlanModel.getAppContent().getObservableHealthPlanList()
                .get(Integer.parseInt(argMultimap.getValue(PREFIX_PLAN).get().trim()) - 1);

        dayToAdd = mealPlanModel.getAppContent().getObservableMealPlanner()
                .get(Integer.parseInt(argMultimap.getValue(PREFIX_DURATION).get().trim()) - 1);

        //run a check to see if a duplicate plan already exists in the list.

        if (planToAddTo.getMealPlans().contains(dayToAdd)) {
            throw new ParseException(String.format(MESSAGE_DUPLICATE, "day"));
        }


        return new AddMealHealthPlanCommand(healthPlanModel, planToAddTo, dayToAdd, dayIndex, planIndex);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
