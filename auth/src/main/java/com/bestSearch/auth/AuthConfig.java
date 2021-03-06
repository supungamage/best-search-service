package com.bestSearch.auth;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
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
            .antMatchers("/api/v1/organization-types/**")
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
//          .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
            .oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));
    }

    private JwtAuthenticationConverter grantedAuthoritiesExtractor(){
      JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
       converter.setJwtGrantedAuthoritiesConverter(jwt -> {
         Collection<String> claims = (Collection<String>) jwt.getClaims().get("cognito:groups");
//         return Stream.of(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
         return claims.stream()
             .map(SimpleGrantedAuthority::new)
             .collect(Collectors.toSet());
       });

       return converter;
    }
  }

