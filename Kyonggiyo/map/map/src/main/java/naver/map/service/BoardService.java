package naver.map.service;

import lombok.RequiredArgsConstructor;
import naver.map.domain.Board;
import naver.map.dto.BoardRequestDTO;
import naver.map.dto.BoardResponseDTO;
import naver.map.exception.CustomException;
import naver.map.exception.ErrorCode;
import naver.map.paging.CommonParams;
import naver.map.paging.Pagination;
import naver.map.repository.BoardRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //final 로 선언된 모든 멤버에 대한 생성자를 만들어 줌
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    /**
     * 게시글 생성
     */
    @Transactional
    public Long save(final BoardRequestDTO params) {

        Board entity = boardRepository.save(params.toEntity());
        return entity.getId();
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

    /**
     * 게시글 삭제
     */
    @Transactional
    public Long delete(final Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.delete();
        return id;
    }

    /**
     * 게시글 리스트 조회
     */
    public List<BoardResponseDTO> findAll() { // sort를 파라미터로 받는 내부 메소드

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

    public List<BoardResponseDTO> findAllByDelteYn(final char deleteYn) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
        List<Board> list = boardRepository.findAllByDeleteYn(deleteYn, sort);
        return list.stream().map(BoardResponseDTO::new).collect(Collectors.toList());
    }

    /**
     * 게시글 상세정보 조회
     */
    @Transactional
    public BoardResponseDTO findById(final Long id) {

        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.increaseHits();
        return new BoardResponseDTO(entity);
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

    /** 페이지네이션 5
     * 게시글 리스트 조회 (With. paigination information)
     */
    public Map<String, Object> findAll(CommonParams params) {

        //게시글 수 조회
        int count = boardMapper.count(params);

        if (count < 1) {
            return Collections.emptyMap();
        }

        //페이지네이션 정보 계산
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        //게시글 리스트 조회
        List<BoardResponseDTO> list = boardMapper.findAll(params);

        Map<String, Object> response = new HashMap<>();
        response.put("params", params);
        response.put("list", list);

        return response;
        //params의 멤버인 pagination에 계산된 페이지네이션 정보를 저장했기 때문에
        //프론트엔드 영역에서는 response.params.pagination과 같이 페이지네이션 정보에 접근할 수 있다
    }
    }