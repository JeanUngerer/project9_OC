package com.projetsuivi.dangerservice.servicesTests;

import com.projetsuivi.dangerservice.constants.DangerStatus;
import com.projetsuivi.dangerservice.constants.Genras;
import com.projetsuivi.dangerservice.dtos.NotesDto;
import com.projetsuivi.dangerservice.dtos.PatientDto;
import com.projetsuivi.dangerservice.mappers.DangerWordsMapper;
import com.projetsuivi.dangerservice.models.Adresse;
import com.projetsuivi.dangerservice.proxies.NotesProxy;
import com.projetsuivi.dangerservice.proxies.PatientProxy;
import com.projetsuivi.dangerservice.services.DangerWordsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DangerServiceTests {

    private static NotesProxy notesProxyMock = mock(NotesProxy.class);
    private static PatientProxy patientProxyMock = mock(PatientProxy.class);

    @Autowired
    private DangerWordsMapper dangerWordsMapper;
    private static DangerWordsService dangerWordsService;

    @Test
    public void calculateDangerWordsNone() {

        List<NotesDto> notesProxyMockReturnList = new ArrayList<>();
        notesProxyMockReturnList.add(new NotesDto("_id", 1L, LocalDateTime.of(1966, Month.DECEMBER, 31, 0, 0),
                "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé"));

        when(notesProxyMock.getNotesByPatientId(anyLong())).thenReturn(notesProxyMockReturnList);


        when(patientProxyMock.getOnePatient(anyLong())).thenReturn(
                new PatientDto(1l, "firstname", "lastname", LocalDateTime.of(1966, Month.DECEMBER, 31, 0, 0), Genras.F,
                        new Adresse(1L, 12, "daStreet", null), "0909090909"));


        DangerWordsService dangerWordsService1 = new DangerWordsService(notesProxyMock, patientProxyMock, dangerWordsMapper);


        String result = dangerWordsService1.getDangerStatus(1l);

        assertEquals(DangerStatus.NONE.getValue(), result);

    }

    @Test
    public void calculateDangerWordsBorderline() {

        List<NotesDto> notesProxyMockReturnList = new ArrayList<>();
        notesProxyMockReturnList.add(new NotesDto("_id", 1L, LocalDateTime.of(1966, Month.DECEMBER, 31, 0, 0),
                "Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement"));
        notesProxyMockReturnList.add(new NotesDto("_id", 1L, LocalDateTime.of(1966, Month.DECEMBER, 31, 0, 0),
                "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale"));

        when(notesProxyMock.getNotesByPatientId(anyLong())).thenReturn(notesProxyMockReturnList);


        when(patientProxyMock.getOnePatient(anyLong())).thenReturn(
                new PatientDto(1l, "firstname", "lastname", LocalDateTime.of(1945, Month.JUNE, 24, 0, 0), Genras.M,
                        new Adresse(1L, 12, "daStreet", null), "0909090909"));


        DangerWordsService dangerWordsService1 = new DangerWordsService(notesProxyMock, patientProxyMock, dangerWordsMapper);


        String result = dangerWordsService1.getDangerStatus(1l);

        assertEquals(DangerStatus.BORDERLINE.getValue(), result);

    }

    @Test
    public void calculateDangerWordsDanger() {

        List<NotesDto> notesProxyMockReturnList = new ArrayList<>();
        notesProxyMockReturnList.add(new NotesDto("_id", 1L, LocalDateTime.of(1966, Month.DECEMBER, 31, 0, 0),
                "Le patient déclare qu'il fume depuis peu"));
        notesProxyMockReturnList.add(new NotesDto("_id", 1L, LocalDateTime.of(1966, Month.DECEMBER, 31, 0, 0),
                "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé"));

        when(notesProxyMock.getNotesByPatientId(anyLong())).thenReturn(notesProxyMockReturnList);


        when(patientProxyMock.getOnePatient(anyLong())).thenReturn(
                new PatientDto(1l, "firstname", "lastname", LocalDateTime.of(2004, Month.JUNE, 18, 0, 0), Genras.M,
                        new Adresse(1L, 12, "daStreet", null), "0909090909"));


        DangerWordsService dangerWordsService1 = new DangerWordsService(notesProxyMock, patientProxyMock, dangerWordsMapper);


        String result = dangerWordsService1.getDangerStatus(1l);

        assertEquals(DangerStatus.INDANGER.getValue(), result);

    }

    @Test
    public void calculateDangerWordsEarlyOnSet() {

        List<NotesDto> notesProxyMockReturnList = new ArrayList<>();
        notesProxyMockReturnList.add(new NotesDto("_id", 1L, LocalDateTime.of(1966, Month.DECEMBER, 31, 0, 0),
                "Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments"));
        notesProxyMockReturnList.add(new NotesDto("_id", 1L, LocalDateTime.of(1966, Month.DECEMBER, 31, 0, 0),
                "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"));
        notesProxyMockReturnList.add(new NotesDto("_id", 1L, LocalDateTime.of(1966, Month.DECEMBER, 31, 0, 0),
                "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé"));
        notesProxyMockReturnList.add(new NotesDto("_id", 1L, LocalDateTime.of(1966, Month.DECEMBER, 31, 0, 0),
                "Taille, Poids, Cholestérol, Vertige et Réaction"));

        when(notesProxyMock.getNotesByPatientId(anyLong())).thenReturn(notesProxyMockReturnList);


        when(patientProxyMock.getOnePatient(anyLong())).thenReturn(
                new PatientDto(1l, "firstname", "lastname", LocalDateTime.of(2002, Month.JUNE, 28, 0, 0), Genras.F,
                        new Adresse(1L, 12, "daStreet", null), "0909090909"));


        DangerWordsService dangerWordsService1 = new DangerWordsService(notesProxyMock, patientProxyMock, dangerWordsMapper);


        String result = dangerWordsService1.getDangerStatus(1l);

        assertEquals(DangerStatus.EARLYONSET.getValue(), result);

    }

}
