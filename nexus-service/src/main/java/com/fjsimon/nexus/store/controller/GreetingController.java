package com.fjsimon.nexus.store.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.fjsimon.nexus.store.model.Greeting;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {

        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/login")
    public RedirectView redirectView(RedirectAttributes attributes) {

        return new RedirectView("/");
    }
}
