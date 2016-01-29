package sharpinu.service;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import sharpinu.BaseTester;

import com.sharpinu.constant.MailTemplateConstants;
import com.sharpinu.email.IMailSender;
import com.sharpinu.util.Lib;

public class MailTester extends BaseTester {

	@Autowired@Qualifier("mailSender")
	IMailSender sender;

	@Test
	public void sendMailTest() {
		Map templateVars = Lib.buildParamsMap(new String[] {"userName", "newPassword"}, new String[]{"username1", "password"});
		//sender.sendUsingFreeMarkerTemplate("sharpinu.tester@gmail.com", "Test FreeMarker Template", MailTemplateConstants.PASSWORD_CHANGED, templateVars);
	}
}
