import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) {
  }

  getData() {
    this.http.get("http://localhost:8080/get-data").subscribe((data) => {
      console.log(data);
    })
  }

  setData(param: any) {
    return this.http.get("http://localhost:8080/set-data", {params: param});
  }
}
