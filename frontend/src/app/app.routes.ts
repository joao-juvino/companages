import { Routes } from '@angular/router';
import { authGuard } from './core/auth.guard';

export const routes: Routes = [
  { path: 'login', loadComponent: () => import('./features/auth/login.component').then(m => m.LoginComponent) },
  { path: 'register', loadComponent: () => import('./features/auth/register.component').then(m => m.RegisterComponent) },
  { path: 'forgot-password', loadComponent: () => import('./features/auth/password.component').then(m => m.PasswordComponent) },
  { path: 'reset-password', loadComponent: () => import('./features/auth/password.component').then(m => m.PasswordComponent) },
  { path: 'invitations/accept', loadComponent: () => import('./features/invitations/invitation-accept.component').then(m => m.InvitationAcceptComponent) },
  {
    path: '',
    canActivate: [authGuard],
    loadComponent: () => import('./layout/shell.component').then(m => m.ShellComponent),
    children: [
      { path: 'dashboard', loadComponent: () => import('./features/dashboard/dashboard.component').then(m => m.DashboardComponent) },
      { path: 'organizations', redirectTo: 'settings', pathMatch: 'full' },
      { path: 'organizations/:id', redirectTo: 'settings', pathMatch: 'full' },
      { path: 'members', loadComponent: () => import('./features/members/members.component').then(m => m.MembersComponent) },
      { path: 'teams', loadComponent: () => import('./features/teams/teams.component').then(m => m.TeamsComponent) },
      { path: 'positions', loadComponent: () => import('./features/positions/positions.component').then(m => m.PositionsComponent) },
      { path: 'organization-chart', loadComponent: () => import('./features/organization-chart/organization-chart.component').then(m => m.OrganizationChartComponent) },
      { path: 'invitations', loadComponent: () => import('./features/invitations/invitations.component').then(m => m.InvitationsComponent) },
      { path: 'activity', loadComponent: () => import('./features/activity/activity.component').then(m => m.ActivityComponent) },
      { path: 'notifications', loadComponent: () => import('./features/notifications/notifications.component').then(m => m.NotificationsComponent) },
      { path: 'settings', loadComponent: () => import('./features/settings/settings.component').then(m => m.SettingsComponent) },
      { path: '', pathMatch: 'full', redirectTo: 'dashboard' }
    ]
  },
  { path: '**', redirectTo: '' }
];
