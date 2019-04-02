package saadow.office_management_app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import saadow.office_management_app.filter.ThreadContextFilter;

@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean<ThreadContextFilter> loggingFilter() {
		FilterRegistrationBean<ThreadContextFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new ThreadContextFilter());
		registrationBean.addUrlPatterns("/*");

		return registrationBean;
	}
}
