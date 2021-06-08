package com.bjtu.questionPlatform;

import com.bjtu.questionPlatform.utils.iterator.JwtTokenFilter;
import com.bjtu.questionPlatform.utils.iterator.RewriteAccessDenyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @program: framework
 * @description: security config
 * @author: CodingLiOOT
 * @create: 2021-03-18 20:32
 * @version: 1.0
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtTokenFilter jwtAuthenticationTokenFilter;

    //自定义无权限访问拦截器
    @Autowired
    private RewriteAccessDenyFilter rewriteAccessDenyFilter;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class) // 添加token过滤器
                .exceptionHandling().authenticationEntryPoint(rewriteAccessDenyFilter)// 凭证无效时的处理
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/login").permitAll()
                .antMatchers("/api/user/sendVerifyCode").permitAll()
                .antMatchers("/api/user/register").permitAll()
                .antMatchers("/api/test/**").permitAll()

                .antMatchers("/api/judgement/**").permitAll()
                .antMatchers("/api/Expert/**").permitAll()


                .antMatchers("/error").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()  // 允许匿名访问
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                // 不使用session，此策略 使得 每次请求都要自行处理权限问题（往SecurityContextHolder.context中添加和查询Authentication）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.addAllowedOriginPattern("*");
        cors.addAllowedHeader("*");
        cors.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", cors);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
