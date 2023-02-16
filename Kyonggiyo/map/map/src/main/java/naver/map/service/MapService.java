package naver.map.service;

import naver.map.domain.Map;
import naver.map.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class MapService {
    private final MapRepository mapRepository;

    @Autowired
    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    public List<Map> findMaps() {
        return mapRepository.findAll();
    }

    public List<Map> findSuwon() {
        return mapRepository.getCampus(0);
    }
    public List<Map> findSeoul() {
        return mapRepository.getCampus(1);
    }

    public List<Map> starOrder() {
        return mapRepository.getOrder();
    }
    public List<Map> starOrderSuwon() {
        return mapRepository.getOrderCamp(0);
    }
    public List<Map> starOrderSeoul() {
        return mapRepository.getOrderCamp(1);
    }

    public List<Map> getSearchList(String keyword){
        return mapRepository.getSearch(keyword);
    }
}
