package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    public TextField emailField;
    @FXML
    public TextField phoneNumber;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField fullName;
    @FXML
    private TextField nameField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button enterBtn;

    private DateTimeFormatter dateTimeFormatter;

    @FXML
    public void initialize() {
        System.out.println("App is running...");

        final String datePattern = "M/dd/yyyy";
        dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
        datePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                String finalDate = null;
                if (date != null)
                    finalDate = dateTimeFormatter.format(date);
                return finalDate;
            }

            @Override
            public LocalDate fromString(String string) {
                LocalDate date = null;
                if (string != null)
                    date = LocalDate.parse(string, dateTimeFormatter);
                return date;
            }
        });
    }

    @FXML
    public void buttonPress(ActionEvent event){
        if (!nameField.getText().isEmpty() ||  !passwordField.getText().isEmpty() && isNumber()){
            System.out.println(nameField.getText());
            System.out.println(passwordField.getText());
            System.out.println(fullName.getText());
            System.out.println(emailField.getText());
        }
    }

    @FXML
    public boolean isNumber(){
        Pattern p = Pattern.compile("(\\d{3})(\\d{3})(\\d{4})");
        Matcher m = p.matcher(phoneNumber.getText());
        if (m.find() && m.group().equals(phoneNumber.getText())){
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validate phone number");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid phone number");
            alert.showAndWait();

            return false;
        }
    }
}
