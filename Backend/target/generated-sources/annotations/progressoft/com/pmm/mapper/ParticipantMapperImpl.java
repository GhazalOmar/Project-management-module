package progressoft.com.pmm.mapper;

import com.example.model.Participant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import progressoft.com.pmm.entity.ParticipantEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-10T23:51:45+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class ParticipantMapperImpl implements ParticipantMapper {

    @Override
    public ParticipantEntity toEntity(Participant participant) {
        if ( participant == null ) {
            return null;
        }

        ParticipantEntity.ParticipantEntityBuilder participantEntity = ParticipantEntity.builder();

        participantEntity.code( participant.getCode() );
        participantEntity.bic( participant.getBic() );
        participantEntity.name( participant.getName() );
        participantEntity.shortName( participant.getShortName() );
        participantEntity.logo( participant.getLogo() );
        participantEntity.settlementBank( participant.getSettlementBank() );
        participantEntity.type( participant.getType() );

        return participantEntity.build();
    }

    @Override
    public Participant toApp(ParticipantEntity participantEntity) {
        if ( participantEntity == null ) {
            return null;
        }

        Participant.ParticipantBuilder participant = Participant.builder();

        participant.code( participantEntity.getCode() );
        participant.bic( participantEntity.getBic() );
        participant.name( participantEntity.getName() );
        participant.shortName( participantEntity.getShortName() );
        participant.type( participantEntity.getType() );
        participant.logo( participantEntity.getLogo() );
        participant.settlementBank( participantEntity.getSettlementBank() );

        return participant.build();
    }
}
