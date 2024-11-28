import {Component, OnInit} from '@angular/core';
import {UserService} from "./user.service";
import {User} from "./User";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss'
})
export class UserComponent implements OnInit {
  users: User[] = [];
  name = '';
  contactNumber = '';
  email = '';

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe((data: User[]) => {
      this.users = data;
    });
  }

  addUser() {
    const id = this.users.length + 1;
    const user = new User(id, this.name, this.email, this.contactNumber);
    this.users.push(user);
    this.userService.addUser(user).subscribe(data => {
      this.users[id-1].status = true;
    });
  }
}
