import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';

export interface Product {
  id: number;
  name: string;
  brand: string;
  price: number;
  description: string;
  image?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private apiService: ApiService) { }

  getProducts(): Observable<Product[]> {
    return this.apiService.get<Product[]>('/product-service/api/notebooks/');
  }

  getProduct(id: number): Observable<Product> {
    return this.apiService.get<Product>(`/product-service/api/notebooks/${id}/`);
  }
}
