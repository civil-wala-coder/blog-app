package reddit.blog.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String about;
}
