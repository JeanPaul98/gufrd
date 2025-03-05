import {Component, Input} from '@angular/core';
import {TippyTooltipDirective} from "../../directives/tippy-tooltip/tippy-tooltip.directive";
import {Props} from "tippy.js";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-info-tooltip',
  standalone: true,
  imports: [
    NgClass,
    TippyTooltipDirective,
  ],
  templateUrl: './info-tooltip.component.html',
  styleUrl: './info-tooltip.component.scss'
})
export class InfoTooltipComponent {

  @Input() message: string = "Infos";
  @Input() color: string = "tw-text-gray-600";
  @Input() bgColor: string = "tw-bg-gray-100";
}
