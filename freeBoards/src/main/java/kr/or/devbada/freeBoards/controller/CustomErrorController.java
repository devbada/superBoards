package kr.or.devbada.freeBoards.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 공통 에러 처리 컨트롤러
 */
@Controller
public class CustomErrorController implements ErrorController {
	
    private static final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);
    
	private static final String PATH = "/error";
	private static final String NOT_FOUND = "/404";
	private static final String INTERNAL_SERVER_ERROR = "/500";
	
	/**
	 * Error
	 * @param HttpServletRequest, HttpServletResponse, Model
	 * @return String
	 */
	@RequestMapping(value = PATH)
	public String error(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("### DEFAULT ERROR");
		printErrorLog(request);
		//model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
		
		if ( request.getAttribute("javax.servlet.error.status_code") != null ) {
			String errorCode = request.getAttribute("javax.servlet.error.status_code").toString();
			
			if("404".equals(errorCode)) {
				return "error/404";
			} else {
				return "error/500";

			}
		} else {
			return "error/500";
		}
	}
	
	/**
	 * Error 404
	 * @param HttpServletRequest, HttpServletResponse, Model
	 * @return String
	 */
	@RequestMapping(value = NOT_FOUND)
	public String error404(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("### PAGE NOT FOUND(404)");
		printErrorLog(request);
		model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
		return "error/error404";
	}
	
	/**
	 * Error 500
	 * @param HttpServletRequest, HttpServletResponse, Model
	 * @return String
	 */
	@RequestMapping(value = INTERNAL_SERVER_ERROR)
	public String error500(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("### INTERNAL SERVER ERROR(500)");
		printErrorLog(request);
		model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
		return "error/error500";
	}
	
	/**
	 * ClientAbortException 회피
	 * @param IOException
	 * @return String
	 * @see
	 * <pre>
	 * 사용자가 첨부파일 다운로드 중 브라우저 강제 종료 또는 취소로 인한 WAS 예외 발생은 회피 시킨다.
	 * </pre>
	 */
	@ExceptionHandler(IOException.class)
	public String handleAbortedConnection(final IOException ex) {
		if(ex.getClass().getName().equals("org.apache.catalina.connector.ClientAbortException")) {
			return null;
		}
		return "error/error500";
	}
	
	/**
	 * 오류 경로를 가져온다.
	 * @return String
	 */
	@Override
	public String getErrorPath() {
		return PATH;
	}
	
	/**
	 * 오류 로그 출력
	 * @param HttpServletRequest
	 */
	private void printErrorLog(HttpServletRequest request) {
		logger.debug("STATUS CODE : " + request.getAttribute("javax.servlet.error.status_code"));
		logger.debug("EXCEPTION TYPE : " + request.getAttribute("javax.servlet.error.exception_type"));
		logger.debug("MESSAGE : " + request.getAttribute("javax.servlet.error.message"));
		logger.debug("REQUEST URI : " + request.getAttribute("javax.servlet.error.request_uri"));
		logger.debug("EXCEPTION : " + request.getAttribute("javax.servlet.error.exception"));
		logger.debug("SERVLET NAME : " + request.getAttribute("javax.servlet.error.servlet_name"));
	}

}
