package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;

	/*
	 * Fazendo a associação Product ~ Category lista para relação 1 produto varias
	 * categorias
	 */
	/*
	 * O transint é para o JPA não interpretar essa lista, util antes de mapeamento
	 * que transformava estas coleções em tabela de associação do modelo relacional.
	 * 
	 * @Transient
	 */
	@ManyToMany
	/*
	 * é para criar uma tabela cujo o nome e quais as chaves estrangeiras que vai
	 * associar a tabela de Produto com a tabela de Categoria. Ambos são passados
	 * pelo parâmetro
	 */
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
	
	/*
	 * Agora iremos colocar a coleção (lista set) que será responsável em armazenar as relação com
	 * o OrdemItem (este mapeia a relação entre Product e Order)
	 */
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>();

	public Product() {

	}

	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	
	/*
	 * Os getters e setters gerados tem que do tipo Order ao invés de OrderItem
	 */
	@JsonIgnore
	public Set<Order> getOrders(){
		Set<Order> set = new HashSet<>();
		for(OrderItem x : items) {
			set.add(x.getOrder());
		}
		return set;
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
