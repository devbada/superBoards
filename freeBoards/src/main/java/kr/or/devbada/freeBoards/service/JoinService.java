package kr.or.devbada.freeBoards.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.devbada.freeBoards.domain.AnonyMembersPVO;
import kr.or.devbada.freeBoards.domain.AnonyMembersRVO;
import kr.or.devbada.freeBoards.dto.JoinDto;
import kr.or.devbada.freeBoards.dto.MailDto;
import kr.or.devbada.freeBoards.mapper.AnonyMembersMapper;

/**
 * 사용자 가입/등록/인증 서비스
 * 
 * @author minam.cho
 * @since August 05, 2020
 */
@Service
public class JoinService {

	private static final Logger logger = LoggerFactory.getLogger(JoinService.class);

	@Autowired
	private AnonyMembersMapper anonyMembersMapper;

	@Autowired
	private MailService mailService;

	/**
	 * 사용자 등록
	 * 
	 * @param pvo
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public int insertAnonyMember(AnonyMembersPVO pvo) throws Exception {
		logger.info("JoinService.insertAnonyMember :: ### 사용자 등록");

		int cnt = -1;

		try {

			cnt = anonyMembersMapper.insertAnonyMember(pvo);

			// 등록 후 인증 메일 전송
			if (cnt > 0) {
				String message = "<h2>사용자 등록을 위한 인증 메일 입니다.</h2><p>아래의 주소를 클릭하여 인증을 마무리해 주시기 바랍니다.</p>";
				message += "<br/><p><a href=http://localhost:8181/join/authenticate.do?joinCertifiedKey="
						+ pvo.getJoinCertifiedKey() + ">인증받기</a></p>";

				MailDto mailInfo = new MailDto();
				mailInfo.setAddress(pvo.getEmail());
				mailInfo.setMessage(message);
				mailInfo.setTitle("사용자 등록 인증메일 :: 초록우산 어린이재단");

				mailService.mailSend(mailInfo);
			}

		} catch (Exception e) {
			cnt = -1;
			logger.error("사용자 등록 실패:::", e);

			throw new Exception(e.getMessage());
		}

		return cnt;
	}

	/**
	 * 이메일 인증 처리
	 * 
	 * @param joinCertifiedKey
	 * @return
	 */
	public int authenticateMemberByJoinCertifiedKey(String joinCertifiedKey) throws Exception {
		int cnt = -1;

		AnonyMembersRVO anonyMember = anonyMembersMapper.selectMemberByJoinCertifiedKey(joinCertifiedKey);

		if ( anonyMember == null ) {
			cnt = -1;
		} else {
			cnt = anonyMembersMapper.updateAnonyMemberByJoinCertifiedKey(joinCertifiedKey);
		}

		return cnt;
	}

	/**
	 * 인증 후 비밀번호 변경처리
	 * @param pvo
	 * @return
	 */
	public int updateMemberPasswordAfterJoin(JoinDto pvo) {
		int cnt = -1;

		AnonyMembersRVO anonyMember = anonyMembersMapper.selectMemberByJoinCertifiedKey(pvo.getJoinCertifiedKey());

		if ( anonyMember == null ) {
			cnt = -1;
		} else {
			cnt = anonyMembersMapper.updateMemberPasswordAfterJoin(pvo);
		}

		return cnt;
	}

	/**
	 * 사용자 정보 업데이트(수정)
	 * @param pvo
	 * @return
	 */
	public int updateAnonyMember(AnonyMembersPVO pvo) {
		return anonyMembersMapper.updateAnonyMember(pvo);
	}

	/**
	 * 비밀번호 재설정을 위한 (=로그인 정보 초기화) 서비스
	 * @param joinCertifiedKey
	 * @param status
	 * @return
	 */
	public int authenticateMemberByJoinCertifiedKeyForResetLoginInfo(String joinCertifiedKey) {
		int cnt = -1;

		AnonyMembersRVO anonyMember = anonyMembersMapper.selectMemberByJoinCertifiedKey(joinCertifiedKey);

		if ( anonyMember == null ) {
			cnt = -1;
		} else {
			cnt = anonyMembersMapper.updateAnonyMemberByJoinCertifiedKeyForResetLoginInfo(joinCertifiedKey);
		}

		return cnt;
	}
	
}
