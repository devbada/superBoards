package kr.or.devbada.freeBoards.handler;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import kr.or.devbada.freeBoards.commons.CommonUtilService;
import kr.or.devbada.freeBoards.domain.AnonyMemberLoginPVO;
import kr.or.devbada.freeBoards.service.AnonyMembersService;

/**
 * 로그인이 실패하면 호출되는 Handler
 */
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {
 
	@Autowired private AnonyMembersService anonyMembersService;
	@Autowired private CommonUtilService commonUtilService;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //response.setStatus(HttpStatus.UNAUTHORIZED.value());
		//request.getRequestDispatcher("/loginFailure").forward(request, response);

		AnonyMemberLoginPVO loginPVO = new AnonyMemberLoginPVO();
		loginPVO.setLoginKey(UUID.randomUUID().toString());
		loginPVO.setMemId(request.getParameter("memId"));
		loginPVO.setRemoteIp(commonUtilService.getClientIP(request));
		loginPVO.setSuccessYn("N");

		anonyMembersService.insertMemberLoginLog(loginPVO);
		
		if (exception instanceof AuthenticationServiceException) {
			request.setAttribute("loginFailMsg", "존재하지 않는 사용자입니다.");
		
		} else if(exception instanceof BadCredentialsException) {
			request.setAttribute("loginFailMsg", "아이디 또는 비밀번호가 틀립니다.");
			
		} else if(exception instanceof LockedException) {
			request.setAttribute("loginFailMsg", "잠긴 계정입니다..");
			
		} else if(exception instanceof DisabledException) {
			request.setAttribute("loginFailMsg", "비활성화된 계정입니다..");
			
		} else if(exception instanceof AccountExpiredException) {
			request.setAttribute("loginFailMsg", "만료된 계정입니다..");
			
		} else if(exception instanceof CredentialsExpiredException) {
			request.setAttribute("loginFailMsg", "비밀번호가 만료되었습니다.");
		}
		
		// 로그인 페이지로 다시 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
		dispatcher.forward(request, response);
		  
	}
}