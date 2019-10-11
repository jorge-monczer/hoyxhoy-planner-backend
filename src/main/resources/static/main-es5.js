(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./$$_lazy_route_resource lazy recursive":
/*!******************************************************!*\
  !*** ./$$_lazy_route_resource lazy namespace object ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/app.component.html":
/*!**************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/app.component.html ***!
  \**************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!--The content below is only a placeholder and can be replaced.-->\n<div *ngIf=\"loginService.isUserLoggedIn\" style=\"padding: 20px;\" >\n\t<mat-toolbar #toolbar1 color=\"primary\">\n\t\t<!--mat-icon>done</mat-icon-->\n\t\t<span><img src=\"../assets/planner-logo.png\"/></span>\n\t\t<!--span> v1</span-->\n\t\t<span class=\"example-spacer\"></span>\n\t\t<button mat-button [mat-menu-trigger-for]=\"menu\">\n\t\t\t<mat-icon>menu</mat-icon>\n\t\t</button>\n\t\t&nbsp;\n\t\t<button mat-button (click)=\"logout()\">\n\t\t\t<mat-icon>exit_to_app</mat-icon>\n\t\t</button>\n\t</mat-toolbar>\n\t\t\n\t<mat-menu #menu=\"matMenu\">\n\t\t<a routerLink=\"/projects\" routerLinkActive=\"active\" mat-menu-item>Projects</a>\n\t\t<a routerLink=\"/users\" routerLinkActive=\"active\" mat-menu-item>Users</a>\n\t\t<a routerLink=\"/backlog\" routerLinkActive=\"active\" mat-menu-item [disabled]=\"!loginService.currentProject\">Backlog</a>\n\t\t<a routerLink=\"/springs\" routerLinkActive=\"active\" mat-menu-item [disabled]=\"!loginService.currentProject\">Springs</a>\n\t\t<a routerLink=\"/asignment\" routerLinkActive=\"active\" mat-menu-item [disabled]=\"!loginService.currentProject\">Asignment</a>\n\t\t<!--a routerLink=\"/springWizard\" routerLinkActive=\"active\" mat-menu-item [disabled]=\"!loginService.currentProject\">Springs Wizard</a-->\n\t\t<a routerLink=\"/capacity\" routerLinkActive=\"active\" mat-menu-item>Capacity</a>\n\t\t<a routerLink=\"/spending\" routerLinkActive=\"active\" mat-menu-item [disabled]=\"!loginService.currentProject\">Spending</a>\n\t</mat-menu>\n\n\t<mat-toolbar style=\"display: flex;\">\n\t\t<span >Welcome, {{loginService.currentName }}</span>\n\t\t<span class=\"example-spacer\"></span>\n\t\t<span >{{loginService.currentProjectName + \" \" + loginService.currentSpringName}}</span>\n\t</mat-toolbar>\n</div>\n<router-outlet></router-outlet>\n"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/asignment/asignment.component.html":
/*!******************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/asignment/asignment.component.html ***!
  \******************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-card>\n  <mat-card-header>\n    <mat-card-title>Spring Asignment Detail</mat-card-title>\n  </mat-card-header>\n  <form #myForm=\"ngForm\">\n    <p *ngIf=\"errorMessage.length > 0\" class=\"text-danger\"> {{errorMessage}}</p>    \n    <mat-form-field class=\"field-half-width\">\n      <mat-select [(ngModel)]=\"asignment.feature\" name=\"featureSelected\" placeholder=\"Feature Selected\" [compareWith]=\"compareFeatures\" required>\n        <mat-option *ngFor=\"let feat of features\" [value]=\"feat\">\n            {{feat.code + \" \" + feat.title}}\n        </mat-option>\n      </mat-select>        \n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <mat-form-field>\n      <mat-select [(ngModel)]=\"asignment.user\" name=\"userSelected\" placeholder=\"User Selected\" [compareWith]=\"compareUsers\" required>\n        <mat-option *ngFor=\"let user of users\" [value]=\"user\">\n            {{user.name}}\n        </mat-option>\n      </mat-select>        \n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <button type=\"submit\" *ngIf=\"!isEditMode\" [disabled]=\"!myForm.valid\" mat-mini-fab color=\"primary\" (click)=\"addAsignment()\" matTooltip=\"Add {{getMatTooltipButton()}}\">\n      <mat-icon>add</mat-icon>\n    </button>  \n    <button type=\"submit\" *ngIf=\"isEditMode\"  [disabled]=\"!myForm.valid\" mat-mini-fab color=\"primary\" (click)=\"updateAsignment()\" matTooltip=\"Confirm update {{getMatTooltipButton()}}\">\n      <mat-icon>check</mat-icon>\n    </button>  \n    <button type=\"submit\" *ngIf=\"isEditMode\"  mat-mini-fab color=\"primary\" (click)=\"cancelEditMode()\" matTooltip=\"Cancel update {{getMatTooltipButton()}}\">\n      <mat-icon>close</mat-icon>\n    </button>\n    </form>\n</mat-card>\n\n<mat-card>\n  <mat-card-header>\n    <mat-card-title>Asignment List</mat-card-title>\n  </mat-card-header>\n  <ag-grid-angular \n    #agGrid style=\"width: 100%; height: 200px;\" \n    class=\"ag-theme-fresh\" \n    [gridOptions]=\"gridOptions\" \n    [columnDefs]=\"columnDefs\" \n    [rowData]=\"rowData\" \n    [context]=\"context\" \n    (gridReady)=\"onGridReady($event)\">\n  </ag-grid-angular>\n</mat-card>\n"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/backlog/features.component.html":
/*!***************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/backlog/features.component.html ***!
  \***************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-card>\n  <mat-card-header>\n    <mat-card-title>Backlog Detail</mat-card-title>\n  </mat-card-header>\n  <form #myForm=\"ngForm\">\n    <p *ngIf=\"errorMessage.length > 0\" class=\"text-danger\"> {{errorMessage}}</p>    \n    <mat-form-field>\n        <input matInput [(ngModel)]=\"feature.code\" name=\"code\" placeholder=\"Code\" required>\n        <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <mat-form-field>\n      <input matInput [(ngModel)]=\"feature.title\" name=\"title\" placeholder=\"Title\" required>\n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <!--mat-form-field>\n      <input matInput [(ngModel)]=\"feature.status\" name=\"status\" placeholder=\"Status\" required #status=\"ngModel\">\n      <mat-error>This field is required</mat-error>\n    </mat-form-field-->\n    <mat-form-field>\n      <input matInput [(ngModel)]=\"feature.estimatedHours\" name=\"estimatedHours\" placeholder=\"Estimated Hours\" type=\"number\" maxlength=\"3\" required>\n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <mat-form-field>\n      <input matInput [(ngModel)]=\"feature.committedDate\" name=\"committedDate\" placeholder=\"Committed Date\" type=\"date\" required #committedDate=\"ngModel\">\n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <button type=\"submit\" [disabled]=\"!myForm.valid\" mat-mini-fab color=\"primary\" (click)=\"addFeature()\" matTooltip=\"Add {{feature.title}}\">\n      <mat-icon>add</mat-icon>\n    </button>  \n    <button type=\"submit\" *ngIf=\"isEditMode\"  [disabled]=\"!myForm.valid\" mat-mini-fab color=\"primary\" (click)=\"updateFeature()\" matTooltip=\"Confirm update {{feature.title}}\">\n      <mat-icon>check</mat-icon>\n    </button>  \n    <button type=\"submit\" *ngIf=\"isEditMode\"  mat-mini-fab color=\"primary\" (click)=\"cancelEditMode()\" matTooltip=\"Cancel update {{feature.title}}\">\n      <mat-icon>close</mat-icon>\n    </button>\n    </form>\n</mat-card>\n\n<mat-card>\n  <mat-card-header>\n    <mat-card-title>BackLog List</mat-card-title>\n  </mat-card-header>\n  <ag-grid-angular \n    #agGrid style=\"width: 100%; height: 200px;\" \n    class=\"ag-theme-fresh\" \n    [gridOptions]=\"gridOptions\" \n    [columnDefs]=\"columnDefs\" \n    [rowData]=\"rowData\" \n    [context]=\"context\" \n    (gridReady)=\"onGridReady($event)\">\n  </ag-grid-angular>\n</mat-card>\n"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/capacity/capacity.component.html":
/*!****************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/capacity/capacity.component.html ***!
  \****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-card>\n  <mat-card-header>\n    <mat-card-title>Spring Asignment Detail</mat-card-title>\n  </mat-card-header>\n  <form #myForm=\"ngForm\">\n    <p *ngIf=\"errorMessage.length > 0\" class=\"text-danger\"> {{errorMessage}}</p>    \n    <mat-form-field class=\"field-half-width\">\n      <mat-select [(ngModel)]=\"capacity.user\" name=\"userSelected\" placeholder=\"User Selected\" [compareWith]=\"compareUsers\" required>\n        <mat-option *ngFor=\"let user of users\" [value]=\"user\">\n            {{user.name}}\n        </mat-option>\n      </mat-select>        \n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <mat-form-field>\n      <input matInput [(ngModel)]=\"capacity.availableHours\" name=\"availableHours\" placeholder=\"Available Hours\" type=\"number\" required>\n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <button type=\"submit\" *ngIf=\"!isEditMode\" [disabled]=\"!myForm.valid\" mat-mini-fab color=\"primary\" (click)=\"addCapacity()\" matTooltip=\"Add {{getMatTooltipButton()}}\">\n      <mat-icon>add</mat-icon>\n    </button>  \n    <button type=\"submit\" *ngIf=\"isEditMode\"  [disabled]=\"!myForm.valid\" mat-mini-fab color=\"primary\" (click)=\"updateCapacity()\" matTooltip=\"Confirm update {{getMatTooltipButton()}}\">\n      <mat-icon>check</mat-icon>\n    </button>  \n    <button type=\"submit\" *ngIf=\"isEditMode\"  mat-mini-fab color=\"primary\" (click)=\"cancelEditMode()\" matTooltip=\"Cancel update {{getMatTooltipButton()}}\">\n      <mat-icon>close</mat-icon>\n    </button>\n    </form>\n</mat-card>\n\n<mat-card>\n  <mat-card-header>\n    <mat-card-title>Asignment List</mat-card-title>\n  </mat-card-header>\n  <ag-grid-angular \n    #agGrid style=\"width: 100%; height: 200px;\" \n    class=\"ag-theme-fresh\" \n    [gridOptions]=\"gridOptions\" \n    [columnDefs]=\"columnDefs\" \n    [rowData]=\"rowData\" \n    [context]=\"context\" \n    (gridReady)=\"onGridReady($event)\">\n  </ag-grid-angular>\n</mat-card>\n"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.html":
/*!***********************************************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.html ***!
  \***********************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-checkbox \r\n    [ngModel]=\"checked\" \r\n    (ngModelChange)=\"onChange($event)\">\r\n</mat-checkbox>"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/login/login.component.html":
/*!**********************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/login/login.component.html ***!
  \**********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!--div class=\"modal-dialog\">\r\n  <div class=\"loginmodal-container\">\r\n    <h1>Login to Your Account</h1><br>\r\n    <button class=\"login loginmodal-submit\" (click)=\"login()\">Login With Google</button>\r\n  </div>\r\n</div-->\r\n<div class=\"login-content\">\r\n<mat-card>\r\n  <mat-card-title>\r\n    <mat-toolbar>\r\n      HOYxHOY Application\r\n    </mat-toolbar>\r\n  </mat-card-title>\r\n  <mat-card-subtitle>\r\n      Login to Your Account\r\n  </mat-card-subtitle>\r\n  <mat-card-content>\r\n    <form  class=\"login-form\" #myForm=\"ngForm\">\r\n      <p *ngIf=\"errorMessage.length > 0\" class=\"text-danger\"> {{errorMessage}}</p>    \r\n      <mat-form-field class=\"field-full-width\">\r\n          <input matInput [(ngModel)]=\"username\" name=\"username\" placeholder=\"user name\" required>\r\n          <mat-error>This field is required</mat-error>\r\n      </mat-form-field>\r\n      <mat-form-field class=\"field-full-width\">\r\n        <input matInput [(ngModel)]=\"password\" name=\"password\" type=\"password\" placeholder=\"password\" required>\r\n        <mat-error>This field is required</mat-error>\r\n      </mat-form-field>\r\n      <div class=\"field-full-width txt_center margin1em\">\r\n        <button color=\"primary\" mat-raised-button type=\"submit\" [disabled]=\"!myForm.valid\" (click)=\"login()\">\r\n            Login\r\n        </button>\r\n      </div>\r\n      <!-- users mrotger@plann.er 123456  larce@plann.er 123456  jm@gmail.com ??????-->\r\n   </form>\r\n  </mat-card-content>\r\n</mat-card>\r\n</div>\r\n"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/projects/projects.component.html":
/*!****************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/projects/projects.component.html ***!
  \****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-card>\n  <mat-card-header>\n    <mat-card-title >Project Detail</mat-card-title>\n  </mat-card-header>\n  <form  #myForm=\"ngForm\">\n    <p *ngIf=\"errorMessage.length > 0\" class=\"text-danger\"> {{errorMessage}}</p>    \n    <mat-form-field>\n        <input matInput [(ngModel)]=\"project.code\" name=\"code\" placeholder=\"Code\" required>\n        <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <mat-form-field>\n      <input matInput [(ngModel)]=\"project.name\" name=\"name\" type=\"text\" placeholder=\"Name\" required>\n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <mat-form-field>\n      <input matInput [(ngModel)]=\"project.startDate\" name=\"startDate\" placeholder=\"Start Date\" type=\"date\" required #startDate=\"ngModel\">\n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <mat-form-field hintLabel=\"Ej. 10,15,20\">\n      <input matInput [(ngModel)]=\"project.springDays\" name=\"springDays\" placeholder=\"Spring Days\" type=\"number\" required>\n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <button type=\"submit\" *ngIf=\"!isEditMode\" [disabled]=\"!myForm.valid\" mat-mini-fab color=\"primary\" (click)=\"addProject()\" matTooltip=\"Add {{project.name}}\">\n      <mat-icon>add</mat-icon>\n    </button>  \n    <button type=\"submit\" *ngIf=\"isEditMode\"  [disabled]=\"!myForm.valid\" mat-mini-fab color=\"primary\" (click)=\"updateProject()\" matTooltip=\"Confirm update {{project.name}}\">\n      <mat-icon>check</mat-icon>\n    </button>  \n    <button type=\"submit\" *ngIf=\"isEditMode\"  mat-mini-fab color=\"primary\" (click)=\"cancelEditMode()\" matTooltip=\"Cancel update {{project.name}}\">\n      <mat-icon>close</mat-icon>\n    </button>\n  </form>\n</mat-card>\n<mat-card>\n  <mat-card-header>\n    <mat-card-title>Project List</mat-card-title>\n  </mat-card-header>\n  <!--Selection: <span id=\"selectedRows\">{{ projectSelected | json }}</span-->\n  <ag-grid-angular \n    #agGrid style=\"width: 100%; height: 200px;\" \n    id=\"myGrid\"\n    class=\"ag-theme-fresh\" \n    [gridOptions]=\"gridOptions\" \n    [columnDefs]=\"columnDefs\" \n    [rowData]=\"rowData\" \n    [context]=\"context\" \n    [rowSelection]=\"rowSelection\"\n    (selectionChanged)=\"onSelectionChanged($event)\"\n    (gridReady)=\"onGridReady($event)\">\n  </ag-grid-angular>\n</mat-card>\n"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/spending/spending.component.html":
/*!****************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/spending/spending.component.html ***!
  \****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-card>\n  <mat-card-header>\n    <mat-card-title>Asignment List</mat-card-title>\n  </mat-card-header>\n  <form #myForm=\"ngForm\">\n    <p class=\"text-danger\">{{errorMessage}}</p>    \n    <ag-grid-angular \n      #agGrid style=\"width: 100%; height: 200px;\" \n      class=\"ag-theme-fresh\" \n      [gridOptions]=\"gridOptions\" \n      [columnDefs]=\"columnDefs\" \n      [rowData]=\"rowData\" \n      [context]=\"context\" \n      (gridReady)=\"onGridReady($event)\"\n      (cellEditingStarted)=\"onCellEditingStarted($event)\">\n    </ag-grid-angular>\n  <div class=\"field-full-width txt_center margin1em\">\n      <button color=\"primary\" class=\"my_button\" mat-raised-button (click)=\"refeshAsignments()\">Cancel</button>\n      <button color=\"primary\" class=\"my_button\" mat-raised-button (click)=\"saveSpendings()\">Save</button>\n  </div>\n  </form>\n</mat-card>\n"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/springs/springs.component.html":
/*!**************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/springs/springs.component.html ***!
  \**************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-card>\n  <mat-card-header>\n    <mat-card-title>Spring Detail</mat-card-title>\n  </mat-card-header>\n  <form #myForm=\"ngForm\">\n    <p *ngIf=\"errorMessage.length > 0\" class=\"text-danger\"> {{errorMessage}}</p>    \n    <mat-form-field>\n        <input matInput [(ngModel)]=\"spring.code\" name=\"code\" placeholder=\"Spring Code\" required>\n        <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <mat-form-field>\n      <input matInput [(ngModel)]=\"spring.name\" name=\"name\" placeholder=\"Spring Name\" required>\n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <mat-form-field>\n      <input matInput [(ngModel)]=\"spring.springDays\" name=\"springDays\" placeholder=\"Spring Day\" type=\"number\" (change)=\"spring.setEndDateMdCalculed()\" required>\n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <mat-form-field>\n      <input matInput [(ngModel)]=\"spring.startDateMd.value\" name=\"startDate\" placeholder=\"Start Date\" type=\"date\" required disabled no-weekend #startDate=\"ngModel\" (change)=\"spring.setEndDateMdCalculed()\" >\n      <mat-error *ngIf=\"startDate.errors && startDate.errors.required\">Email required</mat-error>\n      <mat-error *ngIf=\"startDate.errors && startDate.errors.weekend && !startDate.errors.required \">Start Date on weekend</mat-error>\n      <!--mat-error>This field is required</mat-error-->\n    </mat-form-field>\n    <mat-form-field>\n      <input matInput [(ngModel)]=\"spring.endDateMd.value\" name=\"endDate\" placeholder=\"End Date\" type=\"date\" required disabled>\n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <button type=\"submit\" *ngIf=\"!isEditMode\" [disabled]=\"!myForm.valid\" mat-mini-fab color=\"primary\" (click)=\"addSpring()\" matTooltip=\"Add {{spring.name}}\">\n      <mat-icon>add</mat-icon>\n    </button>  \n    <button type=\"submit\" *ngIf=\"isEditMode\"  [disabled]=\"!myForm.valid\" mat-mini-fab color=\"primary\" (click)=\"updateSpring()\" matTooltip=\"Confirm update {{spring.name}}\">\n      <mat-icon>check</mat-icon>\n    </button>  \n    <button type=\"submit\" *ngIf=\"isEditMode\"  mat-mini-fab color=\"primary\" (click)=\"cancelEditMode()\" matTooltip=\"Cancel update {{spring.name}}\">\n      <mat-icon>close</mat-icon>\n    </button>\n    </form>\n</mat-card>\n\n<mat-card>\n  <mat-card-header>\n    <mat-card-title>Spring List</mat-card-title>\n  </mat-card-header>\n  <ag-grid-angular \n    #agGrid style=\"width: 100%; height: 200px;\" \n    class=\"ag-theme-fresh\" \n    [gridOptions]=\"gridOptions\" \n    [columnDefs]=\"columnDefs\" \n    [rowData]=\"rowData\" \n    [context]=\"context\" \n    [rowSelection]=\"rowSelection\"\n    (selectionChanged)=\"onSelectionChanged($event)\"\n    (gridReady)=\"onGridReady($event)\">\n  </ag-grid-angular>\n</mat-card>\n"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/users/users.component.html":
/*!**********************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/users/users.component.html ***!
  \**********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-card>\n  <mat-card-header>\n    <mat-card-title>User Detail</mat-card-title>\n  </mat-card-header>\n  <form #myForm=\"ngForm\">\n    <p *ngIf=\"errorMessage.length > 0\" class=\"text-danger\"> {{errorMessage}}</p>    \n    <mat-form-field>\n        <input matInput [(ngModel)]=\"user.username\" name=\"user\" placeholder=\"user\" required [disabled]=\"isEditMode\">\n        <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <mat-form-field>\n      <input matInput [(ngModel)]=\"user.name\" name=\"name\" placeholder=\"Name\" required>\n      <mat-error>This field is required</mat-error>\n    </mat-form-field>\n    <mat-form-field>\n      <input matInput [(ngModel)]=\"user.email\" name=\"email\" placeholder=\"Email\" type=\"email\" required email #email=\"ngModel\">\n      <mat-error *ngIf=\"email.errors && email.errors.required\">Email required</mat-error>\n      <mat-error *ngIf=\"email.errors && email.errors.email && !email.errors.required \">Email invalid format</mat-error>\n    </mat-form-field>\n    <mat-form-field hintLabel=\"Ej. +541112341234\">\n      <input matInput [(ngModel)]=\"user.phone\" maxlength=\"13\" name=\"phone\" type=\"tel\" placeholder=\"Phone\" pattern=\"\\+[0-9]*\">\n      <mat-error>Phone invalid format</mat-error>\n    </mat-form-field>\n    <mat-form-field hintLabel=\"Ej. +5491112341234\">\n      <input matInput [(ngModel)]=\"user.mobile\" maxlength=\"14\" name=\"mobile\" type=\"tel\" placeholder=\"Mobile\" pattern=\"\\+[0-9]*\">\n      <mat-error>Mobile invalid format</mat-error>\n    </mat-form-field>\n    <button type=\"submit\" *ngIf=\"!isEditMode\" [disabled]=\"!myForm.valid\" mat-mini-fab color=\"primary\" (click)=\"addUser()\" matTooltip=\"Add {{user.name}}\">\n      <mat-icon>add</mat-icon>\n    </button>  \n    <button type=\"submit\" *ngIf=\"isEditMode\"  [disabled]=\"!myForm.valid\" mat-mini-fab color=\"primary\" (click)=\"updateUser()\" matTooltip=\"Confirm update {{user.name}}\">\n      <mat-icon>check</mat-icon>\n    </button>  \n    <button type=\"submit\" *ngIf=\"isEditMode\"  mat-mini-fab color=\"primary\" (click)=\"cancelEditMode()\" matTooltip=\"Cancel update {{user.name}}\">\n      <mat-icon>close</mat-icon>\n    </button>\n    <button type=\"submit\" *ngIf=\"isEditMode\"  [disabled]=\"!myForm.valid\" mat-mini-fab color=\"primary\" (click)=\"createUser()\" matTooltip=\"Create User {{user.name}}\">\n        <mat-icon>group_add</mat-icon>\n      </button>  \n    </form>\n</mat-card>\n\n<mat-card>\n  <mat-card-header>\n    <mat-card-title>User List</mat-card-title>\n  </mat-card-header>\n  <ag-grid-angular \n    #agGrid style=\"width: 100%; height: 200px;\" \n    class=\"ag-theme-fresh\" \n    [gridOptions]=\"gridOptions\" \n    [columnDefs]=\"columnDefs\" \n    [rowData]=\"rowData\" \n    [context]=\"context\" \n    (gridReady)=\"onGridReady($event)\">\n  </ag-grid-angular>\n</mat-card>\n"

/***/ }),

/***/ "./src/app/app-routing.module.ts":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _projects_projects_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./projects/projects.component */ "./src/app/projects/projects.component.ts");
/* harmony import */ var _login_login_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./login/login.component */ "./src/app/login/login.component.ts");
/* harmony import */ var _users_users_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./users/users.component */ "./src/app/users/users.component.ts");
/* harmony import */ var _springs_springs_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./springs/springs.component */ "./src/app/springs/springs.component.ts");
/* harmony import */ var _backlog_features_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./backlog/features.component */ "./src/app/backlog/features.component.ts");
/* harmony import */ var _asignment_asignment_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./asignment/asignment.component */ "./src/app/asignment/asignment.component.ts");
/* harmony import */ var _capacity_capacity_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./capacity/capacity.component */ "./src/app/capacity/capacity.component.ts");
/* harmony import */ var _spending_spending_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./spending/spending.component */ "./src/app/spending/spending.component.ts");











var routes = [
    { path: '', redirectTo: 'projects', pathMatch: 'full' },
    { path: 'projects', component: _projects_projects_component__WEBPACK_IMPORTED_MODULE_3__["ProjectsComponent"] },
    { path: 'springs', component: _springs_springs_component__WEBPACK_IMPORTED_MODULE_6__["SpringsComponent"] },
    { path: 'backlog', component: _backlog_features_component__WEBPACK_IMPORTED_MODULE_7__["FeaturesComponent"] },
    { path: 'asignment', component: _asignment_asignment_component__WEBPACK_IMPORTED_MODULE_8__["AsignmentComponent"] },
    { path: 'capacity', component: _capacity_capacity_component__WEBPACK_IMPORTED_MODULE_9__["CapacityComponent"] },
    { path: 'spending', component: _spending_spending_component__WEBPACK_IMPORTED_MODULE_10__["SpendingComponent"] },
    { path: 'users', component: _users_users_component__WEBPACK_IMPORTED_MODULE_5__["UsersComponent"] },
    { path: 'login', component: _login_login_component__WEBPACK_IMPORTED_MODULE_4__["LoginComponent"] }
];
var AppRoutingModule = /** @class */ (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forRoot(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());



/***/ }),

/***/ "./src/app/app.component.scss":
/*!************************************!*\
  !*** ./src/app/app.component.scss ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "#toolbar1 {\n  position: fixed;\n  top: 0;\n  left: 0;\n  z-index: 2;\n}\n\n.example-spacer {\n  flex: 1 1 auto;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvQzpcXGdtYWNcXHN0c193b3Jrc3BhY2VzXFxob3l4aG95LXBsYW5uZXJcXGhveXhob3ktcGxhbm5lci1mcm9udGVuZC9zcmNcXGFwcFxcYXBwLmNvbXBvbmVudC5zY3NzIiwic3JjL2FwcC9hcHAuY29tcG9uZW50LnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDRSxlQUFBO0VBQ0EsTUFBQTtFQUNBLE9BQUE7RUFDQSxVQUFBO0FDQ0Y7O0FERUE7RUFDRSxjQUFBO0FDQ0YiLCJmaWxlIjoic3JjL2FwcC9hcHAuY29tcG9uZW50LnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyIjdG9vbGJhcjEge1xyXG4gIHBvc2l0aW9uOiBmaXhlZDtcclxuICB0b3A6IDA7XHJcbiAgbGVmdDogMDtcclxuICB6LWluZGV4OiAyO1xyXG59XHJcblxyXG4uZXhhbXBsZS1zcGFjZXIge1xyXG4gIGZsZXg6IDEgMSBhdXRvO1xyXG59XHJcbiIsIiN0b29sYmFyMSB7XG4gIHBvc2l0aW9uOiBmaXhlZDtcbiAgdG9wOiAwO1xuICBsZWZ0OiAwO1xuICB6LWluZGV4OiAyO1xufVxuXG4uZXhhbXBsZS1zcGFjZXIge1xuICBmbGV4OiAxIDEgYXV0bztcbn0iXX0= */"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./login/login.service */ "./src/app/login/login.service.ts");




var AppComponent = /** @class */ (function () {
    function AppComponent(loginService, router) {
        this.loginService = loginService;
        this.router = router;
        this.title = 'Angular 8 Application';
        if (this.loginService.isUserLoggedIn) {
            this.router.navigate(['']);
        }
        else {
            this.router.navigate(['/login']);
        }
    }
    AppComponent.prototype.logout = function () {
        this.loginService.signOut();
        this.router.navigate(['/login']);
    };
    AppComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! raw-loader!./app.component.html */ "./node_modules/raw-loader/index.js!./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.scss */ "./src/app/app.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_login_login_service__WEBPACK_IMPORTED_MODULE_3__["LoginService"], _angular_router__WEBPACK_IMPORTED_MODULE_2__["Router"]])
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _projects_projects_module__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./projects/projects.module */ "./src/app/projects/projects.module.ts");
/* harmony import */ var _login_login_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./login/login.component */ "./src/app/login/login.component.ts");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./login/login.service */ "./src/app/login/login.service.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! @angular/platform-browser/animations */ "./node_modules/@angular/platform-browser/fesm5/animations.js");
/* harmony import */ var _users_users_module__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! ./users/users.module */ "./src/app/users/users.module.ts");
/* harmony import */ var _springs_springs_module__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! ./springs/springs.module */ "./src/app/springs/springs.module.ts");
/* harmony import */ var _backlog_features_module__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(/*! ./backlog/features.module */ "./src/app/backlog/features.module.ts");
/* harmony import */ var _asignment_asignment_module__WEBPACK_IMPORTED_MODULE_16__ = __webpack_require__(/*! ./asignment/asignment.module */ "./src/app/asignment/asignment.module.ts");
/* harmony import */ var _capacity_capacity_module__WEBPACK_IMPORTED_MODULE_17__ = __webpack_require__(/*! ./capacity/capacity.module */ "./src/app/capacity/capacity.module.ts");
/* harmony import */ var _spending_spending_module__WEBPACK_IMPORTED_MODULE_18__ = __webpack_require__(/*! ./spending/spending.module */ "./src/app/spending/spending.module.ts");



















var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_6__["AppComponent"],
                _login_login_component__WEBPACK_IMPORTED_MODULE_9__["LoginComponent"]
            ],
            imports: [
                _shared_shared_module__WEBPACK_IMPORTED_MODULE_7__["SharedModule"],
                _projects_projects_module__WEBPACK_IMPORTED_MODULE_8__["ProjectsModule"],
                _users_users_module__WEBPACK_IMPORTED_MODULE_13__["UsersModule"],
                _springs_springs_module__WEBPACK_IMPORTED_MODULE_14__["SpringsModule"],
                _backlog_features_module__WEBPACK_IMPORTED_MODULE_15__["FeaturesModule"],
                _capacity_capacity_module__WEBPACK_IMPORTED_MODULE_17__["CapacityModule"],
                _asignment_asignment_module__WEBPACK_IMPORTED_MODULE_16__["AsignmentModule"],
                _spending_spending_module__WEBPACK_IMPORTED_MODULE_18__["SpendingModule"],
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__["BrowserModule"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_5__["AppRoutingModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_11__["MatIconModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_11__["MatMenuModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_11__["MatToolbarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_11__["MatCardModule"],
                _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_12__["BrowserAnimationsModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClientModule"]
            ],
            providers: [
                _login_login_service__WEBPACK_IMPORTED_MODULE_10__["LoginService"]
            ],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_6__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/asignment/asignment.component.scss":
/*!****************************************************!*\
  !*** ./src/app/asignment/asignment.component.scss ***!
  \****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2FzaWdubWVudC9hc2lnbm1lbnQuY29tcG9uZW50LnNjc3MifQ== */"

/***/ }),

/***/ "./src/app/asignment/asignment.component.ts":
/*!**************************************************!*\
  !*** ./src/app/asignment/asignment.component.ts ***!
  \**************************************************/
/*! exports provided: AsignmentComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AsignmentComponent", function() { return AsignmentComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component */ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component.ts");
/* harmony import */ var _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component */ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component.ts");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../login/login.service */ "./src/app/login/login.service.ts");
/* harmony import */ var _backlog_features_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../backlog/features.service */ "./src/app/backlog/features.service.ts");
/* harmony import */ var _users_users_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../users/users.service */ "./src/app/users/users.service.ts");
/* harmony import */ var _asignment__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./asignment */ "./src/app/asignment/asignment.ts");
/* harmony import */ var _asignment_service__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./asignment.service */ "./src/app/asignment/asignment.service.ts");










var AsignmentComponent = /** @class */ (function () {
    function AsignmentComponent(asignmentsService, featuresService, userService, loginService) {
        this.asignmentsService = asignmentsService;
        this.featuresService = featuresService;
        this.userService = userService;
        this.loginService = loginService;
        this.asignment = new _asignment__WEBPACK_IMPORTED_MODULE_8__["Asignment"]();
        this.errorMessage = "";
        this.isEditMode = false;
        this.context = { componentParent: this };
        this.gridOptions = {};
        this.gridOptions.enableFilter = true;
        this.gridOptions.enableSorting = true;
        this.gridOptions.rowSelection = 'single';
        this.gridOptions.suppressRowClickSelection = false;
        this.gridOptions.enableColResize = true;
        this.gridOptions.enableCellChangeFlash = true;
        this.columnDefs = [
            { headerName: 'Id', field: 'id', hide: true },
            { headerName: 'F.Code', field: 'feature.code', filter: 'text', width: 100 },
            { headerName: 'Feature Title', field: 'feature.title', filter: 'text', width: 400 },
            { headerName: 'Estimated Hs.', field: 'feature.estimatedHours', filter: 'text', width: 130 },
            { headerName: 'Remaining Hs.', field: 'remaining', filter: 'text', width: 130 },
            { headerName: 'Asigned', field: 'user.name', filter: 'text', width: 150 },
            { headerName: '', cellRendererFramework: _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_3__["MatEditButtonGridRenderComponent"], width: 75 },
            { headerName: '', suppressFilter: true, cellRendererFramework: _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__["MatRemoveButtonGridRenderComponent"], width: 75 }
        ];
    }
    AsignmentComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.populateAsignments();
        this.featuresService.getFeatures().toPromise().then(function (feats) { return _this.features = feats; });
        this.userService.getUsers().toPromise().then(function (users) { return _this.users = users; });
    };
    AsignmentComponent.prototype.refeshAsignments = function () {
        this.populateAsignments();
        this.initialMode();
    };
    AsignmentComponent.prototype.populateAsignments = function () {
        var _this = this;
        this.asignments.subscribe(function (asignmentList) { return _this.rowData = asignmentList; }, function (error) { return _this.handleError(error); });
    };
    AsignmentComponent.prototype.dateFormatter = function (params) {
        return new Date(params.value).toLocaleDateString("es-ES", { timeZone: 'UTC', year: "numeric", month: "2-digit", day: "2-digit" });
    };
    Object.defineProperty(AsignmentComponent.prototype, "asignments", {
        get: function () {
            return this.asignmentsService.getAsignments();
        },
        enumerable: true,
        configurable: true
    });
    AsignmentComponent.prototype.getAsignmentById = function (id) {
        return this.asignmentsService.getAsignmentById(id);
    };
    AsignmentComponent.prototype.removeAsignment = function (id) {
        var _this = this;
        this.asignmentsService.deleteAsignmentById(id).subscribe(function (data) { return _this.refeshAsignments(); }, function (error) { return console.log(error); });
    };
    AsignmentComponent.prototype.editAsignment = function (data) {
        this.asignment = new _asignment__WEBPACK_IMPORTED_MODULE_8__["Asignment"](data);
        this.errorMessage = "";
    };
    AsignmentComponent.prototype.addAsignment = function () {
        var _this = this;
        //  this.project.status = "OPEN";
        this.asignmentsService.addAsignment(this.asignment).subscribe(function (data) { return _this.refeshAsignments(); }, function (error) { return _this.handleError(error); });
    };
    AsignmentComponent.prototype.handleError = function (res) {
        this.errorMessage = res.error.error_message;
        console.log(res);
    };
    AsignmentComponent.prototype.updateAsignment = function () {
        var _this = this;
        this.asignmentsService.updateAsignment(this.asignment).subscribe(function (data) { return _this.refeshAsignments(); }, function (error) { return _this.handleError(error); });
    };
    AsignmentComponent.prototype.resetControls = function () {
        var _this = this;
        this.myForm.resetForm();
        Object.keys(this.myForm.controls).forEach(function (field) {
            var control = _this.myForm.control.get(field);
            control.markAsUntouched();
        });
    };
    // Call from MatRemoveButtonGridRenderComponent
    AsignmentComponent.prototype.removeFromComponent = function (asignment) {
        this.removeAsignment(asignment.id);
        this.initialMode();
    };
    // Call from MatEditButtonGridRenderComponent
    AsignmentComponent.prototype.editFromComponent = function (data) {
        this.isEditMode = true;
        this.editAsignment(data);
    };
    AsignmentComponent.prototype.cancelEditMode = function () {
        this.initialMode();
    };
    AsignmentComponent.prototype.initialMode = function () {
        this.asignment = new _asignment__WEBPACK_IMPORTED_MODULE_8__["Asignment"]();
        this.errorMessage = "";
        this.isEditMode = false;
        this.resetControls();
    };
    AsignmentComponent.prototype.onGridReady = function (params) {
        this.gridApi = params.api;
        this.gridColumnApi = params.columnApi;
        this.gridColumnApi.autoSizeColumns();
    };
    AsignmentComponent.prototype.compareFeatures = function (o1, o2) {
        return (o1 && o2) ? o1.id === o2.id : o1 === o2;
    };
    AsignmentComponent.prototype.compareUsers = function (o1, o2) {
        return (o1 && o2) ? o1.username === o2.username : o1 === o2;
    };
    AsignmentComponent.prototype.getMatTooltipButton = function () {
        if (this.asignment.feature) {
            return this.asignment.feature.title;
        }
        return "";
    };
    tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ViewChild"])('myForm', { static: false }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", _angular_forms__WEBPACK_IMPORTED_MODULE_2__["NgForm"])
    ], AsignmentComponent.prototype, "myForm", void 0);
    AsignmentComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-asignment',
            template: __webpack_require__(/*! raw-loader!./asignment.component.html */ "./node_modules/raw-loader/index.js!./src/app/asignment/asignment.component.html"),
            styles: [__webpack_require__(/*! ./asignment.component.scss */ "./src/app/asignment/asignment.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_asignment_service__WEBPACK_IMPORTED_MODULE_9__["AsignmentService"], _backlog_features_service__WEBPACK_IMPORTED_MODULE_6__["FeaturesService"], _users_users_service__WEBPACK_IMPORTED_MODULE_7__["UserService"], _login_login_service__WEBPACK_IMPORTED_MODULE_5__["LoginService"]])
    ], AsignmentComponent);
    return AsignmentComponent;
}());



/***/ }),

/***/ "./src/app/asignment/asignment.module.ts":
/*!***********************************************!*\
  !*** ./src/app/asignment/asignment.module.ts ***!
  \***********************************************/
/*! exports provided: AsignmentModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AsignmentModule", function() { return AsignmentModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ag-grid-angular */ "./node_modules/ag-grid-angular/main.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _asignment_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./asignment.component */ "./src/app/asignment/asignment.component.ts");





var AsignmentModule = /** @class */ (function () {
    function AsignmentModule() {
    }
    AsignmentModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__["SharedModule"],
                ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__["AgGridModule"].withComponents([])
            ],
            declarations: [_asignment_component__WEBPACK_IMPORTED_MODULE_4__["AsignmentComponent"]]
        })
    ], AsignmentModule);
    return AsignmentModule;
}());



/***/ }),

/***/ "./src/app/asignment/asignment.service.ts":
/*!************************************************!*\
  !*** ./src/app/asignment/asignment.service.ts ***!
  \************************************************/
/*! exports provided: AsignmentService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AsignmentService", function() { return AsignmentService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../login/login.service */ "./src/app/login/login.service.ts");




var AsignmentService = /** @class */ (function () {
    function AsignmentService(http, loginService) {
        this.http = http;
        this.loginService = loginService;
        this._baseUrl = './api/springs/';
    }
    AsignmentService.prototype.getAsignments = function () {
        return this.http.get(this.baseUrl);
    };
    AsignmentService.prototype.getAsignmentById = function (id) {
        return this.http.get(this.baseUrl + "/" + id);
    };
    AsignmentService.prototype.addAsignment = function (asignment) {
        return this.http.post(this.baseUrl, asignment);
    };
    AsignmentService.prototype.deleteAsignmentById = function (id) {
        return this.http.delete(this.baseUrl + "/" + id);
    };
    AsignmentService.prototype.updateAsignment = function (asignment) {
        return this.http.put(this.baseUrl + "/" + asignment.id, asignment);
    };
    Object.defineProperty(AsignmentService.prototype, "baseUrl", {
        get: function () {
            return this._baseUrl + this.loginService.currentSpringId + '/asignments';
        },
        enumerable: true,
        configurable: true
    });
    AsignmentService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _login_login_service__WEBPACK_IMPORTED_MODULE_3__["LoginService"]])
    ], AsignmentService);
    return AsignmentService;
}());



/***/ }),

/***/ "./src/app/asignment/asignment.ts":
/*!****************************************!*\
  !*** ./src/app/asignment/asignment.ts ***!
  \****************************************/
/*! exports provided: Asignment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Asignment", function() { return Asignment; });
var Asignment = /** @class */ (function () {
    function Asignment(values) {
        if (values === void 0) { values = {}; }
        this.spendingsInt = [];
        Object.assign(this, values);
        this.calculateSumSpending();
    }
    Asignment.prototype.addSpendings = function (length) {
        for (var index = 0; index < length; index++) {
            this.spendingsInt[index] = null;
        }
        return this;
    };
    Asignment.prototype.calculateSumSpending = function () {
        var numOr0 = function (n) { return isNaN(n) ? 0 : n; };
        this.spending = this.spendingsInt.reduce(function (a, b) { return numOr0(a) + numOr0(b); });
    };
    return Asignment;
}());



/***/ }),

/***/ "./src/app/backlog/feature.ts":
/*!************************************!*\
  !*** ./src/app/backlog/feature.ts ***!
  \************************************/
/*! exports provided: Feature */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Feature", function() { return Feature; });
var Feature = /** @class */ (function () {
    function Feature(values) {
        if (values === void 0) { values = {}; }
        this.title = '';
        this.status = '';
        this.estimatedHours = 0;
        this.committedDate = null;
        Object.assign(this, values);
    }
    return Feature;
}());



/***/ }),

/***/ "./src/app/backlog/features.component.scss":
/*!*************************************************!*\
  !*** ./src/app/backlog/features.component.scss ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2JhY2tsb2cvZmVhdHVyZXMuY29tcG9uZW50LnNjc3MifQ== */"

/***/ }),

/***/ "./src/app/backlog/features.component.ts":
/*!***********************************************!*\
  !*** ./src/app/backlog/features.component.ts ***!
  \***********************************************/
/*! exports provided: FeaturesComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FeaturesComponent", function() { return FeaturesComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component */ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component.ts");
/* harmony import */ var _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component */ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component.ts");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../login/login.service */ "./src/app/login/login.service.ts");
/* harmony import */ var _feature__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./feature */ "./src/app/backlog/feature.ts");
/* harmony import */ var _features_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./features.service */ "./src/app/backlog/features.service.ts");








var FeaturesComponent = /** @class */ (function () {
    function FeaturesComponent(featuresService, loginService) {
        this.featuresService = featuresService;
        this.loginService = loginService;
        this.feature = new _feature__WEBPACK_IMPORTED_MODULE_6__["Feature"]();
        this.errorMessage = "";
        this.isEditMode = false;
        this.context = { componentParent: this };
        this.gridOptions = {};
        this.gridOptions.enableFilter = true;
        this.gridOptions.enableSorting = true;
        this.gridOptions.rowSelection = 'single';
        this.gridOptions.suppressRowClickSelection = false;
        this.gridOptions.enableColResize = true;
        this.gridOptions.enableCellChangeFlash = true;
        this.columnDefs = [
            { headerName: 'Id', field: 'id', hide: true },
            { headerName: 'F.Code', field: 'code', filter: 'text', width: 100 },
            { headerName: 'Feature Title', field: 'title', filter: 'text', width: 400 },
            { headerName: 'Estimated Hours', field: 'estimatedHours', filter: 'text', width: 130 },
            { headerName: 'Committed Date', field: 'committedDate', filter: 'text', width: 170 },
            { headerName: '', cellRendererFramework: _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_3__["MatEditButtonGridRenderComponent"], width: 75 },
            { headerName: '', suppressFilter: true, cellRendererFramework: _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__["MatRemoveButtonGridRenderComponent"], width: 75 }
        ];
    }
    FeaturesComponent.prototype.ngOnInit = function () {
        this.populateFeatures();
    };
    FeaturesComponent.prototype.refeshFeatures = function () {
        this.populateFeatures();
        this.initialMode();
    };
    FeaturesComponent.prototype.populateFeatures = function () {
        var _this = this;
        this.features.subscribe(function (featureList) { return _this.rowData = featureList; }, function (error) { return _this.handleError(error); });
    };
    FeaturesComponent.prototype.dateFormatter = function (params) {
        return new Date(params.value).toLocaleDateString("es-ES", { timeZone: 'UTC', year: "numeric", month: "2-digit", day: "2-digit" });
    };
    Object.defineProperty(FeaturesComponent.prototype, "features", {
        get: function () {
            return this.featuresService.getFeatures();
        },
        enumerable: true,
        configurable: true
    });
    FeaturesComponent.prototype.getFeatureById = function (id) {
        return this.featuresService.getFeatureById(id);
    };
    FeaturesComponent.prototype.removeFeature = function (id) {
        var _this = this;
        this.featuresService.deleteFeatureById(id).subscribe(function (data) { return _this.refeshFeatures(); }, function (error) { return console.log(error); });
    };
    FeaturesComponent.prototype.editFeature = function (data) {
        this.feature = new _feature__WEBPACK_IMPORTED_MODULE_6__["Feature"](data);
        this.errorMessage = "";
    };
    FeaturesComponent.prototype.addFeature = function () {
        var _this = this;
        //  this.project.status = "OPEN";
        this.featuresService.addFeature(this.feature).subscribe(function (data) { return _this.refeshFeatures(); }, function (error) { return _this.handleError(error); });
    };
    FeaturesComponent.prototype.handleError = function (res) {
        this.errorMessage = res.error.error_message;
        console.log(res);
    };
    FeaturesComponent.prototype.updateFeature = function () {
        var _this = this;
        this.featuresService.updateFeature(this.feature).subscribe(function (data) { return _this.refeshFeatures(); }, function (error) { return _this.handleError(error); });
    };
    FeaturesComponent.prototype.resetControls = function () {
        var _this = this;
        this.myForm.resetForm();
        Object.keys(this.myForm.controls).forEach(function (field) {
            var control = _this.myForm.control.get(field);
            control.markAsUntouched();
        });
    };
    // Call from MatRemoveButtonGridRenderComponent
    FeaturesComponent.prototype.removeFromComponent = function (feature) {
        this.removeFeature(feature.id);
        this.initialMode();
    };
    // Call from MatEditButtonGridRenderComponent
    FeaturesComponent.prototype.editFromComponent = function (data) {
        this.isEditMode = true;
        this.editFeature(data);
    };
    FeaturesComponent.prototype.cancelEditMode = function () {
        this.initialMode();
    };
    FeaturesComponent.prototype.initialMode = function () {
        this.feature = new _feature__WEBPACK_IMPORTED_MODULE_6__["Feature"]();
        this.errorMessage = "";
        this.isEditMode = false;
        this.resetControls();
    };
    FeaturesComponent.prototype.onGridReady = function (params) {
        this.gridApi = params.api;
        this.gridColumnApi = params.columnApi;
        this.gridColumnApi.autoSizeColumns();
    };
    tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ViewChild"])('myForm', { static: false }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", _angular_forms__WEBPACK_IMPORTED_MODULE_2__["NgForm"])
    ], FeaturesComponent.prototype, "myForm", void 0);
    FeaturesComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-features',
            template: __webpack_require__(/*! raw-loader!./features.component.html */ "./node_modules/raw-loader/index.js!./src/app/backlog/features.component.html"),
            styles: [__webpack_require__(/*! ./features.component.scss */ "./src/app/backlog/features.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_features_service__WEBPACK_IMPORTED_MODULE_7__["FeaturesService"], _login_login_service__WEBPACK_IMPORTED_MODULE_5__["LoginService"]])
    ], FeaturesComponent);
    return FeaturesComponent;
}());



/***/ }),

/***/ "./src/app/backlog/features.module.ts":
/*!********************************************!*\
  !*** ./src/app/backlog/features.module.ts ***!
  \********************************************/
/*! exports provided: FeaturesModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FeaturesModule", function() { return FeaturesModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ag-grid-angular */ "./node_modules/ag-grid-angular/main.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _features_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./features.component */ "./src/app/backlog/features.component.ts");





var FeaturesModule = /** @class */ (function () {
    function FeaturesModule() {
    }
    FeaturesModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__["SharedModule"],
                ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__["AgGridModule"].withComponents([])
            ],
            declarations: [_features_component__WEBPACK_IMPORTED_MODULE_4__["FeaturesComponent"]]
        })
    ], FeaturesModule);
    return FeaturesModule;
}());



/***/ }),

/***/ "./src/app/backlog/features.service.ts":
/*!*********************************************!*\
  !*** ./src/app/backlog/features.service.ts ***!
  \*********************************************/
/*! exports provided: FeaturesService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FeaturesService", function() { return FeaturesService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../login/login.service */ "./src/app/login/login.service.ts");




var FeaturesService = /** @class */ (function () {
    function FeaturesService(http, loginService) {
        this.http = http;
        this.loginService = loginService;
        this.currentProjectId = "";
        this._baseUrl = './api/projects/';
    }
    FeaturesService.prototype.getFeatures = function () {
        return this.http.get(this.baseUrl);
    };
    FeaturesService.prototype.getFeatureById = function (id) {
        return this.http.get(this.baseUrl + "/" + id);
    };
    FeaturesService.prototype.addFeature = function (feature) {
        return this.http.post(this.baseUrl, feature);
    };
    FeaturesService.prototype.deleteFeatureById = function (id) {
        return this.http.delete(this.baseUrl + "/" + id);
    };
    FeaturesService.prototype.updateFeature = function (feature) {
        return this.http.put(this.baseUrl + "/" + feature.id, feature);
    };
    Object.defineProperty(FeaturesService.prototype, "baseUrl", {
        get: function () {
            return this._baseUrl + this.loginService.currentProjectId + '/features';
        },
        enumerable: true,
        configurable: true
    });
    FeaturesService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _login_login_service__WEBPACK_IMPORTED_MODULE_3__["LoginService"]])
    ], FeaturesService);
    return FeaturesService;
}());



/***/ }),

/***/ "./src/app/capacity/capacity.component.scss":
/*!**************************************************!*\
  !*** ./src/app/capacity/capacity.component.scss ***!
  \**************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NhcGFjaXR5L2NhcGFjaXR5LmNvbXBvbmVudC5zY3NzIn0= */"

/***/ }),

/***/ "./src/app/capacity/capacity.component.ts":
/*!************************************************!*\
  !*** ./src/app/capacity/capacity.component.ts ***!
  \************************************************/
/*! exports provided: CapacityComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CapacityComponent", function() { return CapacityComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component */ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component.ts");
/* harmony import */ var _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component */ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component.ts");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../login/login.service */ "./src/app/login/login.service.ts");
/* harmony import */ var _users_users_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../users/users.service */ "./src/app/users/users.service.ts");
/* harmony import */ var _capacity__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./capacity */ "./src/app/capacity/capacity.ts");
/* harmony import */ var _capacity_service__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./capacity.service */ "./src/app/capacity/capacity.service.ts");









var CapacityComponent = /** @class */ (function () {
    function CapacityComponent(capacitysService, userService, loginService) {
        this.capacitysService = capacitysService;
        this.userService = userService;
        this.loginService = loginService;
        this.capacity = new _capacity__WEBPACK_IMPORTED_MODULE_7__["Capacity"]();
        this.errorMessage = "";
        this.isEditMode = false;
        this.context = { componentParent: this };
        this.gridOptions = {};
        this.gridOptions.enableFilter = true;
        this.gridOptions.enableSorting = true;
        this.gridOptions.rowSelection = 'single';
        this.gridOptions.suppressRowClickSelection = false;
        this.gridOptions.enableColResize = true;
        this.gridOptions.enableCellChangeFlash = true;
        this.columnDefs = [
            { headerName: 'Id', field: 'id', hide: true },
            { headerName: 'User', field: 'user.name', filter: 'text', width: 150 },
            { headerName: 'Available Hs', field: 'availableHours', filter: 'text', width: 130 },
            { headerName: 'Total Hs', field: 'availableOnSpring', filter: 'text', width: 130 },
            { headerName: 'Remaining Hs', field: 'remainingOnSpring', filter: 'text', width: 130 },
            { headerName: '', cellRendererFramework: _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_3__["MatEditButtonGridRenderComponent"], width: 75 },
            { headerName: '', suppressFilter: true, cellRendererFramework: _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__["MatRemoveButtonGridRenderComponent"], width: 75 }
        ];
    }
    CapacityComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.populateCapacitys();
        this.userService.getUsers().toPromise().then(function (users) { return _this.users = users; });
    };
    CapacityComponent.prototype.refeshCapacitys = function () {
        this.populateCapacitys();
        this.initialMode();
    };
    CapacityComponent.prototype.populateCapacitys = function () {
        var _this = this;
        this.capacities.subscribe(function (capacityList) { return _this.rowData = capacityList; }, function (error) { return _this.handleError(error); });
    };
    CapacityComponent.prototype.dateFormatter = function (params) {
        return new Date(params.value).toLocaleDateString("es-ES", { timeZone: 'UTC', year: "numeric", month: "2-digit", day: "2-digit" });
    };
    Object.defineProperty(CapacityComponent.prototype, "capacities", {
        get: function () {
            return this.capacitysService.getCapacities();
        },
        enumerable: true,
        configurable: true
    });
    CapacityComponent.prototype.getCapacityById = function (id) {
        return this.capacitysService.getCapacityById(id);
    };
    CapacityComponent.prototype.removeCapacity = function (id) {
        var _this = this;
        this.capacitysService.deleteCapacityById(id).subscribe(function (data) { return _this.refeshCapacitys(); }, function (error) { return console.log(error); });
    };
    CapacityComponent.prototype.editCapacity = function (data) {
        this.capacity = new _capacity__WEBPACK_IMPORTED_MODULE_7__["Capacity"](data);
        this.errorMessage = "";
    };
    CapacityComponent.prototype.addCapacity = function () {
        var _this = this;
        //  this.project.status = "OPEN";
        this.capacitysService.addCapacity(this.capacity).subscribe(function (data) { return _this.refeshCapacitys(); }, function (error) { return _this.handleError(error); });
    };
    CapacityComponent.prototype.handleError = function (res) {
        this.errorMessage = res.error.error_message;
        console.log(res);
    };
    CapacityComponent.prototype.updateCapacity = function () {
        var _this = this;
        this.capacitysService.updateCapacity(this.capacity).subscribe(function (data) { return _this.refeshCapacitys(); }, function (error) { return _this.handleError(error); });
    };
    CapacityComponent.prototype.resetControls = function () {
        var _this = this;
        this.myForm.resetForm();
        Object.keys(this.myForm.controls).forEach(function (field) {
            var control = _this.myForm.control.get(field);
            control.markAsUntouched();
        });
    };
    // Call from MatRemoveButtonGridRenderComponent
    CapacityComponent.prototype.removeFromComponent = function (capacity) {
        this.removeCapacity(capacity.id);
        this.initialMode();
    };
    // Call from MatEditButtonGridRenderComponent
    CapacityComponent.prototype.editFromComponent = function (data) {
        this.isEditMode = true;
        this.editCapacity(data);
    };
    CapacityComponent.prototype.cancelEditMode = function () {
        this.initialMode();
    };
    CapacityComponent.prototype.initialMode = function () {
        this.capacity = new _capacity__WEBPACK_IMPORTED_MODULE_7__["Capacity"]();
        this.errorMessage = "";
        this.isEditMode = false;
        this.resetControls();
    };
    CapacityComponent.prototype.onGridReady = function (params) {
        this.gridApi = params.api;
        this.gridColumnApi = params.columnApi;
        this.gridColumnApi.autoSizeColumns();
    };
    CapacityComponent.prototype.compareUsers = function (o1, o2) {
        return (o1 && o2) ? o1.username === o2.username : o1 === o2;
    };
    CapacityComponent.prototype.getMatTooltipButton = function () {
        if (this.capacity.user) {
            return this.capacity.user.username;
        }
        return "";
    };
    tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ViewChild"])('myForm', { static: false }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", _angular_forms__WEBPACK_IMPORTED_MODULE_2__["NgForm"])
    ], CapacityComponent.prototype, "myForm", void 0);
    CapacityComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-capacity',
            template: __webpack_require__(/*! raw-loader!./capacity.component.html */ "./node_modules/raw-loader/index.js!./src/app/capacity/capacity.component.html"),
            styles: [__webpack_require__(/*! ./capacity.component.scss */ "./src/app/capacity/capacity.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_capacity_service__WEBPACK_IMPORTED_MODULE_8__["CapacityService"], _users_users_service__WEBPACK_IMPORTED_MODULE_6__["UserService"], _login_login_service__WEBPACK_IMPORTED_MODULE_5__["LoginService"]])
    ], CapacityComponent);
    return CapacityComponent;
}());



/***/ }),

/***/ "./src/app/capacity/capacity.module.ts":
/*!*********************************************!*\
  !*** ./src/app/capacity/capacity.module.ts ***!
  \*********************************************/
/*! exports provided: CapacityModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CapacityModule", function() { return CapacityModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ag-grid-angular */ "./node_modules/ag-grid-angular/main.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _capacity_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./capacity.component */ "./src/app/capacity/capacity.component.ts");





var CapacityModule = /** @class */ (function () {
    function CapacityModule() {
    }
    CapacityModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__["SharedModule"],
                ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__["AgGridModule"].withComponents([])
            ],
            declarations: [_capacity_component__WEBPACK_IMPORTED_MODULE_4__["CapacityComponent"]]
        })
    ], CapacityModule);
    return CapacityModule;
}());



/***/ }),

/***/ "./src/app/capacity/capacity.service.ts":
/*!**********************************************!*\
  !*** ./src/app/capacity/capacity.service.ts ***!
  \**********************************************/
/*! exports provided: CapacityService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CapacityService", function() { return CapacityService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../login/login.service */ "./src/app/login/login.service.ts");




var CapacityService = /** @class */ (function () {
    function CapacityService(http, loginService) {
        this.http = http;
        this.loginService = loginService;
        this._baseUrl = './api/springs/';
    }
    CapacityService.prototype.getCapacities = function () {
        return this.http.get(this.baseUrl);
    };
    CapacityService.prototype.getCapacityById = function (id) {
        return this.http.get(this.baseUrl + "/" + id);
    };
    CapacityService.prototype.addCapacity = function (capacity) {
        return this.http.post(this.baseUrl, capacity);
    };
    CapacityService.prototype.deleteCapacityById = function (id) {
        return this.http.delete(this.baseUrl + "/" + id);
    };
    CapacityService.prototype.updateCapacity = function (capacity) {
        return this.http.put(this.baseUrl + "/" + capacity.id, capacity);
    };
    Object.defineProperty(CapacityService.prototype, "baseUrl", {
        get: function () {
            return this._baseUrl + this.loginService.currentSpringId + '/capacities';
        },
        enumerable: true,
        configurable: true
    });
    CapacityService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _login_login_service__WEBPACK_IMPORTED_MODULE_3__["LoginService"]])
    ], CapacityService);
    return CapacityService;
}());



/***/ }),

/***/ "./src/app/capacity/capacity.ts":
/*!**************************************!*\
  !*** ./src/app/capacity/capacity.ts ***!
  \**************************************/
/*! exports provided: Capacity */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Capacity", function() { return Capacity; });
var Capacity = /** @class */ (function () {
    function Capacity(values) {
        if (values === void 0) { values = {}; }
        Object.assign(this, values);
    }
    return Capacity;
}());



/***/ }),

/***/ "./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.component.ts":
/*!*****************************************************************************************!*\
  !*** ./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.component.ts ***!
  \*****************************************************************************************/
/*! exports provided: MatCheckboxGridComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MatCheckboxGridComponent", function() { return MatCheckboxGridComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var MatCheckboxGridComponent = /** @class */ (function () {
    function MatCheckboxGridComponent() {
        this.checked = false;
    }
    MatCheckboxGridComponent.prototype.agInit = function (params) {
        this.params = params;
        this.checked = this.params.value;
    };
    MatCheckboxGridComponent.prototype.refresh = function (params) {
        return false;
    };
    MatCheckboxGridComponent.prototype.afterGuiAttached = function (params) {
    };
    MatCheckboxGridComponent.prototype.onChange = function (checked) {
        this.checked = checked;
        this.params.node.setDataValue(this.params.colDef, this.checked);
        var key = this.params.data.key;
        this.params.context.componentParent.updateFromComponent(key, checked);
    };
    MatCheckboxGridComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'checkbox-cell',
            template: __webpack_require__(/*! raw-loader!./mat-checkbox-grid.html */ "./node_modules/raw-loader/index.js!./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.html")
        })
    ], MatCheckboxGridComponent);
    return MatCheckboxGridComponent;
}());



/***/ }),

/***/ "./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.module.ts":
/*!**************************************************************************************!*\
  !*** ./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.module.ts ***!
  \**************************************************************************************/
/*! exports provided: MatCheckboxGridModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MatCheckboxGridModule", function() { return MatCheckboxGridModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _mat_checkbox_grid_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./mat-checkbox-grid.component */ "./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.component.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");






var MatCheckboxGridModule = /** @class */ (function () {
    function MatCheckboxGridModule() {
    }
    MatCheckboxGridModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_4__["MatCheckboxModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_5__["FormsModule"]
            ],
            entryComponents: [_mat_checkbox_grid_component__WEBPACK_IMPORTED_MODULE_3__["MatCheckboxGridComponent"]],
            declarations: [_mat_checkbox_grid_component__WEBPACK_IMPORTED_MODULE_3__["MatCheckboxGridComponent"]]
        })
    ], MatCheckboxGridModule);
    return MatCheckboxGridModule;
}());



/***/ }),

/***/ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component.scss":
/*!***************************************************************************************************************!*\
  !*** ./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component.scss ***!
  \***************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".mat-mini-fab {\n  height: 22px;\n  width: 22px;\n  border-radius: 80%;\n  line-height: 22px;\n}\n.mat-mini-fab::ng-deep .mat-button-wrapper {\n  padding: 0 !important;\n  line-height: 22px;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvZ3JpZC1jdXN0b20tY29tcG9uZW50cy9tYXQtZWRpdC1idXR0b24tZ3JpZC1yZW5kZXIvQzpcXGdtYWNcXHN0c193b3Jrc3BhY2VzXFxob3l4aG95LXBsYW5uZXJcXGhveXhob3ktcGxhbm5lci1mcm9udGVuZC9zcmNcXGFwcFxcZ3JpZC1jdXN0b20tY29tcG9uZW50c1xcbWF0LWVkaXQtYnV0dG9uLWdyaWQtcmVuZGVyXFxtYXQtZWRpdC1idXR0b24tZ3JpZC1yZW5kZXIuY29tcG9uZW50LnNjc3MiLCJzcmMvYXBwL2dyaWQtY3VzdG9tLWNvbXBvbmVudHMvbWF0LWVkaXQtYnV0dG9uLWdyaWQtcmVuZGVyL21hdC1lZGl0LWJ1dHRvbi1ncmlkLXJlbmRlci5jb21wb25lbnQuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNJLFlBQUE7RUFDQSxXQUFBO0VBQ0Esa0JBQUE7RUFDQSxpQkFBQTtBQ0NKO0FEQUk7RUFDSSxxQkFBQTtFQUNBLGlCQUFBO0FDRVIiLCJmaWxlIjoic3JjL2FwcC9ncmlkLWN1c3RvbS1jb21wb25lbnRzL21hdC1lZGl0LWJ1dHRvbi1ncmlkLXJlbmRlci9tYXQtZWRpdC1idXR0b24tZ3JpZC1yZW5kZXIuY29tcG9uZW50LnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyIubWF0LW1pbmktZmFiIHtcclxuICAgIGhlaWdodDogMjJweDtcclxuICAgIHdpZHRoOiAyMnB4O1xyXG4gICAgYm9yZGVyLXJhZGl1czogODAlO1xyXG4gICAgbGluZS1oZWlnaHQ6IDIycHg7XHJcbiAgICAmOjpuZy1kZWVwIC5tYXQtYnV0dG9uLXdyYXBwZXIge1xyXG4gICAgICAgIHBhZGRpbmc6IDAgIWltcG9ydGFudDtcclxuICAgICAgICBsaW5lLWhlaWdodDogMjJweDtcclxuICAgIH0gICAgXHJcbn1cclxuIiwiLm1hdC1taW5pLWZhYiB7XG4gIGhlaWdodDogMjJweDtcbiAgd2lkdGg6IDIycHg7XG4gIGJvcmRlci1yYWRpdXM6IDgwJTtcbiAgbGluZS1oZWlnaHQ6IDIycHg7XG59XG4ubWF0LW1pbmktZmFiOjpuZy1kZWVwIC5tYXQtYnV0dG9uLXdyYXBwZXIge1xuICBwYWRkaW5nOiAwICFpbXBvcnRhbnQ7XG4gIGxpbmUtaGVpZ2h0OiAyMnB4O1xufSJdfQ== */"

/***/ }),

/***/ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component.ts":
/*!*************************************************************************************************************!*\
  !*** ./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component.ts ***!
  \*************************************************************************************************************/
/*! exports provided: MatEditButtonGridRenderComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MatEditButtonGridRenderComponent", function() { return MatEditButtonGridRenderComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var MatEditButtonGridRenderComponent = /** @class */ (function () {
    function MatEditButtonGridRenderComponent() {
    }
    MatEditButtonGridRenderComponent.prototype.agInit = function (params) {
        this.params = params;
    };
    MatEditButtonGridRenderComponent.prototype.edit = function () {
        var data = this.params.data;
        this.params.context.componentParent.editFromComponent(data);
    };
    MatEditButtonGridRenderComponent.prototype.refresh = function () {
        return false;
    };
    MatEditButtonGridRenderComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-mat-edit-button-grid-render',
            template: "<button mat-mini-fab color=\"primary\" (click)=\"edit()\">\n              <mat-icon  inline=\"true\">mode_edit</mat-icon>\n            </button>",
            styles: [__webpack_require__(/*! ./mat-edit-button-grid-render.component.scss */ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component.scss")]
        })
    ], MatEditButtonGridRenderComponent);
    return MatEditButtonGridRenderComponent;
}());



/***/ }),

/***/ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.module.ts":
/*!**********************************************************************************************************!*\
  !*** ./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.module.ts ***!
  \**********************************************************************************************************/
/*! exports provided: MatEditButtonGridRenderModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MatEditButtonGridRenderModule", function() { return MatEditButtonGridRenderModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./mat-edit-button-grid-render.component */ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");






var MatEditButtonGridRenderModule = /** @class */ (function () {
    function MatEditButtonGridRenderModule() {
    }
    MatEditButtonGridRenderModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatIconModule"]
            ],
            entryComponents: [_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__["MatEditButtonGridRenderComponent"]],
            declarations: [_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__["MatEditButtonGridRenderComponent"]]
        })
    ], MatEditButtonGridRenderModule);
    return MatEditButtonGridRenderModule;
}());



/***/ }),

/***/ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component.scss":
/*!*******************************************************************************************************************!*\
  !*** ./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component.scss ***!
  \*******************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".mat-mini-fab {\n  height: 22px;\n  width: 22px;\n  border-radius: 80%;\n  line-height: 22px;\n}\n.mat-mini-fab::ng-deep .mat-button-wrapper {\n  padding: 0 !important;\n  line-height: 22px;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvZ3JpZC1jdXN0b20tY29tcG9uZW50cy9tYXQtcmVtb3ZlLWJ1dHRvbi1ncmlkLXJlbmRlci9DOlxcZ21hY1xcc3RzX3dvcmtzcGFjZXNcXGhveXhob3ktcGxhbm5lclxcaG95eGhveS1wbGFubmVyLWZyb250ZW5kL3NyY1xcYXBwXFxncmlkLWN1c3RvbS1jb21wb25lbnRzXFxtYXQtcmVtb3ZlLWJ1dHRvbi1ncmlkLXJlbmRlclxcbWF0LXJlbW92ZS1idXR0b24tZ3JpZC1yZW5kZXIuY29tcG9uZW50LnNjc3MiLCJzcmMvYXBwL2dyaWQtY3VzdG9tLWNvbXBvbmVudHMvbWF0LXJlbW92ZS1idXR0b24tZ3JpZC1yZW5kZXIvbWF0LXJlbW92ZS1idXR0b24tZ3JpZC1yZW5kZXIuY29tcG9uZW50LnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDSSxZQUFBO0VBQ0EsV0FBQTtFQUNBLGtCQUFBO0VBQ0EsaUJBQUE7QUNDSjtBREFJO0VBQ0kscUJBQUE7RUFDQSxpQkFBQTtBQ0VSIiwiZmlsZSI6InNyYy9hcHAvZ3JpZC1jdXN0b20tY29tcG9uZW50cy9tYXQtcmVtb3ZlLWJ1dHRvbi1ncmlkLXJlbmRlci9tYXQtcmVtb3ZlLWJ1dHRvbi1ncmlkLXJlbmRlci5jb21wb25lbnQuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbIi5tYXQtbWluaS1mYWIge1xyXG4gICAgaGVpZ2h0OiAyMnB4O1xyXG4gICAgd2lkdGg6IDIycHg7XHJcbiAgICBib3JkZXItcmFkaXVzOiA4MCU7XHJcbiAgICBsaW5lLWhlaWdodDogMjJweDtcclxuICAgICY6Om5nLWRlZXAgLm1hdC1idXR0b24td3JhcHBlciB7XHJcbiAgICAgICAgcGFkZGluZzogMCAhaW1wb3J0YW50O1xyXG4gICAgICAgIGxpbmUtaGVpZ2h0OiAyMnB4O1xyXG4gICAgfSAgICBcclxufVxyXG4iLCIubWF0LW1pbmktZmFiIHtcbiAgaGVpZ2h0OiAyMnB4O1xuICB3aWR0aDogMjJweDtcbiAgYm9yZGVyLXJhZGl1czogODAlO1xuICBsaW5lLWhlaWdodDogMjJweDtcbn1cbi5tYXQtbWluaS1mYWI6Om5nLWRlZXAgLm1hdC1idXR0b24td3JhcHBlciB7XG4gIHBhZGRpbmc6IDAgIWltcG9ydGFudDtcbiAgbGluZS1oZWlnaHQ6IDIycHg7XG59Il19 */"

/***/ }),

/***/ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component.ts":
/*!*****************************************************************************************************************!*\
  !*** ./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component.ts ***!
  \*****************************************************************************************************************/
/*! exports provided: MatRemoveButtonGridRenderComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MatRemoveButtonGridRenderComponent", function() { return MatRemoveButtonGridRenderComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var MatRemoveButtonGridRenderComponent = /** @class */ (function () {
    function MatRemoveButtonGridRenderComponent() {
    }
    MatRemoveButtonGridRenderComponent.prototype.agInit = function (params) {
        this.params = params;
    };
    MatRemoveButtonGridRenderComponent.prototype.remove = function () {
        var data = this.params.data;
        this.params.context.componentParent.removeFromComponent(data);
    };
    MatRemoveButtonGridRenderComponent.prototype.refresh = function () {
        return false;
    };
    MatRemoveButtonGridRenderComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-mat-remove-button-grid-render',
            template: "<button mat-mini-fab color=\"primary\" (click)=\"remove()\">\n              <mat-icon  inline=\"true\">remove</mat-icon>\n            </button>",
            styles: [__webpack_require__(/*! ./mat-remove-button-grid-render.component.scss */ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component.scss")]
        })
    ], MatRemoveButtonGridRenderComponent);
    return MatRemoveButtonGridRenderComponent;
}());



/***/ }),

/***/ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.module.ts":
/*!**************************************************************************************************************!*\
  !*** ./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.module.ts ***!
  \**************************************************************************************************************/
/*! exports provided: MatRemoveButtonGridRenderModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MatRemoveButtonGridRenderModule", function() { return MatRemoveButtonGridRenderModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./mat-remove-button-grid-render.component */ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");






var MatRemoveButtonGridRenderModule = /** @class */ (function () {
    function MatRemoveButtonGridRenderModule() {
    }
    MatRemoveButtonGridRenderModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatIconModule"]
            ],
            entryComponents: [_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__["MatRemoveButtonGridRenderComponent"]],
            declarations: [_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__["MatRemoveButtonGridRenderComponent"]]
        })
    ], MatRemoveButtonGridRenderModule);
    return MatRemoveButtonGridRenderModule;
}());



/***/ }),

/***/ "./src/app/login/login.component.scss":
/*!********************************************!*\
  !*** ./src/app/login/login.component.scss ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".login-content {\n  margin: 0;\n  height: 100%;\n  width: 100%;\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  font-family: Roboto, sans-serif;\n  background: url('login-background.jpg') no-repeat center center fixed;\n  background-size: cover;\n}\n\nmat-card {\n  max-width: 40%;\n  margin: 1em auto;\n  line-height: 100%;\n}\n\nlogin-form {\n  min-width: 150px;\n  max-width: 500px;\n  width: 100%;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbG9naW4vQzpcXGdtYWNcXHN0c193b3Jrc3BhY2VzXFxob3l4aG95LXBsYW5uZXJcXGhveXhob3ktcGxhbm5lci1mcm9udGVuZC9zcmNcXGFwcFxcbG9naW5cXGxvZ2luLmNvbXBvbmVudC5zY3NzIiwic3JjL2FwcC9sb2dpbi9sb2dpbi5jb21wb25lbnQuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNFLFNBQUE7RUFDQSxZQUFBO0VBQ0EsV0FBQTtFQUNBLGFBQUE7RUFDQSx1QkFBQTtFQUNBLG1CQUFBO0VBQ0EsK0JBQUE7RUFDQSxxRUFBQTtFQUlBLHNCQUFBO0FDQ0Y7O0FERUE7RUFDRSxjQUFBO0VBQ0EsZ0JBQUE7RUFDQSxpQkFBQTtBQ0NGOztBRElBO0VBQ0UsZ0JBQUE7RUFDQSxnQkFBQTtFQUNBLFdBQUE7QUNERiIsImZpbGUiOiJzcmMvYXBwL2xvZ2luL2xvZ2luLmNvbXBvbmVudC5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmxvZ2luLWNvbnRlbnQge1xyXG4gIG1hcmdpbjogMDtcclxuICBoZWlnaHQ6IDEwMCU7XHJcbiAgd2lkdGg6IDEwMCU7ICBcclxuICBkaXNwbGF5OiBmbGV4O1xyXG4gIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gIGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcbiAgZm9udC1mYW1pbHk6IFJvYm90bywgc2Fucy1zZXJpZjtcclxuICBiYWNrZ3JvdW5kOiB1cmwoLi4vLi4vYXNzZXRzL2xvZ2luLWJhY2tncm91bmQuanBnKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlciBmaXhlZDsgXHJcbiAgLXdlYmtpdC1iYWNrZ3JvdW5kLXNpemU6IGNvdmVyO1xyXG4gIC1tb3otYmFja2dyb3VuZC1zaXplOiBjb3ZlcjtcclxuICAtby1iYWNrZ3JvdW5kLXNpemU6IGNvdmVyO1xyXG4gIGJhY2tncm91bmQtc2l6ZTogY292ZXI7XHJcbn1cclxuXHJcbm1hdC1jYXJkIHtcclxuICBtYXgtd2lkdGg6IDQwJTtcclxuICBtYXJnaW46IDFlbSBhdXRvO1xyXG4gIGxpbmUtaGVpZ2h0IDogMTAwJTtcclxuLy8gIHBhZGRpbmc6IDIwcHggNDBweDtcclxuLy8gIHRleHQtYWxpZ246IGNlbnRlcjtcclxufVxyXG5cclxubG9naW4tZm9ybSB7XHJcbiAgbWluLXdpZHRoOiAxNTBweDtcclxuICBtYXgtd2lkdGg6IDUwMHB4O1xyXG4gIHdpZHRoOiAxMDAlO1xyXG59XHJcbiIsIi5sb2dpbi1jb250ZW50IHtcbiAgbWFyZ2luOiAwO1xuICBoZWlnaHQ6IDEwMCU7XG4gIHdpZHRoOiAxMDAlO1xuICBkaXNwbGF5OiBmbGV4O1xuICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcbiAgYWxpZ24taXRlbXM6IGNlbnRlcjtcbiAgZm9udC1mYW1pbHk6IFJvYm90bywgc2Fucy1zZXJpZjtcbiAgYmFja2dyb3VuZDogdXJsKC4uLy4uL2Fzc2V0cy9sb2dpbi1iYWNrZ3JvdW5kLmpwZykgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXIgZml4ZWQ7XG4gIC13ZWJraXQtYmFja2dyb3VuZC1zaXplOiBjb3ZlcjtcbiAgLW1vei1iYWNrZ3JvdW5kLXNpemU6IGNvdmVyO1xuICAtby1iYWNrZ3JvdW5kLXNpemU6IGNvdmVyO1xuICBiYWNrZ3JvdW5kLXNpemU6IGNvdmVyO1xufVxuXG5tYXQtY2FyZCB7XG4gIG1heC13aWR0aDogNDAlO1xuICBtYXJnaW46IDFlbSBhdXRvO1xuICBsaW5lLWhlaWdodDogMTAwJTtcbn1cblxubG9naW4tZm9ybSB7XG4gIG1pbi13aWR0aDogMTUwcHg7XG4gIG1heC13aWR0aDogNTAwcHg7XG4gIHdpZHRoOiAxMDAlO1xufSJdfQ== */"

/***/ }),

/***/ "./src/app/login/login.component.ts":
/*!******************************************!*\
  !*** ./src/app/login/login.component.ts ***!
  \******************************************/
/*! exports provided: LoginComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginComponent", function() { return LoginComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _login_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./login.service */ "./src/app/login/login.service.ts");




var LoginComponent = /** @class */ (function () {
    function LoginComponent(loginService, router) {
        this.loginService = loginService;
        this.router = router;
        this.username = "";
        this.password = "";
        this.errorMessage = "";
    }
    LoginComponent.prototype.login = function () {
        var _this = this;
        this.loginService.loginWithUsername(this.username, this.password).subscribe(function (loginUser) {
            _this.loginService.currentUser = loginUser;
            _this.router.navigate(['']);
        }, function (error) {
            _this.errorMessage = error.status == 404 ? "Invalid user name or password" : "Known Error";
            console.log(error);
            _this.router.navigate(['/login']);
        });
    };
    LoginComponent.prototype.logout = function () {
        this.loginService.signOut();
    };
    LoginComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-login',
            template: __webpack_require__(/*! raw-loader!./login.component.html */ "./node_modules/raw-loader/index.js!./src/app/login/login.component.html"),
            styles: [__webpack_require__(/*! ./login.component.scss */ "./src/app/login/login.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_login_service__WEBPACK_IMPORTED_MODULE_3__["LoginService"], _angular_router__WEBPACK_IMPORTED_MODULE_2__["Router"]])
    ], LoginComponent);
    return LoginComponent;
}());



/***/ }),

/***/ "./src/app/login/login.service.ts":
/*!****************************************!*\
  !*** ./src/app/login/login.service.ts ***!
  \****************************************/
/*! exports provided: LoginService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginService", function() { return LoginService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");



var LoginService = /** @class */ (function () {
    function LoginService(http) {
        this.http = http;
        this.userState = null;
        this.projectState = null;
        this.springState = null;
        //public loginUserObs: Observable<LoginUser[]>;
        this.baseUrl = './api/login';
    }
    Object.defineProperty(LoginService.prototype, "isUserAnonymousLoggedIn", {
        get: function () {
            return false;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(LoginService.prototype, "currentUserName", {
        get: function () {
            return this.userState !== null ? this.userState.username : 'Usuario Anónimo';
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(LoginService.prototype, "currentName", {
        get: function () {
            return this.userState !== null ? this.userState.name : 'Usuario Anónimo';
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(LoginService.prototype, "currentUser", {
        get: function () {
            return (this.userState !== null) ? this.userState : null;
        },
        set: function (user) {
            this.userState = user;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(LoginService.prototype, "currentProject", {
        get: function () {
            return (this.projectState !== null) ? this.projectState : null;
        },
        set: function (project) {
            this.projectState = project;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(LoginService.prototype, "currentProjectId", {
        get: function () {
            return (this.projectState !== null) ? this.projectState.id : NaN;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(LoginService.prototype, "currentProjectName", {
        get: function () {
            return (this.projectState !== null) ? this.projectState.code + ' - ' + this.projectState.name : 'Select a project';
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(LoginService.prototype, "currentSpring", {
        get: function () {
            return (this.springState !== null) ? this.springState : null;
        },
        set: function (spring) {
            this.springState = spring;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(LoginService.prototype, "currentSpringId", {
        get: function () {
            return (this.springState !== null) ? this.springState.id : NaN;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(LoginService.prototype, "currentSpringName", {
        get: function () {
            return '& ' + ((this.springState !== null) ? this.springState.code + ' - ' + this.springState.name : 'Select a spring');
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(LoginService.prototype, "isUserLoggedIn", {
        get: function () {
            //    if ((this.userState !== null) && (!this.isUserAnonymousLoggedIn)) {
            return (this.userState != null);
        },
        enumerable: true,
        configurable: true
    });
    //  public createUserWithEmail(email: string, password: string) :Promise<any> {
    //    return this.afAuth.auth.createUserWithEmailAndPassword(email, password);
    //  }
    LoginService.prototype.loginWithUsername = function (username, password) {
        return this.http.put(this.baseUrl + "/" + username, password);
    };
    LoginService.prototype.signOut = function () {
        this.userState = null;
        //     this.http.get(`${this.baseUrl}/login/singout/${this.userState.username}`);
    };
    LoginService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])(),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"]])
    ], LoginService);
    return LoginService;
}());



/***/ }),

/***/ "./src/app/projects/project.ts":
/*!*************************************!*\
  !*** ./src/app/projects/project.ts ***!
  \*************************************/
/*! exports provided: Project */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Project", function() { return Project; });
var Project = /** @class */ (function () {
    //    status: string = '';
    //    backlog: any;
    function Project(values) {
        if (values === void 0) { values = {}; }
        this.code = '';
        this.name = '';
        Object.assign(this, values);
    }
    return Project;
}());



/***/ }),

/***/ "./src/app/projects/projects.component.scss":
/*!**************************************************!*\
  !*** ./src/app/projects/projects.component.scss ***!
  \**************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3Byb2plY3RzL3Byb2plY3RzLmNvbXBvbmVudC5zY3NzIn0= */"

/***/ }),

/***/ "./src/app/projects/projects.component.ts":
/*!************************************************!*\
  !*** ./src/app/projects/projects.component.ts ***!
  \************************************************/
/*! exports provided: ProjectsComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProjectsComponent", function() { return ProjectsComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _project__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./project */ "./src/app/projects/project.ts");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _projects_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./projects.service */ "./src/app/projects/projects.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component */ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component.ts");
/* harmony import */ var _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component */ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component.ts");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../login/login.service */ "./src/app/login/login.service.ts");









var ProjectsComponent = /** @class */ (function () {
    function ProjectsComponent(projectsService, loginService, router) {
        this.projectsService = projectsService;
        this.loginService = loginService;
        this.router = router;
        this.project = new _project__WEBPACK_IMPORTED_MODULE_2__["Project"]();
        this.errorMessage = "";
        this.isEditMode = false;
        this.context = { componentParent: this };
        this.gridOptions = {};
        this.gridOptions.enableFilter = true;
        this.gridOptions.enableSorting = true;
        this.gridOptions.rowSelection = 'single';
        this.columnDefs = [
            { headerName: 'Id', field: 'id', hide: true },
            { headerName: 'Code', field: 'code', filter: 'text', width: 120 },
            { headerName: 'Name', field: 'name', filter: 'text', width: 250 },
            { headerName: 'Start Date', field: 'startDate', filter: 'date', width: 140, valueFormatter: this.dateFormatter },
            { headerName: 'Spring Days', field: 'springDays', type: "numericColumn", filter: 'number', width: 150 },
            { headerName: 'Status', field: 'status', filter: 'text', width: 120 },
            { headerName: '', cellRendererFramework: _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_6__["MatEditButtonGridRenderComponent"], width: 40 },
            { headerName: '', suppressFilter: true, cellRendererFramework: _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_7__["MatRemoveButtonGridRenderComponent"], width: 40 }
        ];
    }
    ProjectsComponent.prototype.ngOnInit = function () {
        this.populateProjects();
    };
    ProjectsComponent.prototype.refeshProjects = function () {
        this.populateProjects();
        this.initialMode();
    };
    ProjectsComponent.prototype.populateProjects = function () {
        var _this = this;
        this.projects.subscribe(function (projectList) { return _this.rowData = projectList; }, function (error) { return _this.handleError(error); });
    };
    ProjectsComponent.prototype.dateFormatter = function (params) {
        return new Date(params.value).toLocaleDateString("es-ES", { timeZone: 'UTC', year: "numeric", month: "2-digit", day: "2-digit" });
    };
    Object.defineProperty(ProjectsComponent.prototype, "projects", {
        get: function () {
            return this.projectsService.getProjects();
        },
        enumerable: true,
        configurable: true
    });
    ProjectsComponent.prototype.getProjectById = function (id) {
        return this.projectsService.getProjectById(id);
    };
    ProjectsComponent.prototype.removeProject = function (id) {
        var _this = this;
        this.projectsService.deleteProjectById(id).subscribe(function (data) { return _this.refeshProjects(); }, function (error) { return console.log(error); });
    };
    ProjectsComponent.prototype.editProject = function (data) {
        this.project = new _project__WEBPACK_IMPORTED_MODULE_2__["Project"](data);
        this.errorMessage = "";
    };
    ProjectsComponent.prototype.addProject = function () {
        var _this = this;
        //  this.project.status = "OPEN";
        this.projectsService.addProject(this.project).subscribe(function (data) { return _this.refeshProjects(); }, function (error) { return _this.handleError(error); });
    };
    ProjectsComponent.prototype.handleError = function (res) {
        this.errorMessage = res.error.error_message;
        console.log(res);
    };
    ProjectsComponent.prototype.updateProject = function () {
        var _this = this;
        this.projectsService.updateProject(this.project).subscribe(function (data) { return _this.refeshProjects(); }, function (error) { return _this.handleError(error); });
    };
    ProjectsComponent.prototype.resetControls = function () {
        var _this = this;
        this.myForm.resetForm();
        Object.keys(this.myForm.controls).forEach(function (field) {
            var control = _this.myForm.control.get(field);
            control.markAsUntouched();
        });
    };
    // Call from MatRemoveButtonGridRenderComponent
    ProjectsComponent.prototype.removeFromComponent = function (project) {
        this.removeProject(project.id);
        this.initialMode();
    };
    // Call from MatEditButtonGridRenderComponent
    ProjectsComponent.prototype.editFromComponent = function (data) {
        this.isEditMode = true;
        this.editProject(data);
    };
    ProjectsComponent.prototype.cancelEditMode = function () {
        this.initialMode();
    };
    ProjectsComponent.prototype.initialMode = function () {
        this.project = new _project__WEBPACK_IMPORTED_MODULE_2__["Project"]();
        this.errorMessage = "";
        this.isEditMode = false;
        this.resetControls();
    };
    ProjectsComponent.prototype.onGridReady = function (params) {
        this.gridApi = params.api;
        this.gridColumnApi = params.columnApi;
        this.gridColumnApi.autoSizeColumns();
    };
    ProjectsComponent.prototype.onSelectionChanged = function () {
        var selectedRows = this.gridApi.getSelectedRows();
        var selectedRowAux;
        selectedRows.forEach(function (row, index) {
            //      if (index !== 0) {
            //        selectedRowsString += ", ";
            //      }
            selectedRowAux = row;
        });
        this.loginService.currentProject = selectedRowAux;
        if (this.loginService.currentSpring === null) {
            this.router.navigate(['/springs']);
        }
        //    this.projectSelected = selectedRowAux;
        //    document.querySelector("#selectedRows").innerHTML = selectedRowsString;
    };
    tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ViewChild"])('myForm', { static: false }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", _angular_forms__WEBPACK_IMPORTED_MODULE_3__["NgForm"])
    ], ProjectsComponent.prototype, "myForm", void 0);
    ProjectsComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-projects',
            template: __webpack_require__(/*! raw-loader!./projects.component.html */ "./node_modules/raw-loader/index.js!./src/app/projects/projects.component.html"),
            styles: [__webpack_require__(/*! ./projects.component.scss */ "./src/app/projects/projects.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_projects_service__WEBPACK_IMPORTED_MODULE_4__["ProjectsService"], _login_login_service__WEBPACK_IMPORTED_MODULE_8__["LoginService"], _angular_router__WEBPACK_IMPORTED_MODULE_5__["Router"]])
    ], ProjectsComponent);
    return ProjectsComponent;
}());



/***/ }),

/***/ "./src/app/projects/projects.module.ts":
/*!*********************************************!*\
  !*** ./src/app/projects/projects.module.ts ***!
  \*********************************************/
/*! exports provided: ProjectsModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProjectsModule", function() { return ProjectsModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ag-grid-angular */ "./node_modules/ag-grid-angular/main.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _projects_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./projects.component */ "./src/app/projects/projects.component.ts");





var ProjectsModule = /** @class */ (function () {
    function ProjectsModule() {
    }
    ProjectsModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__["SharedModule"],
                ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__["AgGridModule"].withComponents([])
            ],
            declarations: [_projects_component__WEBPACK_IMPORTED_MODULE_4__["ProjectsComponent"]]
        })
    ], ProjectsModule);
    return ProjectsModule;
}());



/***/ }),

/***/ "./src/app/projects/projects.service.ts":
/*!**********************************************!*\
  !*** ./src/app/projects/projects.service.ts ***!
  \**********************************************/
/*! exports provided: ProjectsService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProjectsService", function() { return ProjectsService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");



var ProjectsService = /** @class */ (function () {
    function ProjectsService(http) {
        this.http = http;
        this.baseUrl = './api/projects';
    }
    ProjectsService.prototype.getProjects = function () {
        return this.http.get(this.baseUrl);
    };
    ProjectsService.prototype.getProjectById = function (id) {
        return this.http.get(this.baseUrl + "/" + id);
    };
    ProjectsService.prototype.addProject = function (project) {
        return this.http.post(this.baseUrl, project);
    };
    ProjectsService.prototype.deleteProjectById = function (id) {
        return this.http.delete(this.baseUrl + "/" + id);
    };
    ProjectsService.prototype.updateProject = function (project) {
        return this.http.put(this.baseUrl + "/" + project.id, project);
    };
    ProjectsService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"]])
    ], ProjectsService);
    return ProjectsService;
}());



/***/ }),

/***/ "./src/app/shared/date.model.ts":
/*!**************************************!*\
  !*** ./src/app/shared/date.model.ts ***!
  \**************************************/
/*! exports provided: DateModel, Day_of_week */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DateModel", function() { return DateModel; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Day_of_week", function() { return Day_of_week; });
var DateModel = /** @class */ (function () {
    function DateModel(val) {
        this._value = val && val.length > 0 ? new Date(val) : new Date();
    }
    Object.defineProperty(DateModel.prototype, "value", {
        get: function () {
            return this.toString();
        },
        set: function (val) {
            this._value = val && val.length > 0 ? new Date(val) : new Date();
        },
        enumerable: true,
        configurable: true
    });
    DateModel.prototype.setDate = function (value) {
        this._value = value;
    };
    DateModel.prototype.addDays = function (days) {
        this._value.setDate(this._value.getDate() + days);
    };
    DateModel.prototype.setAddDays = function (dateM, days) {
        this._value = new Date(dateM._value);
        this.addDays(days);
    };
    DateModel.prototype.setAddWorkableDays = function (dateM, days) {
        this._value = new Date(dateM._value);
        var wdays = this._value.getDay() + days - 1;
        var workableDays = days - 1 + Math.trunc(wdays / 5) * 2;
        this.addDays(workableDays);
    };
    DateModel.prototype.greaterThan = function (otherValue) {
        return this._value > this.toDate(otherValue);
    };
    DateModel.prototype.greaterOrEqualsThan = function (otherValue) {
        return this._value >= this.toDate(otherValue);
    };
    DateModel.prototype.lessThan = function (otherValue) {
        return this._value < this.toDate(otherValue);
    };
    DateModel.prototype.equalsThan = function (otherValue) {
        return this._value == this.toDate(otherValue);
    };
    DateModel.prototype.toDate = function (value) {
        var valueToDate = null;
        if (typeof value === 'string') {
            valueToDate = new Date(value);
        }
        else if (value instanceof Date) {
            valueToDate = value;
        }
        else {
            valueToDate = value._value;
        }
        return valueToDate;
    };
    DateModel.prototype.toString = function () {
        return this._value.toISOString().split('T')[0];
    };
    return DateModel;
}());

var Day_of_week;
(function (Day_of_week) {
    Day_of_week[Day_of_week["Monday"] = 0] = "Monday";
    Day_of_week[Day_of_week["Tuesday"] = 1] = "Tuesday";
    Day_of_week[Day_of_week["Wednesday"] = 2] = "Wednesday";
    Day_of_week[Day_of_week["Thursday"] = 3] = "Thursday";
    Day_of_week[Day_of_week["Friday"] = 4] = "Friday";
    Day_of_week[Day_of_week["Saturday"] = 5] = "Saturday";
    Day_of_week[Day_of_week["Sunday"] = 6] = "Sunday";
})(Day_of_week || (Day_of_week = {}));


/***/ }),

/***/ "./src/app/shared/date.weekend.directive.ts":
/*!**************************************************!*\
  !*** ./src/app/shared/date.weekend.directive.ts ***!
  \**************************************************/
/*! exports provided: NoWeekendValidator */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "NoWeekendValidator", function() { return NoWeekendValidator; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _date_model__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./date.model */ "./src/app/shared/date.model.ts");




function noWeekend(c) {
    if (c.value == null)
        return null;
    var day = new Date(c.value).getDay();
    if (day == _date_model__WEBPACK_IMPORTED_MODULE_3__["Day_of_week"].Saturday || day == _date_model__WEBPACK_IMPORTED_MODULE_3__["Day_of_week"].Sunday) {
        return { weekend: true };
    }
    return null;
}
var NoWeekendValidator = /** @class */ (function () {
    function NoWeekendValidator() {
    }
    NoWeekendValidator = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["Directive"])({
            selector: '[no-weekend]',
            providers: [
                { provide: _angular_forms__WEBPACK_IMPORTED_MODULE_1__["NG_VALIDATORS"], multi: true, useValue: noWeekend }
            ]
        })
    ], NoWeekendValidator);
    return NoWeekendValidator;
}());



/***/ }),

/***/ "./src/app/shared/shared.module.ts":
/*!*****************************************!*\
  !*** ./src/app/shared/shared.module.ts ***!
  \*****************************************/
/*! exports provided: SharedModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SharedModule", function() { return SharedModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/platform-browser/animations */ "./node_modules/@angular/platform-browser/fesm5/animations.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _grid_custom_components_mat_checkbox_grid_mat_checkbox_grid_module__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.module */ "./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.module.ts");
/* harmony import */ var _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.module */ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.module.ts");
/* harmony import */ var _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_module__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.module */ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.module.ts");
/* harmony import */ var _date_weekend_directive__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./date.weekend.directive */ "./src/app/shared/date.weekend.directive.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");












var SharedModule = /** @class */ (function () {
    function SharedModule() {
    }
    SharedModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_10__["HttpClientModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_11__["BrowserModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatIconModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatFormFieldModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatTableModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatTooltipModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCheckboxModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSelectModule"],
                _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_4__["BrowserAnimationsModule"],
                _grid_custom_components_mat_checkbox_grid_mat_checkbox_grid_module__WEBPACK_IMPORTED_MODULE_6__["MatCheckboxGridModule"],
                _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_module__WEBPACK_IMPORTED_MODULE_7__["MatRemoveButtonGridRenderModule"],
                _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_module__WEBPACK_IMPORTED_MODULE_8__["MatEditButtonGridRenderModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatStepperModule"]
            ],
            exports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_10__["HttpClientModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_11__["BrowserModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatIconModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatFormFieldModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatTableModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatTooltipModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCheckboxModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSelectModule"],
                _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_4__["BrowserAnimationsModule"],
                _grid_custom_components_mat_checkbox_grid_mat_checkbox_grid_module__WEBPACK_IMPORTED_MODULE_6__["MatCheckboxGridModule"],
                _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_module__WEBPACK_IMPORTED_MODULE_7__["MatRemoveButtonGridRenderModule"],
                _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_module__WEBPACK_IMPORTED_MODULE_8__["MatEditButtonGridRenderModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatStepperModule"]
            ],
            declarations: [_date_weekend_directive__WEBPACK_IMPORTED_MODULE_9__["NoWeekendValidator"]]
        })
    ], SharedModule);
    return SharedModule;
}());



/***/ }),

/***/ "./src/app/spending/spending.component.scss":
/*!**************************************************!*\
  !*** ./src/app/spending/spending.component.scss ***!
  \**************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3NwZW5kaW5nL3NwZW5kaW5nLmNvbXBvbmVudC5zY3NzIn0= */"

/***/ }),

/***/ "./src/app/spending/spending.component.ts":
/*!************************************************!*\
  !*** ./src/app/spending/spending.component.ts ***!
  \************************************************/
/*! exports provided: SpendingComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SpendingComponent", function() { return SpendingComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../login/login.service */ "./src/app/login/login.service.ts");
/* harmony import */ var _backlog_features_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../backlog/features.service */ "./src/app/backlog/features.service.ts");
/* harmony import */ var _users_users_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../users/users.service */ "./src/app/users/users.service.ts");
/* harmony import */ var _spending_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./spending.service */ "./src/app/spending/spending.service.ts");
/* harmony import */ var _asignment_asignment__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../asignment/asignment */ "./src/app/asignment/asignment.ts");








var SpendingComponent = /** @class */ (function () {
    function SpendingComponent(spendingsService, featuresService, userService, loginService) {
        var _this = this;
        this.spendingsService = spendingsService;
        this.featuresService = featuresService;
        this.userService = userService;
        this.loginService = loginService;
        this.errorMessage = "";
        this.isEditMode = false;
        this.context = { componentParent: this };
        this.gridOptions = {};
        this.gridOptions.enableFilter = true;
        this.gridOptions.enableSorting = true;
        this.gridOptions.rowSelection = 'single';
        this.gridOptions.suppressRowClickSelection = false;
        this.gridOptions.enableColResize = true;
        this.gridOptions.enableCellChangeFlash = true;
        this.columnDefs = [
            { headerName: 'Id', field: 'id', hide: true },
            { headerName: 'F.Code', field: 'feature.code', filter: 'text', width: 100 },
            { headerName: 'Feature Title', field: 'feature.title', filter: 'text', width: 200 },
            { headerName: 'Estimated', field: 'feature.estimatedHours', filter: 'text', width: 100 },
            { headerName: 'Spending', field: 'spending', filter: 'text', width: 100 },
            { headerName: 'Asigned', field: 'user.name', filter: 'text', width: 150 }
        ];
        var _loop_1 = function (index) {
            this_1.columnDefs.push({ headerName: index + 1, valueGetter: function (params) { return params.data.spendingsInt[index]; }, valueSetter: function (params) { return _this.valueSetter(params, index); }, editable: true, filter: false, width: 50 });
        };
        var this_1 = this;
        for (var index = 0; index < 10; index++) {
            _loop_1(index);
        }
    }
    SpendingComponent.prototype.valueSetter = function (params, index) {
        var ret = false;
        this.errorMessage = "";
        if (params.newValue.length == 0) {
            params.data.spendingsInt[index] = null;
            ret = true;
        }
        else if (!isNaN(+params.newValue)) {
            params.data.spendingsInt[index] = +params.newValue;
            ret = true;
        }
        params.data.calculateSumSpending();
        if (params.data.feature.estimatedHours < params.data.spending) {
            this.errorMessage = "It has been consummed all the feature estimaded time, you must extend it";
            params.data.spendingsInt[index] = null;
            params.data.calculateSumSpending();
            params.data.spendingsInt[index] = params.data.feature.estimatedHours - params.data.spending;
            params.data.calculateSumSpending();
        }
        return ret;
    };
    SpendingComponent.prototype.ngOnInit = function () {
        this.populateAsignments();
    };
    SpendingComponent.prototype.refeshAsignments = function () {
        this.populateAsignments();
        this.initialMode();
    };
    SpendingComponent.prototype.populateAsignments = function () {
        var _this = this;
        //    asignmentList =>   this.rowData = asignmentList.map(asign => new Asignment(asign).addSpendings(this.loginService.currentSpring.springDays)),
        this.asignments.subscribe(function (asignmentList) { return _this.rowData = asignmentList.map(function (asign) { return new _asignment_asignment__WEBPACK_IMPORTED_MODULE_7__["Asignment"](asign); }); }, function (error) { return _this.handleError(error); });
    };
    SpendingComponent.prototype.dateFormatter = function (params) {
        return new Date(params.value).toLocaleDateString("es-ES", { timeZone: 'UTC', year: "numeric", month: "2-digit", day: "2-digit" });
    };
    Object.defineProperty(SpendingComponent.prototype, "asignments", {
        get: function () {
            return this.spendingsService.getSpendings();
        },
        enumerable: true,
        configurable: true
    });
    SpendingComponent.prototype.editAsignment = function (data) {
        this.errorMessage = "";
    };
    SpendingComponent.prototype.handleError = function (res) {
        this.errorMessage = res.error.error_message;
        console.log(res);
    };
    SpendingComponent.prototype.saveSpendings = function () {
        var _this = this;
        this.spendingsService.saveSpendings(this.rowData).subscribe(function (data) { return _this.refeshAsignments(); }, function (error) { return _this.handleError(error); });
    };
    SpendingComponent.prototype.resetControls = function () {
        var _this = this;
        this.myForm.resetForm();
        Object.keys(this.myForm.controls).forEach(function (field) {
            var control = _this.myForm.control.get(field);
            control.markAsUntouched();
        });
    };
    SpendingComponent.prototype.cancelEditMode = function () {
        this.initialMode();
    };
    SpendingComponent.prototype.initialMode = function () {
        this.errorMessage = "";
        this.isEditMode = false;
        this.resetControls();
    };
    SpendingComponent.prototype.onGridReady = function (params) {
        this.gridApi = params.api;
        this.gridColumnApi = params.columnApi;
        this.gridColumnApi.autoSizeColumns();
    };
    SpendingComponent.prototype.onCellEditingStarted = function (params) {
        console.log("cellEditingStarted");
        console.log(params);
    };
    SpendingComponent.prototype.compareFeatures = function (o1, o2) {
        return (o1 && o2) ? o1.id === o2.id : o1 === o2;
    };
    SpendingComponent.prototype.compareUsers = function (o1, o2) {
        return (o1 && o2) ? o1.username === o2.username : o1 === o2;
    };
    tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ViewChild"])('myForm', { static: false }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", _angular_forms__WEBPACK_IMPORTED_MODULE_2__["NgForm"])
    ], SpendingComponent.prototype, "myForm", void 0);
    SpendingComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-spending',
            template: __webpack_require__(/*! raw-loader!./spending.component.html */ "./node_modules/raw-loader/index.js!./src/app/spending/spending.component.html"),
            styles: [__webpack_require__(/*! ./spending.component.scss */ "./src/app/spending/spending.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_spending_service__WEBPACK_IMPORTED_MODULE_6__["SpendingService"], _backlog_features_service__WEBPACK_IMPORTED_MODULE_4__["FeaturesService"], _users_users_service__WEBPACK_IMPORTED_MODULE_5__["UserService"], _login_login_service__WEBPACK_IMPORTED_MODULE_3__["LoginService"]])
    ], SpendingComponent);
    return SpendingComponent;
}());



/***/ }),

/***/ "./src/app/spending/spending.module.ts":
/*!*********************************************!*\
  !*** ./src/app/spending/spending.module.ts ***!
  \*********************************************/
/*! exports provided: SpendingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SpendingModule", function() { return SpendingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ag-grid-angular */ "./node_modules/ag-grid-angular/main.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _spending_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./spending.component */ "./src/app/spending/spending.component.ts");





var SpendingModule = /** @class */ (function () {
    function SpendingModule() {
    }
    SpendingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__["SharedModule"],
                ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__["AgGridModule"].withComponents([])
            ],
            declarations: [_spending_component__WEBPACK_IMPORTED_MODULE_4__["SpendingComponent"]]
        })
    ], SpendingModule);
    return SpendingModule;
}());



/***/ }),

/***/ "./src/app/spending/spending.service.ts":
/*!**********************************************!*\
  !*** ./src/app/spending/spending.service.ts ***!
  \**********************************************/
/*! exports provided: SpendingService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SpendingService", function() { return SpendingService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../login/login.service */ "./src/app/login/login.service.ts");




var SpendingService = /** @class */ (function () {
    function SpendingService(http, loginService) {
        this.http = http;
        this.loginService = loginService;
        this._baseUrl = './api/springs/';
    }
    SpendingService.prototype.getSpendings = function () {
        return this.http.get(this.baseUrl);
    };
    SpendingService.prototype.saveSpendings = function (asignments) {
        return this.http.put(this.baseUrl + "/spendings", asignments);
    };
    Object.defineProperty(SpendingService.prototype, "baseUrl", {
        get: function () {
            return this._baseUrl + this.loginService.currentSpringId + '/asignments';
        },
        enumerable: true,
        configurable: true
    });
    SpendingService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _login_login_service__WEBPACK_IMPORTED_MODULE_3__["LoginService"]])
    ], SpendingService);
    return SpendingService;
}());



/***/ }),

/***/ "./src/app/springs/spirng.ts":
/*!***********************************!*\
  !*** ./src/app/springs/spirng.ts ***!
  \***********************************/
/*! exports provided: Spring */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Spring", function() { return Spring; });
/* harmony import */ var _shared_date_model__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../shared/date.model */ "./src/app/shared/date.model.ts");

var Spring = /** @class */ (function () {
    function Spring(values) {
        if (values === void 0) { values = {}; }
        this.code = '';
        this.name = '';
        Object.assign(this, values);
        this.startDateMd = new _shared_date_model__WEBPACK_IMPORTED_MODULE_0__["DateModel"](this.startDate);
        this.endDateMd = new _shared_date_model__WEBPACK_IMPORTED_MODULE_0__["DateModel"](this.endDate);
    }
    Spring.prototype.applyChanges = function () {
        this.startDate = this.startDateMd.value;
        this.endDate = this.endDateMd.value;
    };
    Spring.prototype.setEndDateMdCalculed = function () {
        this.endDateMd.setAddWorkableDays(this.startDateMd, this.springDays);
    };
    Spring.prototype.isNeededPropagate = function () {
        return !this.endDateMd.equalsThan(this.endDate);
    };
    return Spring;
}());



/***/ }),

/***/ "./src/app/springs/springs.component.scss":
/*!************************************************!*\
  !*** ./src/app/springs/springs.component.scss ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3NwcmluZ3Mvc3ByaW5ncy5jb21wb25lbnQuc2NzcyJ9 */"

/***/ }),

/***/ "./src/app/springs/springs.component.ts":
/*!**********************************************!*\
  !*** ./src/app/springs/springs.component.ts ***!
  \**********************************************/
/*! exports provided: SpringsComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SpringsComponent", function() { return SpringsComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component */ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component.ts");
/* harmony import */ var _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component */ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component.ts");
/* harmony import */ var _springs_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./springs.service */ "./src/app/springs/springs.service.ts");
/* harmony import */ var _spirng__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./spirng */ "./src/app/springs/spirng.ts");
/* harmony import */ var _shared_date_model__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../shared/date.model */ "./src/app/shared/date.model.ts");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../login/login.service */ "./src/app/login/login.service.ts");









var SpringsComponent = /** @class */ (function () {
    function SpringsComponent(springsService, loginService) {
        this.springsService = springsService;
        this.loginService = loginService;
        this.errorMessage = "";
        this.isEditMode = false;
        this.context = { componentParent: this };
        this.gridOptions = {};
        this.gridOptions.enableFilter = true;
        this.gridOptions.enableSorting = true;
        this.gridOptions.suppressRowClickSelection = false;
        this.gridOptions.enableColResize = true;
        this.gridOptions.enableCellChangeFlash = true;
        this.gridOptions.rowSelection = 'single';
        this.spring = this.springsService.setSpringDefaultValues();
        this.columnDefs = [
            { headerName: 'Key', field: 'key', hide: true },
            { headerName: 'Spring Code', field: 'code', filter: 'text', width: 150 },
            { headerName: 'Spring Name', field: 'name', filter: 'text', width: 150 },
            { headerName: 'Status', field: 'status', filter: 'text', width: 150 },
            { headerName: 'Spring Days', field: 'springDays', filter: 'number', width: 150 },
            { headerName: 'Start Date', field: 'startDate', filter: 'text', width: 150, valueFormatter: this.dateFormatter },
            { headerName: 'End Date', field: 'endDate', filter: 'text', width: 150, valueFormatter: this.dateFormatter },
            { headerName: '', cellRendererFramework: _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_3__["MatEditButtonGridRenderComponent"], width: 75 },
            { headerName: '', suppressFilter: true, cellRendererFramework: _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__["MatRemoveButtonGridRenderComponent"], width: 75 }
        ];
    }
    SpringsComponent.prototype.ngOnInit = function () {
        this.populateSprings();
    };
    SpringsComponent.prototype.refeshSprings = function () {
        this.populateSprings();
        this.initialMode();
    };
    SpringsComponent.prototype.populateSprings = function () {
        var _this = this;
        this.springs.subscribe(function (springList) {
            _this.rowData = springList ? springList.map(function (s) { return new _spirng__WEBPACK_IMPORTED_MODULE_6__["Spring"](s); }) : springList;
            _this.spring = _this.springsService.changeSpringDefaultValues(_this.spring, _this.rowData);
        }, function (error) { return _this.handleError(error); });
    };
    SpringsComponent.prototype.currencyFormatter = function (params) {
        return '£' + params.value;
    };
    SpringsComponent.prototype.dateFormatter = function (params) {
        return new Date(params.value).toLocaleDateString("es-ES", { timeZone: 'UTC', year: "numeric", month: "2-digit", day: "2-digit" });
    };
    Object.defineProperty(SpringsComponent.prototype, "springs", {
        get: function () {
            return this.springsService.getSprings();
        },
        enumerable: true,
        configurable: true
    });
    SpringsComponent.prototype.getSpringById = function (id) {
        return this.springsService.getSpringById(id);
    };
    SpringsComponent.prototype.removeSpring = function (id) {
        var _this = this;
        this.springsService.deleteSpringById(id).subscribe(function (data) { return _this.refeshSprings(); }, function (error) { return console.log(error); });
    };
    SpringsComponent.prototype.editSpring = function (data) {
        this.spring = new _spirng__WEBPACK_IMPORTED_MODULE_6__["Spring"](data);
        this.errorMessage = "";
    };
    SpringsComponent.prototype.addSpring = function () {
        var _this = this;
        this.spring.applyChanges();
        this.springsService.addSpring(this.spring).subscribe(function (data) { return _this.refeshSprings(); }, function (error) { return _this.handleError(error); });
    };
    SpringsComponent.prototype.updateSpring = function () {
        var _this = this;
        if (this.spring.isNeededPropagate()) {
            this.propagateSprings(this.spring);
        }
        this.spring.applyChanges();
        this.springsService.updateSpring(this.spring).subscribe(function (data) { return _this.refeshSprings(); }, function (error) { return _this.handleError(error); });
    };
    SpringsComponent.prototype.propagateSprings = function (spring) {
        var _this = this;
        var dateToPropagate = new _shared_date_model__WEBPACK_IMPORTED_MODULE_7__["DateModel"](this.loginService.currentProject.startDate.toString());
        dateToPropagate.setAddWorkableDays(dateToPropagate, 0);
        this.rowData.forEach(function (sprg) {
            if (sprg.code == spring.code) {
                sprg = spring;
            }
            var spr = _this.propagateAndupdateSpring(sprg, dateToPropagate);
            dateToPropagate = spr.endDateMd;
        });
    };
    SpringsComponent.prototype.propagateAndupdateSpring = function (sprg, endDateMd) {
        sprg.startDateMd.setAddWorkableDays(endDateMd, 2);
        sprg.setEndDateMdCalculed();
        sprg.applyChanges();
        this.springsService.updateSpring(sprg).subscribe();
        return sprg;
    };
    SpringsComponent.prototype.resetControls = function () {
        var _this = this;
        this.myForm.resetForm();
        Object.keys(this.myForm.controls).forEach(function (field) {
            var control = _this.myForm.control.get(field); // {2}
            control.markAsUntouched(); // {3}
        });
    };
    // Call from MatRemoveButtonGridRenderComponent
    SpringsComponent.prototype.removeFromComponent = function (spring) {
        this.removeSpring(spring.id);
        this.initialMode();
    };
    // Call from MatEditButtonGridRenderComponent
    SpringsComponent.prototype.editFromComponent = function (data) {
        this.isEditMode = true;
        this.editSpring(data);
    };
    SpringsComponent.prototype.cancelEditMode = function () {
        this.initialMode();
    };
    SpringsComponent.prototype.initialMode = function () {
        this.spring = new _spirng__WEBPACK_IMPORTED_MODULE_6__["Spring"]();
        this.errorMessage = "";
        this.isEditMode = false;
        this.resetControls();
    };
    SpringsComponent.prototype.onSelectionChanged = function () {
        var selectedRows = this.gridApi.getSelectedRows();
        var selectedRowAux;
        selectedRows.forEach(function (row, index) {
            selectedRowAux = row;
        });
        this.loginService.currentSpring = selectedRowAux;
    };
    SpringsComponent.prototype.onGridReady = function (params) {
        this.gridApi = params.api;
        this.gridColumnApi = params.columnApi;
        this.gridColumnApi.autoSizeColumns();
    };
    SpringsComponent.prototype.handleError = function (res) {
        this.errorMessage = res.error.error_message;
        console.log(res);
    };
    tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ViewChild"])('myForm', { static: false }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", _angular_forms__WEBPACK_IMPORTED_MODULE_2__["NgForm"])
    ], SpringsComponent.prototype, "myForm", void 0);
    SpringsComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-springs',
            template: __webpack_require__(/*! raw-loader!./springs.component.html */ "./node_modules/raw-loader/index.js!./src/app/springs/springs.component.html"),
            styles: [__webpack_require__(/*! ./springs.component.scss */ "./src/app/springs/springs.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_springs_service__WEBPACK_IMPORTED_MODULE_5__["SpringsService"], _login_login_service__WEBPACK_IMPORTED_MODULE_8__["LoginService"]])
    ], SpringsComponent);
    return SpringsComponent;
}());



/***/ }),

/***/ "./src/app/springs/springs.module.ts":
/*!*******************************************!*\
  !*** ./src/app/springs/springs.module.ts ***!
  \*******************************************/
/*! exports provided: SpringsModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SpringsModule", function() { return SpringsModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ag-grid-angular */ "./node_modules/ag-grid-angular/main.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _springs_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./springs.component */ "./src/app/springs/springs.component.ts");





var SpringsModule = /** @class */ (function () {
    function SpringsModule() {
    }
    SpringsModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__["SharedModule"],
                ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__["AgGridModule"].withComponents([])
            ],
            exports: [
                _springs_component__WEBPACK_IMPORTED_MODULE_4__["SpringsComponent"]
            ],
            declarations: [_springs_component__WEBPACK_IMPORTED_MODULE_4__["SpringsComponent"]]
        })
    ], SpringsModule);
    return SpringsModule;
}());



/***/ }),

/***/ "./src/app/springs/springs.service.ts":
/*!********************************************!*\
  !*** ./src/app/springs/springs.service.ts ***!
  \********************************************/
/*! exports provided: SpringsService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SpringsService", function() { return SpringsService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../login/login.service */ "./src/app/login/login.service.ts");
/* harmony import */ var _spirng__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./spirng */ "./src/app/springs/spirng.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");





var SpringsService = /** @class */ (function () {
    function SpringsService(http, loginService) {
        this.http = http;
        this.loginService = loginService;
        this.currentProjectId = "";
        this._baseUrl = './api/projects/';
    }
    SpringsService.prototype.getSprings = function () {
        return this.http.get(this.baseUrl);
        ;
    };
    SpringsService.prototype.getSpringById = function (id) {
        return this.http.get(this.baseUrl + "/" + id);
    };
    SpringsService.prototype.addSpring = function (spring) {
        return this.http.post(this.baseUrl, spring);
        ;
    };
    SpringsService.prototype.deleteSpringById = function (id) {
        return this.http.delete(this.baseUrl + "/" + id);
    };
    SpringsService.prototype.updateSpring = function (spring) {
        return this.http.put(this.baseUrl + "/" + spring.id, spring);
    };
    SpringsService.prototype.setSpringDefaultValues = function (spring) {
        if (spring === void 0) { spring = new _spirng__WEBPACK_IMPORTED_MODULE_3__["Spring"](); }
        spring.springDays = this.loginService.currentProject.springDays;
        spring.startDateMd.value = this.loginService.currentProject.startDate.toString();
        spring.setEndDateMdCalculed();
        return spring;
    };
    SpringsService.prototype.changeSpringDefaultValues = function (spring, rowData) {
        spring = this.setSpringDefaultValues(spring);
        if (rowData) {
            rowData.forEach(function (s) {
                if (spring.startDateMd.lessThan(s.endDateMd)) {
                    spring.startDateMd.setAddWorkableDays(s.endDateMd, 2);
                    spring.setEndDateMdCalculed();
                }
            });
        }
        return spring;
    };
    Object.defineProperty(SpringsService.prototype, "baseUrl", {
        get: function () {
            return this._baseUrl + this.loginService.currentProjectId + '/springs';
        },
        enumerable: true,
        configurable: true
    });
    SpringsService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"], _login_login_service__WEBPACK_IMPORTED_MODULE_2__["LoginService"]])
    ], SpringsService);
    return SpringsService;
}());



/***/ }),

/***/ "./src/app/users/user.ts":
/*!*******************************!*\
  !*** ./src/app/users/user.ts ***!
  \*******************************/
/*! exports provided: User */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "User", function() { return User; });
var User = /** @class */ (function () {
    function User(values) {
        if (values === void 0) { values = {}; }
        this.password = '';
        this.email = '';
        this.phone = '';
        this.mobile = '';
        Object.assign(this, values);
    }
    return User;
}());



/***/ }),

/***/ "./src/app/users/users.component.scss":
/*!********************************************!*\
  !*** ./src/app/users/users.component.scss ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3VzZXJzL3VzZXJzLmNvbXBvbmVudC5zY3NzIn0= */"

/***/ }),

/***/ "./src/app/users/users.component.ts":
/*!******************************************!*\
  !*** ./src/app/users/users.component.ts ***!
  \******************************************/
/*! exports provided: UsersComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UsersComponent", function() { return UsersComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _user__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./user */ "./src/app/users/user.ts");
/* harmony import */ var _users_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./users.service */ "./src/app/users/users.service.ts");
/* harmony import */ var _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component */ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component.ts");
/* harmony import */ var _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component */ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component.ts");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _login_login_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../login/login.service */ "./src/app/login/login.service.ts");








var UsersComponent = /** @class */ (function () {
    function UsersComponent(userService, loginService) {
        this.userService = userService;
        this.loginService = loginService;
        this.user = new _user__WEBPACK_IMPORTED_MODULE_2__["User"]();
        this.errorMessage = "";
        this.isEditMode = false;
        this.context = { componentParent: this };
        this.gridOptions = {};
        this.gridOptions.enableFilter = true;
        this.gridOptions.enableSorting = true;
        this.gridOptions.rowSelection = 'simple';
        this.gridOptions.suppressRowClickSelection = false;
        this.gridOptions.enableColResize = true;
        this.columnDefs = [
            { headerName: 'User', field: 'username', filter: 'text' },
            { headerName: 'Name', field: 'name', filter: 'text' },
            { headerName: 'Email', field: 'email', filter: 'text' },
            { headerName: 'Phone', field: 'phone', filter: 'text' },
            { headerName: 'Mobile', field: 'mobile', filter: 'text' },
            { headerName: '', cellRendererFramework: _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_5__["MatEditButtonGridRenderComponent"], width: 40 },
            { headerName: '', suppressFilter: true, cellRendererFramework: _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__["MatRemoveButtonGridRenderComponent"], width: 40 }
        ];
    }
    UsersComponent.prototype.ngOnInit = function () {
        this.populateUsers();
    };
    UsersComponent.prototype.refeshUsers = function () {
        this.populateUsers();
        this.initialMode();
        this.resetControls();
    };
    UsersComponent.prototype.populateUsers = function () {
        var _this = this;
        this.userService.getUsers().subscribe(function (userList) { return _this.rowData = userList; }, function (error) { return _this.handleError(error); });
    };
    UsersComponent.prototype.dateFormatter = function (params) {
        return new Date(params.value).toLocaleDateString("es-ES", { timeZone: 'UTC', year: "numeric", month: "2-digit", day: "2-digit" });
    };
    Object.defineProperty(UsersComponent.prototype, "users", {
        get: function () {
            return this.userService.getUsers();
        },
        enumerable: true,
        configurable: true
    });
    UsersComponent.prototype.getUserByUsername = function (username) {
        return this.userService.getUserByUsername(username);
    };
    UsersComponent.prototype.removeUser = function (username) {
        var _this = this;
        this.userService.deleteUserByUsername(username).subscribe(function (data) { return _this.refeshUsers(); }, function (error) { return console.log(error); });
    };
    UsersComponent.prototype.editUser = function (data) {
        this.user = new _user__WEBPACK_IMPORTED_MODULE_2__["User"](data);
        this.errorMessage = "";
    };
    UsersComponent.prototype.addUser = function () {
        var _this = this;
        //  this.project.status = "OPEN";
        this.userService.addUser(this.user).subscribe(function (data) { return _this.refeshUsers(); }, function (error) { return _this.handleError(error); });
    };
    UsersComponent.prototype.handleError = function (res) {
        this.errorMessage = res.error.error_message;
        console.log(res);
    };
    UsersComponent.prototype.updateUser = function () {
        var _this = this;
        this.userService.updateUser(this.user).subscribe(function (data) { return _this.refeshUsers(); }, function (error) { return _this.handleError(error); });
    };
    UsersComponent.prototype.resetControls = function () {
        var _this = this;
        this.myForm.resetForm();
        Object.keys(this.myForm.controls).forEach(function (field) {
            var control = _this.myForm.control.get(field);
            control.markAsUntouched();
        });
    };
    // Call from MatRemoveButtonGridRenderComponent
    UsersComponent.prototype.removeFromComponent = function (user) {
        this.removeUser(user.username);
        this.initialMode();
    };
    // Call from MatEditButtonGridRenderComponent
    UsersComponent.prototype.editFromComponent = function (data) {
        this.isEditMode = true;
        this.editUser(data);
    };
    UsersComponent.prototype.cancelEditMode = function () {
        this.initialMode();
    };
    UsersComponent.prototype.initialMode = function () {
        this.user = new _user__WEBPACK_IMPORTED_MODULE_2__["User"]();
        this.errorMessage = "";
        this.isEditMode = false;
        this.resetControls();
    };
    UsersComponent.prototype.onGridReady = function (params) {
        this.gridApi = params.api;
        this.gridColumnApi = params.columnApi;
        this.gridColumnApi.autoSizeColumns();
    };
    tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ViewChild"])('myForm', { static: false }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", _angular_forms__WEBPACK_IMPORTED_MODULE_6__["NgForm"])
    ], UsersComponent.prototype, "myForm", void 0);
    UsersComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-user',
            template: __webpack_require__(/*! raw-loader!./users.component.html */ "./node_modules/raw-loader/index.js!./src/app/users/users.component.html"),
            styles: [__webpack_require__(/*! ./users.component.scss */ "./src/app/users/users.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_users_service__WEBPACK_IMPORTED_MODULE_3__["UserService"], _login_login_service__WEBPACK_IMPORTED_MODULE_7__["LoginService"]])
    ], UsersComponent);
    return UsersComponent;
}());



/***/ }),

/***/ "./src/app/users/users.module.ts":
/*!***************************************!*\
  !*** ./src/app/users/users.module.ts ***!
  \***************************************/
/*! exports provided: UsersModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UsersModule", function() { return UsersModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ag-grid-angular */ "./node_modules/ag-grid-angular/main.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _users_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./users.component */ "./src/app/users/users.component.ts");





var UsersModule = /** @class */ (function () {
    function UsersModule() {
    }
    UsersModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__["SharedModule"],
                ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__["AgGridModule"].withComponents([])
            ],
            declarations: [_users_component__WEBPACK_IMPORTED_MODULE_4__["UsersComponent"]]
        })
    ], UsersModule);
    return UsersModule;
}());



/***/ }),

/***/ "./src/app/users/users.service.ts":
/*!****************************************!*\
  !*** ./src/app/users/users.service.ts ***!
  \****************************************/
/*! exports provided: UserService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserService", function() { return UserService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");



var UserService = /** @class */ (function () {
    function UserService(http) {
        this.http = http;
        this.baseUrl = './api/users';
    }
    UserService.prototype.getUsers = function () {
        return this.http.get(this.baseUrl);
    };
    UserService.prototype.getUserByUsername = function (username) {
        return this.http.get(this.baseUrl + "/" + username);
    };
    UserService.prototype.addUser = function (user) {
        return this.http.post(this.baseUrl, user);
    };
    UserService.prototype.deleteUserByUsername = function (username) {
        return this.http.delete(this.baseUrl + "/" + username);
    };
    UserService.prototype.updateUser = function (user) {
        return this.http.put(this.baseUrl + "/" + user.username, user);
    };
    UserService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"]])
    ], UserService);
    return UserService;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false,
    myEndpoint: "localhost:8080"
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.error(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\gmac\sts_workspaces\hoyxhoy-planner\hoyxhoy-planner-frontend\src\main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main-es5.js.map