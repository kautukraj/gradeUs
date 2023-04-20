import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { InstructorDashboardComponent } from './instructor-dashboard/instructor-dashboard.component';
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';
import { InstructorClassComponent } from './instructor-class/instructor-class.component';
import { ClassGroupComponent } from './class-group/class-group.component';
import { StudentClassComponent } from './student-class/student-class.component';
import { StudentTopicComponent } from './student-topic/student-topic.component';
import { InstructorTopicComponent } from './instructor-topic/instructor-topic.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'instructor-dashboard', component: InstructorDashboardComponent },
  { path: 'instructor-class/:classId', component: InstructorClassComponent },
  { path: 'instructor-topic/:topicId', component: InstructorTopicComponent },
  { path: 'class-group/:classGroupId', component: ClassGroupComponent },
  { path: 'student-dashboard', component: StudentDashboardComponent },
  { path: 'student-class/:classId', component: StudentClassComponent },
  { path: 'student-topic/:topicId', component: StudentTopicComponent },
];
