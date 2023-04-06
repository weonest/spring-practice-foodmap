package naver.map.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import naver.map.domain.User;

import java.util.List;

import static naver.map.domain.QUser.user;

@RequiredArgsConstructor // queryFactory를 생성자에 포함
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<String> findAllUsername() {
        List<String> list = queryFactory
                .select(user.username)
                .from(user)
                .fetch();
        return list;
    }

    public List<String> findAllNickname() {
        List<String> list = queryFactory
                .select(user.nickname)
                .from(user)
                .fetch();
        return list;
    }

    public User findByUsername(String username) {
        User result = queryFactory
                .selectFrom(user)
                .where(user.username.eq(username))
                .fetchOne();
        return result;
    }

}
