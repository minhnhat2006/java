package com.sharpinu.spring.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import com.sharpinu.email.IMailSender;
import com.sharpinu.email.IMailTemplateBuilder;
import com.sharpinu.email.MailSender;
import com.sharpinu.email.MailTemplateBuilder;

@Configuration
public class EmailConfig {

	/**
	 * <bean id="freemarkerConfig" class="org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean">
	 *		<property name="templateLoaderPath" value="classpath:com/sharpinu/email/templates"/>
	 *		<property name="preferFileSystemAccess" value="false" />
	 *	</bean>
	 **/
	@Bean
	public FreeMarkerConfigurationFactoryBean freemarkerMailConfig() {
		FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
		freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("classpath:/com/sharpinu/email/templates");
		freeMarkerConfigurationFactoryBean.setPreferFileSystemAccess(false);
		return freeMarkerConfigurationFactoryBean;
	}

	/**
	 *	<bean id="mailSender" class="ccom.sharpinu.email.MailSender" init-method="initAllEmailSystems"/>
	 */
	@Bean(initMethod="initAllEmailSystems")
	public IMailSender mailSender() {
		IMailSender mailSender = new MailSender();
		return mailSender;
	}

	/**
	 *	<bean id="mailTemplateBuilder" class="com.sharpinu.email.MailTemplateBuilder" />
	 **/
	@Bean
	public IMailTemplateBuilder mailTemplateBuilder() {
		MailTemplateBuilder mailTemplateBuilder = new MailTemplateBuilder();
		return mailTemplateBuilder;
	}
}
