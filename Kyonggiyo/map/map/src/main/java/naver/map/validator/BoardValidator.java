package naver.map.validator;

import naver.map.domain.Board;
import naver.map.dto.BoardRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }


    @Override
    public void validate(Object obj, Errors errors) {

        BoardRequestDto b = (BoardRequestDto) obj;
        if (StringUtils.length(b.getTitle()) < 3) {
            errors.rejectValue("title", "key", "제목은 2자 이상 30자 이하입니다");
        }
        if (StringUtils.isEmpty(b.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요");
        }

    }
}
