package frank6;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SolutionautobodyController {
    // formatters for currency and percentages
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();

    // Prices for each checkbox
    private static final BigDecimal AUTO_REPAIR_PRICE = new BigDecimal(1500);
    private static final BigDecimal COLLISION_REPAIR_PRICE = new BigDecimal(4000);
    private static final BigDecimal ENGINE_LIGHT_PRICE = new BigDecimal(100);
    private static final BigDecimal AUTO_GLASS_PRICE = new BigDecimal(500);
    private static final BigDecimal INSPECTION_PRICE = new BigDecimal(40);

    private BigDecimal discountPercentage = new BigDecimal(0.20); // 20% discount for age over 64

    // GUI controls defined in FXML and used by the controller's code
    @FXML
    private TextField amountTextField;

    @FXML
    private TextField saveTextField;

    @FXML
    private TextField totalTextField;

    @FXML
    private CheckBox checkBoxAutoRepair;

    @FXML
    private CheckBox checkBoxCollissionRepairs;

    @FXML
    private CheckBox checkBoxEngineLight;

    @FXML
    private CheckBox checkBoxAutoGlass;

    @FXML
    private CheckBox checkBoxInspection;

    @FXML
    private TextField ageTextField;

    // calculates and displays the total price, discount, and total amount
    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try {
            BigDecimal totalPrice = BigDecimal.ZERO;

            if (checkBoxAutoRepair.isSelected()) {
                totalPrice = totalPrice.add(AUTO_REPAIR_PRICE);
            }

            if (checkBoxCollissionRepairs.isSelected()) {
                totalPrice = totalPrice.add(COLLISION_REPAIR_PRICE);
            }

            if (checkBoxEngineLight.isSelected()) {
                totalPrice = totalPrice.add(ENGINE_LIGHT_PRICE);
            }

            if (checkBoxAutoGlass.isSelected()) {
                totalPrice = totalPrice.add(AUTO_GLASS_PRICE);
            }

            if (checkBoxInspection.isSelected()) {
                totalPrice = totalPrice.add(INSPECTION_PRICE);
            }

            // Apply discount for age over 64
            BigDecimal discount = BigDecimal.ZERO;
            if (!ageTextField.getText().isEmpty()) {
                int age = Integer.parseInt(ageTextField.getText());
                if (age > 64) {
                    discount = totalPrice.multiply(discountPercentage);
                }
            }

            BigDecimal totalAmount = totalPrice.subtract(discount);

            amountTextField.setText(currency.format(totalPrice));
            saveTextField.setText(currency.format(discount));
            totalTextField.setText(currency.format(totalAmount));
        } catch (NumberFormatException ex) {
            amountTextField.setText("Enter amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }

    // called by FXMLLoader to initialize the controller
    public void initialize() {
        // 0-4 rounds down, 5-9 rounds up
        currency.setRoundingMode(RoundingMode.HALF_UP);
    }
}
