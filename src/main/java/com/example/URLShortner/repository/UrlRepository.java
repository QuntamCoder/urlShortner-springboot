package com.example.URLShortner.repository;

import com.example.URLShortner.entity.Url;
import com.example.URLShortner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortUrl(String shortUrl);
    List<Url> findByUser(User user);
}