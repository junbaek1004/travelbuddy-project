import { Component, OnInit } from '@angular/core';
import { BookingService } from 'src/app/services/booking.service';

@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css']
})
export class BookingListComponent implements OnInit {

  bookings: any[] = [];          
  errorMessage: string = '';     

  constructor(private bookingService: BookingService) {}

  ngOnInit(): void {
    this.fetchBookings();        
  }

  fetchBookings(): void {
    this.bookingService.getUserBookings().subscribe({
      next: (data) => {
        this.bookings = data;    
      },
      error: (err) => {
        this.errorMessage = 'Failed to fetch bookings: ' + (err.error?.message || err.statusText);
      }
    });
  }
}