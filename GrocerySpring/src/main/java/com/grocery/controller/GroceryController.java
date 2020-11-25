package com.grocery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.model.GroceryItem;
import com.grocery.model.GroceryList;
import com.grocery.repository.GroceryListItemRepo;
import com.grocery.repository.GroceryListRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GroceryController {
	
	GroceryListRepo groceryRepo;
	GroceryListItemRepo groceryItemRepo;
	
	@Autowired
	public void SetGroceryListRepo(GroceryListRepo groceryListRepo) {
		groceryRepo = groceryListRepo;
	}
	
	@Autowired
	public void SetGroceryItemRepo(GroceryListItemRepo groceryListItemRepo) {
		groceryItemRepo = groceryListItemRepo;
	}
	
	//To find all grocery lists
	@GetMapping("/grocery-list")
	public List<GroceryList> getGroceryList() {
		return groceryRepo.findAll();
	}
		
	//*******************************************************************************************
	//To find all items from a single grocery list. And any other details about the grocery list 
	@GetMapping("/grocery-list/{listId}")
	public List<GroceryItem> getGroceryListItems(@PathVariable("listId") int listId) {
		GroceryList groceryList = groceryRepo.findByListId(listId);
		return groceryItemRepo.findByGroceryList(groceryList);
	}
	//********************************************************************************************
	
	//To create a new grocery list
	@PostMapping(value="/grocery-list")
	public void createGroceryList(@RequestBody GroceryList groceryList) {
		groceryRepo.save(groceryList);
	}
	
	//To create a new item to a grocery list
	@PostMapping(value="/grocery-list/{listId}/item")
	public void createNewListItem(@PathVariable("listId") int listId, @RequestBody GroceryItem groceryItem) {
		GroceryList groceryList = groceryRepo.findByListId(listId);
		groceryItem.setGroceryList(groceryList);
		groceryItemRepo.save(groceryItem);
	}
	
	//To update a grocery list
	@PutMapping(value="/grocery-list/{listId}")
	public void updateGroceryList(@RequestBody GroceryList groceryList ) {
		groceryRepo.save(groceryList);
	}

	//To update a grocery list item
	@PutMapping(value="/grocery-list/{listId}/item")
	public void updateGroceryListItem(@PathVariable("listId") int listId, @RequestBody GroceryItem groceryItem) {
		GroceryList groceryList = groceryRepo.findByListId(listId);
		groceryItem.setGroceryList(groceryList);
		groceryItemRepo.save(groceryItem);
	}
	
	//To remove a grocery list
	@DeleteMapping(value="grocery-list/{listId}")
	public void deleteGroceryList(@PathVariable("listId") int listId) {
		GroceryList groceryList = groceryRepo.findByListId(listId);
		groceryRepo.delete(groceryList);
	}
	
	//To remove a grocery list
	@DeleteMapping(value="grocery-list/{listId}/item/{itemId}")
	public void deleteGroceryListItem(@PathVariable("listId") int listId,@PathVariable("itemId") int itemId) {
		GroceryList groceryList = groceryRepo.findByListId(listId);
		GroceryItem groceryItem = groceryItemRepo.findByItemId(itemId);
		groceryItem.setGroceryList(groceryList);
		System.out.println(groceryItem);
		groceryItemRepo.delete(groceryItem);
	}
}
