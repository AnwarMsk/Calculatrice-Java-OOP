package Calculatrice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EmptyStackException;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class CalculatriceController {

    @FXML
    private Button addition;

    @FXML
    private Button cinq;

    @FXML
    private Button clear;

    @FXML
    private Button deux;

    @FXML
    private Button difference;

    @FXML
    private Button division;

    @FXML
    private Button effacer;

    @FXML
    private Button egal;

    @FXML
    private Button factorielle;

    @FXML
    private Button huit;

    @FXML
    private Button multiplication;

    @FXML
    private Button neuf;

    @FXML
    private Button quatre;

    @FXML
    private Button sept;

    @FXML
    private Button six;

    @FXML
    private Button trois;

    @FXML
    private Button un;

    @FXML
    private Button virgule;

    @FXML
    private Button zero;

    @FXML
    private Button paranthese;

    @FXML
    private Button effacerHist;

    @FXML
    private TextField display;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private ImageView historique;

    @FXML
    private StackPane calculatriceView;

    @FXML
    private StackPane historiqueView;

    @FXML
    private ListView<String> listHistorique = new ListView<>();
    
        private ObservableList<String> historiqueData = FXCollections.observableArrayList();
            private String current = "";
            private boolean par = false;
            private String currentFXML = "Calculatrice";
            private static String user;
            private static String historiqueChemin = "src/Calculatrice/Historique" + user +".txt";
            
            
                @FXML
                public void initialize() {
                    loadHistoriqueData();
                    zero.setOnAction(_ -> bouton("0"));
                    un.setOnAction(_ -> bouton("1"));
                    deux.setOnAction(_ -> bouton("2"));
                    trois.setOnAction(_ -> bouton("3"));
                    quatre.setOnAction(_ -> bouton("4"));
                    cinq.setOnAction(_ -> bouton("5"));
                    six.setOnAction(_ -> bouton("6"));
                    sept.setOnAction(_ -> bouton("7"));
                    huit.setOnAction(_ -> bouton("8"));
                    neuf.setOnAction(_ -> bouton("9"));
                    addition.setOnAction(_ -> bouton("+"));
                    difference.setOnAction(_ -> bouton("-"));
                    multiplication.setOnAction(_ -> bouton("*"));
                    division.setOnAction(_ -> bouton("/"));
                    virgule.setOnAction(_ -> bouton("."));
                    egal.setOnAction(_ -> calculer());
                    factorielle.setOnAction(_ -> factorielle());
                    clear.setOnAction(_ -> clear());
                    effacer.setOnAction(_ -> effacer());
                    paranthese.setOnAction(_ -> paranthese());
                    effacerHist.setOnAction(_ -> effacerHist());
                    historique.setOnMouseClicked(_ -> afficherHistorique());
                }
            
                public static void setUser(String username){
                    user = username;
                }
            
                public String getUser(){
                    return user;
                }
            
                public static void updateHistoriqueChemin(){
                    historiqueChemin = "src/Calculatrice/Historique" + user +".txt";
            }
        
            private void bouton(String text){
                this.current += text;
                display.setText(this.current);
            }
        
            private void calculer(){
                OperationComplexe opp = new OperationComplexe();
                opp.setExpression(current);
                this.current = "";
                double res;
                try{
                    res = opp.calculate();
                    current = String.valueOf(res);
                    display.setText(String.valueOf(res));
                    Historique hist = new Historique();
                    hist.creerExpression(opp.getExpression(), opp.getRes());;
                    hist.ecrireHistorique();
                    ajouterHistorique(hist.getExpression());
                }
                catch (DivisionException e) {
                    display.setText("Erreur : " + e.getMessage());
                    display.setEditable(false);
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(_ -> {
                        display.setText(current);
                        display.setEditable(true);
                    });
                    pause.play();
        
                }
                catch (ExpressionException e){
                    display.setText("Erreur : " + e.getMessage());
                    display.setEditable(false);
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(_ -> {
                        display.setText(current);
                        display.setEditable(true);
                    });
                    pause.play();
                }
                catch (EmptyStackException e){
                    display.setText("Erreur : " + e.getMessage());
                    display.setEditable(false);
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(_ -> {
                        display.setText(current);
                        display.setEditable(true);
                    });
                    pause.play();
                }
            }
        
            private void factorielle() {
                OperationComplexe opp = new OperationComplexe();
                opp.setExpression(current);
                this.current = "";
                double res = 1, old;
                try{
                    old = opp.calculate();
                    for (int i=2; i<=old; i++){
                        res *= i;
                    }
                    current = String.valueOf(res);
                    display.setText(String.valueOf(res));
                }
                catch (DivisionException e) {
                    display.setText("Erreur : " + e.getMessage());
                    display.setEditable(false);
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(_ -> {
                        display.setText(current);
                        display.setEditable(true);
                    });
                    pause.play();
        
                }
                catch (ExpressionException e){
                    display.setText("Erreur : " + e.getMessage());
                    display.setEditable(false);
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(_ -> {
                        display.setText(current);
                        display.setEditable(true);
                    });
                    pause.play();
                }
                catch (EmptyStackException e){
                    display.setText("Erreur : " + e.getMessage());
                    display.setEditable(false);
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(_ -> {
                        display.setText(current);
                        display.setEditable(true);
                    });
                    pause.play();
                }
            }
        
            private void clear(){
                this.current = "";
                display.setText(current);
            }
        
            private void effacer(){
                if (this.current.length() <= 1){
                    if(current.equals("(")){
                        this.par = false;
                    }
                    this.current = "";
                }
                else {
                    char lastChar = this.current.charAt(this.current.length() - 1);
                    if (lastChar == '(') {
                        this.par = false;
                    } 
                    else if (lastChar == ')') {
                        this.par = true;
                    }
                    this.current = current.substring(0, current.length() - 1);
                }
                display.setText(current);
            }
        
            private void paranthese(){
                if(par == true){
                    this.current += ")";
                    display.setText(current);
                    this.par = false;
                }
                else{
                    this.current += "(";
                    display.setText(current);
                    this.par = true;
                }
            }
        
            private void afficherHistorique() {
                if ("Calculatrice".equals(this.currentFXML)) {
                    historiqueView.setVisible(true);
        
                    resizeWindow(historiqueView, calculatriceView.getWidth());
                    
                    currentFXML = "Historique";
                } else {
                    historiqueView.setVisible(false);
        
                    resizeWindow(calculatriceView, 0);
                    
                    currentFXML = "Calculatrice";
                }
            }
        
            private void resizeWindow(StackPane view, double n1) {
                Stage stage = (Stage) mainContainer.getScene().getWindow();
                double width = view.getWidth() + n1;
                
                stage.setWidth(width + 10);
            }
        
            public void loadHistoriqueData() {
                Path path = Paths.get(historiqueChemin);
                if (Files.exists(path)) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(historiqueChemin))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            historiqueData.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            
            listHistorique.setItems(historiqueData);
        }
    }

    public void ajouterHistorique(String ligne){
        historiqueData.add(ligne);
        listHistorique.setItems(historiqueData);
    }

    public void effacerHist(){
        Path path = Paths.get(historiqueChemin);
        try {
            Files.delete(path);
            historiqueData.clear();
            listHistorique.setItems(historiqueData);
        } catch (IOException e) {
        }
    }
}
