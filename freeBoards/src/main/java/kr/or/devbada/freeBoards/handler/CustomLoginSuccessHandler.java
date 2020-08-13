package kr.or.devbada.freeBoards.handler;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import kr.or.devbada.freeBoards.commons.CommonUtilService;
import kr.or.devbada.freeBoards.domain.AnonyMemberLoginPVO;
import kr.or.devbada.freeBoards.service.AnonyMembersService;

/**
 * 로그인이 성공하면 호출되는 Handler
 */
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired private AnonyMembersService anonyMembersService;
	@Autowired private CommonUtilService commonUtilService;
	
	public CustomLoginSuccessHandler(String defaultTargetUrl) {
		setDefaultTargetUrl(defaultTargetUrl);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		// 로그인 기록 남기기
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		AnonyMemberLoginPVO loginPVO = new AnonyMemberLoginPVO();
		loginPVO.setLoginKey(UUID.randomUUID().toString());
		loginPVO.setMemId(userDetails.getUsername());
		loginPVO.setRemoteIp(commonUtilService.getClientIP(request));
		loginPVO.setSuccessYn("Y");

		anonyMembersService.insertMemberLoginLog(loginPVO);

		HttpSession session = request.getSession();
		if (session != null) {
			String redirectUrl = (String) session.getAttribute("prevPage");
			if (redirectUrl != null) {
				session.removeAttribute("prevPage");
				getRedirectStrategy().sendRedirect(request, response, redirectUrl);
			} else {
				super.onAuthenticationSuccess(request, response, authentication);
			}
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
}