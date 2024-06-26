package com.bikeproject.bikeRental.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bikeproject.bikeRental.entity.User;
import com.bikeproject.bikeRental.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findFirstByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            }
        };
    }

	@Override
	public String getEmailByUserId(Long id) {
		User user=userRepository.findById(id).get();
		return user.getEmail();
	}
}