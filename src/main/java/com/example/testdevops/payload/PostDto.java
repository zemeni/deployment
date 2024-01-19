package com.example.testdevops.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.util.Set;

@Data
@Schema(
        description = "PostDto Model Information"
)
public class PostDto {
    private Long id;

    @Schema(description = "Post title")
    @NotEmpty
    @Size(min = 2, message = "post title should have at least 2 characters")
    private String title;

    @Schema(description = "Post description")
    @NotEmpty
    @Size(min = 10, message = "post description should have at least 10 characters")
    private String description;
    private String content;
    private Set<CommentDto> comments;

    private Long categoryId;
}
