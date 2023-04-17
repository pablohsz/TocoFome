package com.pucgo.cliserver.tocofome;

import com.pucgo.cliserver.tocofome.dao.UserDAO;
import com.pucgo.cliserver.tocofome.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

public class SignController {


    public String regexUsername = "^[a-zA-Z0-9]+$";
    public String regexPassword = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    public String regexName = "^[a-zA-ZÀ-ÿ ]+$";
    public String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static UserDAO userDAO = new UserDAO();

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

        boolean inputValid = validInput();
        if (inputValid) {
            User newUser = getUserInfo();
            if (userDAO.createUser(newUser)) {

                alert.setContentText(message);
                alert.showAndWait();
            } else {
                alert.setContentText("Usuário já cadastrado! Por favor, tente realizar o login novamente.");
                alert.showAndWait();
            }

            FXMLLoader newLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Scene scene = new Scene(newLoader.load());
            Stage stage = (Stage) signinButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            alert.setContentText(message);
            alert.show();
        }


    }

    public boolean validInput() {

        if (userTextField.getText().equals("")) {
            message = "O username não pode ficar em branco.";
            userTextField.requestFocus();
        } else if (!userTextField.getText().matches(regexUsername)) {
            message = "Username inválido. Por favor, utilize somente letras e números.";
            userTextField.requestFocus();
        } else if (nameTextField.getText().equals("")) {
            message = "O nome não pode ficar em branco.";
            nameTextField.requestFocus();
        } else if (!nameTextField.getText().matches(regexName)) {
            message = "Nome inválido. Por favor, utilize somente letras e espaços.";
            nameTextField.requestFocus();
        } else if (emailTextField.getText().equals("")) {
            message = "E-mail não pode ficar em branco.";
            emailTextField.requestFocus();
        } else if (!emailTextField.getText().matches(regexEmail)) {
            message = "E-mail inválido. Por favor digite um e-mail válido.";
            emailTextField.requestFocus();
        } else if (passPassField.getText().equals("")) {
            message = "Senha não pode ficar em branco.";
            passPassField.requestFocus();
        } else if (!passPassField.getText().matches(regexPassword)) {
            message = "Senha inválida. A senha deve conter no minímo 8 caracteres contendo letras e números.";
            passPassField.requestFocus();
        } else if (!acceptCheckBox.isSelected()) {
            message = "Você deve concordar com os termos propostos.";
            acceptCheckBox.requestFocus();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            message = "Cadastrado(a) com sucesso! Realize já o login.";
            return true;
        }

        alert = new Alert(Alert.AlertType.ERROR);
        return false;
    }


    public User getUserInfo() {
        User user = new User();
        user.setName(nameTextField.getText());
        user.setUsername(userTextField.getText());
        user.setEmail(emailTextField.getText());
        user.setPassword(DigestUtils.sha1Hex(passPassField.getText()));
        return user;
    }

}
