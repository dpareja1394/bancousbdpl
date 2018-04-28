import { Component, Input, OnInit } from '@angular/core';
import { AuthService} from '../auth.service';
import { Http } from '@angular/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-retirar',
  templateUrl: './retirar.component.html',
  styleUrls: ['./retirar.component.css']
})
export class RetirarComponent{

  auxiliar:any;
  mensaje:string;

  cuenta:string = "";
  consigna:number;
  cliente:number;
  clave:string;

  nombreUsuario:string;


  constructor(private as:AuthService, private http:Http, private router : Router ) {
      this.nombreUsuario = this.as.getNombreUsuario();
  }

  realizarRetiro(){
    if(this.cuenta==null || this.cuenta=="" ||
      this.consigna==null || this.cliente==null
     || this.clave == null || this.clave==""){
      this.mensaje = "Por favor llene todos los campos";
    }
    else{
      this.http.post('http://localhost:8080/banco-web/rest/controllers/transaccion/retirar/'+this.cuenta+'/'+this.as.getUserName()+'/'+this.consigna+'/'+this.cliente+'/'+this.clave, null)
      .subscribe( respuesta => {
        this.auxiliar = respuesta.json(); 
        console.log(this.auxiliar);
        if(this.auxiliar.codigo != 0){ 
          this.mensaje = this.auxiliar.respuesta;
        }else{
          this.mensaje = "Se ha realizado el retiro exitosamente desde la cuenta "+this.cuenta;
          this.cuenta = "";
          this.consigna = null;
          this.cliente = null;
          this.clave = "";
        }
  
  
      } );
    }

    
  }

  limpiarFormularioRetiro(){
    this.mensaje = "";
    this.cuenta = "";
    this.consigna = null;
    this.cliente = null;
    this.clave = "";
  }

  atras(){
		this.router.navigate(['home']) // 
  }

}
