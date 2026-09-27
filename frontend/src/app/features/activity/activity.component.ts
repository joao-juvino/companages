import { Component, OnInit, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../core/api.service';
import { Activity } from '../../core/models';
import { OrganizationContextService } from '../../core/organization-context.service';
import { ToastService } from '../../core/toast.service';

@Component({
  imports: [DatePipe, FormsModule],
  template: `
    <div class="page-head"><div><span class="eyebrow">AUDIT TRAIL</span><h1>Activity</h1><p>A transparent history of important changes in this organization.</p></div></div>
    <div class="toolbar">
      <select [(ngModel)]="action" (change)="load()"><option value="">All actions</option><option value="ORGANIZATION_CREATED">Organization created</option><option value="MEMBER_ADDED">Member added</option><option value="MEMBER_UPDATED">Member updated</option><option value="TEAM_CREATED">Team created</option><option value="TEAM_UPDATED">Team updated</option><option value="JOB_POSITION_CHANGED">Position changed</option><option value="INVITATION_SENT">Invitation sent</option><option value="INVITATION_ACCEPTED">Invitation accepted</option></select>
      <input type="date" [(ngModel)]="from"><button class="btn secondary" (click)="load()">Apply filters</button>
    </div>
    <section class="card timeline">@for(a of items();track a.id){<article><span class="timeline-dot"></span><div><strong>{{a.actorName||'System'}} {{label(a.action)}}</strong><p>{{a.metadata||a.targetType||'record'}}</p><time>{{a.createdAt|date:'medium'}}</time></div></article>}@empty{<div class="empty"><h2>No activity yet</h2><p>Saved changes to members, teams, positions and invitations will appear here.</p></div>}</section>
  `
})
export class ActivityComponent implements OnInit {
  items = signal<Activity[]>([]);
  action = '';
  from = '';
  constructor(private api: ApiService, private context: OrganizationContextService, private toast: ToastService) {}
  ngOnInit() { this.context.load().subscribe({ next: () => this.load(), error: () => this.toast.show('Could not load organizations') }); }
  load() { const id = this.context.selectedId(); if (!id) return; const query = [this.action && `action=${this.action}`, this.from && `from=${new Date(this.from).toISOString()}`, 'size=100'].filter(Boolean).join('&'); this.api.activities(id, query).subscribe({ next: page => this.items.set(page.content), error: error => this.toast.show(error.error?.message || 'Could not load activity') }); }
  label(value: string) { return value.toLowerCase().replaceAll('_', ' '); }
}
