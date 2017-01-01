package dev.tpcompile.l3;


import dev.tpcompile.l3.lexic.Line;
import dev.tpcompile.l3.lexic.Token;
import dev.tpcompile.l3.syntax.AutomateRunner;
import dev.tpcompile.l3.syntax.Fait;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrologCompiler {
    private List<Line> linelist ;
    private List<Fait> faitsList ;
    private String messageErr ;
    private int errorlineIndex ;

    public PrologCompiler(String file) throws FileNotFoundException {
        Scanner s = new Scanner(new File(file));
        linelist = new ArrayList<Line>();
        while (s.hasNextLine())
        {
            linelist.add( new Line( s.nextLine()));
        }
        s.close();
    }

    public void lineAnalyse (){
        for (Line l : linelist){ // pour tout line "l" de listline print l'analyse
            System.out.println( l.analyse());       // pour ecrire ( kakimasu)
        }
    }

    public void runAutomate () {
        errorlineIndex = 0;
        faitsList = new ArrayList<Fait>();
        for (Line line : linelist) {
            errorlineIndex ++ ;
            AutomateRunner ar = new AutomateRunner(line);
            Fait fait = ar.run();
            //System.out.println( errorlineIndex + " " + fait);
            faitsList.add(fait);
            if (ar.getAutomate().getCurrentState().equals("ERROR")){
                messageErr = ar.getAutomate().getMessageErr();
                break;
            }
        }

    }

    public boolean hasErr (){
        return errorlineIndex < linelist.size();
    }

    public void printFaits(){
        for (Fait fait : faitsList)  {
            if (fait != null) {
                System.out.println("L'Identifiant de predicat : " + fait.getName());

                //for (Token arguments : fait.getArgs())
                    System.out.println("Les Arguments " + fait.getArgs());

                    System.out.println("Y'a t'il une Conditions  :" + fait.getconditionExist());
                   System.out.println("le Exprs :" + fait.getExpressions());

                

            }
        }
    }

    public void printErr(){
        System.out.println("error  on line " + errorlineIndex + ">> " + messageErr );
    }
}

