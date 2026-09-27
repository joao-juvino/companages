export type AccessRole='OWNER'|'ADMIN'|'MANAGER'|'MEMBER'; export type RecordStatus='ACTIVE'|'INACTIVE'|'PENDING'|'ARCHIVED';
export interface User{id:number;name:string;email:string;avatarUrl:string|null;phone:string|null;bio:string|null;createdAt:string}
export interface AuthResponse{token:string;accessToken:string;refreshToken:string;expiresIn:number;user:User}
export interface Organization{id:number;name:string;description:string|null;logoUrl:string|null;website:string|null;phone:string|null;email:string|null;address:string|null;status:RecordStatus;currentRole:AccessRole;createdAt:string;updatedAt:string}
export interface Member{id:number;name:string;email:string|null;userId:number|null;organizationId:number;accessRole:AccessRole;positionId:number|null;positionName:string|null;teamId:number|null;teamName:string|null;managerId:number|null;managerName:string|null;status:RecordStatus;joinedAt:string|null;location:string|null;phone:string|null;bio:string|null;avatarUrl:string|null;createdAt:string;updatedAt:string}
export interface Position{id:number;name:string;description:string|null;level:number|null;status:RecordStatus;organizationId:number;createdAt:string;updatedAt:string}
export interface Team{id:number;name:string;description:string|null;leadMemberId:number|null;leadMemberName:string|null;status:RecordStatus;memberCount:number;createdAt:string;updatedAt:string}
export interface Assignment{id:number;organizationId:number;member:Member;position:Position;createdAt:string}
export interface DashboardSummary{organizations:number;members:number;positions:number;assignments:number;recentMembers:Member[]}
export interface OrganizationDashboard{members:number;activeMembers:number;teams:number;positions:number;pendingInvitations:number;recentMembers:Member[];membersByTeam:Record<string,number>;membersByPosition:Record<string,number>;membersByStatus:Record<string,number>;memberGrowth:Record<string,number>}
export interface ChartNode{id:number;name:string;email:string|null;avatarUrl:string|null;position:string|null;team:string|null;role:string;managerId:number|null;children:ChartNode[]}
export interface Invitation{id:number;organizationId:number;organizationName:string;email:string;role:AccessRole;status:string;expiresAt:string;acceptedAt:string|null;createdAt:string}
export interface Activity{id:number;action:string;actorId:number|null;actorName:string;targetType:string|null;targetId:string|null;metadata:string|null;createdAt:string}
export interface Notification{id:number;organizationId:number|null;type:string;title:string;message:string;link:string|null;read:boolean;createdAt:string}
export interface PageResponse<T>{content:T[];page:number;size:number;totalElements:number;totalPages:number;first:boolean;last:boolean}
export interface ApiError{message:string;validationErrors?:Record<string,string>}
