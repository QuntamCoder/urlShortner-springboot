package com.example.URLShortner.controller;

import com.example.URLShortner.dto.CreateUrlRequest;
import com.example.URLShortner.dto.UrlResponse;
import com.example.URLShortner.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.URLShortner.dto.UpdateUrlRequest;


import java.util.List;

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
    @GetMapping("/{id}")
    public ResponseEntity<UrlResponse> getMyUrl(
            @PathVariable Long id,
            Authentication authentication) {

        UrlResponse response =
                urlService.getMyUrl(id, authentication.getName());

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UrlResponse> updateUrl(
            @PathVariable Long id,
            @RequestBody UpdateUrlRequest request,
            Authentication authentication) {

        UrlResponse response = urlService.updateUrl(
                id,
                request,
                authentication.getName()
        );

        return ResponseEntity.ok(response);
    }
    @GetMapping("/my")
    public ResponseEntity<List<UrlResponse>> getMyUrls(
            Authentication authentication) {

        List<UrlResponse> urls =
                urlService.getMyUrls(authentication.getName());

        return ResponseEntity.ok(urls);
    }
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<UrlResponse> deactivateUrl(
            @PathVariable Long id,
            Authentication authentication) {

        UrlResponse response = urlService.deactivateUrl(
                id,
                authentication.getName()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUrl(
            @PathVariable Long id,
            Authentication authentication) {

        urlService.deleteUrl(
                id,
                authentication.getName()
        );

        return ResponseEntity.ok("URL deleted successfully");
    }
}