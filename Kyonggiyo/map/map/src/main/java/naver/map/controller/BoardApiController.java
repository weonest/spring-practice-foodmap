package naver.map.controller;

import lombok.RequiredArgsConstructor;
import naver.map.dto.BoardRequestDTO;
import naver.map.dto.BoardResponseDTO;
import naver.map.exception.CustomException;
import naver.map.exception.ErrorCode;
import naver.map.paging.CommonParams;
import naver.map.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    // 게시글 생성
    @PostMapping("/boards")
    public Long save(@RequestBody final BoardRequestDTO params) {
        return boardService.save(params);
    }

    // 게시글 수정
    @PatchMapping("/boards/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final BoardRequestDTO params) {
        return boardService.update(id, params);
    }

    // 게시글 삭제
    @DeleteMapping("/boards/{id}")
    public Long delete(@PathVariable final Long id) {
        return boardService.delete(id);
    }

//    // 게시글 리스트 조회
//    @GetMapping("/boards")
//    public List<BoardResponseDTO> findAll(@RequestParam final char deleteYn) {
//        return boardService.findAllByDelteYn(deleteYn);
//    }

    // 게시글 상세정보 조회
    @GetMapping("/boards/{id}")
    public BoardResponseDTO findById(@PathVariable final long id) {
        return boardService.findById(id);
    }

    @GetMapping("/test")
    public String test() {
        throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
    }

    // 게시글 리스트 조회 << 페이지네이션 6
    @GetMapping("/boards")
    public Map<String, Object> findAll(final CommonParams params) {
        return boardService.findAll(params);
    }
}
