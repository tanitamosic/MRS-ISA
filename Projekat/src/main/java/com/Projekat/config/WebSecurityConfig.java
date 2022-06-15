package com.Projekat.config;

import com.Projekat.security.RestAuthenticationEntryPoint;
import com.Projekat.security.TokenAuthenticationFilter;
import com.Projekat.service.CustomAccountDetailsService;
import com.Projekat.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
// Ukljucivanje podrske za anotacije "@Pre*" i "@Post*" koje ce aktivirati autorizacione provere za svaki pristup metodi
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

	@Autowired
	private CustomAccountDetailsService customUserDetailsService;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Autowired
	private TokenUtils tokenUtils;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, customUserDetailsService), BasicAuthenticationFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

				.authorizeRequests().antMatchers("/auth/*").permitAll()
				.antMatchers("/index").permitAll() // pocetna strana sa svim ponudama
				.antMatchers("/admin").hasRole("ADMIN")
//				.antMatchers("/auth/register").permitAll()
				.antMatchers("/api/cottages/**").permitAll()
				.antMatchers("/api/boats/**").permitAll()
				.antMatchers("/api/adventures/**").permitAll()
				.antMatchers("/api/search/**").permitAll()
				.anyRequest().authenticated().and()
				.cors();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		 web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
		 web.ignoring().antMatchers(HttpMethod.POST, "/auth/register/*");
		 web.ignoring().antMatchers(HttpMethod.POST, "/api/search/cottages");
		 web.ignoring().antMatchers(HttpMethod.POST, "/api/search/adventures");
		 web.ignoring().antMatchers(HttpMethod.POST, "/api/search/boats");
		 web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
	}

}
