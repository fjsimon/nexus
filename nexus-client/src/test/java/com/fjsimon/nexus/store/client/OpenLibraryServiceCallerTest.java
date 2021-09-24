package com.fjsimon.nexus.store.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OpenLibraryServiceCallerTest {

    @Mock
    private ObjectMapper mapper;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OpenLibraryServiceCaller testee;

    private static final TypeReference<String> TYPE_REFERENCE = new TypeReference<String>() {};

    @Test
    public void get_calls_template_test() {

        ResponseEntity entity = new ResponseEntity<String>(HttpStatus.IM_USED);

        when(restTemplate.exchange(eq("url"), eq(HttpMethod.GET), (HttpEntity<?>) any(), (Class<Object>) any())).thenReturn(entity);

        Optional<String> response = testee.get("url", TYPE_REFERENCE);

        assertThat(response.isPresent(), is(false));
        verify(restTemplate, times(1)).exchange(eq("url"), eq(HttpMethod.GET), (HttpEntity<?>) any(), (Class<Object>) any());
        verifyNoMoreInteractions(restTemplate, mapper);
    }

    @Test
    public void get_response_body_rest() throws JsonProcessingException {

        String body = "body";
        ResponseEntity entity = new ResponseEntity<>(body, HttpStatus.IM_USED);

        when(restTemplate.exchange(eq("url"), eq(HttpMethod.GET), (HttpEntity<?>) any(), (Class<Object>) any())).thenReturn(entity);
        when(mapper.readValue(eq("body"), eq(TYPE_REFERENCE))).thenReturn("parse");

        Optional<String> response = testee.get("url", TYPE_REFERENCE);

        assertThat(response.isPresent(), is(true));
        verify(restTemplate, times(1)).exchange(eq("url"), eq(HttpMethod.GET), (HttpEntity<?>) any(), (Class<Object>) any());
        verify(mapper, times(1)).readValue(eq("body"), eq(TYPE_REFERENCE));
        verifyNoMoreInteractions(restTemplate, mapper);
    }
}