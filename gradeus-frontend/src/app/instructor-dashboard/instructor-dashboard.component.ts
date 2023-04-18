import { Component, OnInit } from '@angular/core';
import { InstructorService } from '../_services/instructor.service';
import { Class } from '../models/models';
import { GlobalService } from '../_services/global.service';
import { MatDialog } from '@angular/material/dialog';
import { AddClassDialogComponent } from '../add-class-dialog/add-class-dialog.component';

@Component({
  selector: 'app-instructor-dashboard',
  templateUrl: './instructor-dashboard.component.html',
  styleUrls: ['./instructor-dashboard.component.scss']
})
export class InstructorDashboardComponent implements OnInit {

  classes: Class[] = [];

  constructor(
    private instructorService: InstructorService,
    public globalService: GlobalService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.instructorService.getAllClasses().subscribe(classes => {
      this.classes = classes;
      
      // console.log(this.classes);
    });
  }

  openAddClassDialog(): void {
    const dialogRef = this.dialog.open(AddClassDialogComponent, {
      width: '500px',
      data: {},
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        let classToSend: Class = result;
        classToSend.teacher = this.globalService.currentCredentials.id;

        this.instructorService.addClass(classToSend, classToSend.teacher).subscribe(
          (value: Class) => {
            this.classes.push(value);
          }
        );
      }
    });
  }


  viewDetails(course: Class): void {
    console.log('View details for course:', course);
  }

  deleteClass(course: Class): void {
    this.instructorService.deleteClass(course.classId).subscribe(
      (value: String) => {
        const index = this.classes.indexOf(course);
        if (index >= 0) {
          this.classes.splice(index, 1);
        }
      }
    );
  }
}
