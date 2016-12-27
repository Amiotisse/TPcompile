package dev.tpcompile.l3;

public enum TokenType {
	IDENTPREDICAT(": identifiant de prédicat "),
	CONST(": Constante "),
	INT(": Entier"), 
	SEPARATOR(": Séparteur"), 
	CONDITION(": condition"),  
	VAR(": Variable"),
	IS(" : is "),
	READ(" : Lire "),
	WRITE(": Ecrire "),
	PLUS(": operateur +"),
	MOINS(": operateur -"),
	MUL(": operateur *"),
	DIV(": operateur /"),
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
		System.out.println(" la valeur est :"+value+"|");
		if(	value.equals(":-"))
			return CONDITION;
		if(value.equals("IS")|| value.equals("is"))
			return IS;
		if(value.equals("READ")|| value.equals("read"))
			return READ;
		if(value.equals("WRITE")|| value.equals("write"))
			return WRITE;
		if(value.equals("+"))
			return PLUS;
		if(value.equals("-"))
			return MOINS;
		if(value.equals("*"))
			return MUL;
		if(value.equals("/"))
			return DIV;
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
