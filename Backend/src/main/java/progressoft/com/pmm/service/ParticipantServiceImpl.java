package progressoft.com.pmm.service;

import com.example.model.Participant;
import com.example.model.Type;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressoft.com.pmm.entity.ParticipantEntity;
import progressoft.com.pmm.exception.ParticipantAlreadyExistsException;
import progressoft.com.pmm.exception.ParticipantNotFoundException;
import progressoft.com.pmm.mapper.ParticipantMapper;
import progressoft.com.pmm.repository.ParticipantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper mapper;

    @Override
    public Participant fetchByCode(String code) {
        ParticipantEntity savedParticipant = participantRepository.findByCode(code)
                .orElseThrow(() -> new ParticipantNotFoundException("Participant with code [" + code + "] not found!"));
        return mapper.toApp(savedParticipant);
    }

    @Override
    public List<Participant> fetchAllParticipants() {
        return participantRepository.findAll()
                .stream()
                .map(mapper::toApp)
                .toList();
    }

    @Override
    public List<String> fetchDirectCode() {
        return participantRepository.findByType(Type.DIRECT)
                .stream()
                .map(ParticipantEntity::getCode)
                .toList();
    }

    @Override
    @Transactional
    public void createParticipant(Participant participant) {
        validateCode(participant.getCode());
        validateBic(participant.getBic());

        ParticipantEntity entity = mapper.toEntity(participant);

        participantRepository.save(entity);
    }

    private void validateBic(String bic) {
        participantRepository.findByBic(bic)
                .ifPresent(e -> {
                     throw new ParticipantAlreadyExistsException("Participant with bic [" + bic + "] already exists");
                });
    }

    private void validateCode(String code) {
        participantRepository.findByCode(code)
                .ifPresent(e -> {
                    throw new ParticipantAlreadyExistsException("Participant with code [" + code + "] already exists");
                });
    }

    @Transactional
    @Override
    public Participant updateParticipant(Participant participant) {
        String code = participant.getCode();
        String bic = participant.getBic();

        ParticipantEntity existingParticipant = participantRepository.findByCode(code)
                .orElseThrow(() -> new ParticipantNotFoundException("Participant with code [" + code + "] not found"));

        if(!(bic.matches(existingParticipant.getBic()))){
            validateBic(bic);
        }

        existingParticipant.setBic(participant.getBic());
        existingParticipant.setName(participant.getName());
        existingParticipant.setShortName(participant.getShortName());
        existingParticipant.setLogo(participant.getLogo());
        existingParticipant.setSettlementBank(participant.getSettlementBank());
        existingParticipant.setType(participant.getType());

        participantRepository.save(existingParticipant);

        return mapper.toApp(existingParticipant);
    }
}

