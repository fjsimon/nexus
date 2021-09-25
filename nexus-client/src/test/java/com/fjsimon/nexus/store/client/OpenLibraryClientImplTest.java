package com.fjsimon.nexus.store.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpenLibraryClientImplTest {

    @Mock
    private OpenLibraryServiceCaller caller;

    @Mock
    private UrlBuilder urlBuilder;

    @InjectMocks
    private OpenLibraryClientImpl testee;

    @Test
    public void retrieveBook_test() {


        UrlBuilder.LinkBuilder linkBuilder = new UrlBuilder.LinkBuilder(new UrlBuilder.Spec("/books?bibkeys=ISBN:#{isbn}&jscmd=#{jscmd}&format=#{format}"));
        when(urlBuilder.aLink(any())).thenReturn(linkBuilder);

        testee.retrieveBook("isbn","jscmd","format");
    }
}