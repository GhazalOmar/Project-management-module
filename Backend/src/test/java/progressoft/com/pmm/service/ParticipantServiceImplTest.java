package progressoft.com.pmm.service;

import com.example.model.Participant;
import com.example.model.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import progressoft.com.pmm.entity.ParticipantEntity;
import progressoft.com.pmm.exception.ParticipantAlreadyExistsException;
import progressoft.com.pmm.exception.ParticipantNotFoundException;
import progressoft.com.pmm.mapper.ParticipantMapper;
import progressoft.com.pmm.mapper.ParticipantMapperImpl;
import progressoft.com.pmm.repository.ParticipantRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParticipantServiceImplTest {
    private ParticipantRepository repository;
    private ParticipantServiceImpl service;

    @BeforeEach
    public void setup(){
        repository = mock(ParticipantRepository.class);
        ParticipantMapper mapper = new ParticipantMapperImpl();
        service = new ParticipantServiceImpl(repository, mapper);
    }

    @Test
    void givenValidCodeWhenFetchingThenSuccess(){
        String code = "TESTCODE";
        String bic = "BIC";

        ParticipantEntity entity = buildEntity(code, bic);
        Participant expected = buildParticipant(code, bic);

        when(repository.findByCode(code)).thenReturn(Optional.of(entity));

        Participant actual = service.fetchByCode(code);

        verify(repository).findByCode(code);

        assertEquals(expected, actual);
    }

    @Test
    void givenInvalidCodeWhenFetchingThenFail(){
        String code = "TESTCODE";

        when(repository.findByCode(code)).thenReturn(Optional.empty());

        ParticipantNotFoundException exception = assertThrows(ParticipantNotFoundException.class, () ->
                service.fetchByCode(code));

        assertEquals("Participant with code [" + code + "] not found!", exception.getMessage());
    }

    @Test
    void givenValidParticipantWhenFetchingThenSuccess(){
        ParticipantEntity entity1 = buildEntity("TESTCODE1", "BIC1");
        ParticipantEntity entity2 = buildEntity("TESTCODE2", "BIC2");
        ParticipantEntity entity3 = buildEntity("TESTCODE3", "BIC3");
        ParticipantEntity entity4 = buildEntity("TESTCODE4", "BIC4");

        when(repository.findAll()).thenReturn(List.of(entity1, entity2, entity3, entity4));

        List<Participant> participants = service.fetchAllParticipants();

        verify(repository).findAll();

        assertNotNull(participants);
    }
    @Test
    void givenValidDirectCodeWhenFetchingThenSuccess(){
        ParticipantEntity entity1 = buildEntity("TESTCODE1", "BIC1");
        ParticipantEntity entity2 = buildEntity("TESTCODE2", "BIC2");
        entity1.setType(Type.DIRECT);
        entity2.setType(Type.DIRECT);

        when(repository.findByType(Type.DIRECT)).thenReturn(List.of(entity1, entity2));

        List<String> codes = service.fetchDirectCode();

        verify(repository).findByType(Type.DIRECT);

        assertNotNull(codes);
    }

    @Test
    void givenValidRequestWhenCreatingThenSuccess(){
        String code = "CODE";
        String bic = "BIC";

        ParticipantEntity entity = buildEntity(code, bic);
        Participant participant = buildParticipant(code, bic);

        when(repository.findByCode(code)).thenReturn(Optional.empty());
        when(repository.findByBic(bic)).thenReturn(Optional.empty());

        service.createParticipant(participant);

        verify(repository).findByCode(code);
        verify(repository).findByBic(bic);

        assertNotNull(entity);
    }

    @Test
    void givenExistedBicCodeWhenFindingThenFail(){
        String code = "CODE";
        String bic = "BIC";

        ParticipantEntity entity = buildEntity(code, bic);
        Participant participant =buildParticipant(code, bic);

        when(repository.findByBic(bic)).thenReturn(Optional.of(entity));

        ParticipantAlreadyExistsException exception = assertThrows(ParticipantAlreadyExistsException.class, () ->
                service.createParticipant(participant));

        assertEquals("Participant with bic [" + bic + "] already exists", exception.getMessage());
    }

    @Test
    void givenExistedCodeValueWhenFindingThenFail(){
        String code = "CODE";
        String bic = "BIC";

        ParticipantEntity entity = buildEntity(code, bic);
        Participant participant =buildParticipant(code, bic);

        when(repository.findByCode(code)).thenReturn(Optional.of(entity));

        ParticipantAlreadyExistsException exception = assertThrows(ParticipantAlreadyExistsException.class, () ->
                service.createParticipant(participant));

        assertEquals("Participant with code [" + code + "] already exists", exception.getMessage());
    }

    @Test
    void givenValidRequestWhenUpdatingThenSuccess(){
        String code = "EXISTED";
        String bic = "BIC";

        Participant participant = buildParticipant(code, bic);
        ParticipantEntity entity = buildEntity(code, bic);

        when(repository.findByCode(code)).thenReturn(Optional.of(entity));

        Participant updated = service.updateParticipant(participant);

        verify(repository).save(entity);

        assertEquals(participant, updated);
    }

    @Test
    void givenInvalidRequestWhenUpdatingThenFail(){
        String code = "EXISTED";
        String bic = "BIC";

        Participant participant = buildParticipant(code, bic);

        when(repository.findByCode(code)).thenReturn(Optional.empty());

        ParticipantNotFoundException exception = assertThrows(ParticipantNotFoundException.class, () ->{
            service.updateParticipant(participant);
        });

        assertEquals("Participant with code [" + code + "] not found", exception.getMessage());
    }

    @Test
    void givenExistedBicValueWhenUpdatingThenFail(){
        ParticipantEntity entity1 = buildEntity("TESTCODE1", "BIC1");
        ParticipantEntity entity2 = buildEntity("TESTCODE2", "BIC2");

        Participant participant1 = buildParticipant(entity1.getCode(), entity2.getBic());

        when(repository.findByCode(participant1.getCode())).thenReturn(Optional.of(entity1));
        when(repository.findByBic(participant1.getBic())).thenReturn(Optional.of(entity2));

        ParticipantAlreadyExistsException exception = assertThrows(ParticipantAlreadyExistsException.class, () -> {
            service.updateParticipant(participant1);
        });

        assertEquals("Participant with bic [" + participant1.getBic() + "] already exists", exception.getMessage());
    }

    private ParticipantEntity buildEntity(String code, String bic){
        return ParticipantEntity.builder()
                .code(code)
                .bic(bic)
                .name("name")
                .shortName("NAME")
                .logo("logo")
                .type(Type.DIRECT)
                .build();
    }

    private Participant buildParticipant(String code, String bic){
        return Participant.builder()
                .code(code)
                .bic(bic)
                .name("name")
                .shortName("NAME")
                .logo("logo")
                .type(Type.DIRECT)
                .build();
    }
}
