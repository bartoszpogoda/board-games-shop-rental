package bgshoprental.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bgshoprental.entity.Client;
import bgshoprental.entity.Employee;
import bgshoprental.repository.ClientRepository;
import bgshoprental.repository.EmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String password;
		Client potentialClient = clientRepository.findByEmail(email);

		if (potentialClient != null) {
			authorities.add(new SimpleGrantedAuthority("client"));
			password = potentialClient.getPasswordHash();
		} else {
			Employee potentialEmployee = employeeRepository.findByEmail(email);

			if (potentialEmployee != null) {
				authorities.add(new SimpleGrantedAuthority("employee"));
				password = potentialEmployee.getPassword();
				
				if(potentialEmployee.isManager()) {
					authorities.add(new SimpleGrantedAuthority("manager"));
				}
				
			} else {
				throw new UsernameNotFoundException("User doesn't exist");
			}
		}

		return new User(email, password, authorities);
	}

}
