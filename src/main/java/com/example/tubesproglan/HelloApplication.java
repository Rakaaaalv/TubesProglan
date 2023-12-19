package com.example.tubesproglan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Kelas utama untuk menjalankan aplikasi kalkulator.
 */
public class HelloApplication extends Application {

    /**
     * Metode utama untuk memulai aplikasi.
     * @param stage Objek Stage yang digunakan untuk menampilkan antarmuka pengguna.
     * @throws Exception Jika terjadi kesalahan selama proses pembuatan antarmuka pengguna.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Memuat tata letak (layout) dari file FXML
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        // Membuat objek Scene dengan menggunakan tata letak yang dimuat
        Scene scene = new Scene(root);

        // Mengatur Scene pada Stage dan menampilkan Stage
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metode utama untuk menjalankan aplikasi.
     * @param args Argumen baris perintah yang dapat digunakan saat menjalankan aplikasi.
     */
    public static void main(String[] args) {
        // Memulai aplikasi
        launch(args);
    }

}
