package at.ac.tuwien.infosys.www.pixy.analysis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A generic repository of recyclable objects.
 *
 * Example usage: You have some *immutable* class that contains
 * its own static repository of objects that have been instantiated
 * from it so far. When a new object is requested from this class,
 * it first checks its repository to see if there already is such
 * an object. In this case, a reference to the already existing
 * object is returned. Otherwise, a new object is created, added to
 * the repository, and returned.
 *
 * Advantages: This saves memory (no redundant objects) and allows
 * users of this class to perform quick comparisons with "==".
 *
 * Guide for how to design such a class:
 * - implement the Recyclable interface
 * - insert a private static GenericRepository<that class>
 * - make the constructor private
 * - use a static factory method instead
 * - make sure that the private constructor is ONLY called by factory methods (and perhaps by static field initializers),
 * and that these methods perform recycling by means of the repository
 * - do not override its default equals and hashCode (would destroy the advantages mentioned above)
 *
 * @author Nenad Jovanovic <enji@seclab.tuwien.ac.at>
 */
public class GenericRepository<E extends Recyclable> {
    // structure hash code (Integer) -> List of Recyclable's
    private Map<Integer, List<E>> repos;

    public GenericRepository() {
        this.repos = new HashMap<>();
    }

    // if the given element equals one from the repository: the
    // repository element is returned; else: the element is
    // entered into the repository and returned
    public E recycle(E recycleMe) {

        if (recycleMe == null) {
            return recycleMe;
        }

        Integer structureHashCode = recycleMe.structureHashCode();
        List<E> candidates = this.repos.get(structureHashCode);
        if (candidates == null) {
            // no candidates list: add recycleMe to the repos and return it
            List<E> recycleMeList = new LinkedList<>();
            recycleMeList.add(recycleMe);
            this.repos.put(structureHashCode, recycleMeList);
            return recycleMe;
        }

        // search the candidates list
        for (E candidate : candidates) {
            if (candidate.structureEquals(recycleMe)) {
                // recycling!
                return candidate;
            }
        }

        // no candidate matches: add recycleMe to this list and return it
        candidates.add(recycleMe);
        return recycleMe;
    }
}