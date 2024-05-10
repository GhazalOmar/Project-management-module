package progressoft.com.pmm.controller;

import com.example.model.Type;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import progressoft.com.pmm.entity.ParticipantEntity;
import progressoft.com.pmm.repository.ParticipantRepository;
import progressoft.com.pmm.service.ParticipantService;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ParticipantControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testCreateParticipant() throws Exception {
        ParticipantEntity entity = new ParticipantEntity("203555", "GGLLBB10", "name", "SHRTN", "SGVsbG8sIFdvcmxkIQ==", null, Type.DIRECT);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/participants/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entity))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.message").value("Participant was saved successfully"))
                .andExpect(jsonPath("$.participant").exists());
    }

    @Test
    void testFetchAllParticipant() throws Exception {
        ParticipantEntity entity1 = new ParticipantEntity("203555", "GGLLBB10", "name", "SHRTN", "SGVsbG8sIFdvcmxkIQ==", null, Type.DIRECT);
        ParticipantEntity entity2 = new ParticipantEntity("203550", "GGLLBB11", "name", "SHRTN", "SGVsbG8sIFdvcmxkIQ==", null, Type.DIRECT);

        participantRepository.save(entity1);
        participantRepository.save(entity2);

        mockMvc.perform(get("/api/v1/participants/view")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].code").value("203555"))
                .andExpect(jsonPath("$[1].code").value("203550"));
    }

    @Test
    void testFetchDirectCodes() throws Exception{
        ParticipantEntity entity1 = new ParticipantEntity("203555", "GGLLBB10", "name", "SHRTN", "SGVsbG8sIFdvcmxkIQ==", null, Type.DIRECT);
        ParticipantEntity entity2 = new ParticipantEntity("203550", "GGLLBB11", "name", "SHRTN", "SGVsbG8sIFdvcmxkIQ==", null, Type.DIRECT);

        participantRepository.save(entity1);
        participantRepository.save(entity2);

        mockMvc.perform(get("/api/v1/participants/fetch-codes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("203555"))
                .andExpect(jsonPath("$[1]").value("203550"));
    }

    @Test
    void testFetchByCode() throws Exception{
        ParticipantEntity entity = new ParticipantEntity("203555", "GGLLBB10", "name", "SHRTN", "SGVsbG8sIFdvcmxkIQ==", null, Type.DIRECT);
        ObjectMapper objectMapper = new ObjectMapper();

        participantRepository.save(entity);

        mockMvc.perform(get("/api/v1/participants/view/203555")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entity))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.message").value("Fetched participant successfully"))
                .andExpect(jsonPath("$.participant").exists());


    }
}