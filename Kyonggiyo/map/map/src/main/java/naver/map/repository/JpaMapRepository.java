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
    public Optional<Map> findByName(String name) {
        List<Map> result = em.createQuery("select m from Map m where m.name = :name", Map.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Map> findByDes(String des) {
        List<Map> result = em.createQuery("select m from Map m where m.des = :des", Map.class)
                .setParameter("des", des)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Map> findBySum(String sum) {
        List<Map> result = em.createQuery("select m from Map m where m.sum = :sum", Map.class)
                .setParameter("sum", sum)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Map> findByCamp(long camp) {
        List<Map> result = em.createQuery("select m from Map m where m.camp = :camp", Map.class)
                .setParameter("camp", camp)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Map> getCampus(long camp) {
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
        return em.createQuery("select m from Map m ORDER BY m.star DESC ", Map.class)
                .getResultList();
    }

    @Override
    public List<Map> getOrderCamp(long camp) {
        return em.createQuery("select m from Map m where m.camp =:camp ORDER BY m.star DESC, m.id ASC ", Map.class)
                .setParameter("camp", camp)
                .getResultList();
    }

    // name, des, sum

    @Override
    public List<Map> getSearch(String keyword) {
        return em.createQuery("select m from Map m where m.name like :keyword or m.des like :keyword or m.sum like :keyword ", Map.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }


}

