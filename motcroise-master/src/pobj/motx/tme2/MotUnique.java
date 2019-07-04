package pobj.motx.tme2;

public class MotUnique implements IContrainte {
	private int m;
	
	public MotUnique(int m) {
		this.m=m;
	}
	
	@Override
	public int reduce(GrillePotentiel grille) {
		int cpt=0;
		//j'ai jamais fait plus salle xD
		for(int i=0,len=grille.getMotsPot().size();i<len;i++) {
			if(i!=m) {
				if(grille.getMot(m).size()>=1)
				cpt+=grille.getMot(i).removeUnMots(grille.getMot(m).get(0));
			}
				
		}
		return cpt;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass())return false;
		MotUnique other = (MotUnique) obj;
		if (m != other.m)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "m="+m;
	}

}
