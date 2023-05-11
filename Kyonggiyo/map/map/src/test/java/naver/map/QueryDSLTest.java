package naver.map;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import naver.map.domain.Map;
import static naver.map.domain.QMap.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@SpringBootTest
public class QueryDSLTest {

    @PersistenceContext // @Autowired 로도 EntityManager 주입 가능
    private EntityManager em;

    @Autowired
    private JPAQueryFactory queryFactory;

    @BeforeEach // 기본적으로 테스트가 시작하기 전에 실행하는 함수
    void createTest() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    void entityManagerTest() {
        List<Map> maps = em.createQuery("select m from Map m", Map.class)
                .getResultList();

        assertThat(maps.size() > 0).isTrue();

    }

    @Test
    void useQueryDSL() {
        List<Map> list = queryFactory.selectFrom(map)
                .fetch();

        assertThat(list.size() > 0).isTrue();
        System.out.println(list);
    }

    @Test
    void findByCamp() {
        List<Map> list = queryFactory
                .selectFrom(map)
                .where(map.camp.eq(0))
                .fetch();
        System.out.println(list);
    }

    @Test
    void findByOrder() {
        List<Map> list = queryFactory
                .selectFrom(map)
                .orderBy(map.star.desc())
                .fetch();
        System.out.println(list);
    }

    @Test
    void findByCampOrder() {
        List<Map> list = queryFactory
                .selectFrom(map)
                .where(map.camp.eq(1))
                .orderBy(map.star.desc())
                .fetch();
        System.out.println(list);

    }

    String keyword = null;
    @Test
    void findByKeyword() {
        List<Map> list = queryFactory
                .selectFrom(map)
                .where(nameEq(keyword))
                .fetch();
        System.out.println(list);
    }

    private BooleanExpression nameEq(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return map.name.eq(keyword);
    }


}