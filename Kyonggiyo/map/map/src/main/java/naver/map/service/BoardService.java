package naver.map.service;

import naver.map.domain.Board;
import naver.map.dto.BoardRequestDto;
import naver.map.dto.BoardResponseDto;
import naver.map.repository.BoardRepository;
import naver.map.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;


    /**
     * 게시글 리스트 조회
     */
    @Transactional
    public Page<BoardResponseDto> findAll(String title, String content, Pageable pageable) {
        return boardRepository.findByTitleContainingOrContentContaining(title, content, pageable)
                .map(BoardResponseDto::new);
    }

    /**
     * 게시글 상세 조회
     */
    @Transactional
    public BoardResponseDto findById(Long id) {
        Board entity = boardRepository.findById(id).orElse(null);
        entity.increaseHits();

        return new BoardResponseDto(entity);
    }

    /**
     * 게시글 생성
     */
    @Transactional
    public Long save(BoardRequestDto param) {
        Board entity = boardRepository.save(param.toEntity());
        return entity.getId();
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(Long id, BoardRequestDto param) {
        Board entity = boardRepository.findById(id).orElse(null);
        entity.update(param.getTitle(), param.getContent());
        return id;
    }

    /**
     * 게시글 삭제
     */

    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }


    /**
     * 유효 검사
     */
    @Transactional
    public void validate(BoardRequestDto param, BindingResult bindingResult) {
        boardValidator.validate(param, bindingResult);
    }
}
