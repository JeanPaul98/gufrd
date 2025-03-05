export interface  HttpResponseModel<T> {
  status: string;
  message: string;
  results: T;
  result: T;
}
