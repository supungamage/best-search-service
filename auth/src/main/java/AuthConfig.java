import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
  public class AuthConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
          .authorizeRequests(authz -> authz
              .antMatchers("/bestsearch-service/actuator**").permitAll()
              .antMatchers(HttpMethod.GET, "api/v1/organization-type/test/**").hasAuthority("SCOPE_read")
              .antMatchers(HttpMethod.POST, "/test").hasAuthority("SCOPE_write")
              .anyRequest().authenticated())
          .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
  }

