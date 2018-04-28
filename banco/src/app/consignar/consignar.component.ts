import { Component, Input, OnInit } from '@angular/core';
import { AuthService} from '../auth.service';
import { Http } from '@angular/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-consignar',
  templateUrl: './consignar.component.html',
  styleUrls: ['./consignar.component.css']
})


export class ConsignarComponent{
  auxiliar:any;
  mensaje:string;

  cuenta:string = "";
  consigna:number;
  cliente:number;

  nombreUsuario:string;

  constructor(private as:AuthService, private http:Http, private router : Router ) {
    this.nombreUsuario = this.as.getNombreUsuario();
  }

  

  realizarConsignacion(){
  
    if(this.cuenta==null || this.cuenta=="" ||
      this.consigna==null || this.cliente==null){
      this.mensaje = "Por favor llene todos los campos";
    }
    else{
      this.http.post('http://localhost:8080/banco-web/rest/controllers/transaccion/consignar/'+this.cuenta+'/'+this.as.getUserName()+'/'+this.consigna+'/'+this.cliente, null)
      .subscribe( respuesta => {
        this.auxiliar = respuesta.json(); 
        if(this.auxiliar.codigo != 0){ 
          this.mensaje = this.auxiliar.respuesta;
        }else{
          this.mensaje = "Se ha realizado la consignación exitosamente a la cuenta "+this.cuenta;
          this.cuenta = "";
          this.consigna = null;
          this.cliente = null;
        }
  
  
      } );
    }

    
  }

  limpiarFormularioConsignacion(){
    this.mensaje = "";
    this.cuenta="";
    this.consigna=null;
    this.cliente = null;
  }

  atras(){
		this.router.navigate(['home']) // 
  }


}
