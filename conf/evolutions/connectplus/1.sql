# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table BRACBankBranch (
  BranchId                  integer identity(1,1) not null,
  SOL                       integer,
  BranchRCCode              integer,
  BranchAddress             varchar(255),
  constraint pk_BRACBankBranch primary key (BranchId))
;

create table Menu (
  MenuId                    integer identity(1,1) not null,
  ParentID                  integer,
  PermissionID              integer,
  MenuText                  varchar(255),
  NavigateUrl               varchar(255),
  MenuOrder                 integer,
  IsVisible                 integer,
  constraint pk_Menu primary key (MenuId))
;

create table PermissionsCriteria (
  PermissionID              integer identity(1,1) not null,
  PermissionName            varchar(255),
  ViewOrder                 integer,
  ModuleID                  integer,
  constraint pk_PermissionsCriteria primary key (PermissionID))
;

create table ReferanceCodes (
  RCID                      integer identity(1,1) not null,
  RCGroup                   integer,
  Code                      varchar(255),
  ParentRCID                integer,
  constraint pk_ReferanceCodes primary key (RCID))
;

create table Roles (
  RoleID                    integer identity(1,1) not null,
  RoleName                  varchar(255),
  constraint pk_Roles primary key (RoleID))
;

create table UserInfo (
  UserID                    integer identity(1,1) not null,
  UserName                  varchar(255),
  FullName                  varchar(255),
  RoleID                    integer,
  BranchId                  integer,
  Zone                      varchar(255),
  PasswordSalt              varchar(255),
  SaltedHash                varchar(255),
  IsActive                  bit default 0,
  IsLocked                  bit default 0,
  IsApproved                bit default 0,
  constraint pk_UserInfo primary key (UserID))
;

create table UserPrivilege (
  UserPermissionID          numeric(19) identity(1,1) not null,
  PermissionID              integer,
  UserID                    integer,
  RoleID                    integer,
  constraint pk_UserPrivilege primary key (UserPermissionID))
;

create table UsersInRoles (
  urID                      integer identity(1,1) not null,
  UserID                    integer,
  RoleID                    integer,
  constraint pk_UsersInRoles primary key (urID))
;




# --- !Downs

drop table BRACBankBranch;

drop table Menu;

drop table PermissionsCriteria;

drop table ReferanceCodes;

drop table Roles;

drop table UserInfo;

drop table UserPrivilege;

drop table UsersInRoles;

