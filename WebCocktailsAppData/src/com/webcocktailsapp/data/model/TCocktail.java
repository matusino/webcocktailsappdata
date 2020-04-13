package com.webcocktailsapp.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_cocktails", schema = "co")
public class TCocktail implements Serializable {
	
	private static final long serialVersionUID = 4598684918094601263L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_cocktails_seq")
	@SequenceGenerator(name = "t_cocktails_seq", sequenceName = "co.t_cocktails_cocktail_id_seq",allocationSize = 1,initialValue = 0)
	@Column(name = "cocktail_id", nullable = false)
	private Long cocktailIdLong;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "url", nullable = false)
	private String url;
	public TCocktail() {
		super();
	}
	public Long getCocktailIdLong() {
		return cocktailIdLong;
	}
	public void setCocktailIdLong(Long cocktailIdLong) {
		this.cocktailIdLong = cocktailIdLong;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cocktailIdLong == null) ? 0 : cocktailIdLong.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		TCocktail other = (TCocktail) obj;
		if (cocktailIdLong == null) {
			if (other.cocktailIdLong != null)
				return false;
		} else if (!cocktailIdLong.equals(other.cocktailIdLong))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
}
