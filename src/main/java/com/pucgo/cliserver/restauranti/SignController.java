package com.pucgo.cliserver.restauranti;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SignController {


    public String regexUsername = "^[a-zA-Z0-9]+$";
    public String regexPassword = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    public String regexName = "^[a-zA-ZÀ-ÿ ]+$";
    public String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public String message;
    public Alert alert;

    @FXML
    private CheckBox acceptCheckBox;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private PasswordField passPassField;

    @FXML
    private Button signinButton;

    @FXML
    private TextField userTextField;


    @FXML
    void signinOnClick(ActionEvent event) throws IOException {
        FXMLLoader newLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(newLoader.load());
        Stage stage = (Stage)signinButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public boolean validInput(){

        if(userTextField.getText().equals("")){

        } else if (userTextField.getText().matches(regexUsername)){

        } else if(nameTextField.getText().equals("")){

        } else if (nameTextField.getText().matches(regexName)){

        } else if(emailTextField.getText().equals("")){

        } else if (emailTextField.getText().matches(regexEmail)){

        } else if(passPassField.getText().equals("")){

        } else if (passPassField.getText().matches(regexPassword)){

        }



        return true;
    }

}
