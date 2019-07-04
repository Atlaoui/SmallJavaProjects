package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import pobj.motx.tme2.CroixContrainte;
import pobj.motx.tme2.IContrainte;
/**
 * @author Nail
 * Classe représentant les emplacement d'un mot dans une case
 * */
public class Emplacement {
	/**
	 * liste des cases représantant un mot dans la grille
	 * */
	private List<Case> lettres;
	private int nb_contrainte;
	/**
	 * Construit un nouveau emplacement
	 * */
	public Emplacement () {
		lettres=new ArrayList<>();
		nb_contrainte=0;
	}
	
	/**
	 * Construit un emplacement avec une liste prédefini
	 * @param List<case> 
	 * */
	public Emplacement (List<Case> liste) {
		lettres=liste;
	}

	/**
	 * retourn la taille de la liste
	 * @return une taille int*/
	public int size() {
		return lettres.size();
	}
	
	/**
	 * Ajoute a une case à la liste
	 * @void ajoute une case */
	public void addCase(Case c) {
		lettres.add(c);
	}
	
	/**
	 * @return la case d'index index
	 * @param index
	 * */
	public Case getCase(int index) {
		return lettres.get(index);
	}
	/***
	 * vérifie si le mot a une case vide
	 * @return true or false 
	 */
	public boolean hasCaseVide() {
		for( Case c : lettres) {
			if(c.isVide())
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String S="";
		for(int i=0,len = lettres.size();i<len;i++)
			S+=lettres.get(i).toString();
		S+="\n";
		return S;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass())return false;
		Emplacement other = (Emplacement) obj;
		if (lettres == null) {
			if (other.lettres != null)return false;
		} else if (lettres.size()!=other.lettres.size())return false;
		for(int i=0,len = lettres.size();i<len;i++)
			if(!lettres.get(i).equals(other.lettres.get(i)))
				return false;
		return true;
	}

	public int getNb_contrainte() {
		return nb_contrainte;
	}

	public void add_contrainte(int nb_contrainte) {
		this.nb_contrainte += nb_contrainte;
	}

}
