package com.pokemite.pokemite.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import skaro.pokeapi.PokeApiReactorNonCachingConfiguration;
import java.time.Duration;

@Configuration
@Import(PokeApiReactorNonCachingConfiguration.class)
public class MyPokeApiReactorNonCachingConfiguration {

    @Bean
    public ConnectionProvider connectionProvider() {
        return ConnectionProvider.builder("default")
                .maxIdleTime(Duration.ofSeconds(10))
                .maxConnections(100)
                .pendingAcquireMaxCount(-1)
                .build();
    }

    @Bean
    public HttpClient httpClient(ConnectionProvider connectionProvider) {
        return HttpClient.create(connectionProvider)
                .compress(true)
                .resolver(DefaultAddressResolverGroup.INSTANCE);
    }
}
