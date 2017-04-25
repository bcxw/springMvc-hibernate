package source;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试基类，其他测试类均继承此基类
 * @author houyong
 *
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class TestBase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
	   PasswordEncoder encoder = new StandardPasswordEncoder();
	   
	   Md5PasswordEncoder md5encoder = new Md5PasswordEncoder();
	   
	   System.out.println(md5encoder.encodePassword("123456", "admin"));
	   
	   
	   
	   
	   System.out.println(encoder.encode("123456"));
	   System.out.println(encoder.encode("123456"));
	   
	   System.out.println(encoder.matches("123456", "40ce3b0b95265b821991eb1e0620b0ba19ef23f8d054b59c6ba3bfe65392e0eb2e3471c50fdc0637"));
	   System.out.println(encoder.matches("123456", "8639255c3f91c52ba9d28f506bfe7e3618a3911130af1adde7397ffbe9d675234e12c178c75e4a0f"));
	}

}
