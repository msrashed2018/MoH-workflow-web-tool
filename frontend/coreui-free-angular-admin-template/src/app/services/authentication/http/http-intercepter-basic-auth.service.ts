import { BasicAuthenticationService } from './../basic-authentication.service';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CORS } from '../../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterBasicAuthService implements HttpInterceptor{

  constructor(
    private basicAuthenticationService : BasicAuthenticationService
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler){
    let basicAuthHeaderString = this.basicAuthenticationService.getAuthenticatedToken();
    let username = this.basicAuthenticationService.getAuthenticatedUser()

    if(basicAuthHeaderString && username) { 
      request = request.clone({
        setHeaders : {
            Authorization : basicAuthHeaderString,
            'Access-Control-Allow-Origin': `${CORS}`
          }
        }) 
    }
    return next.handle(request);
  }
}


// @Injectable()
// export class SpringbootInterceptor implements HttpInterceptor {
//   constructor(public auth: AuthService) {}
//   intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

//     // Clone the request to add the new header
//     const clonedRequest = req.clone({ headers: req.headers.set('Set-Cookie', 'jsessionid=' + this.auth.getJSessionId()) });

//     // Pass control to the next request
//     return next.handle(clonedRequest);
//   }
// }
