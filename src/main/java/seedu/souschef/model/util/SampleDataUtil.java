package seedu.souschef.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import seedu.souschef.model.ingredient.IngredientPortion;
import seedu.souschef.model.ingredient.IngredientServingUnit;

import seedu.souschef.model.planner.Breakfast;
import seedu.souschef.model.planner.Day;
import seedu.souschef.model.planner.Dinner;
import seedu.souschef.model.planner.Lunch;
import seedu.souschef.model.planner.Meal;
import seedu.souschef.model.recipe.CookTime;
import seedu.souschef.model.recipe.Difficulty;
import seedu.souschef.model.recipe.Instruction;
import seedu.souschef.model.recipe.Name;
import seedu.souschef.model.recipe.Recipe;
import seedu.souschef.model.recipe.Tag;

/**
 * Contains utility methods for populating {@code AppContent} with sample data.
 */
public class SampleDataUtil {
    public static Recipe[] getRecipes() {
        return new Recipe[] {
            new Recipe(new Name("Chicken Rice"), new Difficulty("3"), new CookTime("40M"),
                    getInstructionList(
                            new Instruction("Slice and clean up the inner of the chicken 1.2 kg.",
                                    getIngredientPortionSet(new IngredientPortion("Chicken", "kg", 1.2))),
                            new Instruction("Boil the chicken in water 700 ml.",
                                    getIngredientPortionSet(new IngredientPortion("water", "ml", 700.0))),
                            new Instruction("Remove 200 ml of water and put soy sauce 100 ml.",
                                    getIngredientPortionSet(new IngredientPortion("soy sauce", "ml", 100.0))),
                            new Instruction("Cook for 20 mins.", new CookTime("20M"), getIngredientPortionSet())
                    ), getTagSet("Asian", "Singapore", "Poultry")),
            new Recipe(new Name("Black pepper Chicken"), new Difficulty("2"), new CookTime("20M"),
                    getInstructionList(
                            new Instruction("Slice and clean up the inner of the chicken 1.2 kg.",
                                    getIngredientPortionSet(new IngredientPortion("Chicken", "kg", 1.2))),
                            new Instruction("Heat up the pan with old 2 tablespoon.",
                                    getIngredientPortionSet(new IngredientPortion("oil", "tablespoon", 2.0))),
                            new Instruction("Add oyster sauce 3 tablespoon, black pepper 20 g and water 50 ml.",
                                    getIngredientPortionSet(new IngredientPortion("oyster sauce", "tablespoon",
                                            3.0))),
                            new Instruction("Stir-fry for 10 mins.", new CookTime("10M"), getIngredientPortionSet())
                    ),
                    getTagSet("Asian", "Spicy", "Poultry")),
            new Recipe(new Name("Fried Chinese Noodles"), new Difficulty("1"), new CookTime("20M"),
                    getInstructionList(
                            new Instruction("Slice vegetables 70 gram of any kind.",
                                    getIngredientPortionSet(new IngredientPortion("vegetables", "gram", 70.0))),
                            new Instruction("Add water 200 ml and noodles 300 g into the pan.",
                                    getIngredientPortionSet(new IngredientPortion("water", "ml", 200.0),
                                            new IngredientPortion("noodles", "gram", 300.0))),
                            new Instruction("Remove the water and put soy sauce 50 ml.",
                                    getIngredientPortionSet(new IngredientPortion("soy sauce", "ml", 50.0))),
                            new Instruction("Fry for 7 mins.", new CookTime("7M"), getIngredientPortionSet())
                    ),
                    getTagSet("Asian", "Staple", "Vegetarian")),
            new Recipe(new Name("Claypot Rice"), new Difficulty("3"), new CookTime("50M"),
                    getInstructionList(
                            new Instruction("Slice and clean up the inner of the chicken 1.2 kg.",
                                    getIngredientPortionSet(new IngredientPortion("Chicken", "kg", 1.2))),
                            new Instruction("Boil the chicken in water 700 ml.",
                                    getIngredientPortionSet(new IngredientPortion("water", "ml", 700.0))),
                            new Instruction("Remove 200 ml of water and put soy sauce 100 ml.",
                                    getIngredientPortionSet(new IngredientPortion("soy sauce", "ml", 100.0))),
                            new Instruction("Cook for 20 mins.", new CookTime("20M"), getIngredientPortionSet())
                    ),
                    getTagSet("Staple", "Poultry")),
            new Recipe(new Name("Roti Prata"), new Difficulty("3"), new CookTime("40M"),
                    getInstructionList(
                            new Instruction("Sift flour, add salt and water to make dough.",
                                    getIngredientPortionSet(new IngredientPortion("flour", "g", 300.0),
                                            new IngredientPortion("ghee", "cup", 0.5),
                                            new IngredientPortion("water", "cup", 0.5))),
                            new Instruction("Knead thoroughly for 5 minutes.",
                                    new CookTime("5M"), getIngredientPortionSet()),
                            new Instruction("Heat one teaspoon of ghee or butter on a metal "
                                    + "griddle or heavy iron pan."),
                            new Instruction("Fry each prata dough until brown on both sides.")
                    ),
                    getTagSet("Staple", "Vegetarian", "Halal")),
            new Recipe(new Name("Kimchi Jun"), new Difficulty("2"), new CookTime("10M"),
                        getInstructionList(
                                new Instruction("Stir together the kimchi, kimchi juice,"
                                        + " flour, eggs, and green onion in a bowl.",
                                        getIngredientPortionSet(new IngredientPortion("kimchi", "g", 150.0),
                                                new IngredientPortion("flour", "g", 300.0),
                                                new IngredientPortion("egg", "g", 100.0),
                                                new IngredientPortion("green onion", "g", 100.0))),
                                new Instruction("Using about 1/4 cup of batter for each pancake,"
                                        + " pour into skillet, spreading"
                                        + " as thin as possible. Cook pancakes until set and lightly browned, turning"
                                        + " once, 3 to 5 minutes per side.",
                                        new CookTime("5M"),
                                        getIngredientPortionSet())),
                        getTagSet("Snack", "Korean")),
            new Recipe(new Name("Kimchi Soup"), new Difficulty("2"), new CookTime("30M"),
                        getInstructionList(
                                new Instruction("Stir water, kimchi, pork shoulder, hot pepper paste,"
                                        + " sugar, and salt together in a pot.",
                                        getIngredientPortionSet(new IngredientPortion("kimchi", "g", 50.0),
                                                new IngredientPortion("pork shoulder", "g", 50.0),
                                                new IngredientPortion("hot pepper paste", "tablespoon", 2.0))),
                                new Instruction("bring to a boil, reduce heat to medium,"
                                        + " and simmer until meat is tender, about 20 minutes.",
                                        new CookTime("20M"),
                                        getIngredientPortionSet()),
                                new Instruction("Remove from heat and stir in green onions.",
                                        getIngredientPortionSet(new IngredientPortion("green onion", "g", 20.0)))),
                        getTagSet("Staple", "Korean")),
            new Recipe(new Name("Vegan Korean Kimchi Fried Rice"), new Difficulty("3"), new CookTime("20M"),
                        getInstructionList(
                                new Instruction("Add red onion, garlic, and ginger. Cook, stirring occasionally,"
                                        + "until onion softens, about 3 minutes.",
                                        new CookTime("3M"),
                                        getIngredientPortionSet(new IngredientPortion("red onion", "g", 20.0),
                                                new IngredientPortion("garlic", "g", 10.0),
                                                new IngredientPortion("ginger", "g", 10.0))),
                                new Instruction("Stir in cooked rice, soy sauce, sugar, kimchi,"
                                        + " Cook and stir until heated through, about 5 minutes",
                                        new CookTime("5M"),
                                        getIngredientPortionSet(new IngredientPortion("soy sauce", "tablespoon", 1.0),
                                                new IngredientPortion("sugar", "tablespoon", 1.0),
                                                new IngredientPortion("kimchi", "g", 20.0))),
                                new Instruction("scrape the bottom of the skillet to prevent sticking.",
                                        getIngredientPortionSet())),
                        getTagSet("Staple", "Vegan", "Korean"))
        };
    }

    public static Ingredient[] getSampleIngredient() {

        Ingredient [] ingredients = new Ingredient[]{};
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("MM-dd-yyyy");
        sdf.setLenient(false);
        try {
            ingredients = new Ingredient[]{
                new Ingredient(new IngredientName("carrot"), new IngredientAmount(300.0),
                            new IngredientServingUnit("gram"),
                        new IngredientDate(sdf.parse("10-10-2018"))),
                new Ingredient(new IngredientName("tomato"), new IngredientAmount(200.0),
                            new IngredientServingUnit("gram"),
                        new IngredientDate(sdf.parse("10-11-2018"))),
                new Ingredient(new IngredientName("potato"), new IngredientAmount(100.0),
                            new IngredientServingUnit("gram"),
                        new IngredientDate(sdf.parse("10-12-2018"))),
                new Ingredient(new IngredientName("flour"), new IngredientAmount(300.0),
                        new IngredientServingUnit("gram"),
                        new IngredientDate(sdf.parse("10-16-2018"))),
                new Ingredient(new IngredientName("chicken"), new IngredientAmount(540.0),
                        new IngredientServingUnit("gram"),
                        new IngredientDate(sdf.parse("10-21-2018"))),
                new Ingredient(new IngredientName("beef"), new IngredientAmount(300.0),
                        new IngredientServingUnit("gram"),
                        new IngredientDate(sdf.parse("10-25-2018"))),
                new Ingredient(new IngredientName("potato"), new IngredientAmount(100.0),
                        new IngredientServingUnit("gram"),
                        new IngredientDate(sdf.parse("10-30-2018"))),
                new Ingredient(new IngredientName("apple"), new IngredientAmount(120.0),
                        new IngredientServingUnit("gram"),
                        new IngredientDate(sdf.parse("10-30-2018"))),
                new Ingredient(new IngredientName("kimchi"), new IngredientAmount(200.0),
                        new IngredientServingUnit("gram"),
                        new IngredientDate(sdf.parse("11-17-2018")))
            };
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ingredients;

    }

    public static HealthPlan[] getSampleHealthPlan() {
        return new HealthPlan[] {
            new HealthPlan(new HealthPlanName("Lose weight"), new TargetWeight("70"), new CurrentWeight("80"),
                  new CurrentHeight("160"), new Age("25"), new Duration("10"), Scheme.LOSS, new ArrayList<Day>()),
            new HealthPlan(new HealthPlanName("gain weight"), new TargetWeight("60"), new CurrentWeight("50"),
                  new CurrentHeight("160"), new Age("27"), new Duration("10"), Scheme.GAIN, new ArrayList<Day>())
        };
    }

    public static Day[] getDay() {

        LocalDate modelDate = LocalDate.parse("2018-11-28");
        LocalDate modelDate2 = LocalDate.parse("2018-11-29");

        ArrayList<Meal> list = new ArrayList<>();
        ArrayList<Meal> list2 = new ArrayList<>();

        list.add(new Breakfast(getRecipes()[0]));
        list.add(new Lunch(getRecipes()[1]));
        list.add(new Dinner(getRecipes()[2]));

        list2.add(new Breakfast(getRecipes()[2]));
        list2.add(new Lunch(getRecipes()[3]));
        list2.add(new Dinner(getRecipes()[2]));

        return new Day[] {
            new Day (modelDate, list),
            new Day (modelDate2, list2)

        };
    }

    public static ReadOnlyAppContent getSampleRecipes() {
        AppContent sampleAb = new AppContent();
        for (Recipe sampleRecipe : getRecipes()) {
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

    public static List<Instruction> getInstructionList(String... strings) {
        return Arrays.stream(strings)
                .map(Instruction::new)
                .collect(Collectors.toList());
    }

    public static List<Instruction> getInstructionList(Instruction... instructions) {
        return Arrays.stream(instructions)
                .collect(Collectors.toList());
    }

    public static Set<IngredientPortion> getIngredientPortionSet(IngredientPortion... ingredientPortions) {
        return Arrays.stream(ingredientPortions)
                .collect(Collectors.toSet());
    }

}
