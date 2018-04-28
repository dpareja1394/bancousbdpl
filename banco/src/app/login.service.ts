import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/Rx';
import {Observable} from 'rxjs/Rx';
@Injectable()
export class LoginService {

  constructor(public http:Http) { }

  login(e){
    return this.http.get("http://localhost:8080/banco-web/rest/controllers/autenticarUsuario/"+e.usuario+"/"+ e.password).
    map((response:Response)	=> response.json()).catch((error:any)=>Observable.throw({message :"Error"}));
  }

}
