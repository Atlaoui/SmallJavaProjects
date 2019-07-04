package pobj.motx.tme3.start;

import java.util.List;

public class ScoreParDico implements Comparable<ScoreParDico> {
	private List<String> dico;
	private int score;
	
	/*
	 * Classe permetante de trier les dico en fonction d'un scores précis*/
	public ScoreParDico(int score , List<String> dico ) {
		this.score=score;
		this.dico=dico;
	}
	
	public int getScore() {
		return score;
	}
	
	public List<String> getDico(){
		return dico;
	}

	@Override
	public int compareTo(ScoreParDico s) {	
		if(score>s.score)
			return 1;
		if(score<s.score)
			return -1;
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass())return false;
		ScoreParDico other = (ScoreParDico) obj;
		if (dico == null) {
			if (other.dico != null)
				return false;
		} else if (!dico.equals(other.dico))return false;
		if (score != other.score)return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "S= "+score;
	}
	
}
