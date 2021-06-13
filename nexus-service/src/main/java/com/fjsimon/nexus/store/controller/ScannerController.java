package com.fjsimon.nexus.store.controller;

import com.fjsimon.nexus.store.client.OpenLibraryClient;
import com.fjsimon.nexus.store.exceptions.NotFoundException;
import com.fjsimon.nexus.store.model.Book;
import com.fjsimon.nexus.store.model.FileResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/books")
public class ScannerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScannerController.class);

    @Value("${app.files.path}")
    private String path;

    @Autowired
    private OpenLibraryClient openLibraryClient;

    @GetMapping()
    public List<FileResponse> scan(@RequestParam(value="name", defaultValue="World") String name) {

        LOGGER.info(String.format("GET /books path : %s", path));

        Path file_path = Paths.get(path);
        try (Stream<Path> stream = Files.walk(file_path)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(path-> path.getFileName().toString().contains("pdf"))
                    .map(path -> FileResponse
                            .builder()
                            .name(path.getFileName().toString())
                            .path(path.toString()).build())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            LOGGER.info(e.getMessage(), e);
        }

        return Collections.emptyList();
    }


    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map getInfo(@NotNull @RequestParam(value="isbn") String isbn) throws IOException {

        LOGGER.info(String.format("GET /books/info isbn : %s", isbn));

        Optional<Map<String, Book>> optional = openLibraryClient.retrieveBook(isbn, "data", "json");

        return optional.isPresent() ? optional.get() : Collections.emptyMap();
    }


    @GetMapping(value = "/resource", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getResource(@NotNull @RequestParam(value="path") String path)
            throws NotFoundException, IOException {

        LOGGER.info(String.format("GET /books/resource path : %s", path));

        Optional<Path> optionalPath;
        Path file_path = Paths.get(path);
        try (Stream<Path> stream = Files.walk(file_path)) {

            optionalPath = stream.filter(Files::isRegularFile).findFirst();
        }

        if (optionalPath.isPresent()) {

            File file = optionalPath.get().toFile();
            return ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(new InputStreamResource(new FileInputStream(file)));
        } else {

            throw new NotFoundException("Resource Not Found");
        }
    }

}

