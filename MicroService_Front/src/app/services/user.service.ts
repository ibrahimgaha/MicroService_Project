import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';

export interface User {
  id?: number;
  name: string;
  email: string;
  phone?: string;
  address?: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private apiService: ApiService) { }

  getUsers(): Observable<User[]> {
    return this.apiService.get<User[]>('/user-service/User/all');
  }

  getUser(id: number): Observable<User> {
    return this.apiService.get<User>(`/user-service/User/${id}`);
  }

  createUser(user: User): Observable<User> {
    return this.apiService.post<User>('/user-service/User/addUser', user);
  }

  updateUser(id: number, user: User): Observable<User> {
    return this.apiService.put<User>(`/user-service/User/update/${id}`, user);
  }

  deleteUser(id: number): Observable<void> {
    return this.apiService.delete<void>(`/user-service/User/delete/${id}`);
  }
}
