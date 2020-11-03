package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.Application;
import br.edu.compositebit.douglasgiordano.model.entities.Message;
import br.edu.compositebit.douglasgiordano.model.entities.exception.ApiError;
import com.despegar.http.client.*;
import com.despegar.sparkjava.test.SparkServer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import spark.servlet.SparkApplication;

import java.io.IOException;

import static org.junit.Assert.*;

public abstract class SuperControllerTest {

    protected ObjectMapper objectMapper;

    public static class TestContollerTestSparkApplication implements SparkApplication {
        @Override
        public void init() {
            Application.main();
        }
    }

    public abstract SparkServer<SuperCrudControllerTest.TestContollerTestSparkApplication> getServer();

    @Before
    public void setUp() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
    }

    public void checkAttrApiError(ApiError error, String... expectedException) {
        if (error != null) {
            assertEquals(expectedException, error.getMessage());
        } else {
            fail("Object is null!");
        }
    }

    public <T> T create(String path, T entity, Class viewRequestClass, Class viewResponseClass, int expectedCode, Class <T> type) throws HttpClientException, IOException {
        String body = this.objectMapper.writerWithView(viewRequestClass).writeValueAsString(entity);
        PostMethod post = this.getServer().post(path, body,false);
        HttpResponse httpResponse = this.getServer().execute(post);
        entity = objectMapper.readerWithView(viewResponseClass).readValue(httpResponse.body(), type);
        assertEquals(expectedCode, httpResponse.code());
        return entity;
    }

    public <T> T update(String path, T entity, Class viewRequestClass, Class viewResponseClass, int expectedCode, Class <T> type) throws HttpClientException, IOException {
        String body = this.objectMapper.writerWithView(viewRequestClass).writeValueAsString(entity);
        PutMethod post = this.getServer().put(path, body,false);
        HttpResponse httpResponse = this.getServer().execute(post);
        entity = objectMapper.readerWithView(viewResponseClass).readValue(httpResponse.body(), type);
        assertEquals(expectedCode, httpResponse.code());
        return entity;
    }

    public <T> Message delete(String path, int expectedCode) throws HttpClientException, IOException {
        DeleteMethod get = this.getServer().delete(path, false);
        HttpResponse httpResponse = this.getServer().execute(get);
        Message cResponse = objectMapper.readValue(httpResponse.body(), Message.class);
        assertEquals(expectedCode, httpResponse.code());
        assertNotNull(cResponse);
        return cResponse;
    }

    public <T> T searchList(String path, Class view, int expectedCode, Class<T> type) throws HttpClientException, IOException {
        GetMethod get = this.getServer().get(path, false);
        HttpResponse httpResponse = this.getServer().execute(get);
        T cResponse = objectMapper.readerWithView(view).readValue(httpResponse.body(), type);
        assertNotNull(cResponse);
        assertEquals(200, httpResponse.code());
        return cResponse;
    }

    public <T> T search(String path, Class view, int expectedCode, Class <T> type) throws HttpClientException, IOException {
        GetMethod get = this.getServer().get(path, false);
        HttpResponse httpResponse = this.getServer().execute(get);
        T cResponse = objectMapper.readerWithView(view).readValue(httpResponse.body(), type);
        assertNotNull(cResponse);
        assertEquals(200, httpResponse.code());
        return cResponse;
    }

}
