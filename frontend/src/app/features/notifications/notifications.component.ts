import { Component, OnInit, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ApiService } from '../../core/api.service';
import { Notification } from '../../core/models';
import { ToastService } from '../../core/toast.service';

@Component({imports:[DatePipe,RouterLink],template:`<div class="page-head"><div><span class="eyebrow">INBOX</span><h1>Notifications</h1><p>Updates that need your attention.</p></div><button class="btn secondary" (click)="readAll()">Mark all as read</button></div><section class="card notification-list">@for(n of items();track n.id){<article [class.unread]="!n.read" (click)="read(n)"><span class="metric-icon violet">♢</span><div class="grow"><strong>{{n.title}}</strong><p>{{n.message}}</p><time>{{n.createdAt|date:'medium'}}</time></div>@if(n.link){<a class="text-btn" [routerLink]="n.link">Open</a>}</article>}@empty{<div class="empty"><h2>You're all caught up</h2><p>Invitations and important saved changes will appear here.</p></div>}</section>`})
export class NotificationsComponent implements OnInit {
  items = signal<Notification[]>([]);
  constructor(private api: ApiService, private toast: ToastService) {}
  ngOnInit() { this.load(); }
  load() { this.api.notifications().subscribe({ next: page => this.items.set(page.content), error: error => this.toast.show(error.error?.message || 'Could not load notifications') }); }
  read(notification: Notification) { if (notification.read) return; this.api.readNotification(notification.id).subscribe({ next: () => this.items.update(items => items.map(item => item.id === notification.id ? { ...item, read: true } : item)), error: error => this.toast.show(error.error?.message || 'Could not update notification') }); }
  readAll() { this.api.readAllNotifications().subscribe({ next: () => this.items.update(items => items.map(item => ({ ...item, read: true }))), error: error => this.toast.show(error.error?.message || 'Could not update notifications') }); }
}
