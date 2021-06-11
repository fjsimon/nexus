package com.fjsimon.nexus.store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Details {

    @JsonProperty("title")
    private String title;

    @JsonProperty("edition_name")
    private String edition_name;

    @JsonProperty("covers")
    private List<String> covers;

    @JsonProperty("languages")
    private List<Map<String, String>> languages;

    @JsonProperty("subjects")
    private List<String> subjects;

    @JsonProperty("publish_country")
    private String publish_country;

    @JsonProperty("by_statement")
    private String by_statement;

    @JsonProperty("revision")
    private int revision;

    @JsonProperty("publishers")
    private List<String> publishers;

    @JsonProperty("key")
    private String key;

    @JsonProperty("identifiers")
    private Identifiers identifiers;

    @JsonProperty("isbn_10")
    private List<String> isbn_10;

    @JsonProperty("isbn_13")
    private List<String> isbn_13;

    @JsonProperty("lccn")
    private List<String> lccn;

//    private int number_of_pages;
//    private List<Content> table_of_contents;
//    private List<Object> contributors;
//    private String ocaid;
//    private String weight;
//    private List<String> source_records;
//    private List<Object> languages;
//    private List<String> oclc_numbers;
//    private Object type;
//    private String physical_dimensions;
//    private String description;
//    private String physical_format;
//    private Object last_modified;
//    private List<Object> authors;
//    private List<String> publish_places;
//    private String pagination;
//    private Object classifications;
//    private Object created;
//    private String notes;
//    private Identifiers identifiers;
//    private List<String> dewey_decimal_class;
//    private List<String> local_id;
//    private String publish_date;
//    private List<Object> works;

}
