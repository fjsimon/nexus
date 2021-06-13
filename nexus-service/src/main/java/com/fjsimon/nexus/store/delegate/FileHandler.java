package com.fjsimon.nexus.store.delegate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Component
public class FileHandler {

    @Value("${app.file.store.path}")
    private static String filestore_path;

    public void writeData(Path path, String data) throws IOException {

        byte[] databytes = new StringBuilder(data)
                .append(String.format("%n", ""))
                .toString()
                .getBytes();

        Files.write(path, databytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static Path getPath(String fileName) {

        return Paths.get(new StringBuilder(filestore_path)
                .append("/")
                .append(fileName)
                .toString());
    }
}
