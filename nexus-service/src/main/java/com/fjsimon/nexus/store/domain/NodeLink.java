package com.fjsimon.nexus.store.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "node_link")
public class NodeLink implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "link")
    private String link;

    /**
     * Default Constructor.
     */
    protected NodeLink() {}

    public NodeLink(String link) {
        this.link = link;
    }
}