package com.webcocktailsapp.data;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.webcocktailsapp.data.model.CocktailIngredientId;
import com.webcocktailsapp.data.model.TCocktail;
import com.webcocktailsapp.data.model.TIngredient;
import com.webcocktailsapp.data.model.TCocktailXIngredient;

public class WebCocktailsAppData {
	
	private static final String PERSISTENCE_UNIT_NAME = "WebCocktailsAppData";
	private static EntityManagerFactory factory;
	

	public static void main(String[] args) throws IOException {
		
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
		
        List<String> cocktailsUrlList = new LinkedList<String>();
		
		
		Document document = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_IBA_official_cocktails")
				.header("Accept-Encoding", "gzip, deflate")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
				.maxBodySize(0).timeout(600000).get();
		
		Elements divElements = document.getElementsByClass("mw-parser-output");
		Element divElement =  divElements.get(0);
		Elements liElements = divElement.getElementsByTag("li");
		
		for (Element element : liElements) {
			String attrHref = element.getElementsByTag("a").isEmpty() ? "" : element.getElementsByTag("a").get(0).attr("href");
		
			if(attrHref.contains("(cocktail)")) {
				cocktailsUrlList.add("https://en.wikipedia.org" + attrHref);
			}
		
		}
		
		
		for (Iterator<String> iterator = cocktailsUrlList.iterator(); iterator.hasNext();) {
			String cocktailUrl = iterator.next();
			
			
			
			Document document2 = Jsoup.connect(cocktailUrl)
					.header("Accept-Encoding", "gzip, deflate")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
					.referrer("http://www.google.com")
					.maxBodySize(0).timeout(600000).ignoreHttpErrors(true).get();
			
			TCocktail cocktailForCrossTable = null;
//			String name = "";
			Elements nameCheck = document2.getElementsByTag("caption");
			if(!nameCheck.isEmpty()) {
				 String name =  nameCheck.get(0).text();
				 cocktailForCrossTable = insertCocktail(name, cocktailUrl, em);
			}else {
				System.out.println("NENI TU NAZOV");
			}
			
			Elements ingredientsCheck= document2.getElementsByClass("infobox");
			
			if(!ingredientsCheck.isEmpty()) {
				Element tableElement = document2.getElementsByClass("infobox").isEmpty() ? null : document2.getElementsByClass("infobox").get(0);
				Elements liElements2 = tableElement.getElementsByTag("li"); 
				for (Element liElement : liElements2) {
					//Ingredient Name 
					String ingredient = liElement.getElementsByTag("a").isEmpty() ? "" : liElement.getElementsByTag("a").get(0).text().toLowerCase();
					
					if(!ingredient.equals("")) {
						
						TIngredient ingredientForCrossTable= insertIngredient(ingredient, em);
						insertCocktailXIngredient(cocktailForCrossTable, ingredientForCrossTable, em);
						ingredientForCrossTable = null;
					}
				}
			}else {
				System.out.println("NIESU TU INGRENDIENCIE");
				
				}
			cocktailForCrossTable = null;
				
			}
			
				System.out.println("ALL DONE");	
			em.getTransaction().commit();
			em.close();
			
			
		
		

	}
	
	private static TCocktail insertCocktail(String cocktailName, String cocktailUrl, EntityManager em) {

		TCocktail cocktail = new TCocktail();
		cocktail.setName(cocktailName.trim());
		cocktail.setUrl(cocktailUrl.trim());
		em.persist(cocktail);
		em.flush();
		
		System.out.println("Inserted cocktail with name " + cocktail.getName());

		return cocktail;
	}
	
	private static TIngredient insertIngredient(String ingredientName, EntityManager em) {
		
		Query query = em.createQuery("SELECT t from TIngredient t");
		List<TIngredient> ingredientList = query.getResultList();
		
		for (Iterator<TIngredient> it = ingredientList.iterator(); it.hasNext();) {
			TIngredient ingredientFromTable = it.next();
			
			if (ingredientName.trim().toLowerCase().equalsIgnoreCase(ingredientFromTable.getName())) {
				return ingredientFromTable;
			}
		}

		TIngredient newIngredient = new TIngredient();
		newIngredient.setName(ingredientName.trim().toLowerCase());
		em.persist(newIngredient);
		em.flush();
		
		System.out.println("Inserted ingredient with name " + newIngredient.getName());
		
		return newIngredient;
	}
	
	private static void insertCocktailXIngredient(TCocktail cocktail, TIngredient ingredient, EntityManager em) {

//		TypedQuery<TCocktailXIngredient> query = em.createQuery(
//				"SELECT t FROM TCocktailXIngredient t " + 
//				"WHERE t.cocktail.cocktailId = :cocktailId " + 
//				"AND t.ingredient.ingredientId = :ingredientId", TCocktailXIngredient.class);
//		
//		query.setParameter("cocktailId", cocktail.getCocktailIdLong());
//		query.setParameter("ingredientId", ingredient.getIngrediendId());

//		if (query.getResultList().size() == 0) {
			
			TCocktailXIngredient cocktailXIngredient = new TCocktailXIngredient();
			
			cocktailXIngredient.setCocktail(cocktail);
			cocktailXIngredient.setIngredient(ingredient);
			
			cocktailXIngredient.setId(new CocktailIngredientId(cocktail.getCocktailIdLong(), ingredient.getIngrediendId()));
			
			em.persist(cocktailXIngredient);
			em.flush();
			
			System.out.println("Inserted cocktail and ingredient with names " + cocktail.getName() + "/" + ingredient.getName());
//		}
	}

}
