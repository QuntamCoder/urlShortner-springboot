package com.example.URLShortner.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "urls")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_url", nullable = false)
    private String originalUrl;

    @Column(name = "short_url", nullable = false, unique = true)
    private String shortUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "click_count", nullable = false)
    private Long clickCount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Url() {
    }


    public Long getId() {
        return id;
    }

    public Url setId(Long id) {
        this.id = id;
        return this;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public Url setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
        return this;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public Url setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Url setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public Url setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Url setActive(boolean active) {
        this.active = active;
        return this;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public Url setClickCount(Long clickCount) {
        this.clickCount = clickCount;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Url setUser(User user) {
        this.user = user;
        return this;
    }
}