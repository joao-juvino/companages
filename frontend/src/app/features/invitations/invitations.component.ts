import { Component, OnInit, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../core/api.service';
import { Invitation } from '../../core/models';
import { OrganizationContextService } from '../../core/organization-context.service';
import { ToastService } from '../../core/toast.service';

@Component({
  imports: [FormsModule, DatePipe],
  template: `
    <div class="page-head"><div><span class="eyebrow">ACCESS</span><h1>Invitations</h1><p>Invite colleagues safely and track every pending invitation.</p></div><button class="btn primary" [disabled]="!org()" (click)="dialog.set(true)">+ Invite person</button></div>
    <div class="info-box">Using the local email mode? Messages are captured in <a class="text-btn" href="http://localhost:8025" target="_blank" rel="noopener">Mailpit at localhost:8025</a>. With EmailJS enabled, they are delivered to the recipient.</div>
    <section class="card table-card"><div class="table">@for(i of items();track i.id){<div class="table-row"><span class="metric-icon blue">✉</span><div class="grow"><strong>{{i.email}}</strong><small>Expires {{i.expiresAt|date:'mediumDate'}}</small></div><span class="pill">{{i.role}}</span><span class="status" [class.off]="i.status!=='PENDING'">{{i.status}}</span>@if(i.status==='PENDING'){<button class="text-btn" (click)="resend(i)">Resend</button><button class="danger-link" (click)="cancel(i)">Cancel</button>}</div>}@empty{<div class="empty"><h2>No invitations</h2><p>Invite someone to collaborate in this organization.</p></div>}</div></section>
    @if(dialog()){<div class="modal-backdrop" (click)="dialog.set(false)"><section class="modal" (click)="$event.stopPropagation()"><div class="card-head"><div><span class="eyebrow">NEW INVITATION</span><h2>Invite a colleague</h2></div><button class="icon-btn" (click)="dialog.set(false)">×</button></div><label>Email<input type="email" [(ngModel)]="email" placeholder="person@company.com"></label><label>Access role<select [(ngModel)]="role"><option>MEMBER</option><option>MANAGER</option><option>ADMIN</option></select></label><p class="muted">The invitation is valid for seven days and can only be used once.</p><div class="actions"><button class="btn secondary" (click)="dialog.set(false)">Cancel</button><button class="btn primary" [disabled]="!email" (click)="send()">Send invitation</button></div></section></div>}
  `
})
export class InvitationsComponent implements OnInit {
  items = signal<Invitation[]>([]); dialog = signal(false); email = ''; role = 'MEMBER'; org = () => this.context.selectedId();
  constructor(private api: ApiService, private context: OrganizationContextService, private toast: ToastService) {}
  ngOnInit() { this.context.load().subscribe({ next: () => this.load(), error: () => this.toast.show('Could not load organizations') }); }
  load() { const id = this.org(); if (id) this.api.invitations(id).subscribe({ next: page => this.items.set(page.content), error: error => this.toast.show(error.error?.message || 'Could not load invitations') }); }
  send() { const id = this.org(); if (!id) return; this.api.invite(id, { email: this.email, role: this.role }).subscribe({ next: () => { this.email = ''; this.dialog.set(false); this.toast.show('Invitation sent. Open Mailpit at localhost:8025.'); this.load(); }, error: error => this.toast.show(error.error?.message || 'Could not send invitation') }); }
  resend(invitation: Invitation) { const id = this.org(); if (id) this.api.resendInvitation(id, invitation.id).subscribe({ next: () => this.toast.show('Invitation resent. Check localhost:8025.'), error: error => this.toast.show(error.error?.message || 'Could not resend invitation') }); }
  cancel(invitation: Invitation) { const id = this.org(); if (id && confirm(`Cancel invitation for ${invitation.email}?`)) this.api.cancelInvitation(id, invitation.id).subscribe({ next: () => { this.toast.show('Invitation cancelled'); this.load(); }, error: error => this.toast.show(error.error?.message || 'Could not cancel invitation') }); }
}
