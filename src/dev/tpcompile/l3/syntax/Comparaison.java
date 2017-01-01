package dev.tpcompile.l3.syntax;

import dev.tpcompile.l3.lexic.Token;

public class Comparaison implements Expression{

    private Token left ;
    private String operator;
    private Token right ;

    public Token getLeft() {
        return left;
    }

    public void setLeft(Token left) {
        this.left = left;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Token getRight() {
        return right;
    }

    public void setRight(Token right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Comparaison { " +
                left.getValue() + " " +
                operator + " " +
                right.getValue() +
                '}';
    }
}
