package com.fjsimon.nexus.store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    @JsonProperty("info_url")
    private String info_url;

    @JsonProperty("bib_key")
    private String bib_key;

    @JsonProperty("preview_url")
    private String preview_url;

    @JsonProperty("thumbnail_url")
    private String thumbnail_url;

    @JsonProperty("details")
    private Details details;

    @JsonProperty("preview")
    private String preview;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("authors")
    private List<Map<String, String>> authors;

    @JsonProperty("subjects")
    private List<Map<String, String>> subjects;

    @JsonProperty("publish_date")
    private String publish_date;

    @JsonProperty("cover")
    private Map<String, String> cover;

    //    private Object classifications;
    //    private List<String> publishers;
    //    private String pagination;
    //    private Map identifiers;
    //    private List<String> table_of_contents;
    //    private List<String> links;
    //    private String weight;
    //    private int number_of_pages;
    //    private Object cover;
    //    private List subjects;
    //    private String publish_date;
    //    private String key;
    //
    //    private String by_statement;
    //    private List publish_places;
    //    private List ebooks;

}
