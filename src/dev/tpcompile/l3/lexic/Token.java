package dev.tpcompile.l3.lexic;

public class Token {
	private TokenType type ;
	private	String value;
	//private boolean identPredicat = false  ;
	
	public Token(String value, TokenType type){
		this.value=value;
		this.type=type;
	}
	
	public TokenType getType() {
		return type;
	}

	public void setType(TokenType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Token(String value){
		this.value=value;
		this.type= TokenType.defineType(value);// le type de token et de sa valeur 
	}
	public String toString() {
		return value + " : "+ type;
	}

//	public boolean isIdentPredicat() {
//		return identPredicat;
	//}

//	public void setIdentPredicat(boolean identPredicat) {
	//	this.identPredicat = identPredicat;
	//}

	 
}
