package com.pucgo.cliserver.restauranti;

import com.pucgo.cliserver.restauranti.dao.UserDAO;
import com.pucgo.cliserver.restauranti.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

public class LoginPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        //String str = DigestUtils.sha1Hex("Tá maluco hein");
      /*  User user = new User();
        user.setUsername("pablosouza");
        user.setPassword("Senha123");
        user.setEmail("pablosouza@pucgo.edu.br");
        user.setName("Pablo Souza");
        UserDAO userDAO = new UserDAO();
        if(userDAO.createUser(user)){
            System.out.println("SUCESSO");
        } else {
            System.out.println("ERRO");
        }*/

        System.out.println(DigestUtils.sha1Hex("Senha123"));
        launch();
    }
}