package com.fjsimon.nexus.store.repo;

import com.fjsimon.nexus.store.domain.NodeLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "links", path = "links")
public interface NodeLinkRepository extends JpaRepository<NodeLink, Long> {

    Optional<NodeLink> findById(Long id);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    <S extends NodeLink> S save(S s);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    <S extends NodeLink> List<S> saveAll(Iterable<S> iterable);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void delete(NodeLink nodeLink);

    /**
     * Delete all user with ids specified in {@code ids} parameter
     *
     * @param ids List of user ids
     */
    @Modifying
    @Query("delete from NodeLink link where link.id in ?1")
    @RestResource(exported = false)
    void deleteByIdIn(List<Long> ids);


    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends NodeLink> iterable);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void deleteAll();
}