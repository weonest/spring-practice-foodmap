package naver.map.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "board")

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 30, message = "제목 입력")
    private String title;
    private String content;
    private int hits;
    private String password;
    private LocalDateTime createdDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(String title, String content) { // 이와 같은 속성 변경 (도메인 로직)은 내부에 가능
        this.title = title;
        this.content = content;
    }

    //조회수 증가
    public void increaseHits() {
        this.hits++;
    }


}
