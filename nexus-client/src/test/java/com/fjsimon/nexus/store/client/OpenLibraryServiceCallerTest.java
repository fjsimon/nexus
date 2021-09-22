package com.fjsimon.nexus.store.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjsimon.nexus.store.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

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

    private static final TypeReference<Map<String, Book>> BOOK_TYPE = new TypeReference<Map<String, Book>>() {};

    @Test
    public void get_calls_delegates() {

        ResponseEntity entity = new ResponseEntity<String>(HttpStatus.IM_USED);

        when(restTemplate.exchange(eq("url"), eq(HttpMethod.GET), (HttpEntity<?>) any(), (Class<Object>) any())).thenReturn(entity);

        testee.get("url", BOOK_TYPE);

        verify(restTemplate, times(1)).exchange(eq("url"), eq(HttpMethod.GET), (HttpEntity<?>) any(), (Class<Object>) any());
    }

}