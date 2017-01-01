package dev.tpcompile.l3.syntax;

public class Definition implements Expression{

    private String varDefined ;

    private ArithmeticExpression definition;

    public String getVarDefined() {
        return varDefined;
    }

    public void setVarDefined(String varDefined) {
        this.varDefined = varDefined;
    }

    public ArithmeticExpression getDefinition() {
        return definition;
    }

    public void setDefinition(ArithmeticExpression definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "Definition{" +
                "varDefined='" + varDefined + '\'' +
                ", definition=" + definition +
                '}';
    }
}
