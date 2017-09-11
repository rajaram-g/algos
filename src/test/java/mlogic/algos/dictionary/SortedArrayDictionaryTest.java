package mlogic.algos.dictionary;

/**
 * @author Rajaram G
 *
 */
public class SortedArrayDictionaryTest extends DictionaryTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.dictionary.DictionaryTest#createDictionary()
	 */
	@Override
	public Dictionary<String, String> createDictionary() {

		return new SortedArrayDictionary();
	}

}
