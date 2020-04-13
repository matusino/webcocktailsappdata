package com.webcocktailsapp.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CocktailIngredientId implements Serializable{
	
	private static final long serialVersionUID = -3771291453233836884L;

	@Column(name = "cocktail_id")// tuzmenit nazvy na cocktailId ? 
	private Long cocktailId;
	
	@Column(name = "ingrediend_id")
	private Long ingredientId;

	public CocktailIngredientId() {
		super();
	
	}

	public CocktailIngredientId(Long cocktailId, Long ingredientId) {
		this.cocktailId = cocktailId;
		this.ingredientId = ingredientId;
	}
	
	

	public Long getCocktailId() {
		return cocktailId;
	}

	public void setCocktailId(Long cocktailId) {
		this.cocktailId = cocktailId;
	}

	public Long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cocktailId == null) ? 0 : cocktailId.hashCode());
		result = prime * result + ((ingredientId == null) ? 0 : ingredientId.hashCode());
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
		CocktailIngredientId other = (CocktailIngredientId) obj;
		if (cocktailId == null) {
			if (other.cocktailId != null)
				return false;
		} else if (!cocktailId.equals(other.cocktailId))
			return false;
		if (ingredientId == null) {
			if (other.ingredientId != null)
				return false;
		} else if (!ingredientId.equals(other.ingredientId))
			return false;
		return true;
	}
	
	
	
	
	
}
