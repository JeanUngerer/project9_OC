package com.projetsuivi.dangerservice.proxies;

import com.projetsuivi.dangerservice.dtos.NotesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "notes-service", url = "localhost:9032")
public interface NotesProxy {
    @GetMapping(value = "/notes/{id}")
    NotesDto getOneNote(@PathVariable("id") String id);

    @GetMapping(value = "/notes/patient/[id}")
    List<NotesDto> getNotesByPatientId(@PathVariable("id") Long id);
}
