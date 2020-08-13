package kr.or.devbada.freeBoards.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 사용자 result 클래스
 * 
 * @author minam.cho
 * @since August 03, 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnonyMembersRVO extends AnonyMembersDVO {

	private String permissions = "";
	
	private String ip;
	
	private Collection<? extends GrantedAuthority> authorities;

}
