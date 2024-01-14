package com.example.testdevops.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;

    @NotEmpty(message = "name shouldn't be empty")
    private String name;

    @NotEmpty
    @Email(message = "email shouldn't be null or empty")
    private String email;

    @NotEmpty
    @Size(min = 10, message = "body should be more than 10 characters")
    private String body;
}
