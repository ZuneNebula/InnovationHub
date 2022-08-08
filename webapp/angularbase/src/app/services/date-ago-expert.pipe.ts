import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateAgoExpert'
})
export class DateAgoExpertPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    if (value) {
      var seconds = Math.floor((+new Date() - +new Date(value)) / 1000);
      var interval = seconds / 31536000;
      if (interval > 1) {
        return Math.floor(interval) + " years";
      }
      interval = seconds / 2592000;
      if (interval > 1) {
        return Math.floor(interval) + " months";
      }
      interval = seconds / 86400;
      if (interval > 1) {
        return Math.floor(interval) + "d ago";
      }
      interval = seconds / 3600;
      if (interval > 1) {
        return Math.floor(interval) + "h ago";
      }
      interval = seconds / 60;
      if (interval > 1) {
        return Math.floor(interval) + "m ago";
      }
      return Math.floor(seconds)+1 + "s ago";
  }

}

}
