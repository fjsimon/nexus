package com.fjsimon.nexus.store.client;

import com.fjsimon.nexus.store.model.Book;

import java.util.Map;
import java.util.Optional;

public interface OpenLibraryClient {

    Optional<Map<String, Book>> retrieveBook(String isbn, String jscmd, String format);
}
