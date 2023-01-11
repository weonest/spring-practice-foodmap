package naver.map.service;


import naver.map.dto.BoardResponseDTO;
import naver.map.paging.CommonParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BoardMapper {
    /**
     * 게시글 수 조회
     */

    int count(final CommonParams params);

    /**
     * 게시글 리스트 조회
     */
    List<BoardResponseDTO> findAll(final CommonParams params);

    }

