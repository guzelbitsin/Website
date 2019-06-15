import { Component, OnInit } from '@angular/core';

import { RecipeService } from '../shared/recipe.service'; //Path'e dikkat!

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  constructor(private recipeService: RecipeService) { } // Private?
  recipeArray = [];
  showDeletedMessage: boolean; //To control the message show time.

  ngOnInit() {
    this.recipeService.getRecipes().subscribe(
      list => {
        this.recipeArray = list.map(item => {
          return {
            $key: item.key,
            ...item.payload.val()
          };
        });
      });
  }

  onDelete($key){
    if(confirm('Deleting recipe, confirm?')){
      this.recipeService.deleteRecipe($key);
      this.showDeletedMessage = true;
      setTimeout(() => this.showDeletedMessage = false, 1500); //The message will disappear after 1.5 seconds.
    }
    
  }


}
