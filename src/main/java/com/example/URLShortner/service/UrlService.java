package com.example.URLShortner.service;
import com.example.URLShortner.dto.UpdateUrlRequest;
import com.example.URLShortner.dto.CreateUrlRequest;
import com.example.URLShortner.dto.UrlResponse;
import com.example.URLShortner.entity.Url;
import com.example.URLShortner.entity.User;
import com.example.URLShortner.repository.UrlRepository;
import com.example.URLShortner.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.URLShortner.dto.AdminUrlResponse;
import java.time.LocalDateTime;
import java.util.List;
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
    public AdminUrlResponse getAnyUrl(Long id) {

        Url url = urlRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        return new AdminUrlResponse(
                url.getId(),
                url.getOriginalUrl(),
                url.getShortUrl(),
                url.getCreatedAt(),
                url.getExpirationDate(),
                url.isActive(),
                url.getClickCount(),
                url.getUser().getId(),
                url.getUser().getName(),
                url.getUser().getEmail()
        );
    }

    public AdminUrlResponse adminUpdateUrl(
            Long id,
            UpdateUrlRequest request) {

        Url url = urlRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        url.setOriginalUrl(request.getOriginalUrl());
        url.setExpirationDate(request.getExpirationDate());

        Url updatedUrl = urlRepository.save(url);

        return new AdminUrlResponse(
                updatedUrl.getId(),
                updatedUrl.getOriginalUrl(),
                updatedUrl.getShortUrl(),
                updatedUrl.getCreatedAt(),
                updatedUrl.getExpirationDate(),
                updatedUrl.isActive(),
                updatedUrl.getClickCount(),
                updatedUrl.getUser().getId(),
                updatedUrl.getUser().getName(),
                updatedUrl.getUser().getEmail()
        );
    }
    public void adminDeleteUrl(Long id) {

        Url url = urlRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        urlRepository.delete(url);
    }
    public AdminUrlResponse adminDeactivateUrl(Long id) {

        Url url = urlRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        url.setActive(false);

        Url updatedUrl = urlRepository.save(url);

        return new AdminUrlResponse(
                updatedUrl.getId(),
                updatedUrl.getOriginalUrl(),
                updatedUrl.getShortUrl(),
                updatedUrl.getCreatedAt(),
                updatedUrl.getExpirationDate(),
                updatedUrl.isActive(),
                updatedUrl.getClickCount(),
                updatedUrl.getUser().getId(),
                updatedUrl.getUser().getName(),
                updatedUrl.getUser().getEmail()
        );
    }
    public void deleteUrl(Long id, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Url url = urlRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        // Ownership check
        if (!url.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to delete this URL");
        }

        urlRepository.delete(url);
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

    public List<UrlResponse> getMyUrls(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Url> urls = urlRepository.findByUser(user);

        return urls.stream()
                .map(url -> new UrlResponse(
                        url.getId(),
                        url.getOriginalUrl(),
                        url.getShortUrl(),
                        url.getCreatedAt(),
                        url.getExpirationDate(),
                        url.isActive(),
                        url.getClickCount()
                ))
                .toList();
    }
    public UrlResponse getMyUrl(Long id, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Url url = urlRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        if (!url.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to access this URL");
        }

        return new UrlResponse(
                url.getId(),
                url.getOriginalUrl(),
                url.getShortUrl(),
                url.getCreatedAt(),
                url.getExpirationDate(),
                url.isActive(),
                url.getClickCount()
        );
    }
    public UrlResponse updateUrl(
            Long id,
            UpdateUrlRequest request,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Url url = urlRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        // Ownership check
        if (!url.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to update this URL");
        }

        url.setOriginalUrl(request.getOriginalUrl());
        url.setExpirationDate(request.getExpirationDate());

        Url updatedUrl = urlRepository.save(url);

        return new UrlResponse(
                updatedUrl.getId(),
                updatedUrl.getOriginalUrl(),
                updatedUrl.getShortUrl(),
                updatedUrl.getCreatedAt(),
                updatedUrl.getExpirationDate(),
                updatedUrl.isActive(),
                updatedUrl.getClickCount()
        );
    }

    public UrlResponse deactivateUrl(Long id, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Url url = urlRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        // Ownership check
        if (!url.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to deactivate this URL");
        }

        url.setActive(false);

        Url updatedUrl = urlRepository.save(url);

        return new UrlResponse(
                updatedUrl.getId(),
                updatedUrl.getOriginalUrl(),
                updatedUrl.getShortUrl(),
                updatedUrl.getCreatedAt(),
                updatedUrl.getExpirationDate(),
                updatedUrl.isActive(),
                updatedUrl.getClickCount()
        );
    }
    public List<AdminUrlResponse> getAllUrls() {

        List<Url> urls = urlRepository.findAll();

        return urls.stream()
                .map(url -> new AdminUrlResponse(
                        url.getId(),
                        url.getOriginalUrl(),
                        url.getShortUrl(),
                        url.getCreatedAt(),
                        url.getExpirationDate(),
                        url.isActive(),
                        url.getClickCount(),
                        url.getUser().getId(),
                        url.getUser().getName(),
                        url.getUser().getEmail()
                ))
                .toList();
    }
}