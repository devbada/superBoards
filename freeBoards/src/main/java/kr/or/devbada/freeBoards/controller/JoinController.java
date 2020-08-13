package kr.or.devbada.freeBoards.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.devbada.freeBoards.domain.AnonyMembersPVO;
import kr.or.devbada.freeBoards.domain.AnonyMembersRVO;
import kr.or.devbada.freeBoards.dto.JoinDto;
import kr.or.devbada.freeBoards.dto.MailDto;
import kr.or.devbada.freeBoards.enums.AnonyMembersStatusEnum;
import kr.or.devbada.freeBoards.service.AnonyMembersService;
import kr.or.devbada.freeBoards.service.JoinService;
import kr.or.devbada.freeBoards.service.MailService;

/**
 * 사용자 가입/등록/인증 컨트롤러
 * 
 * @author minam.cho
 * @since August 05, 2020
 */
@Controller
@RequestMapping("/join")
public class JoinController {

	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);

	@Autowired
	private JoinService joinService;

	@Autowired
	private AnonyMembersService anonyMembersService;

	@Autowired
	private MailService mailService;

	/**
	 * 루트
	 * 
	 * @param HttpServletRequest, HttpServletResponse
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/")
	public String root(Authentication auth, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "redirect:/join/join.do";
	}

	/**
	 * 사용자 등록 페이지
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/join.do")
	public ModelAndView joinForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("/join/join");
	}

	/**
	 * 사용자 등록 완료(=메일 전송) 후 이동 페이지
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/joinComplete.do")
	public ModelAndView joinComplete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("/join/joinComplete");
	}

	/**
	 * 로그인 정보 초기화 요청 페이지
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/resetLoginInfo.do")
	public ModelAndView resetLoginInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("/join/resetLoginInfo");
	}

	/**
	 * 새로운 사용자 등록
	 * @param request
	 * @param pvo
	 * @return
	 */
	@PostMapping("/insertNewMemberAjax.do")
	public ModelAndView insertNewMember(HttpServletRequest request, @ModelAttribute AnonyMembersPVO pvo) {
		ModelAndView mav = new ModelAndView("jsonView");

		// [1] 등록된 사용자가 있는지 확인

		AnonyMembersRVO memberInfo = anonyMembersService.selectMemberByMail(pvo);

		// 아래에 나열된 이메일 및 아이디는 사용할 수 없다.
		String[] ID_LIST = {"ADMIN", "administrator", "manager", "null", "sysdate"};

		// 아래에 나열된 이메일 및 아이디는 사용할 수 없다.
		String[] EMAIL_LIST = {"ADMIN", "administrator", "manager", "null", "sysdate"};

		String pvoMemId = pvo.getMemId().toUpperCase();

		boolean hasContainIlligalId = false;

		for(int i=0; i < ID_LIST.length; i++) {
			if ( pvoMemId.contains(ID_LIST[i]) ) {
				hasContainIlligalId = true;
				break;
			}
		}

		if ( hasContainIlligalId ) {
			mav.addObject("result", "NOT_ALLOWED_ID");
			return mav;
		}

		// [1-1] 있다면 인증 중 인지 확인하고 메세지 전송
		if (memberInfo != null && StringUtils.isNotEmpty(memberInfo.getJoinCertifiedKey())
				&& memberInfo.getStatus().equals("R") ) {
			mav.addObject("result", "WAIT_AUTHENTICATION"); // 인증 대기 중
			return mav;

		} else if (memberInfo != null ) {
			mav.addObject("result", "DUPLICATE"); // 인증 대기 중
			return mav;

		} else {

			// [1-2] 없다면 등록 후 인증메일 전송
			pvo.setMemPwd(UUID.randomUUID().toString()); // 가입 시 비밀번호 임의 등록
			pvo.setStatus(AnonyMembersStatusEnum.R.name()); // 가입 신청 상태
			pvo.setJoinCertifiedKey(UUID.randomUUID().toString()); // 가입인증을 위한 랜덤 키 UUID 생성
			pvo.setRegId("JOIN_SERVICE");

			int cnt;
			try {
				cnt = joinService.insertAnonyMember(pvo);

				if ( cnt > 0) {
					mav.addObject("result", "SUCCESS"); // 등록 성공
					
				} else {
					mav.addObject("result", "FAIL"); // 실패
					
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
		}
		
		return mav;
	}

	/**
	 * 사용자 이메일 인증 등록 처리
	 * @param joinCertifiedKey
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/authenticate.do")
	public ModelAndView authenticate(
		@RequestParam(value = "joinCertifiedKey", required=true) String joinCertifiedKey) throws Exception {
		ModelAndView mav = new ModelAndView("/join/authenticate");

		try {
			int cnt = joinService.authenticateMemberByJoinCertifiedKey(joinCertifiedKey);

			if ( cnt > 0 ) {
				mav.addObject("joinCertifiedKey", joinCertifiedKey);
				mav.addObject("result", "SUCCESS");
			} else {
				mav.addObject("result", "FAIL");
			}

		} catch (Exception e) {
			logger.error("/join/authenticate ERROR", e.getMessage());
		}
		
		return mav;
	}

	/**
	 * 사용자 이메일 인증 등록(패스워드 변경) 처리
	 * @param joinCertifiedKey
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/authenticatePassword.do")
	public ModelAndView authenticatePassword(
		@RequestParam(value = "joinCertifiedKey", required=true) String joinCertifiedKey) throws Exception {
		ModelAndView mav = new ModelAndView("/join/authenticate");

		try {
			int cnt = joinService.authenticateMemberByJoinCertifiedKeyForResetLoginInfo(joinCertifiedKey);

			if ( cnt > 0 ) {
				mav.addObject("joinCertifiedKey", joinCertifiedKey);
				mav.addObject("result", "SUCCESS");
			} else {
				mav.addObject("result", "FAIL");
			}

		} catch (Exception e) {
			logger.error("/join/authenticate ERROR", e.getMessage());
		}
		
		return mav;
	}

	/**
	 * 인증 후 비밀번호 변경처리
	 * @param pvo
	 * @param joinCertifiedKey
	 * @return
	 */
	@PostMapping("/updateMemberPasswordAjax.do")
	public ModelAndView updateMemberPasswordAjax(@ModelAttribute JoinDto pvo) {

		ModelAndView mav = new ModelAndView("jsonView");

		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(6);

			pvo.setPassword(encoder.encode(pvo.getPassword()));

			int cnt = joinService.updateMemberPasswordAfterJoin(pvo);

			if ( cnt > 0 ) {
				mav.addObject("result", "SUCCESS");
			} else {
				mav.addObject("result", "FAIL");
			}

		} catch (Exception e) {
			logger.error("/join/authenticate ERROR", e.getMessage());
		}

		return mav;
	}
	
	/**
	 * 로그인 정보 초기화 (이메일 전송하여 로그인 정보를 초기화할 수 있도록 한다.)
	 * @param pvo
	 * @return
	 */
	@PostMapping("/requestResetLoginInfoAjax.do")
	public ModelAndView requestResetLoginInfoAjax(@ModelAttribute AnonyMembersPVO pvo) {

		ModelAndView mav = new ModelAndView("jsonView");

		AnonyMembersRVO memberInfo = anonyMembersService.selectMemberByOne(pvo);
		
		if ( memberInfo == null ) {
			mav.addObject("result", "NOT_FOUND");
			return mav;
		} else {

			final String joinCertifiedKey = UUID.randomUUID().toString();

			// 멤버 업데이트
			pvo.setMemPwd(UUID.randomUUID().toString()); // 가입 시 비밀번호 임의 등록
			pvo.setStatus(AnonyMembersStatusEnum.P.name()); // 이메일(비밀번호) 재인증 신청 상태
			pvo.setJoinCertifiedKey(joinCertifiedKey); // 가입인증을 위한 랜덤 키 UUID 생성
			pvo.setUpdId("RESET_LOGIN_SERVICE");

			int cnt;

			try {
				cnt = joinService.updateAnonyMember(pvo);
				
				// 이메일 인증 보내기
				if (cnt > 0) {
					String message = "<h2>비밀번호 재설정을 위한 인증 메일 입니다.</h2><p>아래의 주소를 클릭하여 인증을 마무리해 주시기 바랍니다.</p>";
					message += "<br/><p><a href=http://localhost:8181/join/authenticatePassword.do?joinCertifiedKey="
							+ joinCertifiedKey + ">인증받기</a></p>";

					MailDto mailInfo = new MailDto();
					mailInfo.setAddress(pvo.getEmail());
					mailInfo.setMessage(message);
					mailInfo.setTitle("사용자 비밀번호 재설정 인증메일 :: 초록우산 어린이재단");

					mailService.mailSend(mailInfo);
				}
				
				if ( cnt > 0) {
					mav.addObject("result", "SUCCESS"); // 등록 성공
					
				} else {
					mav.addObject("result", "FAIL"); // 실패
					
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		return mav;
	}
	
}