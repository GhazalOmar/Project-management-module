package progressoft.com.pmm.service;

import com.example.model.Participant;

import java.util.List;

public interface ParticipantService {
    Participant fetchByCode(String code);
    List<Participant> fetchAllParticipants();
    List<String> fetchDirectCode();
    void createParticipant(Participant participant);
    Participant updateParticipant(Participant participant);
}
