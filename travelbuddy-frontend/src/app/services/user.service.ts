import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = `${environment.userServiceUrl}`;

  constructor(private http: HttpClient) { }

  register(userData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, userData);
  }

  
  login(loginData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, loginData, {
      responseType: 'text' 
    });
  }


  saveToken(token: string) {
    localStorage.setItem('token', token);
  }


  getToken(): string | null {
    return localStorage.getItem('token');
  }


  logout() {
    localStorage.removeItem('token');
  }
}

