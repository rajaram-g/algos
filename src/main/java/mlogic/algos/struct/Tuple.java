package mlogic.algos.struct;

/**
 * Key-Value pair
 *
 */
public class Tuple<Key extends Comparable<Key>, Value> implements Comparable<Tuple<Key, Value>> {
	public Key key;
	public Value value;

	public Tuple(Key key, Value value) {
		this.key = key;
		this.value = value;
	}

	public int compareTo(Tuple<Key, Value> o) {
		return this.key.compareTo(o.key);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Tuple<?, ?>) {
			return this.key.equals(((Tuple<Key, Value>) other).key);
		}

		return false;
	}

}