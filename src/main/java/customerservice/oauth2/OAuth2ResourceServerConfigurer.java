package customerservice.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@EnableResourceServer
@Configuration
public class OAuth2ResourceServerConfigurer extends ResourceServerConfigurerAdapter {
	@Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                .antMatchers(HttpMethod.GET, "/customers").hasAuthority("trust");
                //.antMatchers(HttpMethod.POST, "/foo").hasAuthority("FOO_WRITE");
                //you can implement it like this, but I show method invocation security on write
    }

//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        log.info("Configuring ResourceServerSecurityConfigurer ");
//        resources.resourceId("foo").tokenStore(tokenStore);
//    }

    @Autowired
    TokenStore tokenStore;

    @Autowired
    JwtAccessTokenConverter tokenConverter;
}
