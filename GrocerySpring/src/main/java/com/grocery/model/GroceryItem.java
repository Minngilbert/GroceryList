package com.grocery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grocery_item")
public class GroceryItem {
	
	@Id
	@Column(name = "item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
	
	@Column(name="cost_of_item")
	private double costOfItem;
	
	@Column(name= "grocery_name")
	private String groceryName;
	
	@Column(name= "grocery_type")
	private String groceryType;
		
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "list_id", nullable=false)
	private GroceryList groceryList;
}