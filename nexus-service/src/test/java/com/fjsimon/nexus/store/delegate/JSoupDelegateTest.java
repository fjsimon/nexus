package com.fjsimon.nexus.store.delegate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class JSoupDelegateTest {

    @InjectMocks
    private JSoupDelegate testee;

    @Test
    public void display_title_test() throws IOException {

//        testee.display("https://www.pornhub.com/view_video.php?viewkey=ph59c24218e92d0");
        testee.display("https://www.youtube.com/watch?v=l-AImZXeZso");

    }

}