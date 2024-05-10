package progressoft.com.pmm.mapper;

import com.example.model.Participant;
import com.example.model.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import progressoft.com.pmm.entity.ParticipantEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParticipantMapperTest {
    private ParticipantMapper mapper;

    @BeforeEach
    public void setup(){
        mapper = new ParticipantMapperImpl();
    }

    @Test
    void should_be_valid_when_maps_to_entity(){
        String code = "CODE";
        String bic = "bic";

        Participant participant = buildParticipant(code, bic);

        ParticipantEntity entity = mapper.toEntity(participant);

        assertEquals(participant.getCode(), entity.getCode());
        assertEquals(participant.getLogo(), entity.getLogo());
        assertEquals(participant.getName(), entity.getName());
        assertEquals(participant.getType(), entity.getType());
        assertEquals(participant.getCode(), entity.getCode());
        assertEquals(participant.getShortName(), entity.getShortName());
        assertEquals(participant.getSettlementBank(), entity.getSettlementBank());
    }

    @Test
    void should_be_valid_when_maps_to_participant(){
        String code = "CODE";
        String bic = "bic";

        ParticipantEntity entity = buildEntity(code, bic);

        Participant participant = mapper.toApp(entity);

        assertEquals(entity.getCode(), participant.getCode());
        assertEquals(entity.getLogo(), participant.getLogo());
        assertEquals(entity.getName(), participant.getName());
        assertEquals(entity.getType(), participant.getType());
        assertEquals(entity.getCode(), participant.getCode());
        assertEquals(entity.getShortName(), participant.getShortName());
        assertEquals(entity.getSettlementBank(), participant.getSettlementBank());
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
}