package naver.map.repository;

import naver.map.domain.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MapRepository extends JpaRepository<Map, Long> {

    List<Map> findAll();

    List<Map> findByCamp(int camp);

    @Query("select m from Map m ORDER BY m.star DESC, m.id ASC")
    List<Map> findAllByOrder();

    @Query("select m from Map m where m.camp =:camp ORDER BY m.star DESC, m.id ASC")
    List<Map> findWithCampOrder(int camp);

    List<Map> findByNameContainingOrDesContainingOrSumContaining(String name, String des, String sum);
}

    //    오름차순 정렬
//    public List<Map> getOrder() {
//        return em.createQuery("select m from Map m ORDER BY m.star DESC, m.id ASC", Map.class)
//                .getResultList();
//    }
//
//    public List<Map> getOrderByCamp(int camp) {
//        return em.createQuery("select m from Map m where m.camp =:camp ORDER BY m.star DESC, m.id ASC ", Map.class)
//                .setParameter("camp", camp)
//                .getResultList();
//    }

    // name, des, sum
//    public List<Map> getBySearch(String keyword) {
//        return em.createQuery("select m from Map m where m.name like :keyword or m.des like :keyword or m.sum like :keyword", Map.class)
//                .setParameter("keyword", "%" + keyword + "%")
//                .getResultList();
//
//
//    }
