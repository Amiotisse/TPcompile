package dev.tpcompile.l3.syntax;

import dev.tpcompile.l3.lexic.Token;
import dev.tpcompile.l3.lexic.TokenType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.*;
public class Automate {


    private String currentState ;
    private Fait result ;
    private Map<String, Function<Token,String>> transitions ;
    private String messageErr;
    private Token tp;
    private Expression currentExpr ;


    public Automate(){
        currentState = "Entry";
        transitions = new HashMap<String, Function<Token,String>>();

        transitions.put( "Entry" ,
                (tc) ->{
                    if (tc.getType() == TokenType.CONST){
                      result = new Fait(tc.getValue());
                      tp = tc;
                      return "1";
                    }
                    messageErr = " doit commancer par un prédicat";
                    return "ERROR";
                }
        );
        transitions.put( "1" ,
                (tc) ->{
                    if (tc.getType() == TokenType.SEPARATOR && tc.getValue().equals("(") ){
                        tp = tc;
                        return "2";
                    }
                    if (tc.getType() == TokenType.CONDITION ){
                        tp = tc;
                        return "5";
                    }
                    messageErr = "token unexpected : expect '(' after" + tp.getValue();
                    return "ERROR";
                }
        );
        transitions.put( "2" ,
                (tc) ->{
                    if (tc.getType() == TokenType.CONST || tc.getType() == TokenType.VAR || tc.getType() == TokenType.INT){
                        result.getArgs().add(tc);
                        tp = tc;
                        return "3";
                    }
                    messageErr = "token unexpected : expect ident  after " + tp.getValue();
                    return "ERROR";
                }
        );

        transitions.put( "3" ,
                (tc) ->{
                    if (tc.getType() == TokenType.SEPARATOR && tc.getValue().equals(",") ){
                        tp = tc;
                        return "2";
                    }
                    if (tc.getType() == TokenType.SEPARATOR && tc.getValue().equals(")") ){
                        tp = tc;
                        return "4";
                    }
                    messageErr = "token unexpected : expect ',' or ')' after " +  tp.getValue() ;
                    return "ERROR";
                }
        );

        transitions.put( "4" ,
                (tc) ->{
                    if (tc.getType() == TokenType.SEPARATOR && tc.getValue().equals(".") ){
                        return "Terminal";
                    }
                    if (tc.getType() == TokenType.CONDITION ){
                        result.setConditionExist(true);
                        tp = tc;
                        return "5";
                    }
                    messageErr = "token unexpected : expect ',' or ')' after " +  tp.getValue() ;
                    return "ERROR";
                }
        );
        transitions.put( "5" ,
                (tc) ->{
                	if (tc.getType() == TokenType.CONST){
                		tp = tc ;
                        currentExpr = new AppelFait(tc.getValue());
                        return "A1";
                    }
                    if (tc.getType() == TokenType.VAR ){
                    	tp = tc;
                        return "B1";
                    }
                    if (tc.getType() == TokenType.INT || tc.getType() == TokenType.CONST ){
                        tp = tc;
                        return "C1";
                    }
                    messageErr = "token unexpected : expect ',' or ')' after " +  tp.getValue() ;
                    return "ERROR";
                }
        );
        transitions.put( "A1" ,
                (tc) ->{
                    if (tc.getType() == TokenType.SEPARATOR && tc.getValue().equals("(") ){
                        tp = tc;
                        return "A2";
                    }
                    messageErr = "token unexpected : expect '(' after" + tp.getValue();
                    return "ERROR";
                }
        );
        
        transitions.put( "A2" ,
                (tc) ->{
                    if ((tc.getType() == TokenType.CONST || tc.getType() == TokenType.VAR || tc.getType() == TokenType.INT) ){
                        tp = tc;
                        ((AppelFait)currentExpr).getArgs().add(tc);
                        return "A3";
                    }
                    messageErr = "token unexpected : expect '(' after" + tp.getValue();
                    return "ERROR";
                }
        );
        
        transitions.put( "A3" ,
                (tc) ->{
                    if (tc.getType() == TokenType.SEPARATOR && tc.getValue().equals(",") ){
                        tp = tc;
                        return "A2";
                    }
                    if (tc.getType() == TokenType.SEPARATOR && tc.getValue().equals(")") ){
                        tp = tc;
                        return "A4";
                    }
                    messageErr = "token unexpected : expect ',' or ')' after " +  tp.getValue() ;
                    return "ERROR";
                }
        );
        
        transitions.put( "A4" ,
                (tc) ->{
                    if (tc.getType() == TokenType.SEPARATOR && tc.getValue().equals(".") ){
                        result.getExpressions().add(currentExpr);
                        return "Terminal";
                    }
                    if (tc.getType() == TokenType.SEPARATOR && tc.getValue().equals(",")){
                        result.getExpressions().add(currentExpr);
                        tp = tc;
                        return "5";
                    }
                    messageErr = "token unexpected : expect ',' or ')' after " +  tp.getValue() ;
                    return "ERROR";
                }
        );
        
        transitions.put( "B1" ,
                (tc) ->{
                    if (( tc.getType() == TokenType.IS )){
                        currentExpr = new Definition();
                        ((Definition)currentExpr).setVarDefined(tp.getValue());
                        tp = tc;
                        return "B2";
                    }
                    if (( tc.getType() == TokenType.OpComp )){
                        currentExpr = new Comparetion();
                        ((Comparetion)currentExpr).setLeft(tp);
                        ((Comparetion)currentExpr).setOperator(tc.getValue());
                        tp = tc;
                        return "C2";
                    }
                    messageErr = "token unexpected : expect '(' after" + tp.getValue();
                    return "ERROR";
                }
        );
        
        transitions.put( "B2" ,
                (tc) ->{
                    if ((tc.getType() == TokenType.VAR || tc.getType() == TokenType.INT) ){
                        ((Definition)currentExpr).getDefinition().addOperand(tc);
                        tp = tc;
                        return "B3";
                    }
                    messageErr = "token unexpected : expect '(' after" + tp.getValue();
                    return "ERROR";
                }
        );
        
        transitions.put( "B3" ,
                (tc) ->{
                    if (tc.getType() == TokenType.SEPARATOR && tc.getValue().equals(",") ){
                        result.getExpressions().add(currentExpr);
                        tp = tc;
                        return "5";
                    }
                    if (tc.getType() == TokenType.Op ){
                        ((Definition)currentExpr).getDefinition().addOperator(tc.getValue());
                        tp = tc;
                        return "B2";
                    }
                    if (tc.getType() == TokenType.SEPARATOR && tc.getValue().equals(".") ){
                        result.getExpressions().add(currentExpr);
                        return "Terminal";   
                }
                    messageErr = "token unexpected : expect ',' or ')' after " +  tp.getValue() ;
                    return "ERROR";
                }
        );

        transitions.put( "C1" ,
                (tc) ->{
                    if (( tc.getType() == TokenType.OpComp )){
                        currentExpr = new Comparetion();
                        ((Comparetion)currentExpr).setLeft(tp);
                        ((Comparetion)currentExpr).setOperator(tc.getValue());
                        tp = tc;
                        return "C2";
                    }
                    messageErr = "token unexpected : expect '(' after" + tp.getValue();
                    return "ERROR";
                }
        );

        transitions.put( "C2" ,
                (tc) ->{
                    if ((tc.getType() == TokenType.CONST || tc.getType() == TokenType.VAR || tc.getType() == TokenType.INT) ){
                        ((Comparetion)currentExpr).setRight(tc);
                        tp = tc;
                        return "C3";
                    }
                    messageErr = "token unexpected : expect '(' after" + tp.getValue();
                    return "ERROR";
                }
        );
        
        transitions.put( "C3" ,
                (tc) ->{
                    if (tc.getType() == TokenType.SEPARATOR && tc.getValue().equals(".") ){
                        result.getExpressions().add(currentExpr);
                        return "Terminal";
                    }
                    if (tc.getType() == TokenType.SEPARATOR && tc.getValue().equals(",")){
                        result.getExpressions().add(currentExpr);
                        tp = tc;
                        return "5";
                    }
                    messageErr = "token unexpected : expect ',' or ')' after " +  tp.getValue() ;
                    return "ERROR";
                }
        );
        
        transitions.put( "Terminal" ,
                (tc) ->{
                    messageErr = "token unexpected : expect nothing after " +  tp.getValue() ;
                    return "ERROR";
                }
        );
        transitions.put( "ERROR" ,
                (tc) -> "ERROR"
        );
    }

    public String  readTocken( Token tc ) {
        currentState = transitions.get(currentState).apply( tc );
        return currentState ;
    }

    public String getCurrentState (){
        return currentState;
    }

    public Fait getResult() {
        if (!currentState.equals("Terminal")) return null;
        return result ;
    }

    public String getMessageErr() {
        return messageErr;
    }
}