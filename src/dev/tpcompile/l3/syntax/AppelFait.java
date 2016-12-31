package dev.tpcompile.l3.syntax;

import dev.tpcompile.l3.lexic.Token;

import java.util.List;

public class AppelFait implements Expression{

    private String name ;
    private List<Token> args;

    public AppelFait(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AppelFait{" +
                "name='" + name + '\'' +
                ", args=" + args +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
