package Calculatrice;

import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;

public class Historique {
    private static String user;
    private static Path chemin = Paths.get("src/Calculatrice/Historique" + user +".txt");
    private String expression;

    public static void setUser(String username){
        user = username;
    }

    public String getUser(){
        return user;
    }

    public static void updateChemin(){
        chemin = Paths.get("src/Calculatrice/Historique" + user +".txt");
    }

    public String getExpression(){
        return this.expression;
    }

    public void setExpression(String expression){
        this.expression = expression;
    }

    public void creerExpression(int choix, double n1, double n2, double res){
        switch (choix) {
            case 1:
                this.expression = String.valueOf(n1) + " + " +  String.valueOf(n2) + " = " + String.valueOf(res); 
                break;
            
            case 2:
                this.expression = String.valueOf(n1) + " - " +  String.valueOf(n2) + " = " + String.valueOf(res); 
                break;

            case 3:
                this.expression = String.valueOf(n1) + " * " +  String.valueOf(n2) + " = " + String.valueOf(res); 
                break;

            case 4:
                this.expression = String.valueOf(n1) + " / " +  String.valueOf(n2) + " = " + String.valueOf(res); 
                break;
        
            default:
                break;
        }
    }

    public void creerExpression(String expression, double res){
        this.expression = expression + " = " + String.valueOf(res);
    }

    public void ecrireHistorique(){
        byte[] data = (this.expression + "\n").getBytes();
        OutputStream output = null;
        
        try {
            if (!Files.exists(chemin)) {
                Files.createFile(chemin);
            }
            output = new BufferedOutputStream(Files.newOutputStream(chemin, APPEND));
            output.write(data);
            output.flush();
            output.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void lireHistorique(){
        InputStream input = null;
        try {
            input = Files.newInputStream(chemin, READ);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String s = null;
            s = reader.readLine();
            System.out.println(s);
            input.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
