package naver.map.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDTO {
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private char deleteYn; // 삭제 여부

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .hits(0)
                .deleteYn(deleteYn)
                .build();
    }
    //Entity 클래스는 테이블(Table) 또는 레코드(Record) 역할을 하는 데이터베이스 그 자체로 생각할 수 있고,
    //
    //절대로 요청(Request)이나 응답(Response)에 사용되어서는 안 되기 때문에
    //
    //반드시 Request, Response 클래스를 따로 생성(구분)
}
