package naver.map.repository;

import naver.map.domain.Map;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface MapRepository {
    Map save(Map map);

    List<Map> findByCampus(int camp);
    List<Map> findAll();
    List<Map> getOrder();
    List<Map> getOrderByCamp(int camp);
    List<Map> getBySearch(String keyword);


}
