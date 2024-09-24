package ua.goit.service.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LongestProject {
    private String projectName;
    private Long monthDuration;
}
