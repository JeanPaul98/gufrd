
import * as XLSX from 'xlsx';
import { saveAs } from 'file-saver';

export interface IExcelExport<T> {
  data: T[];
  fileName: string;
}

export const  exportToExcel = <T>(dataExport: IExcelExport<T>) => {
  const worksheet = XLSX.utils.json_to_sheet(dataExport.data);
  const fileName = dataExport.fileName;
  const workbook = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(workbook, worksheet, 'Feuille1');
  const excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
  const blob = new Blob([excelBuffer], {type: 'application/octet-stream'});
  saveAs(blob, `${fileName}.xlsx`);
};

export const exportToCsv = <T>(dataExport: IExcelExport<T>) => {
  const worksheet = XLSX.utils.json_to_sheet(dataExport.data);
  const fileName = dataExport.fileName;
  const workbook = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(workbook, worksheet, 'Feuille1');
  const csvBuffer = XLSX.utils.sheet_to_csv(worksheet);
  const blob = new Blob([csvBuffer], { type: 'text/csv;charset=utf-8;' });
  saveAs(blob, `${fileName}.csv`);
};


