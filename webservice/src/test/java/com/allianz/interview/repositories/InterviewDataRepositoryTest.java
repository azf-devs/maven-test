package com.allianz.interview.repositories;

import com.allianz.interview.webservice.config.WebserviceConfiguration;
import com.allianz.interview.dto.InterviewData;
import com.allianz.interview.webservice.exception.DataNotFoundException;
import com.allianz.interview.webservice.repositories.InterviewDataRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@Import(WebserviceConfiguration.class)
public class InterviewDataRepositoryTest {

    @Autowired
    private InterviewDataRepository interviewDataRepository;

    private List<InterviewData> expectedInterviewData;

    @BeforeEach
    public void setup() throws URISyntaxException, IOException {
        expectedInterviewData = new ArrayList<>();

        expectedInterviewData.add(InterviewData.builder().id(1).label("allianz").date(LocalDate.parse("2021-10-01")).build());
        expectedInterviewData.add(InterviewData.builder().id(2).label("toto").date(LocalDate.parse("2021-10-02")).build());
        expectedInterviewData.add(InterviewData.builder().id(3).label("oto").date(LocalDate.parse("2021-10-01")).build());
        expectedInterviewData.add(InterviewData.builder().id(4).label("123456654321").date(LocalDate.parse("2021-10-03")).build());
        expectedInterviewData.add(InterviewData.builder().id(5).label("kayak").date(LocalDate.parse("2021-10-04")).build());
        expectedInterviewData.add(InterviewData.builder().id(6).label("radar").date(LocalDate.parse("2021-10-05")).build());
        expectedInterviewData.add(InterviewData.builder().id(7).label("sagas").date(LocalDate.parse("2021-10-02")).build());

        interviewDataRepository.init();

    }


    @Test
    @DisplayName("Vérifier que la repository est correctement initialisée avec le contenu du fichier interviewData.json")
    public void testInitInterviewData() {

        List<InterviewData> listInterviewData = interviewDataRepository.getAll();

        Assertions.assertNotNull(listInterviewData);
        Assertions.assertEquals(7, listInterviewData.size());
        for (int i = 0; i < expectedInterviewData.size(); i++) {
            Assertions.assertEquals(expectedInterviewData.get(i), listInterviewData.get(i));
        }

    }

    @Test
    @DisplayName("Vérifier la lecture d'un élément interviewData via son id")
    public void testGettingInterviewDataById() {

        InterviewData interviewData = interviewDataRepository.get(3);

        Assertions.assertNotNull(interviewData);
        Assertions.assertEquals(3, interviewData.getId());
        Assertions.assertEquals("oto", interviewData.getLabel());
        Assertions.assertEquals(LocalDate.parse("2021-10-01"), interviewData.getDate());
    }


    @Test
    @DisplayName("Vérifier que le nouvel élément interviewData a été bien sauvegardé")
    public void testAddingNewInterviewData() {
        InterviewData newInterviewData = InterviewData.builder().label("new interview Data").build();
        InterviewData addedInterviewData = interviewDataRepository.persist(newInterviewData);


        Assertions.assertNotNull(addedInterviewData);
        Assertions.assertEquals(8, addedInterviewData.getId());
        Assertions.assertEquals(LocalDate.now(), addedInterviewData.getDate());


        List<InterviewData> listInterviewData = interviewDataRepository.getAll();
        Assertions.assertEquals(8, listInterviewData.size());
        Assertions.assertEquals(listInterviewData.get(7), addedInterviewData);

    }


    @Test
    @DisplayName("Vérifier que l'élément interviewData a été bien modifié")
    public void testUpdatingInterviewData() {
        InterviewData interviewDataToUpdate = InterviewData.builder().id(5).label("updated interview Data").date(LocalDate.now()).build();

        InterviewData updatedInterviewData = interviewDataRepository.update(interviewDataToUpdate);
        Assertions.assertNotNull(updatedInterviewData);
        Assertions.assertEquals(5, updatedInterviewData.getId());
        Assertions.assertEquals("updated interview Data", updatedInterviewData.getLabel());
        Assertions.assertEquals(LocalDate.now(), updatedInterviewData.getDate());

        List<InterviewData> listInterviewData = interviewDataRepository.getAll();
        Assertions.assertEquals(listInterviewData.get(4), updatedInterviewData);

    }


    @Test
    @DisplayName("Vérifier que l'élément interviewData a été bien supprimé")
    public void testRemovingInterviewData() {
        boolean isRemoved = interviewDataRepository.remove(7);

        Assertions.assertTrue(isRemoved);

        List<InterviewData> listInterviewData = interviewDataRepository.getAll();
        Assertions.assertEquals(6, listInterviewData.size());
        Assertions.assertTrue(listInterviewData.stream().noneMatch(interviewData -> interviewData.getId().equals(7)));

    }

    @Test
    @DisplayName("Vérifier qu'une exception est levée lors d'une tentative de lecture d'un element inexistant")
    public void testGettingMissingInterviewData() {
        DataNotFoundException thrownException = Assertions.assertThrows(DataNotFoundException.class, () -> interviewDataRepository.get(10));
        Assertions.assertEquals("DATA_NOT_FOUND", thrownException.getCode());
        Assertions.assertEquals("Aucune donnée ne correspond à l'identifiant demandé", thrownException.getMessage());

    }
}
