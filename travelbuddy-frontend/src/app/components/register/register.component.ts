import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  user = {
    email: '',
    password: '',
    role: 'USER'
  };

  errorMessage: string = '';

  constructor(private userService: UserService, private router: Router) {}

  registerUser(): void {
    this.userService.register(this.user).subscribe({
      next: () => {
        alert('Registration successful');
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.errorMessage = 'Registration failed: ' + (err.error?.message || err.statusText);
      }
    });
  }
}
