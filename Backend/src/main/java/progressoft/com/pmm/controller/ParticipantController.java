package progressoft.com.pmm.controller;

import com.example.api.ParticipantsControllerApi;
import com.example.model.Participant;
import com.example.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import progressoft.com.pmm.service.ParticipantService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParticipantController implements ParticipantsControllerApi {

    private final ParticipantService participantService;

    @Override
    public ResponseEntity<ServerResponse> createParticipant(Participant participant) {
        participantService.createParticipant(participant);
        return ResponseEntity.ok(buildResponse(participant, "Participant was saved successfully"));
    }

    @Override
    public ResponseEntity<List<Participant>> fetchAllParticipants() {
        List<Participant> participants = participantService.fetchAllParticipants();
        return ResponseEntity.ok(participants);
    }

    @Override
    public ResponseEntity<List<String>> fetchDirectCodes() {
        List<String> codes = participantService.fetchDirectCode();
        return ResponseEntity.ok(codes);
    }

    @Override
    public ResponseEntity<ServerResponse> fetchParticipant(String code) {
        Participant savedParticipant = participantService.fetchByCode(code);
        return ResponseEntity.ok(buildResponse(savedParticipant, "Fetched participant successfully"));
    }

    @Override
    public ResponseEntity<ServerResponse> updateParticipant(String code, Participant participant) {
        participant.setCode(code);
        Participant updatedParticipant = participantService.updateParticipant(participant);
        return ResponseEntity.ok(buildResponse(updatedParticipant, "Participant updated successfully"));
    }

    private ServerResponse buildResponse(Participant participant, String message) {
        return ServerResponse.builder()
                .statusCode(200)
                .message(message)
                .participant(participant)
                .build();
    }
}