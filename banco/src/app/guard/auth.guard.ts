import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../auth.service';
// Importar el auth service que es quien guarda nuestra sesión
@Injectable()
export class AuthGuard implements CanActivate {
  constructor(
  	private router : Router,
    private as : AuthService
  	
  	){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
  	if(!this.as.getIsUserLoggedIn ()){
      this.router.navigate(['/']) //Si no está loggeado lo redirecciono a la raíz donde está el login
    }
    return this.as.getIsUserLoggedIn(); 
  }
}
