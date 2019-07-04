package pobj.motx.tme1;

/**
 * @Moi
 * Classe représenanta la grille
 * */
public class Grille {
	private Case[][] mat;	//tableau de tableau de Case
	private int nbCol;		//nombre de colonne
	private int nblig;		//nombre de colonne
	
	/**
	 * Construit une grille
	 * @param haut hauteur de la  la matrice
	 * @param largeur de la matrice
	 */
	public Grille(int haut, int larg) {
		mat= new Case[haut][larg];
		for(int i=0;i<haut;i++) {
			for(int j=0;j<larg;j++) {
				mat[i][j]=new Case(i,j,' ');
			}
		}
		nbCol=larg;
		nblig=haut;
	}
	/**
	 * Construit une grille
		pour un clonage plus éfficace
	*/
	public Grille() {
		mat=null;
	}
	
	/**
	 * retourne une Case a l'emplacement l et c
	 * @param l	numero de la ligne 
	 * @param c numero de colonne
	 * @return
	 */
	public Case getCase(int l , int c ){
		if(mat[l][c].getLig()==l & mat[l][c].getCol()==c)
			return mat[l][c];
		return null;
	}

	/**
	 * 
	 * @return le nombre de colonne de la Grille
	 */
	public int nbCol() {
		return nbCol;
	}

	/**
	 * @return nombre de lignes de la Grille
	 */
	public int nbLig() {
		return nblig;
	}
	
	/**
	 * set une case de courdonnees lig et col avec une valeur c
	 * @param lig numero de la ligne 
	 * @param col numero de colonne
	 * @param c la valeur char à affecter
	 * */
	public void setCase(int lig, int col, char c) {
		if(lig>0 && lig<nblig && col >0 && col<nbCol) 
			mat[lig][col].setChar(c);
	}
	
	/**
	 *méthode de clonage
	 *@return une copie de la Grille contruite depuis une nouvelle Grille et de nouvelles Cases*/
	
	public Grille copy() {
		Grille copy = new Grille();
		copy.mat= new Case[nblig][nbCol];
		copy.nbCol=nbCol;
		copy.nblig=nblig;
		for(int i=0;i<nblig;i++) 
			for(int j=0;j<nbCol;j++) {
				copy.mat[i][j]=mat[i][j].clone();
			}
				return copy;
	}
	
	@Override
	public String toString() {		
		return GrilleLoader.serialize(this, false);
	}

	/**
	 * méthode de modification d'une case dans la matrice 
	 * @void */
	public void setCase(int lig, int col, Case case1) {
		if(lig>0 && lig<nblig && col >0 && col<nbCol) {
			mat[lig][col]=case1;
		}
	}
	
}
