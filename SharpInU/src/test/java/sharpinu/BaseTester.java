package sharpinu;

import java.net.URL;

import javax.annotation.Resource;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sharpinu.spring.context.EmailConfig;
import com.sharpinu.spring.context.RepoConfig;
import com.sharpinu.spring.context.ServiceConfig;
import com.sharpinu.spring.context.SpringConfig;
import com.sharpinu.web.config.WebConfig;

/**
 *
 * @author Mark
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, SpringConfig.class, RepoConfig.class, ServiceConfig.class, EmailConfig.class})
public class BaseTester {
	private static String LOG_FILE = "log4j-test.xml";
	static {
		URL url = BaseTester.class.getResource(LOG_FILE);
		DOMConfigurator.configure(url);
	}

	@Resource
	private WebApplicationContext webApplicationContext;
	
	protected MockMvc mockMVC;
	
	@Before
	public void setUp() {
		mockMVC = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
}
