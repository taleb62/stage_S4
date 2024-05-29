import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RegisterComponent} from './auth/register/register.component';
import {LoginComponent} from './auth/login/login.component';
import {DefaultSidebarComponent} from './components/default-sidebar/default-sidebar.component';

import {AuthGuardService} from "./services/auth-guard.service";

import { AccueilComponent } from './pages/saisie/pages/accueil/accueil.component';
import {PaaComponent} from "./pages/saisie/pages/paa/paa/paa.component";
import {DirectoryComponent} from "./pages/saisie/pages/directory/directory.component";



const routes: Routes = [
  {path: 'sidebar', component: DefaultSidebarComponent,canActivate: [AuthGuardService]},
  // {path: 'sidebar-menu', component: SidebarMenuComponent},
  {path: 'login', component: LoginComponent,canActivate: [AuthGuardService]},
  { path: 'register', component : RegisterComponent,canActivate: [AuthGuardService] },

 // { path: 'traces', component : TracesComponent, },

  { path: "Accueil", component: AccueilComponent, canActivate: [AuthGuardService] },
  { path: "paa", component: PaaComponent, canActivate: [AuthGuardService] },
  { path: "directory", component: DirectoryComponent, canActivate: [AuthGuardService] },

  // {path: '', redirectTo: 'Accueil', pathMatch: 'full',canActivate: [AuthGuardService]},
  {path: '', redirectTo: 'login', pathMatch: 'full',},

//
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
