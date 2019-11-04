package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Label resultText;
    public Label preview;
    public boolean isSecond;
    boolean isMore = false;
    private double right;
    private double left;
    private String selectedOperator;
    private boolean numberInput;
    private boolean isNominate = false;
    @FXML
    private Button number9;
    @FXML
    private Button number8;
    @FXML
    private Button number7;
    @FXML
    private Button number6;
    @FXML
    private Button number5;
    @FXML
    private Button number4;
    @FXML
    private Button number3;
    @FXML
    private Button number2;
    @FXML
    private Button number1;
    @FXML
    private Button number0;
    @FXML
    private Button additionBtn;
    @FXML
    private Button subsBtn;
    @FXML
    private Button multBtn;
    @FXML
    private Button divBtn;
    @FXML
    private Button percentBtn;
    @FXML
    private Button eraseBtn;
    @FXML
    private Button dotBtn;
    @FXML
    private Button squareBtn;
    @FXML
    private Button cleanBtn;
    @FXML
    private Button equals;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resultText.setText("0");
        preview.setText("= 0");
        style();


    }

    public void style() {

        additionBtn.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        subsBtn.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        divBtn.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        multBtn.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        percentBtn.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        squareBtn.setStyle("-fx-background-color: #a7a7a7; -fx-text-fill: #090909;");
        cleanBtn.setStyle("-fx-background-color: #a7a7a7; -fx-text-fill: #090909;");
        eraseBtn.setStyle("-fx-background-color: #a7a7a7; -fx-text-fill: #090909;");
        equals.setStyle("-fx-background-color:  #F9C22C; -fx-text-fill: black;");
        number0.setStyle("-fx-background-color:  #333333; -fx-text-fill: white;");
        number1.setStyle("-fx-background-color:  #333333; -fx-text-fill: white;");
        number2.setStyle("-fx-background-color:  #333333; -fx-text-fill: white;");
        number3.setStyle("-fx-background-color:  #333333; -fx-text-fill: white;");
        number4.setStyle("-fx-background-color:  #333333; -fx-text-fill: white;");
        number5.setStyle("-fx-background-color:  #333333; -fx-text-fill: white;");
        number6.setStyle("-fx-background-color:  #333333; -fx-text-fill: white;");
        number7.setStyle("-fx-background-color:  #333333; -fx-text-fill: white;");
        number8.setStyle("-fx-background-color:  #333333; -fx-text-fill: white;");
        number9.setStyle("-fx-background-color:  #333333; -fx-text-fill: white;");
        dotBtn.setStyle("-fx-background-color:  #333333; -fx-text-fill: white;");
        resultText.setStyle("-fx-text-fill: white;");
        preview.setStyle("-fx-text-fill: white;");
    }

    public void buttonClicked(ActionEvent actionEvent) {
        StringBuilder msg = new StringBuilder();
        StringBuilder prw = new StringBuilder();
        Button button = (Button) actionEvent.getSource();
        String buttonText = button.getText();
        if (buttonText.equals("C")) {
            left = 0;
            isMore = false;
            isNominate = false;
            isSecond = false;
            right = Double.MIN_VALUE;
            selectedOperator = "";

            numberInput = false;
            resultText.setText("0");
            preview.setText("= 0");
            return;
        }
        if (buttonText.matches("<-")) {

            String removeText;
            if (!resultText.getText().equals("0") || !resultText.getText().equals("0.0")) {
                if (Math.abs(Double.parseDouble(resultText.getText())) >= 10 || isNominate) {
                    removeText = resultText.getText();
                    removeText = removeText.substring(0, removeText.length() - 1);

                } else {
                    removeText = "0";
                }

                resultText.setText(removeText);
                if (isSecond) {
                    preview.setText("= " + left + selectedOperator + removeText);

                } else {
                    preview.setText("= " + removeText);


                }
            }

        }

        if (buttonText.matches("[0,1,2,3,4,5,6,7,8,9,.]")) {
            if (!numberInput) {
                numberInput = true;
                resultText.setText("0");
            }
            if (resultText.getText().equals("0.0") || resultText.getText().equals("0")) {
                if (buttonText.matches("[.,]")) {
                    resultText.setText("0" + buttonText);
                    isNominate = true;
                }


            }
            if (buttonText.matches("[.,]") && !isNominate) {
                msg.append(resultText.getText());
                msg.append(buttonText);
                resultText.setText(msg.toString());
                preview.setText("= " + msg.toString());
                isNominate = true;
            } else if (!buttonText.matches("[.,]")) {
                if (!resultText.getText().equals("0")) {
                    msg.append(resultText.getText());
                    msg.append(buttonText);
                    preview.setText("= " + msg.toString() + " ");
                } else {
                    preview.setText("= " + buttonText);
                    msg.append(buttonText);

                }
            }
            if (isSecond) {
                preview.setText("= " + left + selectedOperator + msg.toString());

            }
            if (buttonText.matches("[0,1,2,3,4,5,6,7,8,9]")) {

                resultText.setText(msg.toString());
                right = Double.parseDouble(msg.toString());

            }

            return;
        }

        prw.append("= ");
        if (buttonText.matches("[-,+,*,/,%]")) {

            if (isMore) {
                left = calculated(selectedOperator, left, right);
            } else {
                left = new Double(resultText.getText());
                isMore = true;
            }
            selectedOperator = buttonText;
            prw.append(left + "" + selectedOperator);
            preview.setText(prw.toString());
            if(buttonText.matches("[/,*]")){
                right=1;
            }else{
            right = 0;
            }
            isSecond = true;
            isNominate = false;
            numberInput = false;
            resultText.setText("0");
            return;
        }
        if (buttonText.matches("x²")) {
            selectedOperator = buttonText;
            left = new Double(resultText.getText());

            left = calculated(selectedOperator, left, right);
            resultText.setText(Double.toString(left));
            numberInput = false;
            preview.setText("= " + left + " " + selectedOperator);
            return;
        }

        if (buttonText.equals("=")) {
            isMore = false;
            isSecond = false;
            if (right == Double.MIN_VALUE) {
                if (!resultText.getText().equals("")) {
                    right = (numberInput && right == Double.MIN_VALUE) ? new Double(resultText.getText()) : left;
                    left = calculated(selectedOperator, left, right);
                }
            } else if (right != Double.MIN_VALUE) {
                left = calculated(selectedOperator, left, right);
            }
            resultText.setText(Double.toString(left));
            numberInput = false;
            preview.setText("= " + Double.toString(left));

            return;
        }


    }


    private double calculated(String operator, double left, double right) {
        switch (operator) {
            case "+":
                return round(left + right,4);
            case "-":
                return round(left - right,4);
            case "*":
                return round(left * right,4);
            case "/":
                return round(left / right,4);
            case "x²":
                return round(Math.pow(left, 2),4);
            case "%":
                return round((left * right) / 100,4);
        }
        return left;
    }
    static Double round(Double d, int precise) {
        BigDecimal bigDecimal = BigDecimal.valueOf(d);
        bigDecimal = bigDecimal.setScale(precise, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public void opacity(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.setOpacity(0.6);
    }

    public void opacityOut(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.setOpacity(1);
    }
}
