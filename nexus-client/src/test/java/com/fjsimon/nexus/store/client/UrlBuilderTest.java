package com.fjsimon.nexus.store.client;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UrlBuilderTest {

    @InjectMocks
    private UrlBuilder testee;

    @BeforeEach
    private void setup() {

        ReflectionTestUtils.setField(testee, "baseUrl", "localhost");
    }

    @Test
    public void regex_test() {

        Matcher matcher = Pattern.compile("#\\{(.*?)\\}")
                .matcher("#{baseUrl}/books?bibkeys=ISBN:#{isbn}&jscmd=#{jscmd}&format=#{format}");

        assertThat(matcher.find(), is(true));
        assertThat(matcher.group(1), is("baseUrl"));

        assertThat(matcher.find(), is(true));
        assertThat(matcher.group(1), is("isbn"));

        assertThat(matcher.find(), is(true));
        assertThat(matcher.group(1), is("jscmd"));

        assertThat(matcher.find(), is(true));
        assertThat(matcher.group(1), is("format"));

    }

    @Test
    public void link_builder_test() {

        UrlBuilder.Spec spec = new UrlBuilder.Spec("#{baseUrl}/books?bibkeys=ISBN:#{isbn}&jscmd=#{jscmd}&format=#{format}");
        UrlBuilder.LinkBuilder linkBuilder = testee.aLink(spec);
        linkBuilder.with("isbn", "isbn");
        linkBuilder.with("jscmd", "jscmd");
        linkBuilder.with("format", "format");
        linkBuilder.withParams("param1", "param1");

        assertThat(linkBuilder.build(), is("localhost/books?bibkeys=ISBN:isbn&jscmd=jscmd&format=format&param1=param1"));
    }

    @Test
    public void link_builder_same_var_exceptions_test() {

        UrlBuilder.Spec spec = new UrlBuilder.Spec("#{baseUrl}/books?bibkeys=ISBN:#{isbn}&jscmd=#{jscmd}&format=#{format}");
        UrlBuilder.LinkBuilder linkBuilder = testee.aLink(spec);
        linkBuilder.with("isbn", "isbn");
        assertThrows(IllegalStateException.class, () -> {
            linkBuilder.with("isbn", "isbn");
        });

    }

    @Test
    public void link_builder_different_type_exceptions_test() {

        UrlBuilder.Spec spec = new UrlBuilder.Spec("#{baseUrl}/books?bibkeys=ISBN:#{isbn}&jscmd=#{jscmd}&format=#{format}");
        UrlBuilder.LinkBuilder linkBuilder = testee.aLink(spec);
        linkBuilder.with("isbn", "isbn");
        assertThrows(IllegalStateException.class, () -> {
            linkBuilder.with("unknowed", "unknowed");
        });
    }

}