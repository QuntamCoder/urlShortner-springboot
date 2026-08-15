package com.example.URLShortner.controller;

import com.example.URLShortner.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedirectController {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {

        String originalUrl = urlService.redirect(shortCode);

        return ResponseEntity
                .status(302)
                .header("Location", originalUrl)
                .build();
    }
}