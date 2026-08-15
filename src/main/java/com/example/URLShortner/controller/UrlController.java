package com.example.URLShortner.controller;

import com.example.URLShortner.dto.CreateUrlRequest;
import com.example.URLShortner.dto.UrlResponse;
import com.example.URLShortner.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<UrlResponse> createUrl(
            @RequestBody CreateUrlRequest request,
            Authentication authentication) {

        UrlResponse response =
                urlService.createUrl(
                        request,
                        authentication.getName()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}