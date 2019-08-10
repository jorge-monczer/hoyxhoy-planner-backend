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

module.exports = "<!--The content below is only a placeholder and can be replaced.-->\n<div style=\"padding: 20px;\" >\n  <h1 style=\"color: blue\">grokonez.com</h1>\n  <h3>{{title}}</h3>\n  <nav>\n    <a routerLink=\"customer\" class=\"btn btn-primary active\" role=\"button\" routerLinkActive=\"active\">Customers</a>\n    <a routerLink=\"add\" class=\"btn btn-primary active\" role=\"button\" routerLinkActive=\"active\">Add</a>\n    <a routerLink=\"findbyage\" class=\"btn btn-primary active\" role=\"button\" routerLinkActive=\"active\">Search</a>\n    <a routerLink=\"projects\" class=\"btn btn-primary active\" role=\"button\" routerLinkActive=\"active\">Project</a>\n  </nav>\n  <router-outlet></router-outlet>\n</div>\n"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/create-customer/create-customer.component.html":
/*!******************************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/create-customer/create-customer.component.html ***!
  \******************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<h3>Create Customer</h3>\n<div [hidden]=\"submitted\" style=\"width: 300px;\">\n  <form (ngSubmit)=\"onSubmit()\">\n    <div class=\"form-group\">\n      <label for=\"name\">Name</label>\n      <input type=\"text\" class=\"form-control\" id=\"name\" required [(ngModel)]=\"customer.name\" name=\"name\">\n    </div>\n \n    <div class=\"form-group\">\n      <label for=\"age\">Age</label>\n      <input type=\"text\" class=\"form-control\" id=\"age\" required [(ngModel)]=\"customer.age\" name=\"age\">\n    </div>\n \n    <button type=\"submit\" class=\"btn btn-success\">Submit</button>\n  </form>\n</div>\n \n<div [hidden]=\"!submitted\">\n  <h4>You submitted successfully!</h4>\n  <button class=\"btn btn-success\" (click)=\"newCustomer()\">Add</button>\n</div>"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/customer-details/customer-details.component.html":
/*!********************************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/customer-details/customer-details.component.html ***!
  \********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div *ngIf=\"customer\">\n  <div>\n    <label>Name: </label> {{customer.name}}\n  </div>\n  <div>\n    <label>Age: </label> {{customer.age}}\n  </div>\n  <div>\n    <label>Active: </label> {{customer.active}}\n  </div>\n \n  <span class=\"button is-small btn-primary\" *ngIf='customer.active' (click)='updateActive(false)'>Inactive</span>\n \n  <span class=\"button is-small btn-primary\" *ngIf='!customer.active' (click)='updateActive(true)'>Active</span>\n \n  <span class=\"button is-small btn-danger\" (click)='deleteCustomer()'>Delete</span>\n \n  <hr />\n</div>"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/customers-list/customers-list.component.html":
/*!****************************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/customers-list/customers-list.component.html ***!
  \****************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<h3>Customers</h3>\n<div *ngFor=\"let customer of customers | async\" style=\"width: 300px;\">\n  <app-customer-details [customer]='customer'></app-customer-details>\n</div>\n \n<div>\n  <button type=\"button\" class=\"button btn-danger\" (click)='deleteCustomers()'>Delete All</button>\n</div>"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.html":
/*!***********************************************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.html ***!
  \***********************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-checkbox \r\n    [ngModel]=\"checked\" \r\n    (ngModelChange)=\"onChange($event)\">\r\n</mat-checkbox>"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/projects/projects.component.html":
/*!****************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/projects/projects.component.html ***!
  \****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-card>\n  <mat-card-header>\n    <mat-card-title >Project Detail</mat-card-title>\n  </mat-card-header>\n</mat-card>\n<mat-card>\n  <mat-card-header>\n    <mat-card-title>Project List</mat-card-title>\n  </mat-card-header>\n  <!--Selection: <span id=\"selectedRows\">{{ projectSelected | json }}</span-->\n  <ag-grid-angular \n    #agGrid style=\"width: 100%; height: 300px;\" \n    id=\"myGrid\"\n    class=\"ag-theme-fresh\" \n    [gridOptions]=\"gridOptions\" \n    [columnDefs]=\"columnDefs\" \n    [rowData]=\"rowData\" \n    [context]=\"context\" \n    [rowSelection]=\"rowSelection\"\n    (selectionChanged)=\"onSelectionChanged($event)\"\n    (gridReady)=\"onGridReady($event)\">\n  </ag-grid-angular>\n</mat-card>\n"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/search-customers/search-customers.component.html":
/*!********************************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/search-customers/search-customers.component.html ***!
  \********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<h3>Find By Age</h3>\n<div style=\"width: 300px;\">\n  <form (ngSubmit)=\"onSubmit()\">\n    <div class=\"form-group\">\n      <label for=\"lastname\">Age</label>\n      <input type=\"text\" class=\"form-control\" id=\"age\" required [(ngModel)]=\"age\" name=\"age\">\n    </div>\n \n    <div class=\"btn-group\">\n      <button type=\"submit\" class=\"btn btn-success\">Submit</button>\n    </div>\n  </form>\n</div>\n<ul>\n  <li *ngFor=\"let customer of customers\">\n    <h4>{{customer.id}} - {{customer.name}} {{customer.age}}</h4>\n  </li>\n</ul>"

/***/ }),

/***/ "./src/app/Customer.ts":
/*!*****************************!*\
  !*** ./src/app/Customer.ts ***!
  \*****************************/
/*! exports provided: Customer */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Customer", function() { return Customer; });
class Customer {
}


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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm2015/router.js");
/* harmony import */ var _customers_list_customers_list_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./customers-list/customers-list.component */ "./src/app/customers-list/customers-list.component.ts");
/* harmony import */ var _create_customer_create_customer_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./create-customer/create-customer.component */ "./src/app/create-customer/create-customer.component.ts");
/* harmony import */ var _search_customers_search_customers_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./search-customers/search-customers.component */ "./src/app/search-customers/search-customers.component.ts");
/* harmony import */ var _projects_projects_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./projects/projects.component */ "./src/app/projects/projects.component.ts");







const routes = [
    { path: '', redirectTo: 'customer', pathMatch: 'full' },
    { path: 'customer', component: _customers_list_customers_list_component__WEBPACK_IMPORTED_MODULE_3__["CustomersListComponent"] },
    { path: 'add', component: _create_customer_create_customer_component__WEBPACK_IMPORTED_MODULE_4__["CreateCustomerComponent"] },
    { path: 'findbyage', component: _search_customers_search_customers_component__WEBPACK_IMPORTED_MODULE_5__["SearchCustomersComponent"] },
    { path: 'projects', component: _projects_projects_component__WEBPACK_IMPORTED_MODULE_6__["ProjectsComponent"] },
];
let AppRoutingModule = class AppRoutingModule {
};
AppRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
        imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forRoot(routes)],
        exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
    })
], AppRoutingModule);



/***/ }),

/***/ "./src/app/app.component.scss":
/*!************************************!*\
  !*** ./src/app/app.component.scss ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "h1 {\n  color: blue;\n  font-size: 150%;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvQzpcXGdtYWNcXHN0c193b3Jrc3BhY2VzXFxob3l4aG95LXBsYW5uZXJcXGhveXhob3ktcGxhbm5lci1mcm9udGVuZC9zcmNcXGFwcFxcYXBwLmNvbXBvbmVudC5zY3NzIiwic3JjL2FwcC9hcHAuY29tcG9uZW50LnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDRSxXQUFBO0VBQ0EsZUFBQTtBQ0NGIiwiZmlsZSI6InNyYy9hcHAvYXBwLmNvbXBvbmVudC5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiaDEge1xyXG4gIGNvbG9yOiBibHVlO1xyXG4gIGZvbnQtc2l6ZTogMTUwJTtcclxufSIsImgxIHtcbiAgY29sb3I6IGJsdWU7XG4gIGZvbnQtc2l6ZTogMTUwJTtcbn0iXX0= */"

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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");


let AppComponent = class AppComponent {
    constructor() {
        this.title = 'Angular 8 Application';
    }
};
AppComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-root',
        template: __webpack_require__(/*! raw-loader!./app.component.html */ "./node_modules/raw-loader/index.js!./src/app/app.component.html"),
        styles: [__webpack_require__(/*! ./app.component.scss */ "./src/app/app.component.scss")]
    })
], AppComponent);



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
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm2015/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm2015/forms.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm2015/http.js");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _create_customer_create_customer_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./create-customer/create-customer.component */ "./src/app/create-customer/create-customer.component.ts");
/* harmony import */ var _customer_details_customer_details_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./customer-details/customer-details.component */ "./src/app/customer-details/customer-details.component.ts");
/* harmony import */ var _customers_list_customers_list_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./customers-list/customers-list.component */ "./src/app/customers-list/customers-list.component.ts");
/* harmony import */ var _search_customers_search_customers_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./search-customers/search-customers.component */ "./src/app/search-customers/search-customers.component.ts");
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _projects_projects_module__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./projects/projects.module */ "./src/app/projects/projects.module.ts");













let AppModule = class AppModule {
};
AppModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["NgModule"])({
        declarations: [
            _app_component__WEBPACK_IMPORTED_MODULE_6__["AppComponent"],
            _create_customer_create_customer_component__WEBPACK_IMPORTED_MODULE_7__["CreateCustomerComponent"],
            _customer_details_customer_details_component__WEBPACK_IMPORTED_MODULE_8__["CustomerDetailsComponent"],
            _customers_list_customers_list_component__WEBPACK_IMPORTED_MODULE_9__["CustomersListComponent"],
            _search_customers_search_customers_component__WEBPACK_IMPORTED_MODULE_10__["SearchCustomersComponent"]
        ],
        imports: [
            _shared_shared_module__WEBPACK_IMPORTED_MODULE_11__["SharedModule"],
            _projects_projects_module__WEBPACK_IMPORTED_MODULE_12__["ProjectsModule"],
            _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__["BrowserModule"],
            _app_routing_module__WEBPACK_IMPORTED_MODULE_5__["AppRoutingModule"],
            _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClientModule"]
        ],
        providers: [],
        bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_6__["AppComponent"]]
    })
], AppModule);



/***/ }),

/***/ "./src/app/create-customer/create-customer.component.scss":
/*!****************************************************************!*\
  !*** ./src/app/create-customer/create-customer.component.scss ***!
  \****************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NyZWF0ZS1jdXN0b21lci9jcmVhdGUtY3VzdG9tZXIuY29tcG9uZW50LnNjc3MifQ== */"

/***/ }),

/***/ "./src/app/create-customer/create-customer.component.ts":
/*!**************************************************************!*\
  !*** ./src/app/create-customer/create-customer.component.ts ***!
  \**************************************************************/
/*! exports provided: CreateCustomerComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateCustomerComponent", function() { return CreateCustomerComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _Customer__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../Customer */ "./src/app/Customer.ts");
/* harmony import */ var _customer_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../customer.service */ "./src/app/customer.service.ts");




let CreateCustomerComponent = class CreateCustomerComponent {
    constructor(customerService) {
        this.customerService = customerService;
        this.customer = new _Customer__WEBPACK_IMPORTED_MODULE_2__["Customer"]();
        this.submitted = false;
    }
    ngOnInit() {
    }
    newCustomer() {
        this.submitted = false;
        this.customer = new _Customer__WEBPACK_IMPORTED_MODULE_2__["Customer"]();
    }
    save() {
        this.customerService.createCustomer(this.customer)
            .subscribe(data => {
            console.log(data);
            this.submitted = true;
        }, error => console.log(error));
        this.customer = new _Customer__WEBPACK_IMPORTED_MODULE_2__["Customer"]();
    }
    onSubmit() {
        this.save();
    }
};
CreateCustomerComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-create-customer',
        template: __webpack_require__(/*! raw-loader!./create-customer.component.html */ "./node_modules/raw-loader/index.js!./src/app/create-customer/create-customer.component.html"),
        styles: [__webpack_require__(/*! ./create-customer.component.scss */ "./src/app/create-customer/create-customer.component.scss")]
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_customer_service__WEBPACK_IMPORTED_MODULE_3__["CustomerService"]])
], CreateCustomerComponent);



/***/ }),

/***/ "./src/app/customer-details/customer-details.component.scss":
/*!******************************************************************!*\
  !*** ./src/app/customer-details/customer-details.component.scss ***!
  \******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2N1c3RvbWVyLWRldGFpbHMvY3VzdG9tZXItZGV0YWlscy5jb21wb25lbnQuc2NzcyJ9 */"

/***/ }),

/***/ "./src/app/customer-details/customer-details.component.ts":
/*!****************************************************************!*\
  !*** ./src/app/customer-details/customer-details.component.ts ***!
  \****************************************************************/
/*! exports provided: CustomerDetailsComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CustomerDetailsComponent", function() { return CustomerDetailsComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _Customer__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../Customer */ "./src/app/Customer.ts");
/* harmony import */ var _customer_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../customer.service */ "./src/app/customer.service.ts");
/* harmony import */ var _customers_list_customers_list_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../customers-list/customers-list.component */ "./src/app/customers-list/customers-list.component.ts");





let CustomerDetailsComponent = class CustomerDetailsComponent {
    constructor(customerService, listComponent) {
        this.customerService = customerService;
        this.listComponent = listComponent;
    }
    ngOnInit() {
    }
    updateActive(isActive) {
        this.customerService.updateCustomer(this.customer.id, { name: this.customer.name, age: this.customer.age, active: isActive })
            .subscribe(data => {
            console.log(data);
            this.customer = data;
        }, error => console.log(error));
    }
    deleteCustomer() {
        this.customerService.deleteCustomer(this.customer.id)
            .subscribe(data => {
            console.log(data);
            this.listComponent.reloadData();
        }, error => console.log(error));
    }
};
tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])(),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", _Customer__WEBPACK_IMPORTED_MODULE_2__["Customer"])
], CustomerDetailsComponent.prototype, "customer", void 0);
CustomerDetailsComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-customer-details',
        template: __webpack_require__(/*! raw-loader!./customer-details.component.html */ "./node_modules/raw-loader/index.js!./src/app/customer-details/customer-details.component.html"),
        styles: [__webpack_require__(/*! ./customer-details.component.scss */ "./src/app/customer-details/customer-details.component.scss")]
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_customer_service__WEBPACK_IMPORTED_MODULE_3__["CustomerService"], _customers_list_customers_list_component__WEBPACK_IMPORTED_MODULE_4__["CustomersListComponent"]])
], CustomerDetailsComponent);



/***/ }),

/***/ "./src/app/customer.service.ts":
/*!*************************************!*\
  !*** ./src/app/customer.service.ts ***!
  \*************************************/
/*! exports provided: CustomerService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CustomerService", function() { return CustomerService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm2015/http.js");



let CustomerService = class CustomerService {
    //  private baseUrl = 'http://localhost:8080/api/customers';
    constructor(http) {
        this.http = http;
        this.baseUrl = './api/customers';
    }
    getCustomer(id) {
        return this.http.get(`${this.baseUrl}/${id}`);
    }
    createCustomer(customer) {
        return this.http.post(this.baseUrl, customer);
    }
    updateCustomer(id, value) {
        return this.http.put(`${this.baseUrl}/${id}`, value);
    }
    deleteCustomer(id) {
        return this.http.delete(`${this.baseUrl}/${id}`);
    }
    getCustomersList() {
        return this.http.get(this.baseUrl);
    }
    getCustomersByAge(age) {
        return this.http.get(`${this.baseUrl}/age/${age}`);
    }
    deleteAll() {
        return this.http.delete(this.baseUrl);
    }
};
CustomerService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
        providedIn: 'root'
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"]])
], CustomerService);



/***/ }),

/***/ "./src/app/customers-list/customers-list.component.scss":
/*!**************************************************************!*\
  !*** ./src/app/customers-list/customers-list.component.scss ***!
  \**************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2N1c3RvbWVycy1saXN0L2N1c3RvbWVycy1saXN0LmNvbXBvbmVudC5zY3NzIn0= */"

/***/ }),

/***/ "./src/app/customers-list/customers-list.component.ts":
/*!************************************************************!*\
  !*** ./src/app/customers-list/customers-list.component.ts ***!
  \************************************************************/
/*! exports provided: CustomersListComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CustomersListComponent", function() { return CustomersListComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _customer_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../customer.service */ "./src/app/customer.service.ts");



let CustomersListComponent = class CustomersListComponent {
    constructor(customerService) {
        this.customerService = customerService;
    }
    ngOnInit() {
        this.reloadData();
    }
    deleteCustomers() {
        this.customerService.deleteAll()
            .subscribe(data => {
            console.log(data);
            this.reloadData();
        }, error => console.log('ERROR: ' + error));
    }
    reloadData() {
        this.customers = this.customerService.getCustomersList();
    }
};
CustomersListComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-customers-list',
        template: __webpack_require__(/*! raw-loader!./customers-list.component.html */ "./node_modules/raw-loader/index.js!./src/app/customers-list/customers-list.component.html"),
        styles: [__webpack_require__(/*! ./customers-list.component.scss */ "./src/app/customers-list/customers-list.component.scss")]
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_customer_service__WEBPACK_IMPORTED_MODULE_2__["CustomerService"]])
], CustomersListComponent);



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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");


let MatCheckboxGridComponent = class MatCheckboxGridComponent {
    constructor() {
        this.checked = false;
    }
    agInit(params) {
        this.params = params;
        this.checked = this.params.value;
    }
    refresh(params) {
        return false;
    }
    afterGuiAttached(params) {
    }
    onChange(checked) {
        this.checked = checked;
        this.params.node.setDataValue(this.params.colDef, this.checked);
        let key = this.params.data.key;
        this.params.context.componentParent.updateFromComponent(key, checked);
    }
};
MatCheckboxGridComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'checkbox-cell',
        template: __webpack_require__(/*! raw-loader!./mat-checkbox-grid.html */ "./node_modules/raw-loader/index.js!./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.html")
    })
], MatCheckboxGridComponent);



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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm2015/common.js");
/* harmony import */ var _mat_checkbox_grid_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./mat-checkbox-grid.component */ "./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.component.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm2015/material.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm2015/forms.js");






let MatCheckboxGridModule = class MatCheckboxGridModule {
};
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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");


let MatEditButtonGridRenderComponent = class MatEditButtonGridRenderComponent {
    agInit(params) {
        this.params = params;
    }
    edit() {
        let data = this.params.data;
        this.params.context.componentParent.editFromComponent(data);
    }
    refresh() {
        return false;
    }
};
MatEditButtonGridRenderComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-mat-edit-button-grid-render',
        template: `<button mat-mini-fab color="primary" (click)="edit()">
              <mat-icon>mode_edit</mat-icon>
            </button>`
    })
], MatEditButtonGridRenderComponent);



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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm2015/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm2015/forms.js");
/* harmony import */ var _mat_edit_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./mat-edit-button-grid-render.component */ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.component.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm2015/material.js");






let MatEditButtonGridRenderModule = class MatEditButtonGridRenderModule {
};
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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");


let MatRemoveButtonGridRenderComponent = class MatRemoveButtonGridRenderComponent {
    agInit(params) {
        this.params = params;
    }
    remove() {
        let key = this.params.data.key;
        this.params.context.componentParent.removeFromComponent(key);
    }
    refresh() {
        return false;
    }
};
MatRemoveButtonGridRenderComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-mat-remove-button-grid-render',
        template: `<button mat-mini-fab color="primary" (click)="remove()">
              <mat-icon>remove</mat-icon>
            </button>`
    })
], MatRemoveButtonGridRenderComponent);



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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm2015/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm2015/forms.js");
/* harmony import */ var _mat_remove_button_grid_render_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./mat-remove-button-grid-render.component */ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.component.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm2015/material.js");






let MatRemoveButtonGridRenderModule = class MatRemoveButtonGridRenderModule {
};
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
class Project {
    constructor(values = {}) {
        this.code = '';
        this.name = '';
        this.status = '';
        Object.assign(this, values);
    }
}


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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _project__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./project */ "./src/app/projects/project.ts");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm2015/forms.js");
/* harmony import */ var _projects_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./projects.service */ "./src/app/projects/projects.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm2015/router.js");






let ProjectsComponent = class ProjectsComponent {
    constructor(projectsService, router) {
        this.projectsService = projectsService;
        this.router = router;
        this.project = new _project__WEBPACK_IMPORTED_MODULE_2__["Project"]();
        this.context = { componentParent: this };
        this.gridOptions = {};
        this.gridOptions.enableFilter = true;
        this.gridOptions.enableSorting = true;
        this.gridOptions.rowSelection = 'single';
        this.columnDefs = [
            { headerName: 'Id', field: 'id', hide: true },
            { headerName: 'Code', field: 'code', filter: 'text', width: 120 },
            { headerName: 'Name', field: 'name', filter: 'text', width: 250 },
            { headerName: 'Start Date', field: 'startDate', filter: 'date', width: 140 },
            { headerName: 'Spring Days', field: 'springDays', filter: 'number', width: 150, valueFormatter: this.currencyFormatter },
            { headerName: 'Status', field: 'status', filter: 'text', width: 120 }
        ];
    }
    ngOnInit() {
        this.rowData = [];
        this.projectSub = this.projects.subscribe(projectList => this.rowData = projectList);
    }
    currencyFormatter(params) {
        return '£' + params.value;
    }
    dateFormatter(params) {
        return '£' + params.value;
    }
    ngOnDestroy() {
        if (this.projectSub) {
            this.projectSub.unsubscribe();
        }
    }
    get projects() {
        return this.projectsService.getProjects();
    }
    getProjectByKey(id) {
        return this.projectsService.getProjectById(id);
    }
    removeProject(id) {
        this.projectsService.deleteProjectById(id);
    }
    editProject(data) {
        this.project = new _project__WEBPACK_IMPORTED_MODULE_2__["Project"](data);
    }
    addProject() {
        this.project.status = "OPEN";
        let addedItem = this.projectsService.addProject(this.project);
        this.initialMode();
        return addedItem;
    }
    updateProject() {
        this.projectsService.updateProject(this.project);
        this.initialMode();
    }
    resetControls() {
        Object.keys(this.myForm.controls).forEach(field => {
            const control = this.myForm.control.get(field);
            control.markAsUntouched();
        });
    }
    // Call from MatRemoveButtonGridRenderComponent
    removeFromComponent(id) {
        this.removeProject(id);
        this.initialMode();
    }
    // Call from MatEditButtonGridRenderComponent
    editFromComponent(data) {
        this.editProject(data);
    }
    cancelEditMode() {
        this.initialMode();
    }
    initialMode() {
        this.project = new _project__WEBPACK_IMPORTED_MODULE_2__["Project"]();
        //    this.resetControls();
    }
    isEditMode() {
        return this.project.id;
    }
    onGridReady(params) {
        this.gridApi = params.api;
        this.gridColumnApi = params.columnApi;
        this.gridColumnApi.autoSizeColumns();
    }
    onSelectionChanged() {
        var selectedRows = this.gridApi.getSelectedRows();
        var selectedRowAux;
        selectedRows.forEach(function (row, index) {
            //      if (index !== 0) {
            //        selectedRowsString += ", ";
            //      }
            selectedRowAux = row;
        });
        //    this.loginService.setProjectSelected(selectedRowAux);
        //    if (!this.loginService.currentSpring) {
        //      this.router.navigate(['/springs']);
        //    }
        //    this.projectSelected = selectedRowAux;
        //    document.querySelector("#selectedRows").innerHTML = selectedRowsString;
    }
};
tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ViewChild"])('myForm', null),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", _angular_forms__WEBPACK_IMPORTED_MODULE_3__["NgForm"])
], ProjectsComponent.prototype, "myForm", void 0);
ProjectsComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-projects',
        template: __webpack_require__(/*! raw-loader!./projects.component.html */ "./node_modules/raw-loader/index.js!./src/app/projects/projects.component.html"),
        styles: [__webpack_require__(/*! ./projects.component.scss */ "./src/app/projects/projects.component.scss")]
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_projects_service__WEBPACK_IMPORTED_MODULE_4__["ProjectsService"], _angular_router__WEBPACK_IMPORTED_MODULE_5__["Router"]])
], ProjectsComponent);



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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ag-grid-angular */ "./node_modules/ag-grid-angular/main.js");
/* harmony import */ var ag_grid_angular__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _projects_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./projects.component */ "./src/app/projects/projects.component.ts");





let ProjectsModule = class ProjectsModule {
};
ProjectsModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
        imports: [
            _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__["SharedModule"],
            ag_grid_angular__WEBPACK_IMPORTED_MODULE_2__["AgGridModule"].withComponents([])
        ],
        declarations: [_projects_component__WEBPACK_IMPORTED_MODULE_4__["ProjectsComponent"]]
    })
], ProjectsModule);



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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm2015/http.js");



let ProjectsService = class ProjectsService {
    //  private baseUrl = 'http://localhost:8080/api2/project';
    constructor(http) {
        this.http = http;
        this.baseUrl = './api/project';
        //this.projects = this.http.get(this.baseUrl+"s") as Observable<Project[]>;
    }
    getProjects() {
        //    return this.projects;
        return this.http.get(this.baseUrl + "s");
    }
    getProjectById(id) {
        return this.http.get(`${this.baseUrl}/${id}`);
    }
    addProject(project) {
        return this.http.post(this.baseUrl, project);
    }
    deleteProjectById(id) {
        return this.http.delete(`${this.baseUrl}/${id}`);
    }
    updateProject(project) {
        return this.http.put(`${this.baseUrl}/${project.id}`, project);
    }
};
ProjectsService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
        providedIn: 'root'
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"]])
], ProjectsService);



/***/ }),

/***/ "./src/app/search-customers/search-customers.component.scss":
/*!******************************************************************!*\
  !*** ./src/app/search-customers/search-customers.component.scss ***!
  \******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3NlYXJjaC1jdXN0b21lcnMvc2VhcmNoLWN1c3RvbWVycy5jb21wb25lbnQuc2NzcyJ9 */"

/***/ }),

/***/ "./src/app/search-customers/search-customers.component.ts":
/*!****************************************************************!*\
  !*** ./src/app/search-customers/search-customers.component.ts ***!
  \****************************************************************/
/*! exports provided: SearchCustomersComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SearchCustomersComponent", function() { return SearchCustomersComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _customer_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../customer.service */ "./src/app/customer.service.ts");



let SearchCustomersComponent = class SearchCustomersComponent {
    constructor(dataService) {
        this.dataService = dataService;
    }
    ngOnInit() {
        this.age = 0;
    }
    searchCustomers() {
        this.customers = [];
        this.dataService.getCustomersByAge(this.age)
            .subscribe(customers => this.customers = customers);
    }
    onSubmit() {
        this.searchCustomers();
    }
};
SearchCustomersComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-search-customers',
        template: __webpack_require__(/*! raw-loader!./search-customers.component.html */ "./node_modules/raw-loader/index.js!./src/app/search-customers/search-customers.component.html"),
        styles: [__webpack_require__(/*! ./search-customers.component.scss */ "./src/app/search-customers/search-customers.component.scss")]
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_customer_service__WEBPACK_IMPORTED_MODULE_2__["CustomerService"]])
], SearchCustomersComponent);



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
class DateModel {
    constructor(val) {
        this._value = val && val.length > 0 ? new Date(val) : new Date();
    }
    get value() {
        return this.toString();
    }
    set value(val) {
        this._value = val && val.length > 0 ? new Date(val) : new Date();
    }
    setDate(value) {
        this._value = value;
    }
    addDays(days) {
        this._value.setDate(this._value.getDate() + days);
    }
    setAddDays(dateM, days) {
        this._value = new Date(dateM._value);
        this.addDays(days);
    }
    setAddWorkableDays(dateM, days) {
        this._value = new Date(dateM._value);
        let wdays = this._value.getDay() + days - 1;
        let workableDays = days - 1 + Math.trunc(wdays / 5) * 2;
        this.addDays(workableDays);
    }
    greaterThan(otherValue) {
        return this._value > this.toDate(otherValue);
    }
    greaterOrEqualsThan(otherValue) {
        return this._value >= this.toDate(otherValue);
    }
    lessThan(otherValue) {
        return this._value < this.toDate(otherValue);
    }
    toDate(value) {
        let valueToDate = null;
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
    }
    toString() {
        return this._value.toISOString().split('T')[0];
    }
}
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
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm2015/forms.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _date_model__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./date.model */ "./src/app/shared/date.model.ts");




function noWeekend(c) {
    if (c.value == null)
        return null;
    let day = new Date(c.value).getDay();
    if (day == _date_model__WEBPACK_IMPORTED_MODULE_3__["Day_of_week"].Saturday || day == _date_model__WEBPACK_IMPORTED_MODULE_3__["Day_of_week"].Sunday) {
        return { weekend: true };
    }
    return null;
}
let NoWeekendValidator = class NoWeekendValidator {
};
NoWeekendValidator = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["Directive"])({
        selector: '[no-weekend]',
        providers: [
            { provide: _angular_forms__WEBPACK_IMPORTED_MODULE_1__["NG_VALIDATORS"], multi: true, useValue: noWeekend }
        ]
    })
], NoWeekendValidator);



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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm2015/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm2015/forms.js");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/platform-browser/animations */ "./node_modules/@angular/platform-browser/fesm2015/animations.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm2015/material.js");
/* harmony import */ var _grid_custom_components_mat_checkbox_grid_mat_checkbox_grid_module__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.module */ "./src/app/grid-custom-components/mat-checkbox-grid/mat-checkbox-grid.module.ts");
/* harmony import */ var _grid_custom_components_mat_remove_button_grid_render_mat_remove_button_grid_render_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.module */ "./src/app/grid-custom-components/mat-remove-button-grid-render/mat-remove-button-grid-render.module.ts");
/* harmony import */ var _grid_custom_components_mat_edit_button_grid_render_mat_edit_button_grid_render_module__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.module */ "./src/app/grid-custom-components/mat-edit-button-grid-render/mat-edit-button-grid-render.module.ts");
/* harmony import */ var _date_weekend_directive__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./date.weekend.directive */ "./src/app/shared/date.weekend.directive.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm2015/http.js");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm2015/platform-browser.js");












let SharedModule = class SharedModule {
};
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
const environment = {
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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm2015/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(err => console.error(err));


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
//# sourceMappingURL=main-es2015.js.map