package com.companages.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private static final URI EMAILJS_ENDPOINT = URI.create("https://api.emailjs.com/api/v1.0/email/send");
    private final JavaMailSender sender;
    private final ObjectMapper json;
    private final HttpClient http = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
    private final String from;
    private final String provider;
    private final String serviceId;
    private final String templateId;
    private final String publicKey;
    private final String privateKey;

    public MailService(JavaMailSender sender, ObjectMapper json,
            @Value("${app.mail-from}") String from,
            @Value("${app.mail-provider:smtp}") String provider,
            @Value("${app.emailjs.service-id:}") String serviceId,
            @Value("${app.emailjs.template-id:}") String templateId,
            @Value("${app.emailjs.public-key:}") String publicKey,
            @Value("${app.emailjs.private-key:}") String privateKey) {
        this.sender = sender;
        this.json = json;
        this.from = from;
        this.provider = provider;
        this.serviceId = serviceId;
        this.templateId = templateId;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public void send(String to, String subject, String body) {
        if ("emailjs".equalsIgnoreCase(provider)) sendWithEmailJs(to, subject, body);
        else sendWithSmtp(to, subject, body);
    }

    private void sendWithSmtp(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        sender.send(message);
    }

    private void sendWithEmailJs(String to, String subject, String body) {
        if (serviceId.isBlank() || templateId.isBlank() || publicKey.isBlank()) {
            throw new IllegalStateException("EmailJS is enabled but its service, template or public key is missing");
        }
        try {
            Map<String, Object> payload = new LinkedHashMap<>();
            payload.put("service_id", serviceId);
            payload.put("template_id", templateId);
            payload.put("user_id", publicKey);
            if (!privateKey.isBlank()) payload.put("accessToken", privateKey);
            payload.put("template_params", Map.of(
                    "to_email", to,
                    "email", to,
                    "subject", subject,
                    "message", body,
                    "link", linkFrom(body),
                    "app_name", "Companages"));
            HttpRequest request = HttpRequest.newBuilder(EMAILJS_ENDPOINT)
                    .timeout(Duration.ofSeconds(15))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json.writeValueAsString(payload)))
                    .build();
            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IllegalStateException("EmailJS rejected the message: " + response.body());
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Email delivery was interrupted", exception);
        } catch (Exception exception) {
            if (exception instanceof IllegalStateException state) throw state;
            throw new IllegalStateException("EmailJS delivery failed", exception);
        }
    }

    private String linkFrom(String body) {
        int start = body.indexOf("http");
        return start < 0 ? "" : body.substring(start).trim();
    }
}
