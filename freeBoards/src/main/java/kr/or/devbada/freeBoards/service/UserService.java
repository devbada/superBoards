package kr.or.devbada.freeBoards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.devbada.freeBoards.config.SecurityConfig;
import kr.or.devbada.freeBoards.domain.AnonyMembersRVO;
import kr.or.devbada.freeBoards.entity.UserPrincipal;
import kr.or.devbada.freeBoards.mapper.AnonyMembersMapper;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	AnonyMembersMapper anonyMembersMapper;

	@Autowired
	SecurityConfig securityConfig;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AnonyMembersRVO findUser = anonyMembersMapper.selectMemberById(username);

		if (findUser == null)
			throw new UsernameNotFoundException("User 404");

		return new UserPrincipal(findUser);
	}

}
