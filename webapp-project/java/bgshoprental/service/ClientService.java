package bgshoprental.service;

import java.security.Principal;
import java.util.List;

import bgshoprental.entity.Client;

public interface ClientService {
    Iterable<Client> getAllClients();
    List<Client> findByLastNameContains(String lastNamePortion);
	
	Client getClientFromPrincipal(Principal principal);
}
