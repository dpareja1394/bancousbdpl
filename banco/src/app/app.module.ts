import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AuthGuard  } from './guard/auth.guard';
import { LoginService } from './login.service';
import { AuthService } from './auth.service';
import { AppComponent } from './app.component';
import { BancoComponent } from './banco/banco.component';
import { RetirarComponent } from './retirar/retirar.component';
import { ConsignarComponent } from './consignar/consignar.component';
import { HomeComponent } from './home/home.component';
import { Routes, RouterModule} from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms'
import { NgxMaskModule } from 'ngx-mask';

 const appRoutes: Routes =[{path:'',redirectTo:'/login',pathMatch:'full'},
{path:'login',component: BancoComponent},
{path:'home',component: HomeComponent, canActivate:[AuthGuard]},
{path:'consignacion',component: ConsignarComponent, canActivate:[AuthGuard] }, //el can activate es lo que maneja la seguridad
{path:'retirar', component:RetirarComponent, canActivate:[AuthGuard]}
]; //esto maneja las referencias de las rutas a los componentes
 
@NgModule({
  declarations: [
    AppComponent,
    BancoComponent,
    RetirarComponent,
    ConsignarComponent,
    HomeComponent,
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    HttpModule,
    FormsModule,
    NgxMaskModule.forRoot()
  ],
  providers: [AuthGuard,AuthService,LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
