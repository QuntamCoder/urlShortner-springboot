package com.example.URLShortner.service;

import com.example.URLShortner.dto.CreateUrlRequest;
import com.example.URLShortner.dto.UrlResponse;
import com.example.URLShortner.entity.Url;
import com.example.URLShortner.entity.User;
import com.example.URLShortner.repository.UrlRepository;
import com.example.URLShortner.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final UserRepository userRepository;

    public UrlService(UrlRepository urlRepository,
                      UserRepository userRepository) {

        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
    }

    public UrlResponse createUrl(CreateUrlRequest request, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Url url = new Url();

        url.setOriginalUrl(request.getOriginalUrl());

        String shortUrl = "http://localhost:8080/"
                + UUID.randomUUID().toString().substring(0, 6);

        url.setShortUrl(shortUrl);

        url.setCreatedAt(LocalDateTime.now());

        url.setExpirationDate(request.getExpirationDate());

        url.setActive(true);

        url.setClickCount(0L);

        url.setUser(user);

        Url savedUrl = urlRepository.save(url);

        return new UrlResponse(
                savedUrl.getId(),
                savedUrl.getOriginalUrl(),
                savedUrl.getShortUrl(),
                savedUrl.getCreatedAt(),
                savedUrl.getExpirationDate(),
                savedUrl.isActive(),
                savedUrl.getClickCount()
        );
    }

    public String redirect(String shortCode) {

        String shortUrl = "http://localhost:8080/" + shortCode;

        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));

        if (!url.isActive()) {
            throw new RuntimeException("URL is inactive");
        }

        if (url.getExpirationDate() != null &&
                url.getExpirationDate().isBefore(LocalDateTime.now())) {

            url.setActive(false);
            urlRepository.save(url);

            throw new RuntimeException("URL has expired");
        }

        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);

        return url.getOriginalUrl();
    }
}