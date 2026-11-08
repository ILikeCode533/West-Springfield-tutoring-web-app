package org.tutoringclub.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tutoringclub.service.TutoringRequestService;

@RestController
@RequestMapping("/api/requests")
public class TutoringRequestController {

    private final TutoringRequestService requestService;

    public TutoringRequestController(
            TutoringRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<Void> submitRequest(
            @RequestParam UUID studentId,
            @RequestParam UUID subjectId) {

        requestService.submitRequest(studentId, subjectId);
        return ResponseEntity.status(201).build();
    }
}