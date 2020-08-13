package kr.or.devbada.freeBoards.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 사용 신청 데이터 수집을 위한 DTO
 * @author minam.cho
 * @since August 09, 2020
 */
@Getter
@Setter
@NoArgsConstructor
public class JoinDto {
	private String password;
	private String joinCertifiedKey;
}
