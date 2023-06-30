package org.goafabric.catalogservice;

import io.micrometer.observation.ObservationPredicate;
import org.goafabric.catalogservice.service.repository.entity.ChargeItemEo;
import org.goafabric.catalogservice.service.repository.entity.DiagnosisEo;
import org.goafabric.catalogservice.service.repository.entity.InsuranceEo;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;


/**
 * Created by amautsch on 26.06.2015.
 */

@SpringBootApplication
@ImportRuntimeHints(Application.ApplicationRuntimeHints.class)
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner init(ApplicationContext context) {
        return args -> {if ((args.length > 0) && ("-check-integrity".equals(args[0]))) {SpringApplication.exit(context, () -> 0);}};
    }

    @Bean
    @ConditionalOnMissingClass("org.springframework.security.oauth2.client.OAuth2AuthorizationContext")
    public SecurityFilterChain filterChain(HttpSecurity http, @Value("${security.authentication.enabled:true}") Boolean isAuthenticationEnabled) throws Exception {
        return isAuthenticationEnabled
                ? http.authorizeHttpRequests(auth -> auth.requestMatchers("/actuator/**").permitAll().anyRequest().authenticated())
                    .httpBasic(httpBasic -> {}).csrf(csrf -> csrf.disable()).build()
                : http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()).build();
    }

    @Bean
    ObservationPredicate disableHttpServerObservationsFromName() { return (name, context) -> !name.startsWith("spring.security."); }

    @Bean
    BeforeConvertCallback<ChargeItemEo> beforeConvertCallback1() {
        return aggregate -> {
            if (aggregate.id == null) aggregate.id = UUID.randomUUID().toString();
            return aggregate;
        };
    }

    @Bean
    BeforeConvertCallback<DiagnosisEo> beforeConvertCallback2() {
        return aggregate -> {
            if (aggregate.id == null) aggregate.id = UUID.randomUUID().toString();
            return aggregate;
        };
    }
    @Bean
    BeforeConvertCallback<InsuranceEo> beforeConvertCallback3() {
        return aggregate -> {
            if (aggregate.id == null) aggregate.id = UUID.randomUUID().toString();
            return aggregate;
        };
    }

    /*
    @Bean
    NamingStrategy namingStrategy() {
        return new NamingStrategy() {
            @Override
            public String getSchema() {
                return "catalog";
            }
        };
    }

     */

    static class ApplicationRuntimeHints implements RuntimeHintsRegistrar {
        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            hints.resources().registerPattern("en/*.yml"); //needed for stupid faker
            hints.resources().registerPattern("catalogs/*.csv");
        }
    }


}
