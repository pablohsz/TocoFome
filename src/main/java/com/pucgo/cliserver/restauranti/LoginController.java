package com.pucgo.cliserver.restauranti;

import com.pucgo.cliserver.restauranti.dao.UserDAO;
import com.pucgo.cliserver.restauranti.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Optional;

public class LoginController {


    public static UserDAO userDAO = new UserDAO();

    public void setUserTextField(TextField userTextField) {
        this.userTextField = userTextField;
    }

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
    void loginButtonOnClick(ActionEvent event) {
        Optional<User> searchedUser = userDAO.findByUsername(userTextField.getText());
        if(searchedUser.get().getUsername() != null){
            User user = searchedUser.get();
            String senhaInputada = DigestUtils.sha1Hex(passTextField.getText());
            if(user.getPassword().equals(senhaInputada)){
                System.out.println("Sucesso");
            } else System.out.println("Não é a mesma senha");
        } else {
            System.out.println("Não existe");
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
        Stage stage = (Stage)signinButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public boolean validInput() {
        if (userTextField.getText().equals("")) {
            //Tratativas para input vazio
            message = "Por favor, digite um usuário.";
            userTextField.requestFocus();
            return false;
        } else if (passTextField.getText().equals("")) {
            //Tratativas para input vazio
            message = "Por favor, digite uma senha.";
            passTextField.requestFocus();
            return false;
        } else if (passTextField.getText().length() < 8) {
            //Tratativas para input menor do que o esperado
            message = "A senha contém no mínimo 8 caracteres.";
            passTextField.requestFocus();
            return false;
        } else if (!robotCheckBox.isSelected()) {
            //Tratativas para caso seja um robô
            message = "Infelizmente você não pode prosseguir. Robôs aqui não entram.";
            robotCheckBox.requestFocus();
            return false;
        }
        return true;
    }


    public boolean checkPassword(String pass){
        return pass.equals(DigestUtils.sha1Hex(passTextField.getText()));
    }
}