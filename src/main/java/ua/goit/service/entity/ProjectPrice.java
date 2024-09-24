package ua.goit.service.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectPrice {
    private String projectName;
    private Integer price;
}
