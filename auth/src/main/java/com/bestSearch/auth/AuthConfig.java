package com.bestSearch.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
  public class AuthConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers("/swagger-ui.html")
            .antMatchers("/swagger-resources/**")
            .antMatchers("/v2/api-docs/**")
            .antMatchers("/webjars/**")
            .antMatchers("/api/v1/orders/**")
            .antMatchers("/api/v1/organizations/**")
            .antMatchers("/actuator/**");
  }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests(authz -> authz
              .antMatchers("/actuator/**").permitAll()
              .antMatchers("/api/v1/organization-types/**").permitAll()
              .antMatchers("/api/v1/orders/**").permitAll()
              .antMatchers("/api/v1/organizations/**").permitAll()
              .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
//              .antMatchers(HttpMethod.GET, "api/v1/organization-type/test/**").hasAuthority("SCOPE_read")
//              .antMatchers(HttpMethod.POST, "/test").hasAuthority("SCOPE_write")
              .anyRequest().authenticated())
              //.anyRequest().permitAll());
          .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
  }

