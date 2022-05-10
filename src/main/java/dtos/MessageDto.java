package dtos;

import lombok.Data;

@Data
public class MessageDto {

    private String type;
    private String username;
    private String body;
}
