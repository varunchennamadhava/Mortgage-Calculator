package sample;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import static java.lang.Math.pow;

public class MortgageCalculatorController {


    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);
        yearSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {

                    years = BigDecimal.valueOf(newValue.intValue());
                    yearLabel.setText(yearcool.format(years));
                }
        );
    }


    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    //private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private static final NumberFormat yearcool = NumberFormat.getInstance();

    private BigDecimal years = new BigDecimal(10);
    private BigDecimal hundred = new BigDecimal(100);
    private BigDecimal twelve = new BigDecimal(12);
    private BigDecimal one = new BigDecimal(1);



//    //public TextField getLoanAmountTextField() {
//        return loanAmountTextField;
//    }

    @FXML
    private Label yearLabel;

    @FXML
    private TextField purchasePriceTextField;

    @FXML
    private TextField downPaymentTextField;

    @FXML
    private TextField interestRateTextField;

    @FXML
    private TextField loanAmountTextField;

    @FXML
    private TextField totalTextField;

    @FXML
    private Slider yearSlider;

    @FXML
    void calculateButtonPressed(ActionEvent event) {


    String purchase_price1 = purchasePriceTextField.getText();
    double purchase_price = Double.parseDouble(purchase_price1);

    String downpayment1 = downPaymentTextField.getText();
    double downpayment = Double.parseDouble(downpayment1);


    double loan_amount = purchase_price - downpayment;


//    String rate1 = interestRateTextField.getText();
//    double rate = Double.parseDouble(rate1);


    loanAmountTextField.setText(currency.format(loan_amount));
 //    loanAmountTextField.setText(yearLabel.getText());

        String hi = yearLabel.getText();
       double jk = Double.parseDouble(hi);
       double numofpayments = jk *12;
       String input_rate = interestRateTextField.getText();
       double Rate =  Double.parseDouble(input_rate);
       double rate_per_month = (Rate/100)/12;

       int num_of_payments_rounded= (int)Math.round(numofpayments);

       double term = 1 + rate_per_month;


       double monthly_payment = (rate_per_month*loan_amount*pow(term,num_of_payments_rounded))/(pow(term,num_of_payments_rounded)-1);
       totalTextField.setText(currency.format(monthly_payment));



    }

}
