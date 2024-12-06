package assignment3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField birthdayInput;

    @FXML
    private Button calculateButton;

    @FXML
    private Label resultLabel;

    private Model model;

    public Controller() {
        model = new Model();
    }

    @FXML
    public void calculateDays() {
        try {
            String birthday = birthdayInput.getText();
            String result = model.calculateDaysUntilBirthday(birthday);
            resultLabel.setText(result);
        } catch (Exception e) {
            resultLabel.setText("An unexpected error occurred.");
        }
    }
}
