package pobj.motx.tme3.start;

import pobj.motx.tme3.csp.ICSP;
import pobj.motx.tme3.csp.IVariable;

public class StratFirst implements IChoixVar {

	@Override
	public IVariable chooseVar(ICSP problem) {
		return problem.getVars().get(0);
	}

}
