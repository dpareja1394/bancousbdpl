import { Injectable } from '@angular/core';
/**En este servicio se guardan los datos de la sesión, ten en cuenta que angular es single page aplication así que cada
pestaña en el navegador cuenta como una sesión aparte, para corregir eso puedes usar jwt o en su defecto un localstorage
**/
@Injectable()
export class AuthService {
  private isUserLoggedIn=false;
  private username;
  private nombreUsuario;
  
  constructor() {
  	this.isUserLoggedIn= false;
   }
   setUsername(e){
   	this.username=e;
   }
   getUserName(){
   	return this.username;
   }
   setIsUserLoggedIn(e){
   	this.isUserLoggedIn = e;
   }
   getIsUserLoggedIn(){
   	return this.isUserLoggedIn;
   }

   setNombreUsuario(e){
     this.nombreUsuario=e;
   }

   getNombreUsuario(){
     return this.nombreUsuario;
   }
}
