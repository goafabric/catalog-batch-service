package org.goafabric.catalogservice.service.crossfunctional;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DelegatingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

@Configuration
public class DatasourceMTConfiguration {
    private Boolean initialized = false;

    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();

        return new DelegatingDataSource(dataSource) {
            @Override
            public Connection getConnection() throws SQLException {
                System.out.println("getting connection");
                var con = super.getConnection();
                if (initialized) {
                    System.out.println("getting connection for " + HttpInterceptor.getTenantId());
                    con.setSchema("catalog_" + HttpInterceptor.getTenantId());
                }
                return con;
            }
        };
    }

    @Bean
    public ApplicationRunner schemaCreator(Flyway flyway,
                                           @Value("${database.provisioning.goals}") String goals,
                                           @Value("${multi-tenancy.tenants}") String tenants,
                                           @Value("${multi-tenancy.schema-prefix:_}") String schemaPrefix,
                                           ApplicationContext context) {
        return args -> {
            if (goals.contains("-migrate")) {
                Arrays.asList(tenants.split(",")).forEach(tenant -> {
                            Flyway.configure().configuration(flyway.getConfiguration())
                                    .schemas(schemaPrefix + tenant).defaultSchema(schemaPrefix + tenant)
                                    .placeholders(Map.of("tenantId", tenant))
                                    .load().migrate();
                        }
                );
            }

            initialized = true;
            if (goals.contains("-terminate") && !goals.contains("-import")) { SpringApplication.exit(context, () -> 0); }
        };
    }
    
}
