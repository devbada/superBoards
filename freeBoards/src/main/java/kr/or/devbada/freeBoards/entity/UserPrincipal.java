package kr.or.devbada.freeBoards.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.or.devbada.freeBoards.domain.AnonyMembersRVO;
import lombok.Getter;

@Getter
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;

	private AnonyMembersRVO user;

	public UserPrincipal(AnonyMembersRVO user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		if (this.user.getMemId().equalsIgnoreCase("devbadaAdmin")) {
			return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
		}
		return Collections.singleton(new SimpleGrantedAuthority(this.user.getRole()));
	}

	@Override
	public String getPassword() {
		return user.getMemPwd();
	}

	@Override
	public String getUsername() {
		return user.getMemId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
