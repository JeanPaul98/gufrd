import {Injectable} from '@angular/core';
import localforage from "localforage";

@Injectable({
  providedIn: 'root',
})
export class LocalForageService {
  localforage!:  LocalForage;
  constructor() {
    this.localforage = localforage.createInstance({
      name: 'faw',
      storeName: 'faw',
    });
  }

  getItem(key: string) {
    return this.localforage.getItem(key);
  }

  setItem(key: string, value: any) {
    return this.localforage.setItem(key, value);
  }

  remove(key: string) {
    return this.localforage.removeItem(key);
  }

  clear() {
    return this.localforage.clear();
  }

  listKeys() {
    return this.localforage.keys();
  }
}
