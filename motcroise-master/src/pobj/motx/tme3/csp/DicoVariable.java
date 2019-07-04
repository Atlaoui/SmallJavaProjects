package pobj.motx.tme3.csp;

import java.util.List;

import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;

public class DicoVariable implements IVariable {
	private int index;
	private GrillePotentiel gp;
	private List<String> domain;
	public DicoVariable(int index, GrillePotentiel gp) {
		this.index=index;
		this.gp=gp;
		Dictionnaire d=gp.getMot(index);
		domain=d.getMots();
	}

	@Override
	public List<String> getDomain() {
		return domain;
	}
	
	@Override
	public void setDomain( List<String>  domain){
		this.domain=domain;
	}
	
	@Override
	public String toString() {
		return gp.getGrilleP().getGrille().toString();
	}

	public int getIndex() {
		return index;
	}
	
	public int getsize() {
		return domain.size();
	}
	

	@Override
	public int getContrainteSize() {
		return gp.getContraintes().size();
	}
}
