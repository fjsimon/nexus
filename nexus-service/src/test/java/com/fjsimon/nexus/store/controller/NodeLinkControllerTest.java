package com.fjsimon.nexus.store.controller;

import com.fjsimon.nexus.store.domain.NodeLink;
import com.fjsimon.nexus.store.service.NodeLinkService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NodeLinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NodeLinkService nodeLinkServiceMock;

    @WithMockUser("spring")
    @Test
    public void noParamLinks() throws Exception {

        Page<NodeLink> pages = new PageImpl<>(Collections.emptyList());
        when(nodeLinkServiceMock.getLinks(any(Pageable.class))).thenReturn(pages);

        this.mockMvc.perform(get("/links")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empty").value("true"))
                .andExpect(jsonPath("$.pageable").value("INSTANCE"))
                .andExpect(jsonPath("$.totalPages").value("1"))
                .andExpect(jsonPath("$.totalElements").value("0"))
                .andExpect(jsonPath("$.first").value("true"));
    }

    @WithMockUser("spring")
    @Test
    public void paramLinks() throws Exception {

        when(nodeLinkServiceMock.lookupNodeLinkById(1)).thenReturn(new NodeLink("link"));

        this.mockMvc.perform(get("/links/{nodeLinkId}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.link").value("link"));
    }

}