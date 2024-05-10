package progressoft.com.pmm.mapper;
import com.example.model.Participant;
import org.mapstruct.Mapper;
import progressoft.com.pmm.entity.ParticipantEntity;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    ParticipantEntity toEntity(Participant participant);

    Participant toApp(ParticipantEntity participantEntity);
}
