import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient,private authService: AuthService) {
  }
  addUser(user){
   return this.httpClient.post(environment.signupbaseUrl+"/sign-up",user);
  }
}
