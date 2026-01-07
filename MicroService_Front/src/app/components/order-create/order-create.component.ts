import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommandeService, Commande } from '../../services/commande.service';

@Component({
  selector: 'app-order-create',
  templateUrl: './order-create.component.html',
  styleUrls: ['./order-create.component.css']
})
export class OrderCreateComponent implements OnInit {
  productId: number = 0;
  quantity: number = 1;
  userId: number = 1; // In a real app, this would come from authentication
  loading = false;
  error: string | null = null;
  success = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private commandeService: CommandeService
  ) { }

  ngOnInit(): void {
    this.productId = +this.route.snapshot.paramMap.get('productId')!;
  }

  createOrder(): void {
    if (this.quantity <= 0) {
      this.error = 'Quantity must be greater than 0';
      return;
    }

    this.loading = true;
    this.error = null;

    const commande: Omit<Commande, 'id' | 'date'> = {
      userId: this.userId,
      productId: this.productId,
      quantity: this.quantity
    };

    this.commandeService.createCommande(commande).subscribe({
      next: (result) => {
        this.loading = false;
        this.success = true;
        setTimeout(() => {
          this.router.navigate(['/products']);
        }, 2000);
      },
      error: (err) => {
        this.error = 'Failed to create order';
        this.loading = false;
        console.error('Error creating order:', err);
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/products']);
  }
}
