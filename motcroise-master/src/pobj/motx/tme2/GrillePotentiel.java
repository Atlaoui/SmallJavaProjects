package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme1.GrillePlaces;

public class GrillePotentiel {
	private GrillePlaces grilleP;
	private Dictionnaire dicoComplet;
	private List<Dictionnaire> motsPot;
	private List<IContrainte> contraintes;


	/**
	 * contruit une GrillePotentiel
	 * @param grille grillePlacesdicoList
	 * @param dicoCpmlet dictionnaire
	 */
	
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet){
		this.dicoComplet=dicoComplet;
		grilleP=grille;
		motsPot=new ArrayList<>();
		contraintes = new ArrayList<>();
		Emplacement e;
		Dictionnaire dicoPartiel;
		int j,i,len,len2;

		for( i=0,len=grille.getPlaces().size();i<len;i++) {
			dicoPartiel = dicoComplet.copy();
			len2=grille.getPlaces().get(i).size();
			dicoPartiel.filtreLongueur(len2);	
			e=grille.getPlaces().get(i);
			for(j=0;j<len2;j++) {
				if(!e.getCase(j).isVide() && !e.getCase(j).isPleine())
					dicoPartiel.filtreParLettre(e.getCase(j).getChar(), j);
			}
			motsPot.add(dicoPartiel);
		}
		chercheContrainte();
		//MotUniqueContrainte();
		propage();
	}

	/**
	 * constuit une grillePotentiel a partir d'une grillePlaces, un dictionnaire et une liste de dictionnaire
	 * @param grille
	 * @param dicoComplet
	 * @param dicoList
	 */
	
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet , List<Dictionnaire> motsPot ) {
		this.dicoComplet=dicoComplet;
		grilleP=grille;
		this.motsPot=motsPot;
		contraintes = new ArrayList<>();
		Emplacement e;
		int j,i,len,len2;
		int cpt=0;
		
		for( i=0,len=grille.getPlaces().size();i<len;i++) {
			
			len2=grille.getPlaces().get(i).size();
			this.motsPot.get(cpt).filtreLongueur(len2);	
			e=grille.getPlaces().get(i);
			for(j=0;j<len2;j++) {
				if(!e.getCase(j).isVide() && !e.getCase(j).isPleine())
					this.motsPot.get(cpt).filtreParLettre(e.getCase(j).getChar(), j);
			}
			cpt++;
		}
		chercheContrainte();
		MotUniqueContrainte();
		propage();

	}



	/**
	 * Méthode qui cherche les positions des contraintes */
	private void chercheContrainte() {
		int j,i,c1,c2,len,len2,len3,len4;
		Emplacement e,e2;
		len2=grilleP.getPlaces().size();
		for(i=0,len=grilleP.getNbHorizontal();i<len;i++) {
			for(j=grilleP.getNbHorizontal();j<len2;j++) {
				e=grilleP.getPlaces().get(i);
				e2=grilleP.getPlaces().get(j);
				len3=e.size();
				len4=e2.size();
				for(c1=0;c1<len3;c1++) {
					for(c2=0;c2<len4;c2++) {
						//pas sur ici pour cette condition 
						if(e.getCase(c1).equals(e2.getCase(c2)) && (e.getCase(c1).isVide())) {
							CroixContrainte c = new CroixContrainte(i,c1,j,c2);
							contraintes.add(c);
							e.add_contrainte(1);
							e2.add_contrainte(1);
						}
					}
				}
			}
		}	
	}
	
	private void MotUniqueContrainte() {
		for(int i=0,len=motsPot.size();i<len;i++) {
			if(motsPot.get(i).size()==1) {
				MotUnique m = new MotUnique(i);
				contraintes.add(m);
			}
		}
	}
	

	
	public GrillePlaces getGrilleP() {
		return grilleP;
	}
	public boolean isDead() {
		for(int i=0,len=motsPot.size();i<len;i++)
			if(motsPot.get(i).size()==0)
				return true;
		return false;
	}


	/**
	 * verifie la stabilité du mot 
	 * et si le croisement est realisable
	 * @return boolean
	 */
	private boolean propage() {
		int nb_mots,i,len;
		while(true){
			nb_mots = 0;
			for(i=0,len=contraintes.size(); i<len;i++){
				nb_mots+=contraintes.get(i).reduce(this);
			}
			if(isDead()==true) return false;
			if(nb_mots==0) return true;
		}
	}

	/**
	 * @return motsPot (liste de mots)
	 * */
	public List<Dictionnaire> getMotsPot() {
		return motsPot;
	}
	
	public Dictionnaire getMot(int index) {
		return motsPot.get(index);	
	}

	public List<IContrainte> getContraintes() {
		return contraintes;
	}



	@Override
	public String toString() {
		return grilleP.toString();
	}


	/**
	 * l'emplacement du mot i dans la grille
	 * @param i
	 * @param soluce
	 * @return une grille
	 */

	public GrillePotentiel fixer(int i, String string) {
		List<Dictionnaire> motlist = new ArrayList<Dictionnaire>();

		for( int j = 0 ; j < motsPot.size() ; j++ ){
			motlist.add( motsPot.get(j).copy() );
		}
		return new GrillePotentiel(grilleP.fixer(i,string),dicoComplet,motlist);
	}
}

