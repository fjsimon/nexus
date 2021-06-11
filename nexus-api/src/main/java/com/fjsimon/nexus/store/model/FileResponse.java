package com.fjsimon.nexus.store.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@ToString
public class FileResponse {

    @NotNull
    private String name;

    @NotNull
    private String path;
}
