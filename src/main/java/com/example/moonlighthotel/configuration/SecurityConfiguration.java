package com.example.moonlighthotel.configuration;

import com.example.moonlighthotel.exeptions.CustomHttp403ForbiddenEntryPoint;
import com.example.moonlighthotel.filter.CustomAccessDeniedHandler;
import com.example.moonlighthotel.service.impl.UserServiceImpl;
import com.example.moonlighthotel.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.example.moonlighthotel.constant.SecurityConstant.PROTECTED_URLS;
import static com.example.moonlighthotel.constant.SecurityConstant.PUBLIC_URLS;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private final CustomHttp403ForbiddenEntryPoint authenticationEntryPint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserServiceImpl userService;

    @Autowired
    public SecurityConfiguration(CustomHttp403ForbiddenEntryPoint authenticationEntryPint, CustomAccessDeniedHandler accessDeniedHandler,
                                 JwtTokenUtil jwtTokenUtil, UserServiceImpl userService) {
        this.authenticationEntryPint = authenticationEntryPint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .antMatchers(PUBLIC_URLS).permitAll()
                        //.antMatchers(PROTECTED_URLS).hasAnyAuthority(ADMIN)
                        .antMatchers(PROTECTED_URLS).permitAll()
                        .antMatchers(HttpMethod.POST, "/users", "/rooms").permitAll()
                        .antMatchers(HttpMethod.GET, "/users", "/rooms").permitAll()
                        //.antMatchers(HttpMethod.GET, "/users", "/rooms").hasAnyAuthority(ADMIN)
                        //.anyRequest().denyAll())
                        .anyRequest().permitAll())
                //.addFilterBefore(new JwtTokenFilter(jwtTokenUtil, userService), UsernamePasswordAuthenticationFilter.class)
                //.authorizeRequests()
                //.and()
                .exceptionHandling()
                //.accessDeniedHandler(accessDeniedHandler)
                //.authenticationEntryPoint(authenticationEntryPint)
                .and()
                .cors()
                .and()
                .csrf().disable();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers(HttpMethod.POST, "/users")
                .antMatchers("/users/forgot");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("*")
                        .allowedOrigins("");
            }
        };
    }
}