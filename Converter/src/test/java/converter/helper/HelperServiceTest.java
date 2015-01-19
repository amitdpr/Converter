package converter.helper;

import static org.junit.Assert.assertEquals;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

import converter.config.Config;
import converter.core.SortedProperties;
import converter.util.UtilityServiceTest;

/**
 * This is Unit test class for HelperService.
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 19, 2015
 */
public class HelperServiceTest {

	private static final Logger logger = LoggerService
	.getLogger(HelperServiceTest.class);

	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.log(Level.INFO,"Testing "+UtilityServiceTest.class.getName() );
		
		// Initilize configuartions file
		Config.init();

	}

	/**
	 * Test method for
	 * {@link converter.helper.HelperService#getPropertyMap(java.lang.String)}.
	 */
	@Test
	public void testGetPropertyMap() {
		SortedProperties prop = HelperService
				.getPropertyMap(Config.RomanRegressionFile);
		assertEquals(prop.getProperty("5"), "V");
	}

	/**
	 * Test method for
	 * {@link converter.helper.HelperService#getResourceBundle(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetResourceBundle() {
		ResourceBundle prop = HelperService
				.getResourceBundle(Config.RomanRegressionFile);
		assertEquals(prop.getString("5"), "V");
	}
}
