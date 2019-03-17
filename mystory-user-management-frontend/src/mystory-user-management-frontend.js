import {Component, View} from 'angular2/core';

@Component({
  selector: 'mystory-user-management-frontend'
})

@View({
  templateUrl: 'mystory-user-management-frontend.html'
})

export class MystoryUserManagementFrontend {

  constructor() {
    console.info('MystoryUserManagementFrontend Component Mounted Successfully');
  }

}
