import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BookingService } from 'src/app/services/booking.service';

@Component({
  selector: 'app-booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.css']
})
export class BookingFormComponent {


  bookingData = {
    origin: '',
    destination: '',
    travelDate: '',
    transportType: '',
    distance: 0
  };

  errorMessage: string = '';

  constructor(private bookingService: BookingService, private router: Router) {}

  createBooking(): void {
    this.bookingService.createBooking(this.bookingData).subscribe({
      next: () => {
        alert('Booking created successfully');
        this.router.navigate(['/bookings']);
      },
      error: (err) => {
        this.errorMessage = 'Booking failed: ' + (err.error?.message || err.statusText);
      }
    });
  }
}
