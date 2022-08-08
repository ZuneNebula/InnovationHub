import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'reverseInnovator'
})
export class ReverseInnovatorPipe implements PipeTransform {

  transform(value:any) {
    return value.slice().reverse();
  }

}
