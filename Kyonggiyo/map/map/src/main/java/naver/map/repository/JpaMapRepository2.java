package naver.map.repository;

import naver.map.domain.Map;
import naver.map.domain.Map2;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMapRepository2 implements MapRepository2 {

    private final EntityManager em;

    public JpaMapRepository2(EntityManager em) {
        this.em = em;
    }

    @Override
    public Map2 save(Map2 map2) {
        em.persist(map2);
        return map2;
    }

    @Override
    public Optional<Map> findByName(String name) {
        List<Map> result = em.createQuery("select m from Map2 m where m.name = :name", Map.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Map> findByX(double x) {
        List<Map> result = em.createQuery("select m from Map2 m where m.x = :x", Map.class)
                .setParameter("x", x)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Map> findByY(double y) {
        List<Map> result = em.createQuery("select m from Map2 m where m.y = :y", Map.class)
                .setParameter("y", y)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Map> findByCamp(long camp) {
        List<Map> result = em.createQuery("select m from Map2 m where m.camp = :camp", Map.class)
                .setParameter("camp", camp)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Map> getCampus(long camp) {
        return em.createQuery("select m from Map2 m where m.camp = :camp")
                .setParameter("camp", camp)
                .getResultList();

    }

    @Override
    public List<Map> findAll() {   // 객체 그 자체(m)을 조사함
        return em.createQuery("select m from Map2 m", Map.class)
                .getResultList();
    }

//    오름차순 정렬
    @Override
    public List<Map> getOrder() {
        return em.createQuery("select m from Map2 m ORDER BY m.star DESC ", Map.class)
                .getResultList();
    }
}

