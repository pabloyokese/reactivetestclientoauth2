package customerservice.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * Created by on 01.02.16.
 *
 * @author David Steiman
 */
@Configuration
public class JwtConfiguration {
   
    @Value("${config.oauth2.privateKey}")
    private String privateKey;

    @Value("${config.oauth2.publicKey}")
    private String publicKey;

//    @Bean
//    @Qualifier("tokenStore")
//    public TokenStore tokenStore() {
//
//        System.out.println("Created JwtTokenStore");
//        return new JwtTokenStore(jwtAccessTokenConverter);
//    }
    

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
//        JwtAccessTokenConverter converter =  new JwtAccessTokenConverter();
//        Resource resource = new ClassPathResource("public.cert");
//        String publicKey = null;
//        try {
//            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    		JwtAccessTokenConverter converter =  new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
//        converter.setVerifierKey(publicKey);
//        return converter;
    }
}
