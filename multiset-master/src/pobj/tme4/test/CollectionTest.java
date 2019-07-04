package pobj.tme4.test;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import pobj.tme4.HashMultiSet;

public class CollectionTest {

	@Test
	public void testImplementsCollection() {
		HashMultiSet<String> m = new HashMultiSet<>();
		assertTrue(m instanceof Collection);
	}
	
}
