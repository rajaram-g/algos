package mlogic.algos.analysis;

import javafx.beans.property.SimpleStringProperty;

/**
 * Data structure to store order of growth classification by algorithm and
 * operation.
 * 
 * @author Rajaram G
 *
 */
public class BigORecord {

	/**
	 * Name of the algorithm
	 */
	private SimpleStringProperty algo;

	/**
	 * @return name of the algorithm
	 */
	public String getAlgo() {
		return algo.get();
	}

	/**
	 * Sets the name of the algorithm
	 * 
	 * @param algo
	 *            name of the algorithm
	 */
	public void setAlgo(String algo) {
		this.algo.set(algo);
	}

	/**
	 * Growth classification
	 */
	private SimpleStringProperty clazz;

	/**
	 * @return growth classification
	 */
	public String getClazz() {
		return clazz.get();
	}

	/**
	 * Sets the growth classification
	 * 
	 * @param clazz
	 *            growth classification
	 */
	public void setClazz(String clazz) {
		this.clazz.set(clazz);
	}

	/**
	 * Constructor for the Big O Record
	 * 
	 * @param algo
	 *            Name of the algorithm
	 * @param clazz
	 *            Growth classification
	 */
	BigORecord(String algo, String clazz) {
		this.algo = new SimpleStringProperty(algo);
		this.clazz = new SimpleStringProperty(clazz);
	}

}