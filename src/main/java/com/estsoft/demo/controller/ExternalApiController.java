package com.estsoft.demo.controller;

import com.estsoft.demo.service.ExternalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExternalApiController {
    private final ExternalService externalService;
    public ExternalApiController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/api/external/test")
    public ResponseEntity<Void> callExternal() {
        externalService.call();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/external")
    public ResponseEntity<String> externalApiSave() {
        externalService.save();
        return ResponseEntity.ok("외부 API 데이터 저장 완료");
    }
}
