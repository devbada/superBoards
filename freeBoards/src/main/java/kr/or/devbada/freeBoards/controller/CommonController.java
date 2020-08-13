package kr.or.devbada.freeBoards.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.devbada.freeBoards.commons.CommonUtilService;
import kr.or.devbada.freeBoards.domain.AnonyBoardDtlPVO;

/**
 * 공통 호출 컨트롤러
 * @author minam.cho
 * @since March 20, 2020
 */
@Controller
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	private CommonUtilService commonUtilService;
	
	/*
	 * @Autowired private MenuService menuService;
	 */
	
	@RequestMapping(value="/")
	public ModelAndView root(Authentication auth, HttpServletRequest request) {
		return new ModelAndView("redirect:/public/boardList.do");
	}
	
	/**
	 * 게시글 목록 조회 AJAX
	 * TODO
	 * @param pvo.bmId required
	 * @param request
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value="/selectRoleByMenuListAjax.do")
	public ModelAndView selectBoardListAjax(HttpServletRequest request, @ModelAttribute AnonyBoardDtlPVO pvo) {
		ModelAndView mav = new ModelAndView("jsonView");
		
		/*
		 * List<AnonyBoardDtlRVO> boardDataListResult = new ArrayList<>();
		 * 
		 * if ( StringUtils.isEmpty(pvo.getBmId()) ) { mav.addObject("result",
		 * "EMPTY-BOARD-TYPE");
		 * 
		 * } else {
		 * 
		 * boardDataListResult = menuService.selectAnonyBoardDtlList(pvo);
		 * 
		 * mav.addObject("iTotalRecords", recordsTotal);
		 * mav.addObject("iTotalDisplayRecords", recordsTotal);
		 * 
		 * mav.addObject("result", "200"); mav.addObject("resultDataList",
		 * boardDataListResult); }
		 */
		
		return mav;
	}
	
}