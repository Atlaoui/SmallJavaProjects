package pobj.motx.tme3.start;

import java.util.List;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme3.csp.ICSP;
import pobj.motx.tme3.csp.IVariable;

public class StratMinContraint implements IChoixVar{

	@Override
	public IVariable chooseVar(ICSP problem) {
		List<Emplacement>  listEmp=problem.getGp().getPlaces();
		int index_mot=0;	
		for(int i=1,len =listEmp.size();i<len ;i++)
			if(listEmp.get(i).getNb_contrainte()<listEmp.get(index_mot).getNb_contrainte())
				index_mot=i;
		
		return problem.getVars().get(index_mot);
	}

}
