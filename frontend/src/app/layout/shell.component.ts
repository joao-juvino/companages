import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AuthService } from '../core/auth.service';
import { ApiService } from '../core/api.service';
import { OrganizationContextService } from '../core/organization-context.service';
import { ToastService } from '../core/toast.service';

@Component({
  imports: [RouterOutlet, RouterLink, RouterLinkActive, FormsModule],
  template: `
    <div class="shell">
      <aside [class.open]="menu()">
        <a class="brand" routerLink="/dashboard"><span class="logo">C</span><strong>Companages</strong></a>
        <nav>
          <span class="nav-label">WORKSPACE</span>
          <a routerLink="/dashboard" routerLinkActive="active">⌂ <span>Dashboard</span></a>
          <a routerLink="/members" routerLinkActive="active">♙ <span>Members</span></a>
          <a routerLink="/teams" routerLinkActive="active">◫ <span>Teams</span></a>
          <a routerLink="/organization-chart" routerLinkActive="active">⌘ <span>Organization chart</span></a>
          <a routerLink="/positions" routerLinkActive="active">◇ <span>Positions</span></a>
          <a routerLink="/invitations" routerLinkActive="active">✉ <span>Invitations</span></a>
          <a routerLink="/activity" routerLinkActive="active">↻ <span>Activity</span></a>
          <a routerLink="/notifications" routerLinkActive="active">♢ <span>Notifications</span>@if(unread()){<b class="nav-badge">{{unread()}}</b>}</a>
          <a routerLink="/settings" routerLinkActive="active">⚙ <span>Settings</span></a>
        </nav>
      </aside>
      <main class="workspace">
        <header>
          <button class="icon-btn menu-btn" (click)="menu.set(!menu())">☰</button>
          <div class="organization-picker">
            <select class="org-select" [ngModel]="context.selectedId()" (ngModelChange)="selectOrg($event)">
              @if(!context.organizations().length){<option [ngValue]="null">No organization</option>}
              @for(org of context.organizations();track org.id){<option [ngValue]="org.id">{{org.name}}</option>}
            </select>
            <button class="icon-btn add-org" title="Create organization" aria-label="Create organization" (click)="organizationDialog.set(true)">+</button>
          </div>
          <div class="user"><span class="avatar">{{initial()}}</span><div><strong>{{auth.user()?.name||'Account'}}</strong><small>{{context.selected()?.currentRole||auth.user()?.email}}</small></div><button class="text-btn" (click)="logout()">Log out</button></div>
        </header>
        <section class="page"><router-outlet/></section>
      </main>
    </div>
    @if(organizationDialog()){
      <div class="modal-backdrop" (click)="organizationDialog.set(false)">
        <section class="modal" (click)="$event.stopPropagation()">
          <div class="card-head"><div><span class="eyebrow">NEW WORKSPACE</span><h2>Create organization</h2></div><button class="icon-btn" (click)="organizationDialog.set(false)">×</button></div>
          <label>Name<input [(ngModel)]="organization.name" maxlength="120" autofocus></label>
          <label>Description<textarea [(ngModel)]="organization.description" rows="3" maxlength="500"></textarea></label>
          <div class="actions"><button class="btn secondary" (click)="organizationDialog.set(false)">Cancel</button><button class="btn primary" [disabled]="!organization.name.trim() || savingOrganization()" (click)="createOrganization()">{{savingOrganization()?'Creating…':'Create organization'}}</button></div>
        </section>
      </div>
    }
  `
})
export class ShellComponent implements OnInit {
  menu = signal(false);
  unread = signal(0);
  organizationDialog = signal(false);
  savingOrganization = signal(false);
  organization = { name: '', description: '' };
  constructor(public auth: AuthService, public context: OrganizationContextService, private api: ApiService, private router: Router, private toast: ToastService) {}
  ngOnInit() { if (!this.auth.user()) this.auth.loadMe().subscribe(); this.context.load().subscribe(); this.refreshUnread(); }
  initial() { return (this.auth.user()?.name || 'U')[0].toUpperCase(); }
  selectOrg(id: number | null) { this.context.select(id); this.router.navigate(['/dashboard']); }
  logout() { this.auth.logout(); this.router.navigate(['/login']); }
  createOrganization() {
    if (!this.organization.name.trim()) return;
    this.savingOrganization.set(true);
    this.api.createOrganization({ name: this.organization.name.trim(), description: this.organization.description.trim() }).subscribe({
      next: created => this.context.load().subscribe(() => { this.context.select(created.id); this.organization = { name: '', description: '' }; this.organizationDialog.set(false); this.savingOrganization.set(false); this.toast.show('Organization created'); this.router.navigate(['/dashboard']); }),
      error: error => { this.savingOrganization.set(false); this.toast.show(error.error?.message || 'Could not create organization'); }
    });
  }
  private refreshUnread() { this.api.notificationSummary().subscribe({ next: value => this.unread.set(value.unreadCount), error: () => this.unread.set(0) }); }
}
