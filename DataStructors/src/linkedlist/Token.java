/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author 216CS2017
 */
public class Token {

    int type;
    int operand;
    char operator;
    int precedence;

    public Token(int operand) {
        this.type = 0;
        this.operand = operand;
    }

    public Token(char operator) {
        this.type = 1;
        this.operator = operator;
        switch (operator) {
            case '(':
                precedence = 0;
                break;
            case '+':
            case '-':
                precedence = 1;
                break;
            case '*':
            case '/':
                precedence = 2;
                break;
            case ')':
                precedence = 3;
                break;

        }
    }

    public int evaluate(Element[] expr) {// DOLDUR KİTAP SAYFA 89-90

        Token e, e1, e2;
        Element s;
        Stack c = new Stack<Token>();
        
        for (int i = 0; i < expr.length; i++) {
            e =(Token) expr[i].data;
            if (e.type == 0) {
                c.push(new Node(expr[i]));
            } else {
                e2 = (Token) c.pop().data;
                e1 = (Token) c.pop().data;
                s = new Element(eval(e.operator, e1.operand, e2.operand));
                c.push(new Node(s));
            }
        }
        e = (Token) c.pop().data;
        return e.operand;
    }

    static Token eval(char ch, int e1, int e2) {


        int result = 0;
        switch (ch) {
            case '+':
                result = e1 + e2;
                break;
            case '-':
                result = e1 - e2;
                break;
            case '*':
                result = e1 * e2;
                break;
            case '/':
                result = e1 / e2;
                break;


        }
        return new Token(result);
    }
}
