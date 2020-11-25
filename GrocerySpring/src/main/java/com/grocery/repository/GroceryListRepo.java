package com.grocery.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grocery.model.GroceryList;

@Repository
public interface GroceryListRepo extends CrudRepository<GroceryList, Integer> {
	
	public GroceryList findByListId(int listId);
	public GroceryList findBylistName(String listName);
	public void delete(GroceryList groceryList);
	public List<GroceryList> findAll();
}