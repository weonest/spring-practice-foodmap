package naver.map.validator;

import naver.map.dto.UserRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequestDto.class.equals(clazz);
    }


    @Override
    public void validate(Object obj, Errors errors) {

        UserRequestDto u = (UserRequestDto) obj;
        if (StringUtils.length(u.getUsername()) < 3) {
            errors.rejectValue("username", "key", "ID는 3자 이상 20자 이하입니다");
        }
        if (StringUtils.length(u.getPassword()) < 4) {
            errors.rejectValue("password", "key", "비밀번호는 4자 이상 20자 이하입니다");
        }

    }
}
