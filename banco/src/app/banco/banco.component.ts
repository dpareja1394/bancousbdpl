import { Component, OnInit } from '@angular/core';
import { AuthService} from '../auth.service';
import {LoginService} from '../login.service';
import { Router } from '@angular/router';
import { Http } from '@angular/http';

@Component({
  selector: 'app-banco',
  templateUrl: './banco.component.html',
  styleUrls: ['./banco.component.css']
})
export class BancoComponent implements OnInit {

  constructor(private as:AuthService, private http:Http, private router : Router) { }
	mostrarMensaje:boolean = false;
	auxiliar :any;
  ngOnInit() {
  }
  login(usuario,password){
  	let envio = {"usuario": usuario, "password":password};
		
		this.http.get('http://localhost:8080/banco-web/rest/controllers/usuario/autenticarUsuario/'+usuario+'/'+password)
    .subscribe( respuesta => {
			this.auxiliar = respuesta.json();
			console.log(this.auxiliar);
			if(this.auxiliar.nombre == null){ //lo que retorne el get
				//no se loggea muestra mensaje 		
				this.mostrarMensaje = true;
  		}else{
				this.as.setUsername(this.auxiliar.usuUsuario); //el usuario retornado en el get
				this.as.setNombreUsuario(this.auxiliar.nombre);
  			this.as.setIsUserLoggedIn(true); // a la hora de salir sencillamente eliminas las credenciales y lo pones en false
				this.router.navigate(['home']) // en el caso de ser correcta la autenticación se dirige al componente de inicio, declarado en el app.module
				
			}


    } );
		
	
  }
}
