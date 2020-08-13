package kr.or.devbada.freeBoards.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.devbada.freeBoards.config.PrincipalHelper;
import kr.or.devbada.freeBoards.domain.AnonyMembersRVO;

/**
 * 메인 컨트롤러
 * @author minam.cho
 * @since March 20, 2020
 */
@Controller
public class LoginController {
		
	// private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 루트
	 * @param HttpServletRequest, HttpServletResponse
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/login")
	public String login(Authentication auth, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AnonyMembersRVO loginUser = PrincipalHelper.getUser();
		
		if ( loginUser == null ) {
			return "/login/login";
			
		} else {
			return "redirect:/main.do";
		}
	}
}