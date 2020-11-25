import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { GroceryList } from '../models/groceryList';
import { GroceryService } from '../service/grocery.service';

@Component({
  selector: 'app-grocery-list',
  templateUrl: './grocery-list.component.html',
  styleUrls: ['./grocery-list.component.css']
})
export class GroceryListComponent implements OnInit {

  groceryLists: GroceryList [];
  CurrGroceryListId: number;

  groceryList = new FormGroup({
    listName: new FormControl(''),
  });

  constructor(private groceryService: GroceryService) { }

  ngOnInit(): void {
    this.getGroceryList();
  }

  getGroceryList(){
    this.groceryService.getGroceryLists().subscribe(
      (data) => {
        this.groceryLists = data;
      }
    )
  }
  

  setCurrItemId(currItem){
    this.CurrGroceryListId = currItem['listId'];
    this.groceryList.value.listName = currItem['listName'];
  }

  onSubmit(){
    this.groceryList.value.listId = this.CurrGroceryListId;
    this.groceryService.updateGroceryList(this.groceryList.value).subscribe();
    this.groceryList.reset();
    setTimeout(() => this.getGroceryList(), 300);
  }

  createGroceryList(){
    this.groceryService.createGroceryList(this.groceryList.value).subscribe();
    this.groceryList.reset();
    setTimeout(() => this.getGroceryList(), 300);
  }

  removeGroceryList(groceryList:GroceryList){
    this.groceryService.deleteGroceryList(groceryList.listId).subscribe();
    setTimeout(() => this.getGroceryList(), 300);
  }
}
