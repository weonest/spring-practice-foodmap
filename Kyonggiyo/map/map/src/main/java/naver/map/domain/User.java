package naver.map.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 20)
    private String username;

    @Size(min = 3, max = 20)
    private String nickname;

    @Email
    private String email;

    @Size(min = 4)
    private String password;

    private Boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();


    //    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Baord에서 ManyToOne에 사용된 Join조건을 그대로 사용하겠다
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

    @Builder
    public User(String username, String password, String email, String nickname) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }


}

