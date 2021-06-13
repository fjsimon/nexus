package com.fjsimon.nexus.store.controller;

import com.fjsimon.nexus.store.domain.NodeLink;
import com.fjsimon.nexus.store.model.LinkDto;
import com.fjsimon.nexus.store.service.NodeLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/links")
public class NodeLinkController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NodeLinkController.class);

    @Autowired
    private NodeLinkService nodeLinkService;

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNodeLink(@RequestBody @Valid LinkDto link) {

        nodeLinkService.createNodeLink(link.getLink());
    }

    @GetMapping("/{nodeLinkId}")
    public NodeLink getNodeLink(@PathVariable("nodeLinkId") long nodeLinkId) {

        return nodeLinkService.lookupNodeLinkById(nodeLinkId);
    }

    @GetMapping
    public Page<NodeLink> getNodeLinks(Pageable pageable) {

        return nodeLinkService.getLinks(pageable);
    }
}
