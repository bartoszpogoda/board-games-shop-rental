package bgshoprental.repository;

import bgshoprental.entity.Client;

public interface ClientRepositoryCustom {
	Client findUserByEmail(String email);
}
