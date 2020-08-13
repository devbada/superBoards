package kr.or.devbada.freeBoards.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.devbada.freeBoards.domain.AnonyBoardCommentsPVO;
import kr.or.devbada.freeBoards.domain.AnonyBoardDtlPVO;
import kr.or.devbada.freeBoards.domain.AnonyBoardDtlRVO;
import kr.or.devbada.freeBoards.domain.AnonyBoardRVO;

/**
 * 공개 게시판 매퍼
 * @author minam.cho
 * @since August 03, 2020
 */
@Mapper
public interface AnonyBoardMapper {
	/**
	 * 게시글 목록의 전체 수를 조회
	 * @param pvo
	 * @return
	 */
	int selectAnonyBoardDtlListCount(AnonyBoardDtlPVO pvo);
	
	/**
	 * 게시글 목록을 조회
	 * @param pvo
	 * @return
	 */
	List<AnonyBoardDtlRVO> selectAnonyBoardDtlList(AnonyBoardDtlPVO pvo);

	/**
	 * 게시글 등록
	 * @param pvo
	 * @return
	 */
	int insertBoardDetailAjax(AnonyBoardDtlPVO pvo);

	/**
	 * 게시글 상세 조회
	 * @param pvo
	 * @return
	 */
	AnonyBoardDtlRVO selectAnonyBoardDtl(AnonyBoardDtlPVO pvo);

	/**
	 * 게시글 수정
	 * @param pvo
	 * @return
	 */
	int updateBoardDetailAjax(AnonyBoardDtlPVO pvo);

	/**
	 * 게시판 정보 (단건) 조회
	 * @param bmId
	 * @return
	 */
	AnonyBoardRVO selectAnonyBoard(String bmId);

	/**
	 * 게시글 댓글 작성
	 * @param pvo
	 * @return
	 */
	int insertBoardComment(AnonyBoardCommentsPVO pvo);

}
