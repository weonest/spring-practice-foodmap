package naver.map;


import com.querydsl.jpa.impl.JPAQueryFactory;
import naver.map.domain.Map;
import naver.map.domain.QMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@SpringBootTest
public class QueryDSLTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JPAQueryFactory queryFactory;

    @BeforeEach
    void createTest() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    void entityManagerTest() {
        List<Map> maps = em.createQuery("select m from Map m", Map.class)
                .getResultList();

        System.out.println("emtest = " + maps);

    }

    @Test
    void useQueryDSL() {
        List<Map> list = queryFactory.selectFrom(QMap.map)
                .fetch();

        System.out.println("list = " + list);
    }

}