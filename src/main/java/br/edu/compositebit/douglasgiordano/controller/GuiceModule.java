package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.Application;
import br.edu.compositebit.douglasgiordano.dao.AddressDao;
import br.edu.compositebit.douglasgiordano.dao.ConfigDao;
import br.edu.compositebit.douglasgiordano.dao.CustomerDao;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.sql.SQLException;

/**
 * @author Douglas Montanha Giordano
 * Class responsible for configuring dependency injections using Google Guice.
 */
public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Application.class).in(Singleton.class);
    }

    /**
     * Configure Object Mapper injection
     * @return ObjectMapper
     */
    @Provides
    @Singleton
    private ObjectMapper provideObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        return objectMapper;
    }

    /**
     * Configure JDBI injection
     * @return JDBI Config
     */
    @Provides
    @Singleton
    private ConfigDao provideJdbiConfig() throws SQLException {
        return new ConfigDao();
    }

    /**
     * Configure CustomerDao injection
     * @return CustomerDao
     */
    @Provides
    @Singleton
    private CustomerDao provideCustomerDao() throws SQLException {
        CustomerDao repo = this.provideJdbiConfig().getJdbi().open().attach(CustomerDao.class);
        return repo;
    }

    /**
     * Configure AddressDao injection
     * Configure AddressDao injection
     * @return AddressDao
     */
    @Provides
    @Singleton
    private AddressDao provideAddressDao() throws SQLException {
        AddressDao repo = this.provideJdbiConfig().getJdbi().open().attach(AddressDao.class);
        return repo;
    }
}
