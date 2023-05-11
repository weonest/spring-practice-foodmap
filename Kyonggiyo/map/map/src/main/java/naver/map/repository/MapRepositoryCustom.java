package naver.map.repository;

import naver.map.domain.Map;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MapRepositoryCustom {

    List<Map> findAll();

    List<Map> findByCamp(int camp);

    List<Map> findAllByOrder();

    List<Map> findWithCampOrder(int camp);

    List<Map> findByKeyword(String keyword);
}
