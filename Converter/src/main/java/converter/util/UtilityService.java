package converter.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;

/**
 * This is utility class to provide generic/common methods.
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
 */
public class UtilityService {

	/**
	 * This method create new string with repeated appearance.
	 * 
	 * @param string
	 *            : String to be repeated
	 * @param times
	 *            : How many times to repeat
	 * @return: String with times appearance of string
	 */
	public static String fillStr(String string, int times) {
		if(string==null) return string;
		String str = new String("");
		for (int i = 0; i < times; i++) {
			str += string;
		}
		return str;
	}

	/**
	 * This method creates new Sorted Set of Keys in descending order from the
	 * given Hash Map.
	 * 
	 * @param multiUnits
	 *            : HashMap with Key value pair
	 * @return: TreeSet of Keys in descending sorted order.
	 */
	public static TreeSet<Integer> sortDesending(Map<Integer, String> multiUnits) {
		// CReate TreeMap for Sort the Key in decending order. To process devios
		// by height number
		TreeSet<Integer> unitKeys = new TreeSet<Integer>(
				new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return o2.compareTo(o1);
					}
				});

		// Strore the Units
		unitKeys.addAll(multiUnits.keySet());
		return unitKeys;
	}

}
