package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.repository.ConfigDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Application.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    private ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }

    @Provides
    @Singleton
    private ConfigDao provideJdbiConfig() {
        return new ConfigDao();
    }
}