import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ZoneService } from '../../../services/administration/zone.service';
import { Zone } from '../../../model/zone.model';
import { FormBuilder } from '@angular/forms';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';


import { GENERAL_PAGE_SIZE } from '../../../app.constants';


@Component({
  selector: 'app-list-zones',
  templateUrl: './list-zones.component.html',
  styleUrls: ['./list-zones.component.scss']
})
export class ListZonesComponent implements OnInit {
  zones: Zone[]
  message: string
  

  constructor(
    private zoneService:ZoneService,
    private router : Router, private confirmationModalService: ConfirmModalService
  ) { 

  }
  page: number = 0;
  pages: Array<number>;
  items: number = 0;
  setPage(i,event: any): void {
    // this.currentPage = event.page;
    event.preventDefault();
    this.page = i ;
    this.items = i*GENERAL_PAGE_SIZE;
    this.refreshData();
  }
  nextPage(event: any): void {
    event.preventDefault();
    if((this.page+1) < this.pages.length){
      this.page = this.page+1
      this.items = (this.page)*GENERAL_PAGE_SIZE;
      this.refreshData();
    }
  }
  prevPage(event: any): void {
    event.preventDefault();

    if((this.page-1) >= 0){
      this.page =this.page -1;
      this.items = (this.page)*GENERAL_PAGE_SIZE;
      this.refreshData();
    }
  }
  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.zoneService.retrieveAllZones(this.page,GENERAL_PAGE_SIZE).subscribe(
      response => {
        this.zones = response['content'];
        this.pages = new Array(response['totalPages']);
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف المقر ')
    .then((confirmed) => {
      if(confirmed){
        this.zoneService.deleteZone(id).subscribe (
          response => {
            this.refreshData();
          },
          error =>{
            console.log('oops',error)
            this.message = error.error.message  
          }
        )
      }
    })
  }

  onEdit(id) {
    this.router.navigate(['administration/zones',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/zone-data'])
  }


  @ViewChild('content') content: ElementRef;



















  print(): void {
    let printContents, popupWin;
    // printContents = document.getElementById('print-section').innerHTML;
    printContents =` <div class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>

    <label for="email"><b>Email</b></label>
    <input type="text" placeholder="Enter Email" name="email" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>

    <label for="psw-repeat"><b>Repeat Password</b></label>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" required>
    <hr>
  </div>`


    popupWin = window.open('', '_blank', 'top=0,left=0,height=100%,width=auto');
    popupWin.document.open();
    popupWin.document.write(`
      <html>
        <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <title>Print tab</title>
          <style>
                  body {
                    font-family: Arial, Helvetica, sans-serif;
                    background-color: black;
                  }

                  * {
                    box-sizing: border-box;
                  }

                  /* Add padding to containers */
                  .container {
                    padding: 16px;
                    background-color: white;
                  }

                  /* Full-width input fields */
                  input[type=text], input[type=password] {
                    width: 100%;
                    padding: 15px;
                    margin: 5px 0 22px 0;
                    display: inline-block;
                    border: none;
                    background: #f1f1f1;
                  }

                  input[type=text]:focus, input[type=password]:focus {
                    background-color: #ddd;
                    outline: none;
                  }

                  /* Overwrite default styles of hr */
                  hr {
                    border: 1px solid #f1f1f1;
                    margin-bottom: 25px;
                  }

                  /* Add a blue text color to links */
                  a {
                    color: dodgerblue;
                  }

                  
            </style>
        </head>
    <body onload="window.print();window.close()">${printContents}</body>
      </html>`
    );
    popupWin.document.close();
}
}
