import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { UserModel } from './../models';
import 'rxjs/add/operator/do';
import { EntityModel } from '../models/_entity.model';

@Injectable()
export class EntityService<T extends EntityModel> {
  protected baseUrl = '/api/*/';

  constructor(protected http: HttpClient) { }

  getAll() {
    return this.http.get<T[]>(this.baseUrl);
  }

  getById(id: number) {
    return this.http.get<T>(this.baseUrl + id);
  }

  create(entity: T) {
    return this.http.post(this.baseUrl, entity);
  }

  update(entity: T) {
    return this.http.put(this.baseUrl + entity.id, entity);
  }

  delete(id: number) {
    return this.http.delete(this.baseUrl + id);
  }
}
