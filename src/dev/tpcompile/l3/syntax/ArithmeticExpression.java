package dev.tpcompile.l3.syntax;

import dev.tpcompile.l3.lexic.Token;

import java.util.List;

public class ArithmeticExpression {

    public class ArithmeticExpressionSegment {
        private String operator ;
        private Token operand ;

        @Override
        public String toString() {
            return  operator + ' ' + operand.getValue() ;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public Token getOperand() {
            return operand;
        }

        public void setOperand(Token operand) {
            this.operand = operand;
        }
    }

    private Token fistOperand = null;
    private List<ArithmeticExpressionSegment> chainedExpression;

    public Token getFistOperand() {
        return fistOperand;
    }

    public void setFistOperand(Token fistOperand) {
        this.fistOperand = fistOperand;
    }

    public List<ArithmeticExpressionSegment> getChainedExpression() {
        return chainedExpression;
    }

    public void setChainedExpression(List<ArithmeticExpressionSegment> chainedExpression) {
        this.chainedExpression = chainedExpression;
    }

    private ArithmeticExpressionSegment currentSegment = null;

    public void addOperand (Token operand ){
        if (fistOperand == null ) fistOperand = operand ;
        else currentSegment.setOperand(operand);
    }

    public void addOperator (String operator){
        if (currentSegment != null) chainedExpression.add(currentSegment);
        currentSegment = new ArithmeticExpressionSegment();
        currentSegment.setOperator(operator);
    }

    @Override
    public String toString() {
        String asString = fistOperand.getValue();
        for (ArithmeticExpressionSegment seg : chainedExpression){
            asString += " " + seg;
        }
        return asString;
    }
}

