package seedu.souschef.storage.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.souschef.commons.exceptions.IllegalValueException;
import seedu.souschef.model.AppContent;
import seedu.souschef.model.ReadOnlyAppContent;
import seedu.souschef.model.recipe.Recipe;
import seedu.souschef.storage.XmlSerializableGeneric;

/**
 * An Immutable AppContent that is serializable to XML format
 */
@XmlRootElement(name = "addressbook")
public class XmlSerializableAddressBook implements XmlSerializableGeneric {

    public static final String MESSAGE_DUPLICATE_RECIPE = "Recipes list contains duplicate recipe(s).";

    @XmlElement
    private List<XmlAdaptedRecipe> recipes;

    private AppContent appContent;

    /**
     * Creates an empty XmlSerializableAddressBook.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableAddressBook() {
        recipes = new ArrayList<>();
        appContent = new AppContent();
    }

    public XmlSerializableAddressBook(XmlSerializableAddressBook ab) {
        recipes = ab.recipes;
        this.appContent = ab.appContent;
        recipes.addAll(ab.appContent.getObservableRecipeList().stream().map(XmlAdaptedRecipe::new)
                .collect(Collectors.toList()));
    }

    public XmlSerializableAddressBook(AppContent appContent) {
        this();
        if (appContent != null) {
            this.appContent = appContent;
        } else {
            appContent = new AppContent();

        }
        recipes.addAll(appContent.getObservableRecipeList().stream().map(XmlAdaptedRecipe::new)
                .collect(Collectors.toList()));
    }

    /**
     * Conversion
     */
    public XmlSerializableAddressBook(ReadOnlyAppContent src) {
        this();

        recipes.addAll(src.getObservableRecipeList().stream().map(XmlAdaptedRecipe::new).collect(Collectors.toList()));
    }

    public AppContent getAppContent() {
        return appContent;
    }

    /**
     * Converts this addressbook into the recipeModel's {@code AppContent} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedRecipe}.
     */
    @Override
    public AppContent toModelType() throws IllegalValueException {
        //problem area:
        //this.appContent is returning null at this point

        for (XmlAdaptedRecipe p : recipes) {
            Recipe recipe = p.toModelType();
            if (this.appContent.getRecipes().contains(recipe)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_RECIPE);
            }
            appContent.getRecipes().add(recipe);
        }
        return appContent;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializableAddressBook)) {
            return false;
        }
        return recipes.equals(((XmlSerializableAddressBook) other).recipes);
    }
}
