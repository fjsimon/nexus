package com.fjsimon.nexus.store.mockmvc.controller;

import com.fjsimon.nexus.store.client.OpenLibraryClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OpenLibraryClient openLibraryClientMock;

    @WithMockUser("spring")
    @Test
    public void scan_books_test() throws Exception {

        this.mockMvc.perform(get("/books/scan")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());

    }

    @WithMockUser("spring")
    @Test
    public void scan_books_content_test() throws Exception {

        this.mockMvc.perform(get("/books/scan")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(676)))
                .andExpect(jsonPath("$[0].*", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("appendix.pdf")))
                .andExpect(jsonPath("$[0].path", containsString("Applied Cryptography/appendix.pdf")));

    }
}