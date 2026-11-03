package org.tutoringclub.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PreviewController {

    @GetMapping("/")
    public String overview() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
