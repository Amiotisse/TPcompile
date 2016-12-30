package dev.tpcompile.l3.syntax;


import dev.tpcompile.l3.lexic.Line;
import dev.tpcompile.l3.lexic.Token;

import java.util.List;

public class AutomateRunner {
    private Automate automate ;
    private Line line ;
    public Automate getAutomate(){
        return automate;
    }

    public AutomateRunner (Line line){
        this.automate = new Automate() ;
        this.line = line;
    }

    public Fait run (){
        for (Token tc : line.getTokenList()){
            String cs = automate.readTocken(tc);
         //   System.out.println(" current state = " + cs );
        }
        return automate.getResult();
    }
}
