package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;
/**
 * 
 * @author Nail 
 * Class qui trouve les emplacement des mots dans une grille
 */
public class GrillePlaces {
	private List<Emplacement> places; //Liste d'emplacement
	private final int NbHorizontal;	//pour stocker les nombre de mots horizontaux pendant la construction
	private Grille grille;	//attribue Grille

	/**
	 * construit une GrillePlaces avec des 
	 * @param grille qu'on affecte a l'attribue grille de la casse
	 * cree une nouvelle liste d'emplacement
	 * et recuper le nombre de mots horizontal*/
	public GrillePlaces (Grille grille) {
		places = new ArrayList<>();
		this.grille=grille;
		int i;
		for(i=0;i<grille.nbLig();i++) {
			this.cherchePlaces(getLig(i));
		}
		NbHorizontal=places.size();
		for(i=0;i<grille.nbCol();i++) {
			this.cherchePlaces(getCol(i));
		}
	}


	/**
	 * recherche des emplacement dans la liste de case et ajout dans notre liste d'emplacement places 
	 * @param cases Liste de case
	 * */
	public void cherchePlaces(List<Case> cases) {
		Emplacement mot = new Emplacement();
		int cpt=0;
		for(Case c:cases) {
			if(!c.isPleine()) {
				cpt++;
				mot.addCase(c);
				//Si l'emplacement contient au moins deux lettres on le rajoute
				if(cpt==2) {
					places.add(mot);
				}
			}else {
				cpt=0;
				mot = new Emplacement();
			}
		}
	}

	/**
	 *@param m 
	 *@param soluce
	 *fixer une nouvelle grille avec le mot soluce a l'indice m
	 *@return grille
	 * */

	public GrillePlaces fixer(int m, String  soluce) {
		Grille G= grille.copy();
		GrillePlaces gp=new GrillePlaces(G);
		Emplacement e =gp.places.get(m);
		for(int i =0 ,len=e.size();i<len;i++) {
			e.getCase(i).setChar(soluce.charAt(i));
			G.setCase(e.getCase(i).getLig(),e.getCase(i).getCol(),e.getCase(i));
		}
		return gp;
	}

	/**
	 * cherche les mots horizontaux
	 * @return retourne le nombre de mots
	 */
	public int getNbHorizontal() {
		return NbHorizontal; ////nombre de mots qu'on a stocke pendant la contruction de GrillePLaces
	}

	/**
	 * 
	 * @return la liste des emplacements
	 */
	public List<Emplacement> getPlaces(){
		return places;
	}

	/**
	 * @param lig //numero de la ligne
	 * recupere une ligne de case 
	 * @return liste de case
	 * */
	private List<Case> getLig (int lig){
		List<Case> mot= new ArrayList<Case>();
		for (int i = 0; i<grille.nbCol(); i++) 
			mot.add(grille.getCase(lig, i));
		return mot;

	}

	/**
	 * @param col //numero de la colonne
	 * recuper la colonne de case 
	 * @return liste de case 
	 * */
	private List<Case> getCol(int col){
		List<Case> mot= new ArrayList<Case>();
		for (int i = 0; i < grille.nbLig(); i++) 
			mot.add(grille.getCase(i, col));
		return mot;
	}

	@Override
	public String toString() {
		String S="";
		for (Emplacement emplacement : places) {
			S+=emplacement.toString()+"\n";
		}		
		return S;
	}
	/**
	 * @return la grille de la class 
	 * */
	public Grille getGrille() {
		return grille;
	}


}
