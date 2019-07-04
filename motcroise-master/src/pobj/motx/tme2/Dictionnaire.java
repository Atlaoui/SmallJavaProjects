package pobj.motx.tme2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Un ensemble de mots.
 *
 */
public class Dictionnaire {

	// stockage des mots
	private List<String> mots = new ArrayList<>();

	private EnsembleLettre [] cache=null; 


	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}

	/**
	 * Accès au i-eme mot du dictionnaire.
	 * @param i l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}

	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique de ce Dictionnaire
	 */
	public Dictionnaire copy () {
		Dictionnaire copy = new Dictionnaire();
		EnsembleLettre [] nouveau_cache=null;
		if(cache!=null) {
			 nouveau_cache=new EnsembleLettre[cache.length];
			for(int i=0,len=cache.length;i<len;i++) {
				if(cache[i]!=null)
					nouveau_cache[i]=cache[i].clone();
			}
		}
		copy.mots.addAll(mots);
		copy.cache=nouveau_cache;
		return copy;
	}

	/**
	 * Retire les mots qui ne font pas exactement "len" caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
	 * @param len la longueur voulue 
	 * @return le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		return cpt;
	}

	/**
	 * Retire les mots qui n'ont pas "c" comme caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
	 * @param c le caractere
	 * @param i sa position 
	 * @return le nombre de mots supprimés
	 */
	public int filtreParLettre(char c,int i) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if(mot.length()>i) {
				if(mot.charAt(i)==c)  
					cible.add(mot);
				else
					cpt++;
			}
			else
				cpt++;

		}
		mots = cible;
		if(cpt>0)
			cache=null;
		return cpt;
	}

	/**
	 * Filtre les mots du dictionnaire par un ensemble de lettre sur l'indice index
	 * parcourir les mots et supprimer ceux qui n'ont pas à l'indice index
	 * une lettre dans l'ensembleLettre pot
	 * @param pot ensemble de lettres
	 * @param index indice dans un mot
	 * @return nombre de mot supprimes 
	 * */
	public int filtreParEnsemble(EnsembleLettre pot, int index) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if(pot.contains(mot.charAt(index))) {
				cible.add(mot);
			}
			else
				cpt++;
		}
		mots = cible;
		if(cpt>0)
			cache=null;
		return cpt;

	}

	/**
	 * donne l'ensemble des lettre possible a une position donnee
	 * @param c indice dans un string
	 * @return liste de character 
	 * */

	public EnsembleLettre getMotParLettre(int c) {
		EnsembleLettre e = new EnsembleLettre();
		for(String s : mots) {
			e.add(s.charAt(c));
		}
		return e;
	}


	//@TME3

	public EnsembleLettre charAt(int index) {
		if(mots.size()==0)
			return null;
		if(cache==null) {
			cache=new EnsembleLettre[mots.get(0).length()];
			for(EnsembleLettre e : cache)
				e=null;
		}
		
		if(cache[index]==null) 
			cache[index]=getMotParLettre(index);
		return cache[index];

	}

	public int removeUnMots(String delMot) {
		for(int i=0,len=mots.size();i<len;i++)
			if(mots.get(i).equals(delMot)) {
				mots.remove(i);
				return 1;
			}	
		return 0;
	}
	
	

	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}


	//a completer
	@Override
	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass())return false;
		Dictionnaire other = (Dictionnaire) obj;
		if (mots == null) {
			if (other.mots != null)
				return false;
		} else {
			if (mots.size()!=other.mots.size())
				return false;
			else
				for(int i=0,len=mots.size();i<len;i++)
					if(!mots.get(i).equals(other.mots.get(i)))
						return false;
		}	
		return true;
	}

	public static Dictionnaire loadDictionnaire(String string) {
		Dictionnaire D= new Dictionnaire();
		try (BufferedReader br = new BufferedReader(new FileReader(string))) {
			for (String line = br.readLine() ; line != null ; line = br.readLine() ) {
				D.add(line);
			}
		} catch (IOException e) {
			System.err.println("Erreur d'accer fichier");
			e.printStackTrace();
		}

		return D;
	}
	

	public List<String> getMots(){
		return mots;
	}
	
}
