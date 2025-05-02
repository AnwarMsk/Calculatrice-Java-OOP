package Calculatrice;

import java.util.Stack;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class OperationComplexe {
    private String expression;
    private double res;

    public double getRes(){
        return this.res;
    }
    public void lireExpression(Scanner scanner) throws ExpressionException{
        scanner.nextLine();
        this.expression = scanner.nextLine();
        if (expression.isEmpty()){
            throw (new ExpressionException());
        }
    }

    public String getExpression(){
        return this.expression;
    }

    public void setExpression(String expression){
        this.expression = expression;
    }

    private int valeur(char operator){
        if (operator == '+' || operator == '-'){
            return 1;
        }
        else{
            if (operator == '*' || operator == '/'){
                return 2;
            }
            else{
                return 0;
            }
        }
    }

    private Queue<String> postfix() throws ExpressionException{
        int n = this.expression.length();
        String num = "";
        Queue<String> file = new LinkedList<>();
        Stack<Character> pile = new Stack<>();

        for (int i = 0; i<n; i++){
            char currentChar = this.expression.charAt(i);
            if (Character.isDigit(currentChar) || currentChar == '.'){
                num += this.expression.charAt(i);
            }
            else{
                if(!num.isEmpty()){
                    file.add(num);
                    num = "";
                }
                if (currentChar == '*'  || currentChar == '/' || currentChar == '+'  || currentChar == '-') {
                    while (!pile.isEmpty() && valeur(pile.peek())>valeur(currentChar)) {
                        file.add(Character.toString(pile.pop()));
                    }
                    pile.add(currentChar);
                }
                else if(currentChar == '('){
                    pile.add(currentChar);
                }
                else if(currentChar == ')'){
                    while (pile.peek() != '(') {
                        file.add(Character.toString(pile.pop()));
                    }
                    pile.pop();
                }
                else{
                    throw (new ExpressionException());
                }
            }
        }
        if(!num.isEmpty()){
            file.add(num);
        }
        while (!pile.isEmpty()) {
            file.add(Character.toString(pile.pop()));
        }
        return file;
    }

    public double calculate() throws DivisionException, ExpressionException, EmptyStackException{
        Queue<String> file = new LinkedList<>();
        Stack<Double> pile = new Stack<>();
        char operation;

        try {
            file.addAll(postfix());
        } catch (ExpressionException e) {
            throw new ExpressionException();
        }
        
        while(!file.isEmpty()){
            String current = file.poll();
            if(!current.equals("+") && !current.equals("-") && !current.equals("*") && !current.equals("/")){
                pile.add(Double.parseDouble(current));
            }
            else{
                operation = current.charAt(0);

                double b;
                double a;
                try {
                    b = pile.pop();
                    a = pile.pop();
                    switch (operation) {
                        case '+':
                            pile.add(a + b);
                            break;
    
                        case '-':
                            pile.add(a - b);
                            break;
                            
                        case '*':
                            pile.add(a * b);
                            break;
    
                        case '/':
                            if(b != 0){
                                pile.add(a / b);
                                break;
                            }
                            else{
                                throw (new DivisionException());
                            }
                        default:
                            break;
                    }
                } catch (EmptyStackException e) {
                    throw new EmptyStackException();
                }

            }
        }
        this.res = pile.pop();
        return getRes();
    }
}
