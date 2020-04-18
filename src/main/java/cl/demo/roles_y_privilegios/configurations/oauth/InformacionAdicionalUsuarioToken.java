package cl.demo.roles_y_privilegios.configurations.oauth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import cl.demo.roles_y_privilegios.entities.services.IUsuarioService;

@Component
public class InformacionAdicionalUsuarioToken implements TokenEnhancer {

	@Autowired
	IUsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

//		ResultadoProc<Usuario> usuarioClienteResult = usuarioService.findByEmail(authentication.getName());
//		Usuario usuarioCliente = usuarioClienteResult.getResultado();
		Map<String, Object> info = new HashMap<>();

//		info.put("full_name", usuarioCliente.getNombre().concat(" ").concat(usuarioCliente.getApellidos()));

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

		return accessToken;
	}

}
