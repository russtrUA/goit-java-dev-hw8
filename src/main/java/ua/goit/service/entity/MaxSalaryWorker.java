package ua.goit.service.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaxSalaryWorker {
    private String workerName;
    private String workerLevel;
    private Integer workerSalary;
}
