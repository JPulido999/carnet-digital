package com.unsch.carnet_digital.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionProxyController {

    private final RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<?> getTransactions(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam int page,
            @RequestParam int size
    ) {

        String url = String.format(
            "http://localhost:8081/api/transactions?startDate=%s&endDate=%s&page=%d&size=%d",
            startDate, endDate, page, size
        );

        return restTemplate.getForEntity(url, Object.class);
    }
}

