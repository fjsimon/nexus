package com.fjsimon.nexus.store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Identifiers {

    @JsonProperty("isbn_10")
    private List<String> isbn_10;

    @JsonProperty("isbn_13")
    private List<String> isbn_13;

    @JsonProperty("lccn")
    private List<String> lccn;

    @JsonProperty("amazon")
    private List<String> amazon;

    @JsonProperty("google")
    private List<String> google;

    @JsonProperty("goodreads")
    private List<String> goodreads;

    @JsonProperty("librarything")
    private List<String> librarything;

}
