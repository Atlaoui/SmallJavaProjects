package pobj.motx.tme3.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme1.GrillePlaces;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.ICSP;
import pobj.motx.tme3.csp.MotX;
import pobj.motx.tme3.start.StratFrequence;
import pobj.motx.tme3.start.StratMin;

public class TestSolveurDeGrille {

	@Test
	public void testEasy() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/easy.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());

		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);

		assertTrue(!gp.isDead());

		ICSP mot=new MotX(gp);

		CSPSolver cp= new CSPSolver();
		//cp.solve(mot);
		cp.setChoixVarStrat(new StratMin());

		System.out.println("Succès test GrillePotentiel : easy.");

	}

	
	@Test
	public void testMedium() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/medium.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);

		assertTrue(!gp.isDead());
		
		ICSP mot=new MotX(gp);

		CSPSolver cp= new CSPSolver();
		cp.solve(mot);

		
		System.out.println("Succès test GrillePotentiel : medium.");

	}
	
	
	@Test
	public void testEnonce() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/enonce.grl");

		assertEquals(16, gr.nbCol());
		assertEquals(12, gr.nbLig());

		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);

		assertTrue(!gp.isDead());
		
		
		ICSP mot=new MotX(gp);

		CSPSolver cp= new CSPSolver();
		//cp.solve(mot);
		cp.setChoixVarStrat(new StratMin());
		System.out.println("Succès test GrillePotentiel : mediom enonce.");

	}


	@Test
	public void testLarge() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large.grl");

		assertEquals(20, gr.nbCol());
		assertEquals(20, gr.nbLig());


		//System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);
		
		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		assertTrue(!gp.isDead());

		System.out.println("nbContrainte = "+gp.getContraintes().size());
		MotX mot=new MotX(gp);

		System.out.println(""+StratFrequence.charToInt('z'));
		CSPSolver cp= new CSPSolver();
		StratFrequence strat = new StratFrequence();
		strat.order_Values(mot, null);
		//cp.setChoixVarStrat(new StratMin());
		//cp.solve(mot);
		cp.setChoixVarStrat(new StratMin());
		System.out.println("Succès test GrillePotentiel : large.");
	}
}
