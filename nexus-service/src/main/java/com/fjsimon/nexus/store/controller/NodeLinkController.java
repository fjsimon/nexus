package com.fjsimon.nexus.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjsimon.nexus.store.domain.NodeLink;
import com.fjsimon.nexus.store.exceptions.NotFoundException;
import com.fjsimon.nexus.store.model.FileResponse;
import com.fjsimon.nexus.store.model.LinkDto;
import com.fjsimon.nexus.store.service.NodeLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteNodeLinks(@RequestParam List<Long> nodeLinkIds) {

        nodeLinkService.deleteNodeLinkList(nodeLinkIds);
    }

    @GetMapping("/{nodeLinkId}")
    public NodeLink getNodeLink(@PathVariable("nodeLinkId") long nodeLinkId) {

        return nodeLinkService.lookupNodeLinkById(nodeLinkId);
    }

    @GetMapping
    public Page<NodeLink> getNodeLinks(Pageable pageable) {

        return nodeLinkService.getLinks(pageable);
    }

    @GetMapping(value = "/resource", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> getLinksResource() throws NotFoundException, IOException {

        LOGGER.info(String.format("GET /links/resource"));

        List<NodeLink> links = nodeLinkService.getLinks();

        if (!links.isEmpty()) {

            ObjectMapper mapper = new ObjectMapper();
            byte[] buf = mapper.writeValueAsBytes(links);

            return ResponseEntity.ok()
                    .contentLength(buf.length)
                    .header("Content-Disposition", "attachment; filename=links.json")
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(new ByteArrayInputStream(buf)));

        } else {

            throw new NotFoundException("Links Resource Not Found");
        }
    }
}
