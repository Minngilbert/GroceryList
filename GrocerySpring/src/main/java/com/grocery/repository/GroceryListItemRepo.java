package com.grocery.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.grocery.model.GroceryItem;
import com.grocery.model.GroceryList;

public interface GroceryListItemRepo extends CrudRepository<GroceryItem, Integer>{
	
	public GroceryItem findByItemId(int itemId);
	public void delete(GroceryItem groceryItem);
	public List<GroceryItem> findAll();
	public List<GroceryItem> findByGroceryList(GroceryList groceryList);
}
