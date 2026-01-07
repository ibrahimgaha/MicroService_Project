import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';

export interface Commande {
  id: number;
  userId: number;
  productId: number;
  quantity: number;
  date: string;
}

@Injectable({
  providedIn: 'root'
})
export class CommandeService {

  constructor(private apiService: ApiService) { }

  getCommandes(): Observable<Commande[]> {
    return this.apiService.get<Commande[]>('/commande-service/commandes/');
  }

  createCommande(commande: Omit<Commande, 'id' | 'date'>): Observable<Commande> {
    return this.apiService.post<Commande>('/commande-service/commandes/', commande);
  }

}
