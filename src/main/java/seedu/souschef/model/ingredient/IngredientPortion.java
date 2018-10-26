package seedu.souschef.model.ingredient;

import seedu.souschef.model.UniqueType;

/**
 * ingredient class that stores ingredient name, amount, serving unit.
 */
public class IngredientPortion extends IngredientDefinition {
    private final IngredientAmount amount;
    private final IngredientServingUnit unit;

    public IngredientPortion(IngredientName name, IngredientAmount amount, IngredientServingUnit unit) {
        super(name);
        this.amount = amount;
        this.unit = unit;
    }

    public IngredientAmount getIngredientAmount() {
        return amount;
    }

    public IngredientServingUnit getUnit() {
        return unit;
    }

    /**
     * Returns true if both ingredients of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two recipes.
     */
    @Override
    public boolean isSame(UniqueType other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof IngredientPortion)) {
            return false;
        }

        IngredientPortion otherIngredient = (IngredientPortion) other;

        return otherIngredient != null
                && otherIngredient.getName().equals(getName());
    }

    /**
     * Basically same with isSame method above.
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof IngredientPortion)) {
            return false;
        }

        IngredientPortion otherIngredient = (IngredientPortion) other;

        return otherIngredient != null
                && otherIngredient.getName().equals(getName())
                && otherIngredient.getIngredientAmount().equals(getIngredientAmount())
                && otherIngredient.getUnit().equals(getUnit());
    }

    @Override
    public String toString() {
        return super.toString() + " Amount: " + getIngredientAmount().toString() + " " + getUnit();
    }
}
