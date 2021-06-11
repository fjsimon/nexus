package com.fjsimon.nexus.store.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class LinkDto {

    @NotNull
    @NotEmpty
    private String link;

}
