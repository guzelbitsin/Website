import { Component, OnInit } from '@angular/core';

import { RecipeService } from '../shared/recipe.service';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  constructor(private recipeService: RecipeService) { } //Public mi yapilmali?
  submitted: boolean;
  showSuccessMessage: boolean; //This variable is used to Timeout the success message.
  formControls = this.recipeService.form.controls;
  ngOnInit() {
  }

  onSubmit() {
    this.submitted = true;
    if (this.recipeService.form.valid) {
      if (this.recipeService.form.get('$key').value == null)
        this.recipeService.insertRecipe(this.recipeService.form.value);
      else
        this.recipeService.updateRecipe(this.recipeService.form.value);
        this.showSuccessMessage = true;
        setTimeout(() => this.showSuccessMessage = false, 1500); //Success message will disappear after 1.5 seconds.
      this.submitted = false;
      this.recipeService.form.reset();
    }
  }

}
