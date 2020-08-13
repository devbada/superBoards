package kr.or.devbada.freeBoards.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.devbada.freeBoards.config.PrincipalHelper;

/**
 * 메인 컨트롤러
 * @author minam.cho
 * @since March 20, 2020
 */
@Controller
public class MainController {
	
	@RequestMapping(value="/")
	public ModelAndView root(Authentication auth, HttpServletRequest request) {
		return new ModelAndView("redirect:/main.do");
	}
	
	@RequestMapping(value="/main.do")
	public ModelAndView main(Authentication auth, HttpServletRequest request) {
		return new ModelAndView("main/main");
	}
	
	@RequestMapping(value="/board.do")
	public ModelAndView board(Authentication auth, HttpServletRequest request) {
		PrincipalHelper.getMemId();
		return new ModelAndView("main/board");
	}
}