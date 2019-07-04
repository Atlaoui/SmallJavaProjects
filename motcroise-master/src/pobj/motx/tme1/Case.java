package pobj.motx.tme1;

/**
 * 
 * Classe de repr�sentation d'une case de la grille */

public class Case {

	/**
	 * Position de la case dans la grille
	 * */
	private int lig;	//le numero de ligne
	private int col;	//le numero de colonne
	private char c;		//le caractere de la case
	

	/**
	 * Construit un point de coordonn�es initiales sp�cifi�es
	 * @param lig numero  de la ligne
	 * @param col nemero de la colonne 
	 * @param valeur de la case
	 */
	public Case(int lig, int col, char c){
		this.c=c;
		this.lig=lig;
		this.col=col;
	}
	/**
	 * getter de la valeur de la case
	 * @return l'attribut c
	 */
	public char getChar() {
		return c;
	}
	
	/**Modifi la valeur de la case
	 * @void change la valeur de de la case*/
	public void setChar(char charAt) {
		c=charAt;
	}

	/**
	 * getter du numero de ligne de la case
	 * @return l'attribut lig
	 */
	public int getLig() {
		return lig;
	}
	/**
	 * getter du numero de colonne de la case
	 * @return l'attribut col
	 */
	public int getCol() {
		return col;
	}
	/**
	 * verifier si la case est vide
	 * @return true ou false
	 */
	
	public boolean isVide() {
		return c==' ';
	}
	
	/**
	 * verifier si la case est pleine
	 * @return true ou false
	 */
	public boolean isPleine() {
		return c=='*';
	}
	
	/**
	 * Faire un clonne sur la case de parametre lig, col et c 
	 * @return une nouvel Case*/
	@Override
	public Case clone() {
		return new Case(lig,col,c);
	}

	@Override
	public boolean equals( Object  obj) {
		if( this == obj ) return true;
		if( obj == null) return false;
		if(getClass()!= obj.getClass())
			return false ;
		//ici donner le nom de l'objet ainsi que les teste des atribut
		Case other = ( Case ) obj ;
		if ( lig!= other.lig ) return false;
		if( col!= other.col) return false;
		if(c!=other.c)return false; 
		return true;
	}

	@Override
	public String toString() {
		return ""+c;
	}

}
