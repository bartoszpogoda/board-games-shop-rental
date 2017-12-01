package bgshoprental.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bgshoprental.entity.Client;
import bgshoprental.repository.ClientRepository;
import bgshoprental.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Iterable<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public List<Client> findByLastNameContains(String lastNamePortion) {
		return clientRepository.findByLastNameContains(lastNamePortion);
	}
}