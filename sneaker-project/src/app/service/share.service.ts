import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShareService {

  constructor() {
  }

  private key: BehaviorSubject<any> = new BehaviorSubject<any>('');
  private length: BehaviorSubject<any> = new BehaviorSubject<any>('');
  private totalPrice: BehaviorSubject<any> = new BehaviorSubject<any>('');
  private isChanged: BehaviorSubject<any> = new BehaviorSubject<any>(false);

  getKey(): Observable<any> {
    return this.key.asObservable();
  }

  setKey(key: any): void {
    this.key.next(key);
  }

  getTotalPrice(): Observable<any> {
    return this.totalPrice.asObservable();
  }

  setTotalPrice(totalPrice: any): void {
    this.totalPrice.next(totalPrice);
  }

  getLength(): Observable<any> {
    return this.length.asObservable();
  }

  setLength(length: any): void {
    this.length.next(length);
  }


  setIsChanged(changeInCart: boolean): void {
    this.isChanged.next(changeInCart);
  }

  quantityIsChanged(): Observable<any> {
    return this.isChanged.asObservable();
  }
}
