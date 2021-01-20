package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import javax.persistence.Transient;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	/*
	 * Para a relação Category ~ Product, é necessário criar
	 * essa lista (set), onde a 1 categoria pode ter vários produtos.
	 * Aqui nesse conjunto (coleção), deve ser instanciado com o ponteiro de Product.
	 * 
	 * O nome products foi determinado no modelo de domínio
	 */
	//O transient impede do JPA interpretar isso daqui, para não dá erro.
	//@Transient
	
	/*
	 * Aqui é o mapeamento que está definido em Products.
	 * O JsonIgnore é para não haver o loop que chama a entidade que
	 * está relacionada a Product que tem Category dentro, ai quando vê
	 * que Category tem Product dentro, então fica um chamada eterno.
	 */
	@JsonIgnore
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products = new HashSet<>();
	
	public Category() {
		
	}

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*
	 * Para coleções não faz sentido colocar o setter setProducts(),
	 * porque não se troca uma coleção inteira.
	 */
	public Set<Product> getProducts() {
		return products;
	}
	
	
}
