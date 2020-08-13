package kr.or.devbada.freeBoards.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.devbada.freeBoards.domain.AnonyMemberLoginPVO;
import kr.or.devbada.freeBoards.domain.AnonyMembersPVO;
import kr.or.devbada.freeBoards.domain.AnonyMembersRVO;
import kr.or.devbada.freeBoards.dto.JoinDto;

/**
 * 사용자 ID 매퍼
 * @author minam.cho
 * @since August 03, 2020
 */
@Mapper
public interface AnonyMembersMapper {
	/**
	 * 단건의 사용자 정보를 조회
	 * @param memId
	 * @return
	 */
	AnonyMembersRVO selectMemberById(String memId);

	/**
	 * 사용자 등록
	 * @param pvo
	 * @return
	 */
	int insertAnonyMember(AnonyMembersPVO pvo);

	/**
	 * 회원가입을 위한 사용자 정보 조회
	 * @param pvo
	 * @return
	 */
	AnonyMembersRVO selectMemberByMail(AnonyMembersPVO pvo);

	/**
	 * 등록 인증키를 이용하여 등록 사용자 조회
	 * @param joinCertifiedKey
	 * @return
	 */
	AnonyMembersRVO selectMemberByJoinCertifiedKey(String joinCertifiedKey);

	/**
	 * 등록 인증키를 이용하여 사용자 인증 완료 처리 (업데이트)
	 * @param joinCertifiedKey
	 * @return
	 */
	int updateAnonyMemberByJoinCertifiedKey(String joinCertifiedKey);

	/**
	 * 회원 메일 인증 후 비밀번호 변경처리
	 * @param pvo
	 * @return
	 */
	int updateMemberPasswordAfterJoin(JoinDto pvo);

	/**
	 * 로그인 기록 남기기
	 * @param loginPVO
	 */
	void insertMemberLoginLog(AnonyMemberLoginPVO loginPVO);

	/**
	 * 사용자 정보 업데이트(수정)
	 * @param pvo
	 * @return
	 */
	int updateAnonyMember(AnonyMembersPVO pvo);

	/**
	 * 등록 인증키를 이용하여 사용자 인증 완료 처리 (로그인 정보 재설정)
	 * @param joinCertifiedKey
	 * @return
	 */
	int updateAnonyMemberByJoinCertifiedKeyForResetLoginInfo(String joinCertifiedKey);

	/**
	 * 단건의 등록된 사용자 조회(범용)
	 * @param pvo
	 * @return
	 */
	AnonyMembersRVO selectMemberByOne(AnonyMembersPVO pvo);
	
}
