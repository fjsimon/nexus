package com.fjsimon.nexus.store.repo;

import com.fjsimon.nexus.store.domain.NodeLink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "links", path = "links")
public interface NodeLinkRepository extends PagingAndSortingRepository<NodeLink, Long> {

    Optional<NodeLink> findById(Long id);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    <S extends NodeLink> S save(S s);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    <S extends NodeLink> Iterable<S> saveAll(Iterable<S> iterable);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void delete(NodeLink nodeLink);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends NodeLink> iterable);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void deleteAll();
}