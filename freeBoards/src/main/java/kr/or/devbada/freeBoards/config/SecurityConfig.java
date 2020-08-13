package kr.or.devbada.freeBoards.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import kr.or.devbada.freeBoards.handler.CustomLoginFailureHandler;
import kr.or.devbada.freeBoards.handler.CustomLoginSuccessHandler;
import kr.or.devbada.freeBoards.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new CustomLoginSuccessHandler("/main.do");
	}
	
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new CustomLoginFailureHandler();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.eraseCredentials(false).userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/js/**", "/css/**", "/img/**", "/lib/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().httpBasic();

		http.authorizeRequests()
			.antMatchers("/login/**").permitAll()
			.antMatchers("/join/**").permitAll()
			.antMatchers("/system/**").hasAnyAuthority("ADMIN")
			.antMatchers("/**").authenticated();
		
		http.formLogin().loginPage("/login").permitAll() // 로그인 안했으면 무조건 로그인 페이지로 이동
			.usernameParameter("memId")
			.passwordParameter("memPwd")
			.loginProcessingUrl("/login/connect")
			.defaultSuccessUrl("/main.do")
			//.failureHandler(failureHandler()) // 로그인 실패하면 로그인 실패 제어 컨트롤러로 이동
			//.failureUrl("/loginFailure") // 로그인 실패하면 로그인 페이지로
			;

		http.logout().logoutUrl("/logout")
			.logoutSuccessUrl("/login").invalidateHttpSession(true).deleteCookies("JSESSIONID") // 로그아웃하면 로그인 페이지로
			.clearAuthentication(true);
		
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin()
			.successHandler(successHandler())
			.failureHandler(failureHandler())
		;

		http.exceptionHandling().accessDeniedPage("/error/403");
		http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/login");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
