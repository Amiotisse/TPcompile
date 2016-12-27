package dev.tpcompile.l3;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
	
    
    private static List<Line> linelist;
    
    public static void main(String[] args) throws FileNotFoundException {
    	int choix = 3;
    	while (choix!=0 ){
    		System.out.println("Voulez vous : \n 1) Choisir un fichier \n 2) Ecrir un fichier \n 0) Sortir \n ");
    		Scanner clavier = new Scanner (System.in);
    		choix = clavier.nextInt();
    		if (choix == 1){
    			Scanner s = new Scanner(new File("FichierProlog.txt"));
    	        linelist = new ArrayList<Line>();
    	        while (s.hasNextLine())
    	        {
    	            linelist.add( new Line( s.nextLine()));
    	        }
    	        s.close();
    	        for (Line l : linelist){ // pour tout line "l" de listline print l'analyse 
    	               System.out.println( l.analyse());       // pour ecrire ( kakimasu) 
    	        }
    	        
    		}else if (choix == 2){
    			 System.out.println("Ecrivez votre commande Prolog :\n");
    			 String lignes = clavier.next();
    			 linelist = new ArrayList<Line>();
    			 linelist.add( new Line(lignes));
    			 for (Line l : linelist){ 
  	             System.out.println( l.analyse()); 
    		}
    	 

    	}
    }
}}