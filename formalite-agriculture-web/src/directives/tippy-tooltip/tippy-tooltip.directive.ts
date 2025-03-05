import {AfterViewInit, Directive, ElementRef, Input, OnChanges, OnDestroy, SimpleChanges} from '@angular/core';
import tippy, {Instance, Props, roundArrow} from "tippy.js";
import _ from "lodash";

@Directive({
  selector: '[appTippyDirective]',
  standalone: true
})
export class TippyTooltipDirective implements OnDestroy, AfterViewInit, OnChanges{

  constructor(private readonly el: ElementRef) {}

  private instance: Instance<Props> | null = null;

  @Input() tooltip!: string;
  @Input() tooltipOptions!: Partial<Props>;

  ngAfterViewInit() {
    this.instance = tippy(this.el.nativeElement as Element, {});
    this.updateProps({
      ...(this.tooltipOptions ?? {}),
      content: this.tooltip,
    });
  }

  ngOnDestroy() {
    this.instance?.destroy();
    this.instance = null;
  }

  ngOnChanges(changes: SimpleChanges) {
    let props = {
      ...(this.tooltipOptions ?? {}),
      content: this.tooltip,
    };

    if ('tooltipOptions'  in changes) {
      props = {...(changes['tooltipOptions'].currentValue ?? {}), content: this.tooltip};
    }
    if ('tooltip' in changes) {
      props.content = changes['tooltip'].currentValue;
    }

    this.updateProps(props);
  }

  private updateProps(props: Partial<Props>) {
    if (this.instance && !_.isEqual(this.instance.props, props)) {
      this.instance.setProps(this.normalizeOptions(props));
      if (!props.content) {
        this.instance.disable();
      } else {
        this.instance.enable();
      }
    }
  }

  private normalizeOptions = (props: Partial<Props>): Partial<Props> => ({
    ...(props || {}),
    duration: props?.duration ?? [50, 50],
  });
}
