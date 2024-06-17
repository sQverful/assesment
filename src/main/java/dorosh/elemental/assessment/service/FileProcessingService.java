package dorosh.elemental.assessment.service;

import dorosh.elemental.assessment.entity.EntryFileOutcomeMapper;
import dorosh.elemental.assessment.exception.ErrorsException;
import dorosh.elemental.assessment.mapper.EntryFileModelMapper;
import dorosh.elemental.assessment.openapi.model.EntryFileOutcome;
import dorosh.elemental.assessment.openapi.model.Error;
import dorosh.elemental.assessment.validator.InputFileValidator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileProcessingService {

    private final EntryFileModelMapper entryFileModelMapper;
    private final EntryFileOutcomeMapper entryFileOutcomeMapper;
    private final InputFileValidator inputFileValidator;

    public List<EntryFileOutcome> convertToJson(@NonNull Resource file) {
        final var errors = new ArrayList<Error>();
        inputFileValidator.validate(file, errors);
        if (!errors.isEmpty()){
            throw new ErrorsException(errors);
        }

        List<EntryFileOutcome> entryFileOutcomes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            reader.lines()
                    .map(line -> line.split("\\n"))
                    .flatMap(Arrays::stream)
                    .map(entryFileModelMapper::toEntryFileModel)
                    .toList()
                    .forEach(entryFileModel -> entryFileOutcomes.add(entryFileOutcomeMapper.toEntryFileOutcomeDto(entryFileModel)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
        return entryFileOutcomes;
    }
}
