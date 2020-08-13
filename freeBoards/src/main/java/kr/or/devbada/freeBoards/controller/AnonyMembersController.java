package kr.or.devbada.freeBoards.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.devbada.freeBoards.commons.CommonUtilService;

/**
 * 사용자 컨트롤러
 * @author minam.cho
 * @since August 03, 2020
 */
@Controller
@RequestMapping("/anonyMembers")
public class AnonyMembersController {
	
	@Autowired
	CommonUtilService commonUtilService;

	Logger logger = (Logger) LogManager.getLogger(Main.class);

	@RequestMapping(value="/main.do")
	public String main(HttpServletRequest request) throws Exception {
		return "/anonyMembers/main";
	}
	
}
