package ua.goit.service.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class YoungestEldestWorker {
    private String typeOfAge;
    private String workerName;
    private LocalDate birthDate;
}
