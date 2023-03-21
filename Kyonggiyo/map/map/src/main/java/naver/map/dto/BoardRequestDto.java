package naver.map.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import naver.map.domain.Board;
import naver.map.domain.User;

@Getter
@AllArgsConstructor
public class BoardRequestDto {

    private String title; // 제목
    private String content; // 내용
    private User user; // 작성자

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }

}