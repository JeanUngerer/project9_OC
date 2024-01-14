package com.projetsuivi.patientsservice.controllersTests;

import com.projetsuivi.patientsservice.constants.Genras;
import com.projetsuivi.patientsservice.dtos.PatientDto;
import com.projetsuivi.patientsservice.enities.PatientEntity;
import com.projetsuivi.patientsservice.models.Adresse;
import com.projetsuivi.patientsservice.repositories.PatientRepository;
import com.projetsuivi.patientsservice.services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.projetsuivi.patientsservice.utils.ObjectAsJsonStrings.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = true)
public class PatientsTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PatientService patientService;

    @Autowired
    PatientRepository patientRepository;

    @Test
    public void createAPI() throws Exception {


        PatientDto patientDto = new PatientDto(1l, "firstcreate", "lastcreate", LocalDateTime.now(),
                Genras.M, new Adresse(1l, 123, "dastreetEEE", null), "0909090909");


        mockMvc.perform(put("/patients/patient/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(patientDto)))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.firstName").value("firstcreate"))
                .andExpect(jsonPath("$.lastName").value("lastcreate"));
    }

    @Test
    public void getAPI() throws Exception {


        PatientDto patientDto = new PatientDto(1l, "firstget", "lastget", LocalDateTime.now(),
                Genras.M, new Adresse(1l, 123, "dastreetEEE", null), "0909090909");

        patientService.createPatient(patientDto);
        Optional<PatientEntity> patientEntityOption = patientRepository.findByFirstNameAndLastName(patientDto.getFirstName(), patientDto.getLastName());


        if(patientEntityOption.isPresent()) {
            PatientEntity patientEntity = patientEntityOption.get();

            mockMvc.perform(get("/patients/patient/{id}", patientEntity.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(patientDto)))
                    .andDo(print())
                    .andExpect(status().isOk()).andExpect(content()
                            .contentType("application/json"))
                    .andExpect(jsonPath("$.firstName").value("firstget"))
                    .andExpect(jsonPath("$.lastName").value("lastget"));
        } else {
            assert false;
        }
    }

    @Test
    public void updateAPI() throws Exception {


        PatientDto patientDto = new PatientDto(1l, "firstupdate", "lastupdate", LocalDateTime.now(),
                Genras.M, new Adresse(1l, 123, "dastreetEEE", null), "0909090909");


        mockMvc.perform(put("/patients/patient/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(patientDto)))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.firstName").value("firstupdate"))
                .andExpect(jsonPath("$.lastName").value("lastupdate"));
    }

    @Test
    public void deleteAPI() throws Exception {


        PatientDto patientDto = new PatientDto(1l, "firstdelete", "lastdelete", LocalDateTime.now(),
                Genras.M, new Adresse(1l, 123, "dastreetEEE", null), "0909090909");


        mockMvc.perform(put("/patients/patient/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(patientDto)))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.firstName").value("firstdelete"))
                .andExpect(jsonPath("$.lastName").value("lastdelete"));
    }
}
