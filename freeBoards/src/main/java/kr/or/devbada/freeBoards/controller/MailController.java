package kr.or.devbada.freeBoards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.devbada.freeBoards.dto.MailDto;
import kr.or.devbada.freeBoards.service.MailService;
import lombok.AllArgsConstructor;

/**
 * 메일 전송을 위한 컨트롤러
 * 
 * @author minam.cho
 * @see https://victorydntmd.tistory.com/342
 *
 */
@Controller
@AllArgsConstructor
public class MailController {

	@Autowired
	private final MailService mailService;

	/**
	 * 메일 보내기
	 * @param mailDto
	 */
	@PostMapping("/mail")
	public void execMail(MailDto mailDto) {
		mailService.mailSend(mailDto);
	}
	
	/**
	 * 회원가입 인증 메일 보내기
	 * @param mailDto
	 */
	@PostMapping("/joinAuthMailSend")
	public void joinAuthMailSend(MailDto mailDto) {
		mailService.mailSend(mailDto);
	}
}
