/**
 * 
 */
package source;

import java.util.Map;
import java.util.WeakHashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import repository.Config;
import service.ConfigService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author houyong
 *
 */
public class TestConfigServiceImpl extends TestBase {

	@Autowired
	private ConfigService configService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link service.impl.ConfigServiceImpl#getConfig()}.
	 */
	@Test
	public void testGetConfig() {
//		Config config = new Config();
//		config = config.findById(Config.class, "a");
//		ObjectMapper om = new ObjectMapper();
//		Map<String, Object> map =om.convertValue(config, new TypeReference<Map<String, Object>>(){});
//		System.out.println(map);
//		
//		
//		map=new WeakHashMap<String,Object>(){{put("success","asdfasdf");put("data",true);}};
//		
//		System.out.println(map);
//		
//		double a=1234567890123456789012345678090.11;
		
//		System.out.println("xixi");
//		int i=0;
//		while(i>0){
//			System.out.println("haha");
//		}
		
		
		System.out.println("".trim());

	}
	
	

}
