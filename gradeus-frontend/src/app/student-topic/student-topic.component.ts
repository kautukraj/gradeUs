import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GlobalService } from '../_services/global.service';
import { StudentService } from '../_services/student.service';
import { Topic, User } from '../models/models';

@Component({
  selector: 'app-student-topic',
  templateUrl: './student-topic.component.html',
  styleUrls: ['./student-topic.component.scss']
})
export class StudentTopicComponent implements OnInit {

  topicId!: number;
  currentTopic!: null | Topic;

  members!: User[];
  membersForm!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public globalService: GlobalService,
    private studentService: StudentService
  ) {

  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('topicId');
    if (id) {
      this.topicId = <number>(<unknown>id);
    }
    else {
      this.router.navigate(['student-dashboard']);
      return;
    }

    this.studentService.getTopicById(this.topicId).subscribe(currentTopic => {
      this.currentTopic = currentTopic;
      this.studentService.getGroupMembersInClass(this.currentTopic.classObj).subscribe(
        members => {
          this.members = members;
          const formControls: any = {};
          for (let member of this.members) {
            formControls[member.id] = new FormControl(null);
          }
          this.membersForm = new FormGroup(formControls);
        }
      )
    });
  }

  submitScore(member: any) {
    // console.log(`Submitting score ${this.membersForm.get(member.id).value} for ${member.name}`);
    // Call API to submit score here
  }

}
