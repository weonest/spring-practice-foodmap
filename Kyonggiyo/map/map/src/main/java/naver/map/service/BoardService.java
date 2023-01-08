package naver.map.service;

import lombok.RequiredArgsConstructor;
import naver.map.domain.Board;
import naver.map.domain.BoardRequestDTO;
import naver.map.domain.BoardResponseDTO;
import naver.map.domain.Map;
import naver.map.exception.CustomException;
import naver.map.exception.ErrorCode;
import naver.map.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시글 생성
     */
    @Transactional
    public Long save(final BoardRequestDTO params) {

        Board entity = boardRepository.save(params.toEntity());
        return entity.getId();
    }

    /**
     * 게시글 리스트 조회
     */
    public List<BoardResponseDTO> findAll() {

        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
        List<Board> list = boardRepository.findAll(sort);
        return list.stream().map(BoardResponseDTO::new).collect(Collectors.toList());

        // return 문을 풀어서 작성하면

        //List<BoardResponseDto> boardList = new ArrayList<>();
        //
        //    for (Board entity : list) {
        //        boardList.add(new BoardResponseDto(entity));
        //    }
        //
        //    return boardList;
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(final Long id, final BoardRequestDTO params) {
        //사용자 정의 예외 클래스 생성 이후 적용
        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent(), params.getWriter());
        return id;
    }

    //풀어서 쓰면 다음과 같다
    //@Transactional
    //public Long update(final Long id, final BoardRequestDto params) {
    //
    //    Board entity = boardRepository.findById(id).orElse(null);
    //
    //    if (entity == null) {
    //        throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
    //    }
    //
    //    entity.update(params.getTitle(), params.getContent(), params.getWriter());
    //    return id;
    //}

}