export class User {
  id: number;
  name: string;
  email: string;
  contactNumber: string;
  status: boolean;

  constructor(id: number, name: string, email: string, contactNumber: string, status = false) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.contactNumber = contactNumber;
    this.status = status;
  }
}
