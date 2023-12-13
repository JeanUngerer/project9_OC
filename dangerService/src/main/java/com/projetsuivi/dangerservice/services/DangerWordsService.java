package com.projetsuivi.dangerservice.services;


import com.projetsuivi.dangerservice.constants.DangerStatus;
import com.projetsuivi.dangerservice.constants.DangerWords;
import com.projetsuivi.dangerservice.constants.Genras;
import com.projetsuivi.dangerservice.mappers.DangerWordsMapper;
import com.projetsuivi.dangerservice.models.Notes;
import com.projetsuivi.dangerservice.models.Patient;
import com.projetsuivi.dangerservice.proxies.NotesProxy;
import com.projetsuivi.dangerservice.proxies.PatientProxy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class DangerWordsService {

    NotesProxy notesProxy;

    PatientProxy patientProxy;

    DangerWordsMapper dangerWordsMapper;

    public DangerWordsService(NotesProxy notesProxy, PatientProxy patientProxy, DangerWordsMapper dangerWordsMapper) {
        this.notesProxy = notesProxy;
        this.patientProxy = patientProxy;
        this.dangerWordsMapper = dangerWordsMapper;
    }

/*
        ● aucun risque (None) : Le dossier du patient ne contient aucune note du médecin
        contenant les déclencheurs (terminologie) ;
        ● risque limité (Borderline) : Le dossier du patient contient entre deux et cinq
        déclencheurs et le patient est âgé de plus de 30 ans ;
        ● danger (In Danger) : Dépend de l'âge et du sexe du patient. Si le patient est un homme
        de moins de 30 ans, alors trois termes déclencheurs doivent être présents. Si le patient
        est une femme et a moins de 30 ans, il faudra quatre termes déclencheurs. Si le patient
        a plus de 30 ans, alors il en faudra six ou sept ;
        ● apparition précoce (Early onset) : Encore une fois, cela dépend de l'âge et du sexe. Si
        le patient est un homme de moins de 30 ans, alors au moins cinq termes déclencheurs
        sont nécessaires. Si le patient est une femme et a moins de 30 ans, il faudra au moins
        sept termes déclencheurs. Si le patient a plus de 30 ans, alors il en faudra huit ou plus.
 */

    public String getDangerStatus(Long patientId) {

        Patient patient = dangerWordsMapper.dtoToPatient(patientProxy.getOnePatient(patientId));
        List<Notes> allNotes = dangerWordsMapper.dtosToNotes(notesProxy.getNotesByPatientId(patientId));

        // Extract all trigger words from the notes
        List<String> triggerWords = allNotes.stream()
                .map(Notes::getNote)
                .flatMap(note -> Arrays.stream(note.split("\\s+")))
                .filter(word -> Arrays.stream(DangerWords.values()).anyMatch(dangerWords -> word == dangerWords.getValue() ))
                .toList();

        log.info("Trigger words list : " + triggerWords);
        System.out.println("Trigger words list : " + triggerWords);
        // Calculate danger status based on conditions
        int numTriggerWords = triggerWords.size();
        log.info("Trigger words list size : " + numTriggerWords);
        System.out.println("Trigger words list size : " + numTriggerWords);
        int age = LocalDateTime.now().getYear() - patient.getBirthdate().getYear();
        log.info("patient : " + patient.toString());
        System.out.println("patient : " + patient.toString());
        log.info("all notes : " + allNotes.toString());
        System.out.println("all notes : " + allNotes.toString());
        log.info("patient age : " + age);
        System.out.println("patient age : " + age);
        Genras gender = patient.getGenra();
        log.info("patient gender : " + gender);
        System.out.println("patient gender : " + gender);
        String dangerStatus = DangerStatus.NONE.getValue();

        if (numTriggerWords == 0) {
            dangerStatus = DangerStatus.NONE.getValue();
        } else if (numTriggerWords >= 2 && numTriggerWords <= 5 && age > 30) {
            dangerStatus = DangerStatus.BORDERLINE.getValue();
        } else if (age < 30 && ((gender == Genras.M && numTriggerWords >= 3 && numTriggerWords < 5) || (gender == Genras.F && numTriggerWords >= 4&& numTriggerWords < 7))
                || (age >= 30 && (numTriggerWords == 6 || numTriggerWords == 7))) {
            dangerStatus = DangerStatus.INDANGER.getValue();
        } else if ((age > 30 && numTriggerWords >= 8) || (age <=30 && (gender == Genras.M && numTriggerWords >= 5) || (gender == Genras.F && numTriggerWords >= 7))) {
            dangerStatus = DangerStatus.EARLYONSET.getValue();
        }

        return dangerStatus;
    }

}
