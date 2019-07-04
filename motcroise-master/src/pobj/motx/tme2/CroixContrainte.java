package pobj.motx.tme2;

public class CroixContrainte implements IContrainte {
	private int m1,c1,m2,c2;


	/**
	 * Construit une CroixContrainte
	 * @param m1 premier emplacement
	 * @param m2 deuxieme emplacement
	 * @param c1 premiere case
	 * @param c2 deuxieme case
	 */
	public CroixContrainte(int m1,int c1,int m2,int c2) {
		this.m1=m1;
		this.c1=c1;
		this.c2=c2;
		this.m2=m2;
	}

	/**
	 * @param GrillePotentiel
	 * @return le nombre de mots filtres
	 */
	@Override
	public int reduce(GrillePotentiel grille) {
		EnsembleLettre l1 = grille.getMot(m1).charAt(c1);
		EnsembleLettre l2 = grille.getMot(m2).charAt(c2);
		EnsembleLettre s= EnsembleLettre.intersection(l1, l2);	
		if(s==null)
			return 0;
		int cpt=0,lenS=s.size();
		if(l1.size()>lenS)
			cpt+=grille.getMot(m1).filtreParEnsemble(s,c1);
		if(l2.size()>lenS)
			cpt+=grille.getMot(m2).filtreParEnsemble(s,c2);
		return cpt;
	}


	/**
	 * methode equals définie pour l'objet CroixContrainte
	 */

	@Override
	public boolean equals( Object  obj) {
		if( this == obj ) return true;
		if( obj == null) return false;
		if(getClass()!= obj.getClass())return false ;
		//ici donner le nom de l'objet ainsi que les teste des atribut
		CroixContrainte other = ( CroixContrainte ) obj ;
		if ( m1!= other.m1 ) return false;
		if(c2!=other.c2)return false; 
		if( c1!= other.c1) return false;
		if(m2!=other.m2)return false; 
		return true;
	}
	@Override
	public String toString() {
		return "m1="+m1+" c1="+c1+" m2="+m2+" c2="+c2;
	}


}
