import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { GroceryItem } from '../models/groceryItem';
import { GroceryService } from '../service/grocery.service';

@Component({
  selector: 'app-grocery-item',
  templateUrl: './grocery-item.component.html',
  styleUrls: ['./grocery-item.component.css']
})
export class GroceryItemComponent implements OnInit {
  
  groceryListId: String;
  currGroceryItemId: number;
  groceryItems: GroceryItem [];
  
  groceryItem = new FormGroup({
    groceryName: new FormControl(''),
    groceryType: new FormControl(''),
    costOfItem: new FormControl('')
  });

  constructor(private groceryService: GroceryService, private router: Router) { 
    let url:string = this.router.url;
    let splitUrl =  url.split('/');
    this.groceryListId = splitUrl[splitUrl.length -1];
  }

  ngOnInit(): void {
    this.getGroceryItems();
  }

  getGroceryItems(){
    this.groceryService.getGroceryItemsByListId(this.groceryListId).subscribe(
      (data) => {
        this.groceryItems = data;
      }
    )
  }

  setCurrItemId(currItem){
    this.currGroceryItemId = currItem['itemId'];
    this.groceryItem.value.groceryName = currItem['groceryName'];
    this.groceryItem.value.groceryType = currItem['groceryType'];
    this.groceryItem.value.costOfItem = currItem['costOfItem'];
  }

  onSubmit(){
    this.groceryItem.value.itemId = this.currGroceryItemId;
    this.groceryService.updateGroceryItem(this.groceryListId, this.groceryItem.value).subscribe();
    this.groceryItem.reset();
    setTimeout(() => this.getGroceryItems(), 300);
  }

  createItem(){
    this.groceryService.updateGroceryItem(this.groceryListId, this.groceryItem.value).subscribe();
    this.groceryItem.reset();
    setTimeout(() => this.getGroceryItems(), 300);
  }

  removeItem(groceryItem:GroceryItem){
    this.groceryService.deleteGroceryItem(this.groceryListId, groceryItem.itemId).subscribe();
    setTimeout(() => this.getGroceryItems(), 300);
  }
}