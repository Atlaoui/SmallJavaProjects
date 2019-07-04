package pobj.motx.tme3.start;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pobj.motx.tme3.csp.ICSP;
import pobj.motx.tme3.csp.IVariable;

public class StratFrequence implements IChoixValeur {

	//avancez parcontre il manque le réordonnement
	@Override
	public List<String> order_Values(ICSP problem, IVariable v) {
		int [] ScrablePts= {1,3,3,2,1,4,2,4,1,8,10,1,2,1,1,3,8,1,1,1,1,4,10,10,10,10};
		int [] nbLettres = new int[26];
		List<ScoreParDico>  scroParMot = new ArrayList<>();
		
		int i,len_mot,scores_total;
		for(i=0;i<26;i++)
			nbLettres[i]=0;
		
		
		for(IVariable mots : problem.getVars()) {
			scores_total=0;
			for(String mot : mots.getDomain()) {
				for(i=0,len_mot=mot.length();i<len_mot;i++)
					 nbLettres[charToInt(mot.charAt(i))]+=1*ScrablePts[charToInt(mot.charAt(i))];
			}
			
			for(i=0;i<26;i++)
				scores_total+=nbLettres[i];
			
			scroParMot.add(new ScoreParDico(scores_total,mots.getDomain()));
		}
		
		Collections.sort(scroParMot);
		int index_mot=0;
		for(IVariable mots : problem.getVars()) {
			mots.setDomain(scroParMot.get(index_mot).getDico());
			index_mot++;
		}
		
		return null;
	}
	
	
	/**
	 * donne la valeur d'un char en int - ca position 'a'=0 'b'=1*/
	public static int charToInt(char valeur) {
	    return (int)valeur - (int)'a';
	}
}
