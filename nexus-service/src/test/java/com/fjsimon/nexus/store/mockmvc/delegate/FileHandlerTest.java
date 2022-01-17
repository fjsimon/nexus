package com.fjsimon.nexus.store.mockmvc.delegate;

import com.fjsimon.nexus.store.delegate.FileHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.file.Path;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class FileHandlerTest {

    @InjectMocks
    private FileHandler testee;

    @BeforeEach
    public void setUp() {

        ReflectionTestUtils.setField(FileHandler.class, "filestore_path", "/path");
    }

    @Test
    public void get_path_test() {

        Path path = testee.getPath(String.format("%s_%s_%s.txt", LocalDate.now(), "platform", "category"));
        assertThat(path.toString(), is(new StringBuilder().append("/path/").append(LocalDate.now()).append("_platform_category.txt").toString()));
    }

//    @Test(expected = NoSuchFileException.class)
//    public void write_data_test() throws IOException {
//
//        testee.writeData(testee.getPath(String.format("%s_%s_%s.txt", LocalDate.now().toString(), "platform", "category")), "data");
//    }

}