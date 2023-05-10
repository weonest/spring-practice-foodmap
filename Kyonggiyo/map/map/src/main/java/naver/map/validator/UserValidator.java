package naver.map.validator;

import naver.map.domain.User;
import naver.map.dto.UserRequestDto;
import naver.map.repository.UserRepository;
import naver.map.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepository userRepository;


    @Override // 해당 클래스를 validator가 지원하는지
    public boolean supports(Class<?> clazz) {
        return UserRequestDto.class.equals(clazz);
    }


    @Override
    public void validate(Object obj, Errors errors) {

        UserRequestDto u = (UserRequestDto) obj;
        if (StringUtils.length(u.getUsername()) < 3) {
            errors.rejectValue("username", "key", "ID는 3자 이상 20자 이하입니다");
        }else if (userRepository.findAllUsername().contains(u.getUsername())) {
                errors.rejectValue("username", "NotUnique", "이미 존재하는 ID입니다");
        }

        if (StringUtils.length(u.getNickname()) < 3) {
            errors.rejectValue("nickname", "key", "Nickname은 3자 이상 20자 이하입니다");
        }else if (userRepository.findAllNickname().contains(u.getNickname())) {
                errors.rejectValue("nickname", "NotUnique", "이미 존재하는 Nickname입니다");
        }

        if (StringUtils.length(u.getPassword()) < 4) {
            errors.rejectValue("password", "key", "비밀번호는 4자 이상입니다");
        }

    }
}
