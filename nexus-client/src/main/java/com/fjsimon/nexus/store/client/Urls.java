package com.fjsimon.nexus.store.client;

public final class Urls {

    // base url = https://openlibrary.org/api
    public static final UrlBuilder.Spec GET_BOOK = new UrlBuilder.Spec("#{baseUrl}/books?bibkeys=ISBN:#{isbn}&jscmd=#{jscmd}&format=#{format}");

    private Urls() {}

}
