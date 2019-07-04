package pobj.motx.tme3.csp;

import java.util.List;

public interface IVariable {
	List<String> getDomain();

	void setDomain(List<String> dico);

	int getContrainteSize();
	
}
