package com.grocery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grocery_list")
public class GroceryList {

	@Id
	@Column(name="list_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int listId;
	
	@Column(name = "list_name", nullable = false)
	private String listName;

//	@JsonManagedReference(value="groceryListItem")
//	@OneToMany(mappedBy = "groceryList")
//	@JsonIgnore
//	private Set<GroceryItem> groceryItem;
}
