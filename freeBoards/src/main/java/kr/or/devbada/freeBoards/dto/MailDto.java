package kr.or.devbada.freeBoards.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 메일 데이터 전송 사용을 위한 DTOP
 * @author minam.cho
 * @see https://victorydntmd.tistory.com/342
 */
@Getter
@Setter
@NoArgsConstructor
public class MailDto {
	private String address;
	private String title;
	private String message;
}
