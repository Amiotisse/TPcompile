package dev.tpcompile.l3.lexic;

import java.util.ArrayList;
import java.util.List;

public class Line {
	private String str;
	private List<Token> TokenList;

	public Line(String str) {
		this.str = str;

		// System.out.println(" ---> "+ str);
		TokenList = new ArrayList<Token>(); // initialisation d'une liste pour
											// recuperer les token yeah!!!!
		if (!str.startsWith("%") && !str.matches(" *")) {
			String utilStr = str.replaceAll("\\(", " ( ");
			// System.out.println(" ---> "+ utilStr);
			utilStr = utilStr.replaceAll("\\)", " ) ");
			// System.out.println(" ---> "+ utilStr);
			utilStr = utilStr.replaceAll(",", " , ");
			// System.out.println(" ---> "+ utilStr);
			utilStr = utilStr.replaceAll("\\.", " . ");
			// System.out.println(" ---> "+ utilStr);
			
			// System.out.println(" ---> "+ utilStr);
			utilStr = utilStr.replaceAll("\\+", " + ");
			utilStr = utilStr.replaceAll("-", " - ");// :- --> ": - " 
			utilStr = utilStr.replaceAll("\\*", " * ");
			utilStr = utilStr.replaceAll("/", " / ");
			utilStr = utilStr.replaceAll(": -", " :- ");//": - " --> " :-  "
			utilStr = utilStr.replaceAll("=", " = ");
			String[] tokenStrTab = utilStr.split(" +");

			for (String tokenStr : tokenStrTab) {
				// System.out.println(" ---> "+ tokenStr);
				Token t = new Token(tokenStr);
				// System.out.println(" ---> "+ t);
				TokenList.add(t);// ajouter un token a la liste tokenliste
			}
		}
	}

	public String afficheErr() {
		String err = "";
		// ne se termine pas par un point (.)
		if (TokenList.size() > 0
				&& !TokenList.get(TokenList.size() - 1).getValue().equals(".")) {
			err += "\n la ligne dois finir par un point '.' ! ";
		}
		// parenth�se non fermer ! :p (algorithme werahli NABIL hihihihi :D w
		// ma3labalahch kisemouh)
		int i = 0;
		int cpt = 0;
		while (cpt >= 0 && i < TokenList.size()) {
			if (TokenList.get(i).getValue().equals("("))
				cpt++;
			else if (TokenList.get(i).getValue().equals(")"))
				cpt--;
			i++;
		}
		if (cpt != 0) {
			err += " \n Erreur de syntaxe de parenth�se '()' ";
		}

		if (!err.equals("")) {
			err = "Les Erreur trouver sont : " + err + "\n ... ";
			return err;
		}
		return null;

	}

	public String analyse() {

		String eror = afficheErr();
		String result = ">>> " + str + '\n';
		if (eror == null)
			for (Token t : TokenList) {
				result = result + t + "\n";
			}
		else
			result+= eror+ '\n';
		return result;
	}

	public List<Token> getTokenList() {
		return TokenList;
	}
}
