package com.pucgo.cliserver.tocofome;

import com.pucgo.cliserver.tocofome.dao.UserDAO;
import com.pucgo.cliserver.tocofome.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.Optional;

public class LoginController {


    Alert alert;
    static UserDAO userDAO = new UserDAO();

    private String message;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passTextField;

    @FXML
    private Button signinButton;

    @FXML
    private CheckBox robotCheckBox;

    @FXML
    private TextField userTextField;

    @FXML
    void loginButtonOnClick(ActionEvent event) throws IOException {


        if (validInput()) {
            Optional<User> searchedUser = userDAO.findByUsername(userTextField.getText());
            if (searchedUser.get().getUsername() != null) {
                User user = searchedUser.get();
                String senhaInputada = DigestUtils.sha1Hex(passTextField.getText());
                if (checkPassword(senhaInputada)) {
                    FXMLLoader newLoader = new FXMLLoader(MainViewController.class.getResource("main-view.fxml"));
                    Parent root = newLoader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setMaximized(false);
                    stage.centerOnScreen();
                    stage.show();
                } else {
                    alert.setHeaderText("Informação inválida");
                    alert.setContentText("A senha informada está incorreta.");
                    alert.show();

                }
            } else {
                alert.setHeaderText("Dados inválidos");
                alert.setContentText("Este usuário não existe cadastrado no sistema.");
                alert.show();
            }
        } else {
            alert.setContentText(message);
            alert.show();
        }


    }

    @FXML
    void robotOnClick(ActionEvent event) {
    }

    @FXML
    void signinButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader newLoader = new FXMLLoader(getClass().getResource("signin-view.fxml"));
        Parent root = newLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) signinButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public boolean validInput() {
        if (userTextField.getText().equals("")) {
            //Tratativas para input vazio
            message = "Por favor, digite um usuário.";
            userTextField.requestFocus();
        } else if (passTextField.getText().equals("")) {
            //Tratativas para input vazio
            message = "Por favor, digite uma senha.";
            passTextField.requestFocus();
        } else if (passTextField.getText().length() < 8) {
            //Tratativas para input menor do que o esperado
            message = "A senha contém no mínimo 8 caracteres.";
            passTextField.requestFocus();
        } else if (!robotCheckBox.isSelected()) {
            //Tratativas para caso seja um robô
            message = "Infelizmente você não pode prosseguir. Robôs aqui não entram.";
            robotCheckBox.requestFocus();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            return true;
        }
        alert = new Alert(Alert.AlertType.ERROR);
        return false;
    }


    public boolean checkPassword(String pass) {
        return pass.equals(DigestUtils.sha1Hex(passTextField.getText()));
    }
}