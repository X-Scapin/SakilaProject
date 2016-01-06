package isep.web.sakila.webapi.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import isep.web.sakila.jpa.config.PersistenceConfig;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "isep.web.sakila.webapi" })
@Import({ PersistenceConfig.class })
public class MvcConfig extends WebMvcConfigurerAdapter {

}
