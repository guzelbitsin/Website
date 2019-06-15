import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AngularFireModule } from 'angularfire2';
import { AngularFireDatabaseModule } from 'angularfire2/database';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecipeComponent } from './recipe/recipe.component';
import { RecipeListComponent } from './recipe-list/recipe-list.component';
import { RecipeService} from './shared/recipe.service';
import { environment } from '../environments/environment'; //Path sorun yaratabilir, dikkat!

@NgModule({
  declarations: [
    AppComponent,
    RecipeComponent,
    RecipeListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireDatabaseModule,
    FormsModule //Gereksiz olabilir, dikkat!
  ],
  providers: [RecipeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
