package naver.map.service;

import naver.map.domain.Map;
import naver.map.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MapService {

    @Autowired
    private MapRepository mapRepository;
//    public MapService(MapRepository mapRepository) {
//        this.mapRepository = mapRepository;
//    }

    public List<Map> findMap() {
        return mapRepository.findAll();
    }
    public List<Map> findSuwon() {
        return mapRepository.findByCamp(0);
    }
    public List<Map> findSeoul() {
        return mapRepository.findByCamp(1);
    }

    public List<Map> starOrder() {
        return mapRepository.findAllByOrder();
    }
    public List<Map> starOrderSuwon() {
        return mapRepository.findWithCampOrder(0);
    }
    public List<Map> starOrderSeoul() {
        return mapRepository.findWithCampOrder(1);
    }

    public List<Map> getSearchList(String keyword){
        return mapRepository.findByKeyword(keyword);
    }
}
