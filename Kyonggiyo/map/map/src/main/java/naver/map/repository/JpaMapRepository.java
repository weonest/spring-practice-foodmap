package naver.map.repository;

import naver.map.domain.Map;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMapRepository implements MapRepository {

    private final EntityManager em;

    public JpaMapRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Map save(Map map) {
        em.persist(map);
        return map;
    }


    @Override
    public List<Map> findByCampus(int camp) {
        return em.createQuery("select m from Map m where m.camp = :camp")
                .setParameter("camp", camp)
                .getResultList();

    }

    @Override
    public List<Map> findAll() {   // 객체 그 자체(m)을 조사함
        return em.createQuery("select m from Map m", Map.class)
                .getResultList();
    }

//    오름차순 정렬
    @Override
    public List<Map> getOrder() {
        return em.createQuery("select m from Map m ORDER BY m.star DESC, m.id ASC", Map.class)
                .getResultList();
    }

    @Override
    public List<Map> getOrderByCamp(int camp) {
        return em.createQuery("select m from Map m where m.camp =:camp ORDER BY m.star DESC, m.id ASC ", Map.class)
                .setParameter("camp", camp)
                .getResultList();
    }

    // name, des, sum

    @Override
    public List<Map> getBySearch(String keyword) {
        return em.createQuery("select m from Map m where m.name like :keyword or m.des like :keyword or m.sum like :keyword", Map.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();


//        List<Map> result = em.createQuery("select m from Map m where m.name like :keyword or m.des like :keyword or m.sum like :keyword order by m.star DESC", Map.class)
//                .setParameter("keyword", "%" + keyword + "%")
//                .getResultList();
//        return result.stream().findAny();
    }


}

