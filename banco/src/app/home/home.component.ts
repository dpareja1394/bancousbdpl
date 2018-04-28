import { Component, OnInit } from '@angular/core';
import { AuthService} from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  nombreUsuario:string;
  constructor(private as:AuthService, private router : Router) {
    this.nombreUsuario = this.as.getNombreUsuario();
   }

  ngOnInit() {
  }

  salir(){
    console.log('Ha presionado el boton salir');
    this.as.setUsername(null); //sacar usuario de la sesion
  			this.as.setIsUserLoggedIn(false); // 
				this.router.navigate(['login']) // 
  }

  consignar(){
		this.router.navigate(['consignacion']) // 
  }

  retirar(){
		this.router.navigate(['retirar']) // 
  }

}
