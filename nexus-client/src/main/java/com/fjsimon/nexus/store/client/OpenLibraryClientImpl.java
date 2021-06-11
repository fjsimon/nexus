package com.fjsimon.nexus.store.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fjsimon.nexus.store.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Optional;

@Component
public class OpenLibraryClientImpl implements OpenLibraryClient {

    private static final String ISBN = "isbn";
    private static final String JSCMD = "jscmd";
    private static final String FORMAT = "format";

    private static final TypeReference<Map<String, Book>> BOOK_TYPE = new TypeReference<Map<String, Book>>() {};

    private static final Logger log = LoggerFactory.getLogger(OpenLibraryClientImpl.class);

    @Autowired
    private OpenLibraryServiceCaller caller;

    @Autowired
    private UrlBuilder urlBuilder;

    @Override
    public Optional<Map<String, Book>> retrieveBook(String isbn, String jscmd, String format) {

        Assert.notNull(isbn, "isbn is required");
        Assert.notNull(isbn, "jscmd is required");
        Assert.notNull(isbn, "format is required");

        String url = urlBuilder.aLink(Urls.GET_BOOK)
                .with(ISBN, isbn)
                .with(JSCMD, jscmd)
                .with(FORMAT, format)
                .build();

        log.info("retrieveBook " + url);

        return caller.get(url, BOOK_TYPE);
    }
}
