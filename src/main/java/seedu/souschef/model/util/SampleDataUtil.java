package seedu.souschef.model.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.souschef.model.AppContent;
import seedu.souschef.model.ReadOnlyAppContent;

import seedu.souschef.model.healthplan.Age;
import seedu.souschef.model.healthplan.CurrentHeight;
import seedu.souschef.model.healthplan.CurrentWeight;
import seedu.souschef.model.healthplan.Duration;
import seedu.souschef.model.healthplan.HealthPlan;
import seedu.souschef.model.healthplan.HealthPlanName;
import seedu.souschef.model.healthplan.Scheme;
import seedu.souschef.model.healthplan.TargetWeight;

import seedu.souschef.model.ingredient.Ingredient;
import seedu.souschef.model.ingredient.IngredientAmount;
import seedu.souschef.model.ingredient.IngredientDate;
import seedu.souschef.model.ingredient.IngredientName;
import seedu.souschef.model.ingredient.IngredientServingUnit;

import seedu.souschef.model.planner.Day;
import seedu.souschef.model.planner.Meal;
import seedu.souschef.model.recipe.CookTime;
import seedu.souschef.model.recipe.Difficulty;
import seedu.souschef.model.recipe.Name;
import seedu.souschef.model.recipe.Recipe;
import seedu.souschef.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AppContent} with sample data.
 */
public class SampleDataUtil {
    public static Recipe[] getSampleRecipes() {
        return new Recipe[] {
            new Recipe(new Name("Alex Yeoh"), new Difficulty("1"), new CookTime("PT30M"),
                    getTagSet("friends")),
            new Recipe(new Name("Bernice Yu"), new Difficulty("1"), new CookTime("PT30M"),
                    getTagSet("colleagues", "friends")),
            new Recipe(new Name("Charlotte Oliveiro"), new Difficulty("1"), new CookTime("PT30M"),
                    getTagSet("neighbours")),
            new Recipe(new Name("David Li"), new Difficulty("1"), new CookTime("PT30M"),
                    getTagSet("family")),
            new Recipe(new Name("Irfan Ibrahim"), new Difficulty("1"), new CookTime("PT30M"),
                    getTagSet("classmates")),
            new Recipe(new Name("Roy Balakrishnan"), new Difficulty("1"), new CookTime("PT30M"),
                    getTagSet("colleagues"))
        };
    }

    public static Ingredient[] getSampleIngredient() {

        Ingredient [] ingredients = new Ingredient[]{};
        try {
            ingredients = new Ingredient[]{
                new Ingredient(new IngredientName("Carrot"), new IngredientAmount(300.0),
                            new IngredientServingUnit("gram"), new IngredientDate("12-25-2018")),
                new Ingredient(new IngredientName("Tomato"), new IngredientAmount(200.0),
                            new IngredientServingUnit("gram"), new IngredientDate("12-26-2018")),
                new Ingredient(new IngredientName("Potato"), new IngredientAmount(100.0),
                            new IngredientServingUnit("gram"), new IngredientDate("12-24-2018"))
            };
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ingredients;

    }

    public static HealthPlan[] getSampleHealthPlan() {
        return new HealthPlan[] {
            new HealthPlan(new HealthPlanName("Lose weight"), new TargetWeight("70"), new CurrentWeight("80"),
                  new CurrentHeight("160"), new Age("25"), new Duration("10"), Scheme.LOSS),
            new HealthPlan(new HealthPlanName("gain weight"), new TargetWeight("60"), new CurrentWeight("50"),
                  new CurrentHeight("160"), new Age("27"), new Duration("10"), Scheme.GAIN)
        };
    }

    public static Day[] getDay() {

        LocalDate modelDate = LocalDate.parse("2018-10-22");
        LocalDate modelDate2 = LocalDate.parse("2018-10-23");

        ArrayList<Meal> list = new ArrayList<>();
        ArrayList<Meal> list2 = new ArrayList<>();

        list.add(new Meal(Meal.Slot.BREAKFAST, getSampleRecipes()[0]));
        list.add(new Meal(Meal.Slot.LUNCH, getSampleRecipes()[1]));
        list.add(new Meal(Meal.Slot.DINNER, getSampleRecipes()[2]));

        list2.add(new Meal(Meal.Slot.BREAKFAST, getSampleRecipes()[2]));
        list2.add(new Meal(Meal.Slot.LUNCH, getSampleRecipes()[3]));
        list2.add(new Meal(Meal.Slot.DINNER, getSampleRecipes()[2]));

        return new Day[] {
            new Day (modelDate, list),
            new Day (modelDate2, list2)

        };
    }





    public static ReadOnlyAppContent getSampleAddressBook() {
        AppContent sampleAb = new AppContent();
        for (Recipe sampleRecipe : getSampleRecipes()) {
            sampleAb.getRecipes().add(sampleRecipe);
        }
        return sampleAb;
    }

    public static ReadOnlyAppContent getSampleIngredients() {
        AppContent sampleIngredients = new AppContent();
        for (Ingredient sampleIngredient : getSampleIngredient()) {
            sampleIngredients.getIngredients().add(sampleIngredient);
        }
        return sampleIngredients;
    }

    public static ReadOnlyAppContent getSampleHealthPlans() {
        AppContent sampleHealthPlans = new AppContent();
        for (HealthPlan sampleHealthPlan : getSampleHealthPlan()) {
            sampleHealthPlans.getHealthPlans().add(sampleHealthPlan);

        }
        return sampleHealthPlans;
    }

    public static ReadOnlyAppContent getSampleDays() {
        AppContent sampleDays = new AppContent();
        for (Day sampleDay : getDay()) {
            sampleDays.getMealPlanner().add(sampleDay);

        }
        return sampleDays;
    }



    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
