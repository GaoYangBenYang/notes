package com.gy.services;

import com.gy.bean.Book;
import com.gy.dao.UserDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDaoMapper userDaoMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Book book = userDaoMapper.selectByName("gy");
        if (book==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("select");
        return new User(book.getUserName(),new BCryptPasswordEncoder().encode(book.getPassword()),auths);
    }
}
