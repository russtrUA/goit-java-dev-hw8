package ua.goit.service.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    private long id;
    private String name;
}
