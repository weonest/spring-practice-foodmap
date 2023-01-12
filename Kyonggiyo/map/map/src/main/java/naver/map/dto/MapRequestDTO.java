package naver.map.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
public class MapRequestDTO {

    private String name; // 상호명
    private String des;  // 설명
    private String sum;  // 요약
}