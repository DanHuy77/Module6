import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Sneaker} from './model/Sneaker';
import {LoginForm} from './model/loginForm';
import {JwtResponse} from './dto/JwtResponse';
import {SneakerDetailDto} from './dto/sneaker-detail-dto';

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

  login(loginForm: LoginForm): Observable<any> {
    console.log(loginForm);
    return this.httpClient.post<JwtResponse>('http://localhost:8080/api/public/signin', loginForm);
  }

  searchSneakerByKey(key: string): Observable<any> {
    return this.httpClient.get<any>('http://localhost:8080/sneaker?key=' + key);
  }

  addToCart(sneakerDetailId: number, accountId: number): Observable<any> {
    // tslint:disable-next-line:max-line-length
    return this.httpClient.get<any>('http://localhost:8080/sneaker/addToCart?sneakerDetailId=' + sneakerDetailId + '&accountId=' + accountId);
  }

  showCart(accountId: number): Observable<any> {
    return this.httpClient.get<any>('http://localhost:8080/sneaker/showCart?accountId=' + accountId);
  }

  removeItemFromCart(detailId: number): Observable<any> {
    return this.httpClient.get<any>('http://localhost:8080/sneaker/removeItemFromCart?sneakerDetailId=' + detailId);
  }

  changeInCartQuantity(detailId: number, newQuantity: number): Observable<any> {
    return this.httpClient.get<any>
    ('http://localhost:8080/sneaker/changeInCartQuantity?sneakerDetailId=' + detailId + '&newQuantity=' + newQuantity);
  }

  getCustomerInfo(idCustomer: string): Observable<any> {
    return this.httpClient.get<any>('http://localhost:8080/customer/customerInfo?id=' + idCustomer);
  }

  checkout(sneakerInCart: SneakerDetailDto[]): Observable<any> {
    console.log(sneakerInCart);
    return this.httpClient.post('http://localhost:8080/sneaker/checkout', sneakerInCart);
  }

  getCustomerPaymentHistory(idCustomer: string): Observable<any> {
    return this.httpClient.get('http://localhost:8080/sneaker/paymentHistory?customerId=' + idCustomer);
  }

  getPaymentDetail(purchaseOrderId: number): Observable<any> {
    return this.httpClient.get('http://localhost:8080/sneaker/paymentDetail?purchaseOrderId=' + purchaseOrderId);
  }

  getSlotQuantity(accountId: number): Observable<any> {
    return this.httpClient.get('http://localhost:8080/sneaker/countSlotInCart?idAccount=' + accountId);
  }
}
