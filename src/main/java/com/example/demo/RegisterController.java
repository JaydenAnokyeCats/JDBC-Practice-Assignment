package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordFieldAppear;
    @FXML
    private Button submitButton;
    @FXML
    private Button revealButton;
    boolean visible = false;

    @FXML
    public void display(ActionEvent event) throws SQLException {
        try{
            JdbcDao jdbcDao = new JdbcDao();
            jdbcDao.display();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @FXML
    public void onClick(ActionEvent event){
        if (visible){
            visible = false;
            passwordFieldAppear.setText("");
            passwordFieldAppear.setVisible(false);
        }else{
            visible = true;
            String pass = passwordField.getText();
            passwordFieldAppear.setText(pass);
            passwordFieldAppear.setVisible(true);
        }

    }
    @FXML
    public void register(ActionEvent event) throws SQLException {
        Window owner = submitButton.getScene().getWindow();

        System.out.println(usernameField.getText());
        System.out.println(emailField.getText());
        System.out.println(passwordField.getText());
        if (usernameField.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Error! (Null Name)", "Please enter your name.");
            return;
        }
        if (emailField.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Error! (Null Email)", "Please enter your email address.");
            return;
        }
        if (passwordField.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Error (Null Password)!", "Please enter a password!");
            return;
        }

        String fullName = usernameField.getText();
        String emailId = emailField.getText();
        String password = passwordField.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertRecord(fullName, emailId, password);
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText("null");
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}