package kr.or.devbada.freeBoards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kr.or.devbada.freeBoards.dto.MailDto;
import kr.or.devbada.freeBoards.handler.MailHandler;

/**
 * 메일서비스 구현체
 * @see https://victorydntmd.tistory.com/342
 * @author minam.cho
 */
@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	private static final String FROM_ADDRESS = "imdevbada@gmail.com";

	public void mailSend(MailDto mailDto) {
		try {
			MailHandler mailHandler = new MailHandler(mailSender);

			// 받는 사람
			mailHandler.setTo(mailDto.getAddress());
			// 보내는 사람
			mailHandler.setFrom(MailService.FROM_ADDRESS);
			// 제목
			mailHandler.setSubject(mailDto.getTitle());
			// HTML Layout
			String htmlContent = "<p>" + mailDto.getMessage() + "<p>";
			mailHandler.setText(htmlContent, true);
			// 첨부 파일
			//mailHandler.setAttach("newTest.txt", "static/originTest.txt");
			// 이미지 삽입
			//mailHandler.setInline("sample-img", "static/sample1.jpg");
			mailHandler.send();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
