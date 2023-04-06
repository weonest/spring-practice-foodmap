package naver.map.service;


import naver.map.domain.Role;
import naver.map.domain.User;
import naver.map.dto.UserRequestDto;
import naver.map.repository.UserRepository;
import naver.map.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserValidator userValidator;

    public User save(UserRequestDto param) {
        User user = param.toEntity();

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.setEnabled(true); // 추후 계정 비활성화 여부

        Role role = new Role();
        role.setId(1L);
        user.getRoles().add(role);

        return userRepository.save(user);
    }

    @Transactional
    public void validate(UserRequestDto user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
    }

}
