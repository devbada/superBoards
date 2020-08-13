package kr.or.devbada.freeBoards.domain;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 사용자 Domain Class
 * @author minam.cho
 * @since August 03, 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnonyMembersDVO {
	private String memId; // 로그인 ID (PK)
	private String memPwd; // 비밀번호
	private String name; // : 실명
	private String nickName; // : 별명
	private String email; // : 이메일주소(중요: 고유)
	private String status; // : 사용자 상태(R: 가입신청(메일인증대기), A: 활성(메일 인증 후 사용중 상태), D: 중지상태(관리자 등에 의해 임의), L: 탈퇴상태(본인에 직접 탈퇴)
	private String joinCertifiedKey; // : 가입 인증을 위해 사용하는 고유키 (UUID)
	private Date joinCertifiedDt; // : 인증일자
	private Date regDt; // : 등록일
	private String regId; // : 등록자
	private Date updDt; // : 수정일
	private String updId; // : 수정자
	private Date levDt; // : 탈퇴일
	private Date lastLoginDt; // : 최근 로그인 일시
	private String role; // : 권한(ROLE_USER: 일반, ROLE_ADMIN: 관리자)
}
