import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { InstructorDashboardComponent } from './instructor-dashboard/instructor-dashboard.component';
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';
import { InstructorClassComponent } from './instructor-class/instructor-class.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'instructor-dashboard', component: InstructorDashboardComponent },
  { path: 'instructor-class/:classId', component: InstructorClassComponent },
  { path: 'student-dashboard', component: StudentDashboardComponent },
];
