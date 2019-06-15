import { Injectable } from '@angular/core'; //Hata olabilir mi?
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { AngularFireDatabase, AngularFireList } from 'angularfire2/database';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private firebase: AngularFireDatabase) { } //Private mi olmali?
  recipeList: AngularFireList<any>;

  form = new FormGroup({
    $key: new FormControl(null),
    description: new FormControl('', [Validators.required, Validators.minLength(1)]),
    tag: new FormControl('', [Validators.required, Validators.minLength(1)]),
    picture: new FormControl('', Validators.required)
  });

  getRecipes() {
    this.recipeList = this.firebase.list('recipes');
    return this.recipeList.snapshotChanges();
  }

  insertRecipe(recipe){
    this.recipeList.push({
      description: recipe.description,
      tag: recipe.tag,
      picture: recipe.picture
    });
  }

  populateForm(recipe){
    this.form.setValue(recipe);
  }

  updateRecipe(recipe){
    this.recipeList.update(recipe.$key, 
      {
        description: recipe.description,
        tag: recipe.tag,
        picture: recipe.picture
      });
  }

  deleteRecipe($key: string){
    this.recipeList.remove($key);
  }
}
