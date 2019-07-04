package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

public class EnsembleLettre {
	private List<Character>  lettres = new ArrayList<>();
	private static int cpt=1;
	private final int id=cpt++;


	/**
	 * verifie si une lettre est contenue dans la liste
	 * @param c lettre
	 * @return boolean
	 */
	public boolean contains(char c) {
		return lettres.contains(c);		
	}

	/**
	 * Ajoute une lettre c a la liste
	 * @param c
	 */
	public void add(char c) {
		for(int i=0 ,len =lettres.size();i<len;i++)
			if(lettres.get(i).charValue()==c)
				return;
		lettres.add(new Character(c));
	}

	/**
	 * @param i indice de lettre
	 * @return un caractere
	 * */
	public Character getLettres(int i) {
		return lettres.get(i);
	}

	/**
	 * getter pour lettres
	 * @return
	 */
	public List<Character> getListC(){
		return lettres;
	}

	/**
	 * @return la taille de l'ensemble de lettres
	 * */
	public int size() {
		return lettres.size();
	}
	/**
	 * effectue l'intersection entre deux ensembles de lettres
	 * @param b ensemble de lettre
	 * @return
	 */
	public static EnsembleLettre intersection(EnsembleLettre e1,EnsembleLettre e2) {
		if(e1!=null && e2!=null) {
			EnsembleLettre res = e1.clone();

			res.lettres.retainAll(e2.lettres);
			return res;
		}
		return null;
	}
	/**
	 * retourne une copie de ensembleLettre
	 * */
	@Override
	public EnsembleLettre clone() {
		EnsembleLettre e1 = new EnsembleLettre();
		for(Character c : lettres) {
			e1.add(new Character(c));
		}
		return e1;
	}

	@Override
	public String toString() {
		StringBuilder S=new StringBuilder();
		S.append("Ensemble "+id+" \n");
		for(int i=0 , len = lettres.size() ;i<len ;i++)
			S.append(" "+lettres.get(i));
		S.append("\n");
		return S.toString();
	}



	/*	public static EnsembleLettre intersection(EnsembleLettre e1,EnsembleLettre e2) {
		Collections.sort(e1.lettres);
		Collections.sort(e2.lettres);
		EnsembleLettre res = new EnsembleLettre();
	    int i = 0, j = 0; 
	    while (i != e1.size() && j != e2.size()) { 
	        if (e1.getLettres(i) < e2.getLettres(j)) {
	            i ++;
	        } else if (e1.getLettres(i) > e2.getLettres(j)) { 
	            j ++;
	        } else { 
	            res.add(e1.getLettres(i)); 
	            i ++;  j ++;
	        }
	    }
		return res;
	}
	 */
}
