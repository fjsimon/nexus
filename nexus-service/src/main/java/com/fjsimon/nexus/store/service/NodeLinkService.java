package com.fjsimon.nexus.store.service;

import com.fjsimon.nexus.store.domain.NodeLink;
import com.fjsimon.nexus.store.repo.NodeLinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NodeLinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NodeLinkService.class);

    @Autowired
    private NodeLinkRepository nodeLinkRepository;

    public NodeLink lookupNodeLinkById(long id)  {

        return nodeLinkRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Node Link : " + id));
    }

    public Page<NodeLink> getLinks(Pageable pageable) {

        return nodeLinkRepository.findAll(pageable);
    }

    public void createNodeLink(String link) {

        nodeLinkRepository.save(new NodeLink(link));
    }

    @Transactional
    public void deleteNodeLinkList(List<Long> ids) {

        nodeLinkRepository.deleteByIdIn(ids);
    }

    public List<NodeLink> getLinks() {

        return nodeLinkRepository.findAll();
    }
}
