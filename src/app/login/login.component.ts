import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  userName: string;
  password: string;
  show: boolean = false;
  initialRole:string;
  validationStatus: boolean = false;
  status :any = false;
  authenticationStatus : any = false;
 
   constructor(  private activateRoute: ActivatedRoute,
                 private authService: AuthService,
                  private route: Router
                  ) { }
 
   ngOnInit() {
 
      this.activateRoute.paramMap
          .subscribe(params=> {
            this.status=params.get('status');
    //  this.initialRole = this.authService.getInitialRole();
 
     this.loginForm= new FormGroup({
         "userName": new FormControl(this.userName, Validators.required),
          "password": new FormControl(this.password, Validators.required)
     });
    });
   }
 
   send(loginForm) {
 
    //  this.authService.authenticate(loginForm.value.username, loginForm.value.password)
    //     .subscribe((response) => {
    //            this.validationStatus = false;
    //            this.authService.setToken(response.token);
               
    //            this.authService.setRole(response.role);
    //            this.authService.setUser(response.user);
    //           if(response.role == 'ADMIN'){
    //            this.route.navigate(['admin']);
    //           }
    //           if(response.role == 'PATIENT'){
    //            this.route.navigate(['customer']);
    //           }
    //           if(response.role == 'DOCTOR'){
    //            this.route.navigate(['doctor']);
    //           }       
    //    },
    //    (responseError) => {
    //      console.log(responseError.error.message);
 
    //      if(responseError.error.message === 'PENDING') {
    //        this.authenticationStatus = "PENDING";
    //      }else if(responseError.error.message === 'REJECTED'){
    //        this.authenticationStatus = "REJECTED";
    //      }else{
    //        this.validationStatus = true;
    //      }
    //    });

    this.authService.authenticate(loginForm.value.userName, loginForm.value.password)
      .subscribe((response) => {
          this.validationStatus = false;
          this.authService.setToken(response.token);
          this.authService.setRole(response.role);
          this.authService.setUser(response.user);
         // this.authService.logIn();

          this.route.navigate(["admin"]);
      },
      (responseError) => {
        console.log(responseError.error.message);
        this.validationStatus = true;
      }); 
       
    
   }
 
   passwordFunction() {
     this.show = !this.show;
   }
 
   back(){
     this.route.navigate(['']);
   }
 
   signIn(){

       this.route.navigate(['signup']);
     
   }
 
 

}
