package seedu.souschef.storage;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;

import seedu.souschef.commons.exceptions.DataConversionException;
import seedu.souschef.commons.util.XmlUtil;
import seedu.souschef.storage.healthplan.XmlSerializableHealthPlan;
import seedu.souschef.storage.ingredient.XmlSerializableIngredient;
import seedu.souschef.storage.mealplanner.XmlSerializableMealPlan;
import seedu.souschef.storage.recipe.XmlSerializableRecipe;

/**
 * Stores addressbook data in an XML file
 */
public class XmlFileStorage {

    /**
     * Constants to access the specific portions of the xml files
     */
    public static final String TYPE_RECIPE = "recipe";
    public static final String TYPE_INGREDIENT = "ingredient";
    public static final String TYPE_HEALTHPLAN = "healthplan";
    public static final String TYPE_MEALPLAN = "mealplan";


    /**
     * Saves the given addressbook data to the specified file.
     */
    public static void saveDataToFile(Path file, XmlSerializableGeneric xmlContent)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, xmlContent);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }
    }

    /**
     * Returns address book in the file or an empty address book
     */
    public static XmlSerializableGeneric loadDataFromSaveFile(Path file) throws DataConversionException,
            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableGeneric.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }


    /**
     * Returns address book in the file or an empty address book
     */
    public static XmlSerializableGeneric loadDataFromSaveFile(Path file, String type) throws DataConversionException,
            FileNotFoundException {
        try {
            if (TYPE_RECIPE.equals(type)) {
                return XmlUtil.getDataFromFile(file, XmlSerializableRecipe.class);
            } else if (TYPE_INGREDIENT.equals(type)) {
                return XmlUtil.getDataFromFile(file, XmlSerializableIngredient.class);
            } else if (TYPE_HEALTHPLAN.equals(type)) {
                return XmlUtil.getDataFromFile(file, XmlSerializableHealthPlan.class);
            } else if (TYPE_MEALPLAN.equals(type)) {
                return XmlUtil.getDataFromFile(file, XmlSerializableMealPlan.class);
            }

        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
        return null;
    }
}
