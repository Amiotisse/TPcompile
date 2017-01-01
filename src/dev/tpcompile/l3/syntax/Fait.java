package dev.tpcompile.l3.syntax;

import dev.tpcompile.l3.lexic.Token;

import java.util.ArrayList;
import java.util.List;

public class Fait {

    private String name ;
    private List<Token> args;
    private boolean conditionExist;

    private List<Expression> expressions;


    public Fait(String name ) {
        this.name = name;
        this.conditionExist = false;
        args = new ArrayList<Token>();
        expressions = new ArrayList<Expression>();
    }

    public List<Token> getArgs(){
        return this.args;
    }

    public String getName() {
        return name;
    }

    public void setConditionExist(boolean conditionExist) {
        this.conditionExist = conditionExist;
    }

    public boolean getconditionExist() {
        return conditionExist;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
