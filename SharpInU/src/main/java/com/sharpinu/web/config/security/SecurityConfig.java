package com.sharpinu.web.config.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String REMEMBER_ME_KEY = UUID.randomUUID().toString();
	private static final String REMEMBER_ME_PARAMETER = "rememberMe";

	@Autowired
	@Qualifier("sharpInUUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	@Qualifier("sharpInUAuthenticationSuccessHandler")
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	@Qualifier("sharpInULogoutSuccessHandler")
	private LogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	@Qualifier("rememberMeServices")
	private RememberMeServices rememberMeServices;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**"); // #3
	}

	@Bean(name = "sharpInUAuthenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * Customize REMEMBER_ME_PARAMETER. Issue: Default value is "remember-me".
	 * We cannot bind this parameter to form Resolve: Change remember me
	 * parameter to "rememberMe"
	 * 
	 * @return
	 */
	@Bean
	RememberMeServices rememberMeServices() {
		TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService);
		rememberMeServices.setParameter(REMEMBER_ME_PARAMETER);
		return rememberMeServices;
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.authorizeRequests()
				// .antMatchers("/*", "/*/**", "/assets/**", "/sec/sign_up.in",
				// "/sec/sign_in.in").permitAll()
				.antMatchers("/assets/**", "/sec/sign_up.in", "/sec/sign_in.in", "/dwr/*", "/sec/forgot_password.in", "/sec/reset_password**", "/sec/new_password.in").permitAll()
				.antMatchers("/user*", "/sec/**", "/career_form.in", "/resume_form.in", "/company_form.in","/ticket/*/single_ticket.in","/ticket/*/user_tickets.in").hasAuthority("ROLE_USER").antMatchers("/admin*", "/admin/**").hasAuthority("ROLE_ADMIN")
				.and().formLogin().loginPage("/sec/sign_in.in").usernameParameter("email").passwordParameter("password").loginProcessingUrl("/sec/security_check.in")
				.failureHandler(new SharpInUAuthenticationFailureHandler()).successHandler(authenticationSuccessHandler).permitAll();

		http.logout().logoutUrl("/sec/sign_out.in").logoutSuccessHandler(logoutSuccessHandler);

		http.rememberMe().key(REMEMBER_ME_KEY).rememberMeServices(rememberMeServices);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
