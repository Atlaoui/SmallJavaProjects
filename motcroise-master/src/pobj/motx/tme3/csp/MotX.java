package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import pobj.motx.tme1.GrillePlaces;
import pobj.motx.tme2.GrillePotentiel;

public class MotX implements ICSP {
	
	 private List<IVariable> vars;
	 private GrillePotentiel gp;

	public MotX(GrillePotentiel gp ) {
		vars=new ArrayList<>();
		this.gp=gp;
		
		for(int i =0 ,len= gp.getGrilleP().getPlaces().size();i<len;i++)
			if(gp.getGrilleP().getPlaces().get(i).hasCaseVide())
				vars.add(new DicoVariable(i,gp));
	}
	
	@Override
	public List<IVariable> getVars() {
		return vars;
	}

	@Override
	public boolean isConsistent() {
		return !gp.isDead();
	}

	@Override
	public ICSP assign(IVariable vi, String val) {
		MotX retour=null;
		if(vi!=null && (vi instanceof DicoVariable)) {	
			GrillePotentiel nouvelGp=null;
			DicoVariable dvi = (DicoVariable) vi;
			nouvelGp=gp.fixer(dvi.getIndex(),val);
			retour=new MotX(nouvelGp);
		}
		return retour;
	}
	
	@Override
	public void setVar(List<IVariable> nouvel) {
		vars=nouvel;
	}
	
	@Override
	public GrillePlaces getGp() {
		return gp.getGrilleP();
	}

}
