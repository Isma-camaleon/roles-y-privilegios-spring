package cl.demo.roles_y_privilegios.configurations.oauth;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import cl.demo.roles_y_privilegios.entities.Role;
import cl.demo.roles_y_privilegios.entities.Usuario;
import cl.demo.roles_y_privilegios.entities.services.IUsuarioService;
import cl.demo.roles_y_privilegios.utils.ResultadoProc;

@Component
public class InformacionAdicionalUsuarioToken implements TokenEnhancer {

	@Autowired
	IUsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		ResultadoProc<Usuario> usuarioResult = usuarioService.findByEmail(authentication.getName());
		Usuario usuario = usuarioResult.getResultado();
		Map<String, Object> info = new HashMap<>();

		Set<String> roles = new HashSet<>();
		usuario.getRoles().forEach((final Role rol) -> roles.add(rol.getNombre()));
		info.put("roles", roles);

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

		return accessToken;
	}

}
