package com.fjsimon.nexus.store.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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
        when(caller.get(anyString(), any())).thenReturn(Optional.of(Collections.EMPTY_LIST));

        testee.retrieveBook("isbn","jscmd","format");

        verify(urlBuilder, times(1)).aLink(any());
        verify(caller, times(1)).get(anyString(), any());
        verifyNoMoreInteractions(urlBuilder, caller);
    }
}