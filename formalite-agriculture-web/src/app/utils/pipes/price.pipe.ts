import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'price',
  standalone: true
})
export class PricePipe implements PipeTransform {

  transform(value: number, ...args: unknown[]): string {
    return this.formatToMoney(value);
  }

  private formatToMoney(amount: number): string {
    const formatter = new Intl.NumberFormat('fr-FR', {style: 'currency', currency: 'XOF'});
    return formatter.format(amount)
  }
}
