package naver.map.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
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
        List<Map> maps = queryFactory
                .selectFrom(map)
                .where(map.camp.eq(camp))
                .fetch();
        return maps;
    }

    @Override
    public List<Map> findAllByOrder() {
        List<Map> maps = queryFactory
                .selectFrom(map)
                .orderBy(map.star.desc(), map.name.asc())
                .fetch();
        return maps;
    }

    @Override
    public List<Map> findWithCampOrder(int camp) {
        List<Map> maps = queryFactory
                .selectFrom(map)
                .where(map.camp.eq(camp))
                .orderBy(map.star.desc(), map.name.asc())
                .fetch();

        return maps;
    }

    @Override
    public List<Map> findByKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty())
            return null;

        List<Map> maps = queryFactory
                .selectFrom(map)
                .where(nameCon(keyword)
                        .or(desCon(keyword)
                                .or(sumCon(keyword))))
                .fetch();

        return maps;
    }

    // 왜 여기서 처리가 안 될까?
    private BooleanExpression nameCon(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return map.name.contains(keyword);
    }

    private BooleanExpression desCon(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return map.des.contains(keyword);
    }

    private BooleanExpression sumCon(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return map.sum.contains(keyword);
    }


}
