package com.fjsimon.nexus.store.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class LinkDto {

    @NotNull
    @NotEmpty
    private String link;

}
