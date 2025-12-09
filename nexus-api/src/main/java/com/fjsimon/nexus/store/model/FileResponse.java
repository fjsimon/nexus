package com.fjsimon.nexus.store.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

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
