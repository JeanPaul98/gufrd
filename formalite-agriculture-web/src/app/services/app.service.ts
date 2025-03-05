import {Injectable} from '@angular/core';
import {saveAs} from 'file-saver';
import {formatDate} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor() {
  }

  csvDownload(headers: any, data: any) {
    if (!data || !data.length) {
      return;
    }
    const separator = ',';
    const csvData = headers.join(separator) + '\n' + data.map((row: any) => {
      return headers.map((headerKey: any) => {
        return row[headerKey] === null || row[headerKey] === undefined ? "" : row[headerKey];
      }).join(separator);
    }).join('\n');
    console.log(csvData);
    this.exportCsv(csvData, 'data.csv'); // download to csv
  }

  csvTransaction(headers: any, data: any) {
    if (!data || !data.length) {
      return;
    }
    const separator = ',';
    const csvData = headers.join(separator) + '\n' + data.map((row: any) => {
      return headers.map((headerKey: any) => {
        if (headerKey === "dateCallBack" || headerKey === "dateTransaction") {
          row[headerKey] = formatDate(new Date(row[headerKey]), "dd/MM/yyyy HH:mm:ss", "en");
        }
        return row[headerKey] === null || row[headerKey] === undefined ? "" : row[headerKey];
      }).join(separator);
    }).join('\n');
    this.exportCsv(csvData, `data-${formatDate(new Date(), "dd-MM-yyyy-HH:mm:ss", "en")}.csv`); // download to csv
  }

  private exportCsv(csvData: string, csv: string) {
    // @ts-ignore
    const csvFile = new Blob([csvData], {type: 'text/csv;charset=utf-8;'});
    saveAs(csvFile, csv);
  }
}
