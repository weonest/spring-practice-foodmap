package naver.map.repository;

import naver.map.domain.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // 게시글 리스트 조회 (삭제 여부 기준)
    List<Board> findAllByDeleteYn(final char deleteYn, final Sort sort);
}
