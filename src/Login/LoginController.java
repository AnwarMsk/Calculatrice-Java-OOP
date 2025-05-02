package Login;

import Calculatrice.Historique;
import Calculatrice.CalculatriceController;

import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button connection;

    @FXML
    private Label errorField;

    @FXML
    private Button inscription;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextArea usernameField;

    private String username;
    private String password;
    private static final Path chemin = Paths.get("src/Login/Login.txt");

    private boolean userExists(String user){
        InputStream input = null;
        try {
            input = Files.newInputStream(chemin, READ);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String s = null;
            while ((s = reader.readLine()) != null) {
                int indice = s.indexOf(":");
                if (indice != -1) {
                    s = s.substring(0, indice);
                    if(s.equals(user)) {
                        input.close();
                        return true;
                    }
                }
            }
            input.close();
            return false;
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            return false;
        }
    }

    public void inscription(){
        this.username = usernameField.getText();
        this.password = passwordField.getText();
        
        try {
            if (!Files.exists(chemin)) {
                Files.createFile(chemin);
            }
            if(userExists(this.username)) {
                errorField.setText("Ce nom d'utilisateur existe déjà");
            }
            else {
                byte[] data = (this.username + ":" + this.password + "\n").getBytes();
                OutputStream output = null;
                output = new BufferedOutputStream(Files.newOutputStream(chemin, APPEND));
                output.write(data);
                output.flush();
                output.close();
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void connection(){
        this.username = usernameField.getText();
        this.password = passwordField.getText();

        InputStream input = null;
        try {
            if (!Files.exists(chemin)) {
                Files.createFile(chemin);
            }
            input = Files.newInputStream(chemin, READ);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String s = null;
            while ((s = reader.readLine()) != null) {
                if(s.equals(this.username + ":" + this.password)) {
                    input.close();
                    versCalculatrice(this.username);
                    break;
                }
                else{
                    errorField.setText("Nom d'utilisateur ou mot de passe incorrect");
                }
            }
            input.close();
        } catch (IOException e) {
            System.out.println("Erreur 1: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void versCalculatrice(String user) {
        try {
            Historique.setUser(user);
            Historique.updateChemin();

            CalculatriceController.setUser(user);
            CalculatriceController.updateHistoriqueChemin();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Calculatrice/Calculatrice.fxml"));
            Parent calculatrice = loader.load();

            Stage stage = (Stage) connection.getScene().getWindow();

            stage.setScene(new Scene(calculatrice));
            stage.setTitle("Calculatrice");
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
