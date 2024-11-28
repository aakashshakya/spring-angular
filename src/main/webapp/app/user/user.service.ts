import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "./User";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getAllUsers() {
    return this.http.get<User[]>("http://localhost:8080/api/users");
  }

  addUser(user: User) {
    return this.http.post("http://localhost:8080/api/users", user);
  }
}
