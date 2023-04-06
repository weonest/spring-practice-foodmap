package naver.map.repository;

import naver.map.domain.User;

import java.util.List;

public interface UserRepositoryCustom {
    public List<String> findAllUsername();

    public List<String> findAllNickname();

    public User findByUsername(String username);
}
