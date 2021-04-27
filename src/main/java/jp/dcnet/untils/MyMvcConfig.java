package jp.dcnet.untils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new LoginHandLerInterCeptor())
				.addPathPatterns("/index/center","/index/#/","/index/settlementorder","/index/userorderpage")
				.excludePathPatterns("../index/css/**", "../index/img/**", "../index/js/**", "../loginAndReg/css/**","../center/css/**",
						IndexUrl.INDEX_VIEW, IndexUrl.LOGIN_VIEW, IndexUrl.REGISTERED_VIEW);

	}
}
