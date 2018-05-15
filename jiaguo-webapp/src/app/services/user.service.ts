import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { UserModel } from './../models';
import { EntityService } from './_entity.service';

@Injectable()
export class UserService extends EntityService<UserModel> {

  constructor(protected http: HttpClient) {
    super(http);
    this.baseUrl = '/api/users/';
  }
  
}
