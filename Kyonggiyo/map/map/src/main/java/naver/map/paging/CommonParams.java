package naver.map.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonParams {


    //페이지네이션 2
    private int page;           // 현재 페이지 번호
    private int recordPerPage;  // 페이지당 출력할 데이터 개수
    private int pageSize;       // 화면 하단에 출력할 페이지 개수
    private String keyword;     // 검색 키워드
    private String searchType;  // 검색 유형
    private Pagination pagination; // 페이지네이션 정보 << 페이지네이션 6

}