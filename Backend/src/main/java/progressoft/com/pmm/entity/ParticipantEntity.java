package progressoft.com.pmm.entity;

import com.example.model.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "participants")
public class ParticipantEntity {

    @Id
    private String code;
    private String bic;
    private String name;
    private String shortName;

    @Lob
    private String logo;
    private String settlementBank;

    @Enumerated(EnumType.STRING)
    private Type type;
}
