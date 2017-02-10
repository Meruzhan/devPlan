package app.config;

import app.config.EventConfig;
import app.config.UserConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by zaven.chilingaryan on 12/2/2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "app")
@Import({UserConfig.class, EventConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
