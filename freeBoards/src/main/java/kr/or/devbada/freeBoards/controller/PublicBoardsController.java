package kr.or.devbada.freeBoards.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.devbada.freeBoards.commons.CommonUtilService;
import kr.or.devbada.freeBoards.domain.AnonyBoardCommentsPVO;
import kr.or.devbada.freeBoards.domain.AnonyBoardDtlPVO;
import kr.or.devbada.freeBoards.domain.AnonyBoardDtlRVO;
import kr.or.devbada.freeBoards.domain.AnonyBoardRVO;
import kr.or.devbada.freeBoards.service.PublicBoardService;


/**
 * 오픈 게시판 컨트롤러
 * @author minam.cho
 * @since March 20, 2020
 */
@Controller
@RequestMapping("/public")
public class PublicBoardsController {
	
	@Autowired
	private CommonUtilService commonUtilService;
	
	@Autowired
	private PublicBoardService publicBoardService;
	
	@RequestMapping(value="/")
	public ModelAndView root(Authentication auth, HttpServletRequest request) {
		return new ModelAndView("redirect:/public/boardList.do");
	}
	
	/**
	 * 글 목록
	 * @param request
	 * @return
	 */
	@GetMapping(value="/boardList.do")
	public ModelAndView boardList(HttpServletRequest request,
			@RequestParam(name = "bmId") String bmId ) {
		ModelAndView mav = new ModelAndView("public/boardList");
		mav.addObject("bmId", bmId);
		
		// Board 조회하여 정보가 없으면 main으로 리다이렉트 해야 함
		AnonyBoardRVO boardInfo = publicBoardService.selectAnonyBoard(bmId);

		if ( boardInfo == null ){
			mav.setViewName("error/404");
		}

		return mav;
	}
	
	/**
	 * 글쓰기 페이지
	 * @param request
	 * @return
	 */
	@GetMapping(value="/boardWrite.do")
	public ModelAndView boardWrite(HttpServletRequest request,
			@RequestParam(name = "bmId") String bmId, 
			@RequestParam(name = "action", required = false) String action ,
			@ModelAttribute AnonyBoardDtlPVO pvo ) {
		ModelAndView mav = new ModelAndView("public/boardWrite");

		if ( StringUtils.isNotEmpty(action) && action.equals("MODIFY") ) {
			// 수정모드로 진입하기.
			AnonyBoardDtlRVO result = publicBoardService.selectAnonyBoardDtl(pvo);
			mav.addObject("boardDetail", result);
		}
		
		mav.addObject("bmId", bmId);
		mav.addObject("action", action);
		
		return mav;
	}
	
	/**
	 * 글 상세보기
	 * @param request
	 * @return
	 */
	@GetMapping(value="/boardDetail.do")
	public ModelAndView boardDetail(HttpServletRequest request, @ModelAttribute AnonyBoardDtlPVO pvo ) {
		ModelAndView mav = new ModelAndView("public/boardDetail");
		
		AnonyBoardDtlRVO result = publicBoardService.selectAnonyBoardDtl(pvo);
		
		mav.addObject("boardDetail", result);
		
		return mav;
	}
	
	/**
	 * 게시글 목록 조회 AJAX
	 * @param pvo.bmId required
	 * @param request
	 * @param pvo
	 * @return
	 */
	@GetMapping(value="/selectBoardListAjax.do")
	public ModelAndView selectBoardListAjax(HttpServletRequest request, @ModelAttribute AnonyBoardDtlPVO pvo) {
		ModelAndView mav = new ModelAndView("jsonView");
		
		List<AnonyBoardDtlRVO> boardDataListResult = new ArrayList<>();

		if ( StringUtils.isEmpty(pvo.getBmId()) ) {
			mav.addObject("result", "EMPTY-BOARD-TYPE");
			
		} else {

			int recordsTotal = publicBoardService.selectAnonyBoardDtlListCount(pvo);
			boardDataListResult = publicBoardService.selectAnonyBoardDtlList(pvo);
			
			mav.addObject("iTotalRecords", recordsTotal); 
			mav.addObject("iTotalDisplayRecords", recordsTotal);
			
			mav.addObject("result", "200");
			mav.addObject("resultDataList", boardDataListResult);
		}
		
		return mav;
	}
	
	/**
	 * 게시글 등록 AJAX
	 * @param request
	 * @param pvo
	 * @return
	 */
	@PostMapping(value="/insertBoardDetailAjax.do")
	public ModelAndView insertBoardDetailAjax(Principal principal, @ModelAttribute AnonyBoardDtlPVO pvo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("jsonView");
		
		if ( StringUtils.isEmpty(pvo.getBmId()) ) {
			mav.addObject("result", "EMPTY-BOARD-TYPE");
			
		} else {
			pvo.setRegId(principal.getName());
			pvo.setRegIp(commonUtilService.getClientIP(request));

			if (StringUtils.isNotEmpty(pvo.getReplyTargetBdId()) ) { 
				// ReplyTargetBdId가 있다는 뜻은 답글을 달고 있다는 뜻이다.

				pvo.setParentBdId(pvo.getReplyTargetBdId());

				mav.addObject("writeType", "REPLY");

			} else {
				mav.addObject("writeType", "INSERT");

			}
			
			int cnt = publicBoardService.insertBoardDetailAjax(pvo);
			
			if ( cnt > 0 ) {
				mav.addObject("result", "SUCCESS");
			} else {
				mav.addObject("result", "FAIL");
			}
		}
		
		return mav;
	}

	/**
	 * 게시글 수정 AJAX
	 * @param request
	 * @param pvo
	 * @return
	 */
	@PostMapping(value="/updateBoardDetailAjax.do")
	public ModelAndView updateBoardDetailAjax(Principal principal, @ModelAttribute AnonyBoardDtlPVO pvo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("jsonView");
		
		if ( StringUtils.isEmpty(pvo.getBmId()) ) {
			mav.addObject("result", "EMPTY-BOARD-TYPE");
			
		} else {
			pvo.setUpdId(principal.getName());
			pvo.setUpdIp(commonUtilService.getClientIP(request));

			// 글쓴이와 수정자(로그인 사용자)가 동일해야 한다.
			AnonyBoardDtlRVO result = publicBoardService.selectAnonyBoardDtl(pvo);

			if ( result.getRegId().equals(pvo.getUpdId())) {
				int cnt = publicBoardService.updateBoardDetailAjax(pvo);
				
				if ( cnt > 0 ) {
					mav.addObject("result", "SUCCESS");
				} else {
					mav.addObject("result", "FAIL");
				}
				
			} else {
				mav.addObject("result", "WRONG_WRITER");

			}
		}
		
		return mav;
	}

	/**
	 * 게시글 댓글 목록 가져오기
	 * @param request
	 * @param pvo
	 * @return
	 */
	@GetMapping(value="/selectBoardCommentsListAjax.do")
	public ModelAndView selectBoardCommentsListAjax(HttpServletRequest request, @ModelAttribute AnonyBoardDtlPVO pvo) {
		ModelAndView mav = new ModelAndView("jsonView");
		
		List<AnonyBoardDtlRVO> boardDataListResult = new ArrayList<>();

		if ( StringUtils.isEmpty(pvo.getBmId()) ) {
			mav.addObject("result", "EMPTY-BOARD-TYPE");
			
		} else {

			int recordsTotal = publicBoardService.selectAnonyBoardDtlListCount(pvo);
			boardDataListResult = publicBoardService.selectAnonyBoardDtlList(pvo);
			
			mav.addObject("iTotalRecords", recordsTotal); 
			mav.addObject("iTotalDisplayRecords", recordsTotal);
			
			mav.addObject("result", "200");
			mav.addObject("resultDataList", boardDataListResult);
		}
		
		return mav;
	}

	/**
	 * 게시글의 댓글 작성
	 * @param pvo
	 * @return
	 */
	@PostMapping(value="insertBoardComment")
	public String postMethodName(@ModelAttribute AnonyBoardCommentsPVO pvo) {
		//TODO: process POST request

		int cnt = publicBoardService.insertBoardComment(pvo);
		
		return "SUCCESS";
	}
	
}