package naver.map.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDTO {

    private Long id; // PK
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private int hits; // 조회 수
    private char deleteYn; // 삭제 여부
    private LocalDateTime createdDate; // 생성일
    private LocalDateTime modifiedDate; // 수정일

    public BoardResponseDTO(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.hits = entity.getHits();
        this.deleteYn = entity.getDeleteYn();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
    // 응답도 마찬가지로 Entity 클래스가 직접 사용되어서는 안 되기 때문에 따로 생성
}
