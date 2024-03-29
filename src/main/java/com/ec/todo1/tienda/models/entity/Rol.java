package com.ec.todo1.tienda.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The persistent class for the rol database table.
 * Hulk Store 2019 - Todos los derechos reservados
 * @author edisoncsis
 * @version $1.0$
 */
@Entity
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String rol;
	
	private String descripcion;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
