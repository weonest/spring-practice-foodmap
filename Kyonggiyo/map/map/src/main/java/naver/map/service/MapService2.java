package naver.map.service;

import naver.map.domain.Map;
import naver.map.repository.MapRepository;
import naver.map.repository.MapRepository2;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class MapService2 {
    private final MapRepository2 mapRepository2;

    @Autowired
    public MapService2(MapRepository2 mapRepository2) {
        this.mapRepository2 = mapRepository2;
    }

    public List<Map> findMaps() {
        return mapRepository2.findAll();
    }

    public List<Map> findSuwon() {
        return mapRepository2.getCampus(0);
    }
    public List<Map> findSeoul() {
        return mapRepository2.getCampus(1);
    }

    public List<Map> starOrder() {
        return mapRepository2.getOrder();
    }

}
