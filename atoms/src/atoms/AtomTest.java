package atoms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class AtomTest {

	@Test
	void test() {
		Atom a1 = new Atom();
		assertEquals(Set.of(), a1.getNeighbors());
		
		Atom a2 = new Atom();
		a1.addBond(a2);
		assertEquals(Set.of(a2), a1.getNeighbors());
		assertEquals(Set.of(a1), a2.getNeighbors());
		
		Atom a3 = new Atom();
		a2.addBond(a3);
		assertEquals(Set.of(a2), a1.getNeighbors());
		assertEquals(Set.of(a1, a3), a2.getNeighbors());
		assertEquals(Set.of(a2), a3.getNeighbors());
		
		a2.removeBond(a1);
		assertEquals(Set.of(), a1.getNeighbors());
		assertEquals(Set.of(a3), a2.getNeighbors());
		assertEquals(Set.of(a2), a3.getNeighbors());
	}

}
