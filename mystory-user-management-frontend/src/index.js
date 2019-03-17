import {Component, View} from 'angular2/core';
import {bootstrap} from 'angular2/platform/browser';
import {MystoryUserManagementFrontend} from 'mystory-user-management-frontend';

@Component({
  selector: 'main'
})

@View({
  directives: [MystoryUserManagementFrontend],
  template: `
    <mystory-user-management-frontend></mystory-user-management-frontend>
  `
})

class Main {

}

bootstrap(Main);
