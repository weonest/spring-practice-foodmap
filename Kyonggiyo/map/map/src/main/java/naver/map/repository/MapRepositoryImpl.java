package naver.map.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import naver.map.domain.Map;
import static naver.map.domain.QMap.map;

import java.util.List;

@RequiredArgsConstructor
public class MapRepositoryImpl implements MapRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Map> findAll() {
        List<Map> maps = queryFactory
                .selectFrom(map)
                .fetch();
        return maps;
    }

    @Override
    public List<Map> findByCamp(int camp) {
        return null;
    }

    @Override
    public List<Map> findAllByOrder() {
        return null;
    }

    @Override
    public List<Map> findWithCampOrder(int camp) {
        return null;
    }

    @Override
    public List<Map> findByNameContainingOrDesContainingOrSumContaining(String name, String des, String sum) {
        return null;
    }
}
