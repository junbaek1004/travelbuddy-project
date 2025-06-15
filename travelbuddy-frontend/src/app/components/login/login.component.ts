import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginData = {
    email: '',
    password: ''
  };

  errorMessage: string = '';

  constructor(private userService: UserService, private router: Router) {}

  login(): void {
  this.userService.login(this.loginData).subscribe({
    next: (response) => {
      console.log('✅ Login API Raw Response:', response);

      let token = '';

      
      if (typeof response === 'string') {
        try {
          const parsed = JSON.parse(response);
          token = parsed.token;
        } catch (e) {
          this.errorMessage = 'Login failed: Malformed token response';
          return;
        }
      } else {
        token = response?.token;
      }

      if (token) {
        localStorage.setItem('token', token); // ✅ Only the JWT, not the whole object
        console.log('✅ Stored token:', token);
        alert('Login successful');
        this.router.navigate(['/bookings']);
      } else {
        this.errorMessage = 'Login failed: Token missing from response';
      }
    },
    error: (err) => {
      this.errorMessage = 'Login failed ' + (err.error?.message || err.statusText);
    }
  });
}
}
