import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { GroceryItemComponent } from './grocery-item/grocery-item.component';
import { GroceryListComponent } from './grocery-list/grocery-list.component';

const routes: Routes = [
  {path: 'viewListItems/:id', component: GroceryItemComponent},
  {path: '', component:GroceryListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
