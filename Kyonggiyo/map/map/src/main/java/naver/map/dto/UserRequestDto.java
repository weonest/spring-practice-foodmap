package naver.map.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import naver.map.domain.User;

@Getter
@AllArgsConstructor
public class UserRequestDto {

    private String username;
    private String nickname;
    private String email;
    private String password;


    public User toEntity() {
        return User.builder()
                .username(username)
                .nickname(nickname)
                .email(email)
                .password(password)
                .build();
    }
}
