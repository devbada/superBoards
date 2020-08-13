package kr.or.devbada.freeBoards.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.devbada.freeBoards.domain.AnonyMemberLoginPVO;
import kr.or.devbada.freeBoards.domain.AnonyMembersPVO;
import kr.or.devbada.freeBoards.domain.AnonyMembersRVO;
import kr.or.devbada.freeBoards.mapper.AnonyMembersMapper;

@Service
public class AnonyMembersService {
	
	private static final Logger logger = LoggerFactory.getLogger(AnonyMembersService.class);

	@Autowired
	AnonyMembersMapper anonyMembersMapper;

	/**
	 * 등록된 사용자 조회 (단건)
	 * @param pvo
	 * @return
	 */
	public AnonyMembersRVO selectAnonyMember(AnonyMembersPVO pvo) {

		logger.info("AnonyMemberService.selectAnonymember");
		AnonyMembersRVO findUser = anonyMembersMapper.selectMemberById(pvo.getMemId());

		return findUser;
	}

	public AnonyMembersRVO selectMemberByMail(AnonyMembersPVO pvo) {
		return anonyMembersMapper.selectMemberByMail(pvo);
	}

	/**
	 * 로그인 기록 남기기
	 * @param loginPVO
	 */
	public void insertMemberLoginLog(AnonyMemberLoginPVO loginPVO) {
		anonyMembersMapper.insertMemberLoginLog(loginPVO);
	}

	/**
	 * 단건의 등록된 사용자 조회(범용)
	 * @param pvo
	 * @return
	 */
	public AnonyMembersRVO selectMemberByOne(AnonyMembersPVO pvo) {
		return anonyMembersMapper.selectMemberByOne(pvo);
	}

}
