package naver.map.repository;

import naver.map.domain.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MapRepository extends JpaRepository<Map, Long>, MapRepositoryCustom{

//    List<Map> findAll();
//
//    List<Map> findByCamp(int camp);
//
//    @Query("select m from Map m ORDER BY m.star DESC, m.id ASC")
//    List<Map> findAllByOrder();
//
//    @Query("select m from Map m where m.camp =:camp ORDER BY m.star DESC, m.id ASC")
//    List<Map> findWithCampOrder(int camp);
//
//    List<Map> findByNameContainingOrDesContainingOrSumContaining(String name, String des, String sum);
}


