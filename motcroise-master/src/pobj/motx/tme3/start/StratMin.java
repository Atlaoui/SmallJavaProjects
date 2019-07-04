package pobj.motx.tme3.start;

import pobj.motx.tme3.csp.ICSP;
import pobj.motx.tme3.csp.IVariable;

public class StratMin implements IChoixVar{

	@Override
	public IVariable chooseVar(ICSP problem) {
		IVariable ret=problem.getVars().get(0);
		for(IVariable v : problem.getVars())
			if(v.getDomain().size()<ret.getDomain().size())
				ret=v;
		return ret;
	}

}
