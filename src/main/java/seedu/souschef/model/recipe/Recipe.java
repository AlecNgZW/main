package seedu.souschef.model.recipe;

import static seedu.souschef.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.souschef.model.UniqueType;
import seedu.souschef.model.tag.Tag;

/**
 * Represents a Recipe in the application content.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Recipe extends UniqueType {

    // Identity fields
    private final Name name;
    private final CookTime cookTime;
    private final Difficulty difficulty;

    // Data fields
    private final ArrayList<Instruction> instructions = new ArrayList<>();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Recipe(Name name, Difficulty difficulty, CookTime cooktime, List<Instruction> instructions, Set<Tag> tags) {
        requireAllNonNull(name, difficulty, cooktime, tags);
        this.name = name;
        this.cookTime = cooktime;
        this.difficulty = difficulty;
        this.instructions.addAll(instructions);
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public CookTime getCookTime() {
        return cookTime;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public List<Instruction> getInstructions() {
        return Collections.unmodifiableList(instructions);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both recipes of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two recipes.
     */
    private boolean isSame(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
                && otherRecipe.getName().equals(getName())
                && otherRecipe.getCookTime().equals(getCookTime())
                && otherRecipe.getDifficulty().equals(getDifficulty());
    }

    /**
     * Returns true if other is a instance of Recipe and they are of the same name have at least one other identity
     * field that is the same.
     * This defines a weaker notion of equality between two recipes.
     */
    @Override
    public boolean isSame(UniqueType uniqueType) {
        if (uniqueType instanceof Recipe) {
            return isSame((Recipe) uniqueType);
        } else {
            return false;
        }
    }

    /**
     * Returns true if both recipes have the same identity and data fields.
     * This defines a stronger notion of equality between two recipes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Recipe)) {
            return false;
        }

        Recipe otherRecipe = (Recipe) other;
        return otherRecipe.getName().equals(getName())
                && otherRecipe.getCookTime().equals(getCookTime())
                && otherRecipe.getDifficulty().equals(getDifficulty())
                && otherRecipe.getTags().equals(getTags())
                && otherRecipe.getInstructions().equals(getInstructions());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, cookTime, difficulty, tags, instructions);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" CookTime: ")
                .append(getCookTime())
                .append(" Difficulty: ")
                .append(getDifficulty())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Instructions: ");
        getInstructions().forEach(builder::append);
        return builder.toString();
    }

}
