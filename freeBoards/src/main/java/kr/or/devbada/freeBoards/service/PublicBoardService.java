package kr.or.devbada.freeBoards.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.devbada.freeBoards.domain.AnonyBoardCommentsPVO;
import kr.or.devbada.freeBoards.domain.AnonyBoardDtlPVO;
import kr.or.devbada.freeBoards.domain.AnonyBoardDtlRVO;
import kr.or.devbada.freeBoards.domain.AnonyBoardRVO;
import kr.or.devbada.freeBoards.mapper.AnonyBoardMapper;

/**
 * 공개 게시판 서비스 
 * @author minam.cho
 * @since August 04, 2020
 */
@Service
public class PublicBoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(PublicBoardService.class);

	@Autowired
	private AnonyBoardMapper anonyBoardMapper;

	/**
	 * 게시글 목록의 전체 수를 조회
	 * @param pvo
	 * @return
	 */
	public int selectAnonyBoardDtlListCount(AnonyBoardDtlPVO pvo) {
		return anonyBoardMapper.selectAnonyBoardDtlListCount(pvo);
	}
	
	/**
	 * 게시글 목록 조회
	 * @param pvo
	 * @return
	 */
	public List<AnonyBoardDtlRVO> selectAnonyBoardDtlList(AnonyBoardDtlPVO pvo) {
		
		List<AnonyBoardDtlRVO> euCustomerList = anonyBoardMapper.selectAnonyBoardDtlList(pvo);

		return euCustomerList;
	}

	/**
	 * 게시글 등록
	 * @param pvo
	 * @return
	 */
	public int insertBoardDetailAjax(AnonyBoardDtlPVO pvo) {
		int resultCnt = -1;
		try {
			resultCnt = anonyBoardMapper.insertBoardDetailAjax(pvo);
			
		} catch (Exception e) {
			logger.error("PublicBoardService.insertBoardDetailAjax Error");
		}
		return resultCnt;
	}

	public AnonyBoardDtlRVO selectAnonyBoardDtl(AnonyBoardDtlPVO pvo) {
		AnonyBoardDtlRVO boardDetail = null;
		
		try {
			boardDetail = anonyBoardMapper.selectAnonyBoardDtl(pvo);
			
		} catch (Exception e) {
			logger.error("PublicBoardService.insertBoardDetailAjax Error");
		}
		return boardDetail;
	}

	public int updateBoardDetailAjax(AnonyBoardDtlPVO pvo) {
		int resultCnt = -1;
		try {
			resultCnt = anonyBoardMapper.updateBoardDetailAjax(pvo);
			
		} catch (Exception e) {
			logger.error("PublicBoardService.updateBoardDetailAjax Error");
		}
		return resultCnt;
	}

	/**
	 * 게시판 정보 (단건) 조회
	 * @param bmId
	 * @return
	 */
	public AnonyBoardRVO selectAnonyBoard(String bmId) {
		return anonyBoardMapper.selectAnonyBoard(bmId);
	}

	/**
	 * 게시글 상세 댓글 입력
	 * @param pvo
	 * @return
	 */
	public int insertBoardComment(AnonyBoardCommentsPVO pvo) {
		return anonyBoardMapper.insertBoardComment(pvo);
	}
	
}
