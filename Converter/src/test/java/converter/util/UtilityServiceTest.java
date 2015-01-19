package converter.util;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

import converter.helper.LoggerService;

/**
 * @author : amitkumarsingapore@gmail.com Date: Jan 19, 2015
 */
public class UtilityServiceTest {

	private static final Logger logger = LoggerService
			.getLogger(UtilityServiceTest.class);

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.log(Level.INFO, "Testing " + UtilityServiceTest.class.getName());

	}

	/**
	 * Test method for
	 * {@link converter.util.UtilityService#fillStr(java.lang.String, int)}.
	 */
	@Test
	public void testFillStr() {
		assertEquals(UtilityService.fillStr("X", 3), "XXX");
		assertEquals(UtilityService.fillStr("", 3), "");
		assertEquals(UtilityService.fillStr(null, 3), null);
	}

	/**
	 * Test method for
	 * {@link converter.util.UtilityService#sortDesending(java.util.Map)}.
	 */
	@Test
	public void testSortDesending() {
		Map<Integer, String> multiUnits = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(1, "I");
				put(5, "V");
			}
		};

		TreeSet<Integer> keys = UtilityService.sortDesending(multiUnits);
		assertEquals(5, keys.first().intValue());
		assertEquals(1, keys.last().intValue());

	}

}
