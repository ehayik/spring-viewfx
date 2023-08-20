package org.github.ehayik.toolbelt.viewfx;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan
public class ViewFxAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "viewfx")
    ViewFxProperties viewFxProperties() {
        return ViewFxProperties.builder()
                .indexViewUrl("/index")
                .width(800)
                .height(600)
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    ViewFxStageRouter viewFxStageRouter(ViewFxProperties viewFxProperties, ApplicationContext applicationContext) {
        return new ViewFxStageInitializer(viewFxProperties, applicationContext);
    }
}
