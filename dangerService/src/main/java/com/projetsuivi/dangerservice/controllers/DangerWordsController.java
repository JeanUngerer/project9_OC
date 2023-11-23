package com.projetsuivi.dangerservice.controllers;


import com.projetsuivi.dangerservice.services.DangerWordsService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("danger")
public class DangerWordsController {

    private final DangerWordsService dangerWordsService;

    @GetMapping("/status/{id}")
    public ResponseEntity<String> getDangerStatus(@PathVariable("id") Long id) {
        return ResponseEntity.ok(dangerWordsService.getDangerStatus(id));
    }
}
