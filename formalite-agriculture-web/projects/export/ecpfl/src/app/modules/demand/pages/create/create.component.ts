import {Component} from '@angular/core';
import {DemandService} from '../../services/demand.service';

@Component({
    selector: 'app-create',
    templateUrl: './create.component.html',
    styleUrl: './create.component.scss'
})
export class CreateComponent {
    items = [1, 2, 3, 4];
    fileName = '';

    // urlRoute$: Observable<string> = this.demandService.currentRoute$;

    constructor(private demandService: DemandService) {

    }

    onClickBack() {
        this.demandService.back();
    }

    onFileSelected(event: Event) {
        const target: HTMLInputElement = event.target as HTMLInputElement;

        const file: File | null | undefined = target?.files?.item(0);

        console.log('====================================');
        console.log(target?.files);
        console.log('====================================');
        if (file) {
            this.fileName = file.name;
        }
    }
}
