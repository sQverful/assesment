package dorosh.elemental.assessment.mapper;

import dorosh.elemental.assessment.entity.EntryFileModel;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EntryFileModelMapper {

    public EntryFileModel toEntryFileModel(@NonNull String row){
        String[] dataParts = row.split("\\|");
        return EntryFileModel.builder()
                .uuid(UUID.fromString(dataParts[0]))
                .id(dataParts[1])
                .name(dataParts[2])
                .likes(dataParts[3])
                .transport(dataParts[4])
                .avgSpeed(Double.parseDouble(dataParts[5]))
                .topSpeed(Double.parseDouble(dataParts[6]))
                .build();
    }
}
