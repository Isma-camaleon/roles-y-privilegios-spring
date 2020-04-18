package cl.demo.roles_y_privilegios.entities.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.demo.roles_y_privilegios.entities.repositories.RolRepository;
import cl.demo.roles_y_privilegios.entities.services.IRolService;
import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class RolService implements IRolService {
	@Autowired
	RolRepository rolRepository;
}
