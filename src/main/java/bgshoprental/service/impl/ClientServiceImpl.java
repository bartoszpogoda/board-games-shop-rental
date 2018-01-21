package bgshoprental.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import bgshoprental.entity.Client;
import bgshoprental.repository.ClientRepository;
import bgshoprental.service.ClientService;
import bgshoprental.service.UserService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private Md5PasswordEncoder md5PasswrdEncoder;
	
	@Autowired
	private UserService userService;

	@Override
	public Iterable<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public List<Client> findByLastNameContains(String lastNamePortion) {
		return clientRepository.findByLastNameContains(lastNamePortion);
	}

	@Override
	public Client getClientFromPrincipal(Principal principal) {
		String loggedInUserEmail = principal.getName();

		return userService.getClientByEmail(loggedInUserEmail);
	}

	@Override
	public void register(Client client, String password) {
		client.setPasswordHash(md5PasswrdEncoder.encodePassword(password, null));
		
		clientRepository.save(client);
	}
}
