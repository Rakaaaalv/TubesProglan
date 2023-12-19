package com.example.tubesproglan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.application.Platform;

/**
 * Kelas Controller untuk aplikasi kalkulator.
 */
public class HelloController implements Initializable {

    @FXML
    private TextField txt_result;

    String op ="";
    double number1;
    double number2;

    /**
     * Menangani klik tombol angka dan menambahkan digit yang sesuai ke dalam TextField.
     * @param ae ActionEvent yang dipicu oleh klik tombol.
     */
    @FXML
    public void Number(ActionEvent ae) {
        String no = ((Button)ae.getSource()).getText();
        txt_result.setText(txt_result.getText() + no);
    }

    /**
     * Menangani klik tombol titik desimal dan menambahkan titik desimal ke dalam TextField jika belum ada.
     * @param ae ActionEvent yang dipicu oleh klik tombol.
     */
    @FXML
    public void Koma(ActionEvent ae) {
        String currentText = txt_result.getText();
        if (!currentText.contains(".")) {
            txt_result.setText(currentText + ".");
        }
    }

    /**
     * Menangani klik tombol operator dan melakukan operasi aritmatika yang sesuai.
     * @param ae ActionEvent yang dipicu oleh klik tombol.
     */
    @FXML
    public void Operation(ActionEvent ae) {
        String operation = ((Button)ae.getSource()).getText();
        if (!operation.equals("=")){

            if(!op.equals("")){
                return;
            }
            op = operation;
            number1 = Double.parseDouble(txt_result.getText());
            txt_result.setText("");
        } else {

            if(op.equals("")){
                return;
            }
            number2 = Double.parseDouble(txt_result.getText());
            calculate(number1, number2, op);
            op="";
        }
    }

    /**
     * Menangani klik tombol backspace dan menghapus karakter terakhir dari TextField.
     * @param ae ActionEvent yang dipicu oleh klik tombol.
     */
    @FXML
    public void Backspace(ActionEvent ae) {
        String currentText = txt_result.getText();
        if (!currentText.isEmpty()) {
            txt_result.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    /**
     * Menangani klik tombol clear dan menghapus seluruh isi dari TextField.
     * @param ae ActionEvent yang dipicu oleh klik tombol.
     */
    @FXML
    public void Clear(ActionEvent ae) {
        txt_result.clear();
    }

    /**
     * Melakukan perhitungan aritmatika berdasarkan operator yang diberikan.
     * Menampilkan alert kesalahan untuk pembagian oleh nol dan input yang tidak valid.
     * @param n1 Operand pertama.
     * @param n2 Operand kedua.
     * @param op Operator aritmatika.
     */
    @FXML
    public void calculate(double n1, double n2, String op) {
        switch (op) {
            case "+":
                txt_result.setText(String.valueOf(n1 + n2));
                break;
            case "-":
                txt_result.setText(String.valueOf(n1 - n2));
                break;
            case "*":
                txt_result.setText(String.valueOf(n1 * n2));
                break;
            case "/":
                if (n2 == 0) {
                    showAlert("Error", "Tidak dapat dibagi oleh nol.");
                    txt_result.clear();
                } else {
                    txt_result.setText(String.valueOf(n1 / n2));
                }
                break;
            default:
                if (n2 == 0) {
                    showAlert("Error", "Harap masukkan angka kedua yang valid.");
                } else {
                    showAlert("Error", "Operator tidak valid.");
                }
                break;
        }
    }

    /**
     * Menampilkan alert dengan judul dan konten yang ditentukan.
     * @param title Judul dari alert.
     * @param content Konten atau pesan dari alert.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> txt_result.requestFocus());
    }
}
