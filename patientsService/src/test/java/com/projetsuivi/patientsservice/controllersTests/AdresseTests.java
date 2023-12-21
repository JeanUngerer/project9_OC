package com.projetsuivi.patientsservice.controllersTests;

import com.projetsuivi.patientsservice.dtos.AdresseDto;
import com.projetsuivi.patientsservice.enities.AdresseEntity;
import com.projetsuivi.patientsservice.repositories.AdresseRepository;
import com.projetsuivi.patientsservice.services.AdresseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.projetsuivi.patientsservice.utils.ObjectAsJsonStrings.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = true)
public class AdresseTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    AdresseService adresseService;

    @Autowired
    AdresseRepository adresseRepository;



    @Test
    public void createAPI() throws Exception {


        AdresseDto adresseDto = new AdresseDto(1l, 12, "dastreet", null);


        mockMvc.perform(put("/patients/adresse/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(adresseDto)))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.number").value(12))
                .andExpect(jsonPath("$.street").value("dastreet"));
    }

    @Test
    public void getAPI() throws Exception {


        AdresseDto adresseDto = new AdresseDto(1l, 12, "dastreet", null);

        adresseService.createAdresse(adresseDto);
        AdresseEntity adresseEntity = adresseRepository.findByNumberAndStreet(adresseDto.getNumber(), adresseDto.getStreet()).get();



        mockMvc.perform(get("/patients/adresse/{id}", adresseEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(adresseDto)))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.number").value(12))
                .andExpect(jsonPath("$.street").value("dastreet"));
    }

    @Test
    public void updateAPI() throws Exception {


        AdresseDto adresseDto = new AdresseDto(1l, 12, "dastreet", null);


        mockMvc.perform(put("/patients/adresse/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(adresseDto)))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.number").value(12))
                .andExpect(jsonPath("$.street").value("dastreet"));
    }

    @Test
    public void deleteAPI() throws Exception {


        AdresseDto adresseDto = new AdresseDto(1l, 12, "dastreet", null);


        mockMvc.perform(put("/patients/adresse/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(adresseDto)))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.number").value(12))
                .andExpect(jsonPath("$.street").value("dastreet"));
    }
}
