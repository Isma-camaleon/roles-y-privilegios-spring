package cl.demo.roles_y_privilegios.entities.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.demo.roles_y_privilegios.entities.repositories.PrivilegioRepository;
import cl.demo.roles_y_privilegios.entities.services.IPrivilegioService;
import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class PrivilegioService implements IPrivilegioService {

	@Autowired
	PrivilegioRepository privilegioRepository;

}
