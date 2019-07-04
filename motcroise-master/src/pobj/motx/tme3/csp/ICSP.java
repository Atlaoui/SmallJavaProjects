package pobj.motx.tme3.csp;

import java.util.List;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme1.GrillePlaces;

public interface ICSP {
		
	public List<IVariable> getVars();
	
	public boolean isConsistent();
	
	public ICSP assign(IVariable vi, String val);

	public void setVar(List<IVariable> listDico);
	
	public GrillePlaces getGp();
}
