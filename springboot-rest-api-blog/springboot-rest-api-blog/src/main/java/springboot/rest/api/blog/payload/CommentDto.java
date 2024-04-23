package springboot.rest.api.blog.payload;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import springboot.rest.api.blog.entity.Post;

@Data
public class CommentDto {
    private long id;
    @NotEmpty(message = "Name should not be null or empty!")
    private String name;
    @NotEmpty
    @Email(message = "Email should not be null or empty and should be valid email address!")
    private String email;
    @NotEmpty
    @Size(min = 10, message = "Content should not be null or empty and have at least 10 characters!")
    private String content;
}
