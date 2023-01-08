package naver.map.controller;

import lombok.RequiredArgsConstructor;
import naver.map.domain.BoardRequestDTO;
import naver.map.domain.BoardResponseDTO;
import naver.map.exception.CustomException;
import naver.map.exception.ErrorCode;
import naver.map.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public Long save(@RequestBody final BoardRequestDTO params) {
        return boardService.save(params);
    }

    @GetMapping("/boards")
    public List<BoardResponseDTO> findAll() {
        return boardService.findAll();
    }

    @PatchMapping("/boards/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final BoardRequestDTO params) {
        return boardService.update(id, params);
    }

    @GetMapping("/test")
    public String test() {
        throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
    }
}
