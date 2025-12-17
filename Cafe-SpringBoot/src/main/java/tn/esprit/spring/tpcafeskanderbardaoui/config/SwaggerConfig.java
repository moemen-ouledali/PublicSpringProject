package tn.esprit.spring.tpcafeskanderbardaoui.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("☕ Cafe Skander Bardaoui API")
                        .version("1.0.0")
                        .description("""
                                API documentation for the Cafe Skander project.  
                                Manage clients, commandes, promotions, and more efficiently.
                                """)
                        .contact(new Contact()
                                .name("Skander Bardaoui")
                                .email("skonbardaoui@gmail.com")
                                .url("https://www.linkedin.com/in/skander-bardaoui-b916091a5/")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Profile")
                        .url("https://github.com/skon2"));
    }

    @Bean
    public GroupedOpenApi adresseApi() {
        return GroupedOpenApi.builder()
                .group("Adresse")
                .pathsToMatch("/api/adresse/**")
                .build();
    }

        @Bean
    public GroupedOpenApi articleApi() {
        return GroupedOpenApi.builder()
                .group("Article")
                .pathsToMatch("/api/articles/**")
                .build();
    }

    @Bean
    public GroupedOpenApi carteFideliteApi() {
        return GroupedOpenApi.builder()
                .group("CarteFidelite")
                .pathsToMatch("/api/carte-fidelite/**")
                .build();
    }

    @Bean
    public GroupedOpenApi clientApi() {
        return GroupedOpenApi.builder()
                .group("Client")
                .pathsToMatch("/api/client/**")
                .build();
    }

    @Bean
    public GroupedOpenApi commandeApi() {
        return GroupedOpenApi.builder()
                .group("Commande")
                .pathsToMatch("/api/commande/**")
                .build();
    }

    @Bean
    public GroupedOpenApi detailCommandeApi() {
        return GroupedOpenApi.builder()
                .group("Detail_Commande")
                .pathsToMatch("/api/detail-commande/**")
                .build();
    }

    @Bean
    public GroupedOpenApi promotionApi() {
        return GroupedOpenApi.builder()
                .group("Promotion")
                .pathsToMatch("/api/promotion/**")
                .build();
    }
}
