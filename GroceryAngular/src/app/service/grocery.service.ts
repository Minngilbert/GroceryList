import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GroceryList } from '../models/groceryList';
import { GroceryItem } from '../models/groceryItem';

@Injectable({
  providedIn: 'root'
})
export class GroceryService {

  constructor(private http: HttpClient) { }

  url ="http://localhost:8080/";


  getGroceryLists():Observable<GroceryList[]>{
    return this.http.get<GroceryList[]>(this.url +"grocery-list");
  }

  createGroceryList(groceryList:GroceryList):Observable<GroceryList>{
    return this.http.post<GroceryList>(this.url + "grocery-list", groceryList);
  }

  updateGroceryList(groceryList:any):Observable<Object>{
    return this.http.put<Object[]>(this.url +"grocery-list/" +groceryList.listId ,groceryList );
  }

  deleteGroceryList(listId:number){
    return this.http.delete(this.url +"grocery-list/" + listId);
  }

  getGroceryItemsByListId(listId:String):Observable<GroceryItem[]>{
    return this.http.get<GroceryItem[]>(this.url + "grocery-list/" + listId);
  }

  createGroceryItem(listId:String, groceryItem:any):Observable<Object>{
    return this.http.post<Object[]>(this.url +"grocery-list/" + listId +"/item",groceryItem );
  }

  updateGroceryItem(listId:String, groceryItem:any):Observable<Object>{
    return this.http.put<Object[]>(this.url +"grocery-list/" + listId +"/item", groceryItem );
  }

  deleteGroceryItem(listId:String, itemId:number){
    console.log(listId, itemId);
    return this.http.delete(this.url +"grocery-list/" + listId +"/item/" + itemId);
  }
}