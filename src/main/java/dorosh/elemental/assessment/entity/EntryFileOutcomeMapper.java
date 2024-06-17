package dorosh.elemental.assessment.entity;

import dorosh.elemental.assessment.openapi.model.EntryFileOutcome;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class EntryFileOutcomeMapper {

    public EntryFileOutcome toEntryFileOutcomeDto(@NonNull EntryFileModel entryFileModel){
        return new EntryFileOutcome()
                .name(entryFileModel.getName())
                .transport(entryFileModel.getTransport())
                .topSpeed(entryFileModel.getTopSpeed());
    }
}
