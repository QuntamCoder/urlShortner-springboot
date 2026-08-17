package com.example.URLShortner.controller;
import com.example.URLShortner.dto.AdminUrlResponse;
import com.example.URLShortner.dto.UpdateUrlRequest;
import com.example.URLShortner.service.UrlService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UrlService urlService;

    public AdminController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/urls")
    public ResponseEntity<List<AdminUrlResponse>> getAllUrls() {

        return ResponseEntity.ok(
                urlService.getAllUrls()
        );
    }

    @GetMapping("/urls/{id}")
    public ResponseEntity<AdminUrlResponse> getUrl(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                urlService.getAnyUrl(id)
        );
    }
    @PutMapping("/urls/{id}")
    public ResponseEntity<AdminUrlResponse> updateUrl(
            @PathVariable Long id,
            @RequestBody UpdateUrlRequest request) {

        return ResponseEntity.ok(
                urlService.adminUpdateUrl(id, request)
        );
    }
    @DeleteMapping("/urls/{id}")
    public ResponseEntity<String> deleteUrl(
            @PathVariable Long id) {

        urlService.adminDeleteUrl(id);

        return ResponseEntity.ok("URL deleted successfully");
    }
    @PatchMapping("/urls/{id}/deactivate")
    public ResponseEntity<AdminUrlResponse> deactivateUrl(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                urlService.adminDeactivateUrl(id)
        );
    }
    @GetMapping("/api/admin/dashboard")
    public String dashboard(){
        return "welcome admin home";
    }
}
