import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShareService {

  constructor() {
  }

  private key: BehaviorSubject<any> = new BehaviorSubject<any>('');

  getKey(): Observable<any> {
    return this.key.asObservable();
  }

  setKey(key: string): void {
    this.key.next(key);
  }
}
