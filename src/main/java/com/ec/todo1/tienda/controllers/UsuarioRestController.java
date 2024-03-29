package com.ec.todo1.tienda.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.todo1.tienda.dto.response.BodyResponse;
import com.ec.todo1.tienda.dto.response.UsuarioResponse;
import com.ec.todo1.tienda.models.entity.Usuario;
import com.ec.todo1.tienda.services.IUsuarioService;

/**
 * The REST API for processing the incoming requests the usuario. 
 * Hulk Store 2019 - Todos los derechos reservados
 * @author edisoncsi
 * @version $1.0$
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioRestController extends BaseRestController{
	final static Logger logger = LoggerFactory.getLogger(UsuarioRestController.class);

	@Autowired
	private IUsuarioService usuarioService;
	
	/**
	 * API REST para crear un usuario
	 * @param model
	 * @param reqUser
	 * @return
	 */
	@PostMapping(produces = "application/json")
	public BodyResponse<UsuarioResponse> saveUsuario(ModelMap model, @RequestBody Usuario reqUser) {
		BodyResponse<UsuarioResponse> usuario = null;
		try {
			usuario = usuarioService.crearUsuario(reqUser);
		} catch (DataIntegrityViolationException ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}
		if(usuario == null) {
			throw new NoSuchElementException("Ya existe un usuario con email: " + reqUser.getCorreo());
		}
		return usuario;
	}

	/**
	 * API REST para obtener todos los usuarios
	 * @return
	 */
	@GetMapping(produces = "application/json")
	public List<Usuario> getUsuarios() {
		try {
			return usuarioService.obtenerUsuarios();
		} catch (DataIntegrityViolationException ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}

	}
	
	/**
	 * API REST para obtener un Usuario por correo y password
	 * @param correo
	 * @param password
	 * @return
	 */
	@GetMapping(value="/{correo}/{password}", produces = "application/json")
	public BodyResponse<UsuarioResponse> getUsuarioPorCedulaPassword(@PathVariable("correo") String correo, @PathVariable("password") String password ) {
		try {
			return usuarioService.obtenerPorCorreoPassword(correo, password);
		} catch (DataIntegrityViolationException ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}catch (Exception ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Error verifique los datos ingresados: ");
		}
	}

}
