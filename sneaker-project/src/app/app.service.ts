import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Sneaker} from './model/Sneaker';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private httpClient: HttpClient) {
  }

  getAllSneaker(): Observable<any> {
    return this.httpClient.get<any>('http://localhost:8080/sneaker/');
  }

  addSneaker(sneaker: Sneaker): Observable<any> {
    return this.httpClient.post('http://localhost:8080/sneaker', sneaker);
  }

  findSneakerById(id: number): Observable<Sneaker> {
    return this.httpClient.get<Sneaker>('http://localhost:8080/sneaker/' + id);
  }

  editSneaker(sneaker: Sneaker): Observable<any> {
    return this.httpClient.patch('http://localhost:8080/sneaker/' + sneaker.sneakerId, sneaker);
  }


  removeSneaker(id: number): Observable<any> {
    return this.httpClient.delete('http://localhost:8080/sneaker/' + id);
  }

  getImageListById(id: number): Observable<any> {
    return this.httpClient.get<any>('http://localhost:8080/image?id=' + id);
  }

  getSneakerDetail(id: number): Observable<any> {
    return this.httpClient.get<any>('http://localhost:8080/sneaker/detail?id=' + id);
  }
}
