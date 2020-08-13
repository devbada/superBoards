package kr.or.devbada.freeBoards.enums;

/**
 * 
 * 사용자 상태
 * @author minam.cho
 *
 */
public enum AnonyMembersStatusEnum {
	// : 사용자 상태(R: 가, A: 활성(메일 인증 후 사용중 상태), D: 중지상태(관리자 등에 의해 임의), L: 탈퇴상태(본인에 직접 탈퇴)
	
	/**
	 * 가입신청(메일인증대기)
	 */
	R,
	
	/**
	 * 활성(메일 인증 후 사용중 상태)
	 */
	A,
	
	/**
	 * 중지상태(관리자 등에 의해 임의)
	 */
	D,
	
	/**
	 * 탈퇴상태(본인에 직접 탈퇴)
	 */
	L,

	/**
	 * 비밀번호 재인증 요청 상태
	 */
	P
	
}
