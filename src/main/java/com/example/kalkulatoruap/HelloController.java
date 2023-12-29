package com.example.kalkulatoruap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Kelas controller untuk aplikasi kalkulator sederhana menggunakan JavaFX.
 */
public class HelloController implements Initializable {

    @FXML
    private Label prevnum;

    @FXML
    private Label result;

    @FXML
    private Button clear;

    @FXML
    private Button delete;

    @FXML
    private Button showHistoryButton;


    private double num1 = 0;
    private double total = 0;
    private String operator = "";
    private boolean check = true;

    /**
     * Melakukan operasi aritmatika dasar berdasarkan operator yang diberikan.
     *
     * @param num1     Operan pertama.
     * @param num2     Operan kedua.
     * @param operator Operator aritmatika (+, -, *, /).
     * @return Hasil operasi aritmatika.
     */
    public Double create(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return (num1 + num2);
            case "-":
                return (num1 - num2);
            case "*":
                return (num1 * num2);
            case "/":
                if (num2 == 0)
                    return 0.0;
                return (num1 / num2);
            default:
                return 0.0;
        }
    }

    /**
     * Menambahkan angka ke label hasil.
     *
     * @param number Angka yang akan ditambahkan.
     */
    public void number(String number) {
        result.setText(result.getText() + number);
    }

    /**
     * Menambahkan angka ke label angka sebelumnya.
     *
     * @param number Angka yang akan ditambahkan.
     */
    public void prevNumber(String number) {
        prevnum.setText(prevnum.getText() + number);
    }

    /**
     * Menambahkan operator ke label angka sebelumnya.
     *
     * @param operator Operator yang akan ditambahkan.
     */
    public void prevOperator(String operator) {
        prevnum.setText(prevnum.getText() + " " + operator + " ");
    }

    /**
     * Menangani klik tombol angka dan memperbarui label hasil dan label angka sebelumnya.
     *
     * @param event ActionEvent yang dipicu oleh klik tombol.
     */
    public void process(ActionEvent event) {
        if (check) {
            result.setText("");
            prevnum.setText("");
            check = false;
        }
        Button button = (Button) event.getSource();
        String value = button.getText();

        number(value);
        prevNumber(value);
    }

    /**
     * Menangani klik tombol operator dan memperbarui operator, num1, dan label angka sebelumnya.
     *
     * @param event ActionEvent yang dipicu oleh klik tombol operator.
     */
    public void operatorProcess(ActionEvent event) {
        Button button = (Button) event.getSource();
        String value = button.getText();

        // Cek inputan kosong
        if (result.getText().isEmpty() || prevnum.getText().endsWith(" ")) {
            // Tampilkan Alert inputan kosong
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Input angka sebelum input operator.");
            alert.showAndWait();
            return;
        }

        if (!value.equals("Ans")) {
            if (!operator.isEmpty())
                return;

            operator = value;
            prevOperator(operator);
            num1 = Double.parseDouble(result.getText());
            result.setText("");
        } else {
            if (operator.isEmpty())
                return;

            double num2 = Double.parseDouble(result.getText());

            if (operator.equals("/") && num2 == 0) {
                // Peringatan pembagian oleh nol
                result.setText("Division by zero");
                return;
            }

            total = create(num1, num2, operator);

            result.setText(String.valueOf(total));

            // Tambahkan hasil ke dalam history
            String expression = prevnum.getText() + " " + operator + " " + num2 + " = " + total;
            history.add(expression);

            operator = "";
            check = true;
        }
    }

    /**
     * Menghapus label hasil dan label angka sebelumnya.
     *
     * @param event ActionEvent yang dipicu oleh tombol hapus.
     */
    public void Clear(ActionEvent event) {
        if (event.getSource() == clear) {
            result.setText("");
            prevnum.setText("");
        }
    }

    /**
     * Menghapus karakter terakhir dari label hasil dan label angka sebelumnya.
     *
     * @param event ActionEvent yang dipicu oleh tombol backspace.
     */
    public void Backspace(ActionEvent event) {
        String currentTextResult = result.getText();
        String currentTextPrevnum = prevnum.getText();

        if (!currentTextResult.isEmpty()) {
            result.setText(currentTextResult.substring(0, currentTextResult.length() - 1));
        }

        if (!currentTextPrevnum.isEmpty()) {
            prevnum.setText(currentTextPrevnum.substring(0, currentTextPrevnum.length() - 1));
        }
    }

    private List<String> history = new ArrayList<>();

    public void showHistory() {
        if (history.isEmpty()) {
            // Tampilkan pesan jika history belum ada
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("History Kalkulator");
            alert.setHeaderText(null);
            alert.setContentText("History belum ada.");
            alert.showAndWait();
        } else {
            // Tampilkan history jika sudah ada
            StringBuilder historyText = new StringBuilder("History:\n");
            for (String expression : history) {
                historyText.append(expression).append("\n");
            }

            // Tampilkan history dengan menggunakan Alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("History Kalkulator");
            alert.setHeaderText(null);
            alert.setContentText(historyText.toString());
            alert.showAndWait();
        }
    }



    public void showHistory(ActionEvent event) {
        showHistory();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
