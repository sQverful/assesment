package dorosh.elemental.assessment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class EntryFileModel {
    private UUID uuid;
    private String id;
    private String name;
    private String likes;
    private String transport;
    private double avgSpeed;
    private double topSpeed;
}
