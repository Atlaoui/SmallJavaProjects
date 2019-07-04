package pobj.tme4.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.tme4.HashMultiSet;
import pobj.tme4.MultiSet;

public class HashMultiSetTest {

	@Test
	public void testAdd() {
		MultiSet<String> m = new HashMultiSet<>();
		m.add("a");
		m.add("a",5);
		assertEquals(6, m.count("a"));
	}

}
