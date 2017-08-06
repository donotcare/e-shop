package ru.otus.eshop.model.system;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.eshop.model.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDetails extends BaseEntity {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Column(name = "username", unique = true)
    private @NonNull String username;
    private String password;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private @NonNull List<Role> roles;

    public void setPassword(String password){
        this.password = PASSWORD_ENCODER.encode(password);
    }

}
