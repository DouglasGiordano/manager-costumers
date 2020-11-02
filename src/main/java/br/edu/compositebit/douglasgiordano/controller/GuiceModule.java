package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.Application;
import br.edu.compositebit.douglasgiordano.dao.AddressDao;
import br.edu.compositebit.douglasgiordano.dao.ConfigDao;
import br.edu.compositebit.douglasgiordano.dao.CostumerDao;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.sql.SQLException;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Application.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    private ObjectMapper provideObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        return objectMapper;
    }

    @Provides
    @Singleton
    private ConfigDao provideJdbiConfig() throws SQLException {
        return new ConfigDao();
    }

    @Provides
    @Singleton
    private CostumerDao provideCostumerDao() throws SQLException {
        CostumerDao repo = this.provideJdbiConfig().getJdbi().open().attach(CostumerDao.class);
        return repo;
    }

    @Provides
    @Singleton
    private AddressDao provideAddressDao() throws SQLException {
        AddressDao repo = this.provideJdbiConfig().getJdbi().open().attach(AddressDao.class);
        return repo;
    }
}
