package api;

import api.dto.in.CreateCandidate;
import api.dto.in.UpdateCandidate;
import domain.Candidate;
import domain.CandidateService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest

class CandidateApiTest {
    @Inject
    CandidateApi candidateApi;

    @InjectMock
    CandidateService candidateService;

    @Test
    void create() {
        CreateCandidate dto = Instancio.create(CreateCandidate.class);
        ArgumentCaptor<Candidate> captor = ArgumentCaptor.forClass(Candidate.class);
        candidateApi.create(dto);

        verify(candidateService).save(captor.capture());
        verifyNoMoreInteractions(candidateService);

        Candidate candidate = captor.getValue();

        assertEquals(dto.photo(), candidate.photo());
        assertEquals(dto.givenName(), candidate.givenName());
        assertEquals(dto.familyName(), candidate.familyName());
        assertEquals(dto.email(), candidate.email());
        assertEquals(dto.phone(), candidate.phone());
        assertEquals(dto.jobTitle(), candidate.jobTitle());
    }

   @Test
  void update(){
      String id = UUID.randomUUID().toString();
      UpdateCandidate dto = Instancio.create(UpdateCandidate.class);
      Candidate candidate = dto.toDomain(id);

      ArgumentCaptor<Candidate> captor = ArgumentCaptor.forClass(Candidate.class);
      when(candidateService.findById(id)).thenReturn(candidate);

      var response = candidateApi.update(id, dto);

      verify(candidateService).save(captor.capture());
      verify(candidateService).findById(id);
      verifyNoMoreInteractions(candidateService);

      assertEquals(api.dto.out.Candidate.fromDomain(candidate), response);
    }
   @Test
  void list(){
      var candidates = Instancio.stream(Candidate.class).limit(10).toList();

      when(candidateService.findAll()).thenReturn(candidates);

      var response = candidateApi.list();

      verify(candidateService).findAll();
      verifyNoMoreInteractions(candidateService);

      assertEquals(candidates.stream().map(api.dto.out.Candidate::fromDomain).toList(), response);
    }
}