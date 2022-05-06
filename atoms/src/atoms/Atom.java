package atoms;

import java.util.HashSet;
import java.util.Set;

import logicalcollections.LogicalSet;

/**
 * Each instance of this class represents an atom in a molecule.
 * 
 * @invar | !getNeighbors().contains(this)
 * @invar The bidirectional association between Atom objects is consistent.
 *     | getNeighbors().stream().allMatch(n -> n.getNeighbors().contains(this))
 */
public class Atom {
	
	/**
	 * @invar | neighbors != null // Phase 1 invariant
	 * @invar | neighbors.stream().allMatch(n -> n != null && n != this && n.neighbors.contains(this)) // Phase 2 invariant
	 * @representationObject
	 * @peerObjects
	 */
	private HashSet<Atom> neighbors = new HashSet<Atom>();
	
	/**
	 * @post | result != null
	 * @creates | result
	 * @peerObjects
	 * @post | result.stream().allMatch(n -> n != null)
	 */
	public Set<Atom> getNeighbors() { return Set.copyOf(neighbors); }
	
	/**
	 * @post | getNeighbors().isEmpty()
	 */
	public Atom() {}
	
	/**
	 * @pre | atom != null
	 * @pre | atom != this
	 * @mutates_properties | this.getNeighbors(), atom.getNeighbors()
	 * @post | getNeighbors().equals(LogicalSet.plus(old(getNeighbors()), atom))
	 * @post | atom.getNeighbors().equals(LogicalSet.plus(old(atom.getNeighbors()), this))
	 */
	public void addBond(Atom atom) {
		neighbors.add(atom);
		atom.neighbors.add(this);
	}
	
	/**
	 * @pre | atom != null
	 * @pre | getNeighbors().contains(atom)
	 * @mutates_properties | getNeighbors(), atom.getNeighbors()
	 * @post | getNeighbors().equals(LogicalSet.minus(old(getNeighbors()), atom))
	 * @post | atom.getNeighbors().equals(LogicalSet.minus(old(atom.getNeighbors()), this))
	 */
	public void removeBond(Atom atom) {
		neighbors.remove(atom);
		atom.neighbors.remove(this);
	}

}
