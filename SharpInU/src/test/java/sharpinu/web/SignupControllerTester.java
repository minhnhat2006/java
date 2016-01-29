package sharpinu.web;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import sharpinu.BaseTester;

/**
 * Test actions in SignupController
 * 
 * @author Mark
 *
 */
public class SignupControllerTester extends BaseTester {
	@Test
	public void testSingupRequestPage () {
		RequestBuilder requestBuilder = new RequestBuilder() {
			
			public MockHttpServletRequest buildRequest(ServletContext servletContext) {
				MockHttpServletRequest request = new MockHttpServletRequest(servletContext, "GET", "/sec/sign_up.in");
				request.addHeader("Accept", "text/html");
				return request;
			}
		};
		try {
			ResultActions resultAction = mockMVC.perform(requestBuilder).andDo(print());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

