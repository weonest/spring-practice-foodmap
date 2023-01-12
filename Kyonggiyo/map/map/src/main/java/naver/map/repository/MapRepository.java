package naver.map.repository;

import naver.map.domain.Map;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface MapRepository {
    Map save(Map map);

    Optional<Map> findByName(String name);
    Optional<Map> findByDes(String Des);
    Optional<Map> findBySum(String sum);
    Optional<Map> findByCamp(long camp);

    List<Map> getCampus(long camp);
    List<Map> findAll();
    List<Map> getOrder();
    List<Map> getOrderCamp(long camp);
    List<Map> getSearch(String keyword);


}
