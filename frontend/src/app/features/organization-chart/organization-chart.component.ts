import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../core/api.service';
import { ChartNode } from '../../core/models';
import { OrganizationContextService } from '../../core/organization-context.service';
import { ToastService } from '../../core/toast.service';

@Component({
  imports: [FormsModule],
  template: `
    <div class="page-head">
      <div><span class="eyebrow">REPORTING LINES</span><h1>Organization chart</h1><p>Explore managers, teams and direct reports.</p></div>
      <button class="btn secondary" (click)="expandAll()">Expand all</button>
    </div>
    <div class="toolbar"><input [(ngModel)]="search" (keyup.enter)="load()" placeholder="Find a person"><button class="btn secondary" (click)="load()">Search & center</button></div>
    @if(loading()){
      <div class="state"><span class="spinner"></span>Building the chart…</div>
    } @else if(flat().length) {
      @if(!hasConnections()){
        <div class="info-box">No reporting lines are assigned yet. Edit a member and select their manager to connect the chart.</div>
      }
      <section class="chart-board">
        <div class="chart-list">
          @for(item of flat();track item.node.id){
            <article class="person-card chart-person" [class.child-node]="item.depth > 0" [id]="'member-'+item.node.id" [style.margin-left.px]="item.depth*42" (click)="selected.set(item.node)">
              @if(item.node.children.length){<button class="tree-toggle" (click)="$event.stopPropagation();toggle(item.node)">{{collapsed.has(item.node.id)?'+':'−'}}</button>}@else{<span class="tree-spacer"></span>}
              <span class="avatar soft">{{item.node.name[0]}}</span>
              <div class="grow"><strong>{{item.node.name}}</strong><small>{{item.node.position||'No position'}} · {{item.node.team||'No team'}}</small></div>
              <span class="pill">{{item.node.role}}</span>
            </article>
          }
        </div>
      </section>
    } @else {
      <div class="card empty"><h2>No hierarchy to display</h2><p>Add members and select their managers to build the chart.</p></div>
    }
    @if(selected();as person){
      <div class="modal-backdrop" (click)="selected.set(null)"><section class="modal person-detail" (click)="$event.stopPropagation()">
        <button class="icon-btn close" (click)="selected.set(null)">×</button><span class="avatar profile-avatar">{{person.name[0]}}</span><h2>{{person.name}}</h2><p>{{person.email||'No email provided'}}</p>
        <div class="detail-list"><div><small>POSITION</small><strong>{{person.position||'Not assigned'}}</strong></div><div><small>TEAM</small><strong>{{person.team||'Not assigned'}}</strong></div><div><small>ACCESS</small><strong>{{person.role}}</strong></div><div><small>DIRECT REPORTS</small><strong>{{person.children.length}}</strong></div></div>
        <button class="btn secondary full" (click)="center(person)">Center in chart</button>
      </section></div>
    }
  `
})
export class OrganizationChartComponent implements OnInit {
  nodes = signal<ChartNode[]>([]);
  selected = signal<ChartNode | null>(null);
  loading = signal(false);
  collapsed = new Set<number>();
  search = '';
  flat = () => this.flatten(this.nodes());
  hasConnections = () => this.flat().some(item => item.depth > 0);
  constructor(private api: ApiService, private context: OrganizationContextService, private toast: ToastService) {}
  ngOnInit() { this.context.load().subscribe({ next: () => this.load(), error: () => this.toast.show('Could not load organizations') }); }
  load() {
    const id = this.context.selectedId();
    if (!id) return;
    this.loading.set(true);
    this.api.chart(id, this.search).subscribe({
      next: value => { this.nodes.set(value); this.loading.set(false); if (this.search) setTimeout(() => { const first = this.flat().find(x => x.node.name.toLowerCase().includes(this.search.toLowerCase())); if (first) this.center(first.node); }); },
      error: error => { this.loading.set(false); this.toast.show(error.error?.message || 'Could not build organization chart'); }
    });
  }
  toggle(node: ChartNode) { this.collapsed.has(node.id) ? this.collapsed.delete(node.id) : this.collapsed.add(node.id); this.nodes.update(value => [...value]); }
  expandAll() { this.collapsed.clear(); this.nodes.update(value => [...value]); }
  center(node: ChartNode) { this.selected.set(null); document.getElementById(`member-${node.id}`)?.scrollIntoView({ behavior: 'smooth', block: 'center' }); }
  private flatten(nodes: ChartNode[], depth = 0): { node: ChartNode; depth: number }[] { return nodes.flatMap(node => [{ node, depth }, ...(this.collapsed.has(node.id) ? [] : this.flatten(node.children, depth + 1))]); }
}
