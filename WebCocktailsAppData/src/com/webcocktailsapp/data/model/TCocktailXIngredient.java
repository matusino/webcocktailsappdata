package com.webcocktailsapp.data.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "t_cocktails_x_ingredients", schema = "co")
public class TCocktailXIngredient implements Serializable {
	
	private static final long serialVersionUID = 3038197062663745313L;

	@EmbeddedId
    private CocktailIngredientId id;

    @ManyToOne
    @MapsId("cocktailId")
    @JoinColumn(name = "cocktail_id", nullable = false)
    private TCocktail cocktail;
 
    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id", nullable = false)
    private TIngredient ingredient;

	public TCocktailXIngredient() {
		super();
	
	}

	public CocktailIngredientId getId() {
		return id;
	}

	public void setId(CocktailIngredientId id) {
		this.id = id;
	}

	public TCocktail getCocktail() {
		return cocktail;
	}

	public void setCocktail(TCocktail cocktail) {
		this.cocktail = cocktail;
	}

	public TIngredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(TIngredient ingredient) {
		this.ingredient = ingredient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cocktail == null) ? 0 : cocktail.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
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
		TCocktailXIngredient other = (TCocktailXIngredient) obj;
		if (cocktail == null) {
			if (other.cocktail != null)
				return false;
		} else if (!cocktail.equals(other.cocktail))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		return true;
	}
	
	
	
	

}
