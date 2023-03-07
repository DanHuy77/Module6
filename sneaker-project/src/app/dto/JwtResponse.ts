export class JwtResponse {
  token: string;
  name: string;
  avatar: string;
  roles: any[];
  email: string;
  idCustomer: string;
  id: string;

  constructor(token: string, name: string, avatar: string, roles: any[], email: string, id: string
  ) {
    this.token = token;
    this.name = name;
    this.avatar = avatar;
    this.roles = roles;
    this.email = email;
    this.id = id;
  }
}
