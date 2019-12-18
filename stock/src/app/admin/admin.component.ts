import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  fileData: File = null;
  uploadResponse: any;
  fileStatus: boolean =false;

  constructor(private http: HttpClient, private adminService: AdminService) { }

  ngOnInit() {
  }

  fileProgress(fileInput: any) {
      this.fileData = <File>fileInput.target.files[0];
  }
   
  onSubmit() {
      const formData = new FormData();
      formData.append('file', this.fileData);
      this.adminService.uploadFile(formData).subscribe((response)=>
      {
         this.fileStatus=true;
          this.uploadResponse=response;
      })
  }
}
