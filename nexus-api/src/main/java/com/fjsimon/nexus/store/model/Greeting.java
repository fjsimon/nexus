package com.fjsimon.nexus.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Greeting {

    private long id;
    private String content;
}
