package com.fjsimon.nexus.store.client;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class UrlsTest {

    @Test
    public void get_book_test() {

        assertThat(Urls.GET_BOOK.getUrl(), is("#{baseUrl}/books?bibkeys=ISBN:#{isbn}&jscmd=#{jscmd}&format=#{format}"));
    }
}