package seedu.souschef.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.souschef.model.healthplan.HealthPlan;

/**
 *
 * Class to handle the exterior of the Healthplan Panel
 */
public class HealthPlanCard extends UiPart<Region> {

    private static final String FXML = "HealthPlanListCard.fxml";

    public final HealthPlan plan;

    @FXML
    private VBox frame;
    @FXML
    private Label name;
    @FXML
    private Label tweight;
    @FXML
    private Label cweight;
    @FXML
    private Label cheight;
    @FXML
    private Label age;
    @FXML
    private Label duration;

    @FXML
    private Label id;

    @FXML
    private Label scheme;


    /**
     *
     * Constructor for panel
     */
    public HealthPlanCard(HealthPlan plan, int displayedIndex) {
        super(FXML);
        this.plan = plan;
        id.setText(displayedIndex + ". ");
        name.setText(plan.getHealthPlanName().planName);
        tweight.setText(plan.getTargetWeight().value);
        cweight.setText(plan.getCurrentWeight().value);
        cheight.setText(plan.getCurrentHeight().value);
        age.setText(plan.getAge().value);
        duration.setText(plan.getDuration().value);
        scheme.setText(plan.getScheme().toString());

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HealthPlanCard)) {
            return false;
        }

        // state check
        HealthPlanCard card = (HealthPlanCard) other;
        return id.getText().equals(card.id.getText())
                && plan.equals(card.plan);
    }




}
