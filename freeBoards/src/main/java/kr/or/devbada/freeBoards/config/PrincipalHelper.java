package kr.or.devbada.freeBoards.config;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import kr.or.devbada.freeBoards.domain.AnonyMembersRVO;
import kr.or.devbada.freeBoards.entity.UserPrincipal;

@Component
public class PrincipalHelper {

	public static AnonyMembersRVO getUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (null == authentication)
			return null;

		if (false == authentication.isAuthenticated())
			return null;

		if (authentication instanceof AnonymousAuthenticationToken)
			return null;

		return ((UserPrincipal) authentication.getPrincipal()).getUser();
	}

	public static String getMemId() {
		AnonyMembersRVO user = getUser();
		return null == user ? null : user.getMemId();
	}

	public static String getNickName() {
		AnonyMembersRVO user = getUser();
		return null == user ? null : user.getNickName();
	}

	public static String getRealName() {
		AnonyMembersRVO user = getUser();
		return null == user ? null : user.getName();
	}
}
