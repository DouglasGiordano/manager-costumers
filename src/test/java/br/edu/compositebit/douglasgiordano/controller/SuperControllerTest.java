package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.model.entities.exception.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Inject;
import org.junit.Before;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@lombok.Data
public class SuperControllerTest {
    protected ObjectMapper objectMapper;

    /**
     * Initializes the mock of web service resources.
     */
    @Before
    public void setUp() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerSubtypes(Data.class);
        objectMapper.registerSubtypes(LocalDateTime.class);
    }


    /**
     * Check api error attr
     *
     * @param error
     * @param expectedException
     */
    public void checkAttrApiError(ApiError error, String... expectedException) {
        if (error != null) {
            assertEquals(expectedException, error.getMessage());
        } else {
            fail("Object is null!");
        }
    }
}
