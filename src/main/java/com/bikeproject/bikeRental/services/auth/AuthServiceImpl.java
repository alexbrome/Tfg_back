package com.bikeproject.bikeRental.services.auth;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bikeproject.bikeRental.Enums.UserRole;
import com.bikeproject.bikeRental.dto.SignupRequest;
import com.bikeproject.bikeRental.dto.UserDto;
import com.bikeproject.bikeRental.entity.User;
import com.bikeproject.bikeRental.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	
	//Crear una cuenta de administrador
	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
		if(adminAccount == null) {
			User newAdminAccount = new User();
			newAdminAccount.setName("Admin");
			newAdminAccount.setEmail("admin@gmail.com");
			newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
			newAdminAccount.setUserRole(UserRole.ADMIN);
			userRepository.save(newAdminAccount);
			//System.out.println("administrador creado con exito");
		}
	}
    //Crear cliente/usuario
	public UserDto createCustomer(SignupRequest signupRequest) {
		User user = new User();
		user.setName(signupRequest.getName());
		user.setEmail(signupRequest.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setUserRole(UserRole.CUSTOMER);
		User createdUser = userRepository.save(user);
		UserDto userDto = new UserDto();
		userDto.setId(createdUser.getId());     
		return userDto;
	}

	//Comprobar si el email existe
	@Override
	@Transactional
	public boolean hasCustomerWithEmail(String email) {		
		return userRepository.findFirstByEmail(email).isPresent();
	}

}
