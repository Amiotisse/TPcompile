package dev.tpcompile.l3.lexic;

public enum TokenType {
	CONST(": Constante "),
	INT(": Entier"), 
	SEPARATOR(": Separteur"),
	CONDITION(": condition"),  
	VAR(": Variable"),
	IS(" : is "),
	NL(": Saut de line "),
	READ(" : Lire "),
	WRITE(": Ecrire "),
	Op(" : Operateur Aritmetique"),
	/*PLUS(": operateur +"),
	MOINS(": operateur -"),
	MUL(": operateur *"),
	DIV(": operateur /"),*/

	//OPERATORS(": operator Alrithmique "),
	EGALE(": operateur ="),
	ERREUR("ERREUR");
	
	private String discription;

	private TokenType(String disp) {
		discription = disp;

	}

	public String toString() {
		return discription;
	}
	
	public static TokenType defineType (String value){
		if(	value.equals(":-"))
			return CONDITION;
		if(value.equals("IS")|| value.equals("is"))
			return IS;
		if(value.equals("NL")|| value.equals("nl"))
			return NL;
		if(value.equals("READ")|| value.equals("read"))
			return READ;
		if(value.equals("WRITE")|| value.equals("write"))
			return WRITE;
		if(value.matches("[\\+-\\*/]"))
			return Op;
		
		if(value.equals("="))
			return EGALE;
		if( value.matches("[\\.,\\()]"))
			return SEPARATOR ;
		if( value.matches("[0-9]+"))
			return INT ;
		if( value.matches("[A-Z].*"))
			return VAR;
		if( value.matches("[a-z].*"))	
			return CONST;
		
		return ERREUR;
	}
}
