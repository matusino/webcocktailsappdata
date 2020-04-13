package com.webcocktailsapp.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_ingredients", schema = "co")
public class TIngredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_ingrediends_seq")
	@SequenceGenerator(name = "t_ingrediends_seq", sequenceName = "co.t_ingredients_ingrediend_id_seq",allocationSize = 1,initialValue = 0)
	@Column(name = "ingrediend_id", nullable = false)
	private Long ingrediendId;
	@Column(name = "name", nullable = false)
	private String name;
	public TIngredient() {
		super();
	
	}
	public Long getIngrediendId() {
		return ingrediendId;
	}
	public void setIngrediendId(Long ingrediendId) {
		this.ingrediendId = ingrediendId;
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
		result = prime * result + ((ingrediendId == null) ? 0 : ingrediendId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TIngredient other = (TIngredient) obj;
		if (ingrediendId == null) {
			if (other.ingrediendId != null)
				return false;
		} else if (!ingrediendId.equals(other.ingrediendId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
