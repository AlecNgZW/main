package seedu.souschef.model.recipe;

import static seedu.souschef.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.souschef.model.tag.Tag;

/**
 * A utility class to help with building Recipe objects.
 */
public class RecipeBuilder {

    private Name name;
    private Difficulty difficulty;
    private CookTime cookTime;
    private Set<Tag> tags;
    private List<Instruction> instructions;

    public RecipeBuilder(Name name, Difficulty difficulty, CookTime cooktime, Set<Tag> tags) {
        requireAllNonNull(name, difficulty, cooktime, tags);
        this.name = name;
        this.cookTime = cooktime;
        this.difficulty = difficulty;
        this.tags = new HashSet<>();
        this.tags.addAll(tags);
        instructions = new ArrayList<>();
    }

    /**
     * Initializes the RecipeBuilder with the data of {@code recipeToCopy}.
     */
    public RecipeBuilder(Recipe recipeToCopy) {
        name = recipeToCopy.getName();
        difficulty = recipeToCopy.getDifficulty();
        cookTime = recipeToCopy.getCookTime();
        tags = new HashSet<>(recipeToCopy.getTags());
        instructions = new ArrayList<>(recipeToCopy.getInstructions());
    }

    /**
     * Parses the {@code instructions} into a {@code List<Instruction>} and set it to the {@code Recipe}
     * that we are building.
     */
    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }

    public Recipe build() {
        // Clear after build
        return new Recipe(name, difficulty, cookTime, instructions, tags);
    }
}

