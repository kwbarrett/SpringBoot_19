package com.example.demo;

import com.example.demo.Role;
import com.example.demo.User;
import com.example.demo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public SSUserDetailService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
       /* try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                System.out.println("user not found with the provided username " + user.toString());
                return null;
            }

            System.out.println(" user from username " + user.toString());
            return new CustomUserDetails(user, getAuthorities(user));
        } catch (Exception e){
            throw new UsernameNotFoundException("User not found");
        }*/

        try {
            User user = userRepository.findByUsername(username);
            if (user == null)
            {
                return null;
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthorities(user));
        }
        catch (Exception e)
        {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role role : user.getRoles())
        {
            System.out.println("roles are " + role.getRole());
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }

        System.out.println("authorities are "  + authorities.toString());
        return authorities;
    }
}