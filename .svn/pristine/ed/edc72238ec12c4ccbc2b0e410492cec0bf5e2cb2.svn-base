
INSERT [dbo].[Roles] ([RoleName]) VALUES (N'Back-office Admin')-----124(p_K)
INSERT [dbo].[Roles] ([RoleName]) VALUES (N'BSSO Officer')-----125
INSERT [dbo].[Roles] ([RoleName]) VALUES (N'CCSO Officer')-----126
INSERT [dbo].[Roles] ([RoleName]) VALUES (N'Branch Manager')-----127
INSERT [dbo].[Roles] ([RoleName]) VALUES (N'Regional Manager')-----128
INSERT [dbo].[Roles] ([RoleName]) VALUES (N'Head of SME')-----129
INSERT [dbo].[Roles] ([RoleName]) VALUES (N'Office-Maker')-----130
INSERT [dbo].[Roles] ([RoleName]) VALUES (N'Office-Checker')-----131



select * from Roles order BY RoleID desc



INSERT [dbo].[Modules]  VALUES (71, N'Service Delivery Model', NULL)---->71


select * from Modules ORDER BY MID DESC



INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (673, N'sdm.TaskTypeManagement.create', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (674, N'sdm.TaskTypeManagement.view', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (675, N'sdm.TaskTypeManagement.update', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (676, N'sdm.TaskTypeManagement.approve', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (677, N'sdm.ReportManagement.serviceDetailedReport', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (678, N'sdm.ReportManagement.pendingTokenReport', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (679, N'sdm.ReportManagement.serviceWiseReport', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (680, N'sdm.ReportManagement.counterWiseSummary', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (681, N'sdm.ReportManagement.ccsoTransactionReport', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (682, N'sdm.ReportManagement.liveInterfaceView', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (683, N'sdm.ReportManagement.userSummaryProfile', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (684, N'sdm.ReportManagement.detailedTokenReport', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (685, N'sdm.ReportManagement.breakReport', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (686, N'sdm.CardManagement.create', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (687, N'sdm.CardManagement.view', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (688, N'sdm.ReportManagement.update', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (689, N'sdm.CardManagement.approve', 1, 1, 71)

INSERT [dbo].[PermissionsCriteria] ([PermissionID], [PermissionName], [ViewOrder], [DisplayInPermissionManagement], [ModuleID]) VALUES (690, N'sdm.CustomerAndTaskManagement.homepage', 1, 1, 71)





select * from PermissionsCriteria WHERE PermissionID=673


INSERT [dbo].[UserPrivilege] ([UserPermissionID], [PermissionID], [RoleID], [UserID]) VALUES (139525, 153, 54, NULL)


INSERT [dbo].[UsersInRoles] ([UserID], [RoleID]) VALUES (189, 130)

INSERT [dbo].[UsersInRoles] ([UserID], [RoleID]) VALUES (8741, 130)




INSERT [dbo].[UserPrivilege] ([PermissionID], [RoleID], [UserID]) VALUES (686, 130, NULL)

INSERT [dbo].[UserPrivilege] ([PermissionID], [RoleID], [UserID]) VALUES (690, 130, NULL)



INSERT [dbo].[UserInfo] ([PIN], [FullName], [UserName], [Password], 
[RoleID], [DepartmentID], [WingID], [SupervisorUserID], [IsActive], [Designation], 
[Email], [PasswordSalt], [SaltedHash], [BranchId], [HomeAddress], [HomePhone], 
[OfficePhone], [MobileNumber], [OfficeAddress], [ThemeName], [ProductTitle], [IsLocked], 
[LastLockoutDate], [IsApproved], [CreateDate], [LastLoginDate], [FailedPasswordAttemptCount], 
[FailedPasswordAttemptWindowStart], [LastPasswordChangedDate], [Gender], [CreatedBy], [ApprovedBy], 
[ApprovedDate], [DepartmentType], [Zone], [Territory], [Unit], [CRMCenter], [ROCName]) VALUES 
(N'5063', N'Shoikat Hossain', N'Shoikat', N'dB0Uj7mX3wU=', 130, 227, 0, 0, 1, N'Software Engineer', N'shoikathossain@yahoo.com', N'-1679505561', N'?+V(?b??gW? ????', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, NULL, CAST(0x0000A395010B7A88 AS DateTime), 1, CAST(0x0000A39300DAD62C AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, N'0')



INSERT [dbo].[UserInfo] ([PIN], [FullName], [UserName], [Password], 
[RoleID], [DepartmentID], [WingID], [SupervisorUserID], [IsActive], [Designation], 
[Email], [PasswordSalt], [SaltedHash], [BranchId], [HomeAddress], [HomePhone], 
[OfficePhone], [MobileNumber], [OfficeAddress], [ThemeName], [ProductTitle], [IsLocked], 
[LastLockoutDate], [IsApproved], [CreateDate], [LastLoginDate], [FailedPasswordAttemptCount], 
[FailedPasswordAttemptWindowStart], [LastPasswordChangedDate], [Gender], [CreatedBy], [ApprovedBy], 
[ApprovedDate], [DepartmentType], [Zone], [Territory], [Unit], [CRMCenter], [ROCName]) VALUES 
(N'1293', N'Asfak Shaon', N'Shaon', N'dB0Uj7mX3wU=', 130, 227, 0, 0, 1, N'Software Engineer', N'asfakshaon@yahoo.com', N'-1679505561', N'?+V(?b??gW? ????', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, NULL, CAST(0x0000A395010B7A88 AS DateTime), 1, CAST(0x0000A39300DAD62C AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, N'0')










INSERT [dbo].[Menu] ([ParentID], [PermissionID], [MenuText], [NavigateUrl], [MenuOrder], [IsVisible]) VALUES (1, NULL, 0, N'Home', N'Default.aspx', 0, 1)
INSERT [dbo].[Menu] ([ParentID], [PermissionID], [MenuText], [NavigateUrl], [MenuOrder], [IsVisible]) VALUES (3, 438, 1, N'Manage Roles', N'Administration\ManageRoles.aspx', 1, 1)




INSERT [dbo].[Branches] ([Branches_ID], [Branches_Code], [Branches_Name], [Bank_ID]) VALUES (1, N'1000010', N'Dhaka', 1)
INSERT [dbo].[Branches] ([Branches_ID], [Branches_Code], [Branches_Name], [Bank_ID]) VALUES (2, N'1000020', N'Agrabad', 1)



INSERT INTO "dbo"."UserInfo" (UserID,PIN,FullName,UserName,Password,RoleID,DepartmentID,WingID,SupervisorUserID,IsActive,Designation,Email,PasswordSalt,SaltedHash,BranchId,HomeAddress,HomePhone,OfficePhone,MobileNumber,OfficeAddress,ThemeName,ProductTitle,IsLocked,LastLockoutDate,IsApproved,CreateDate,LastLoginDate,FailedPasswordAttemptCount,FailedPasswordAttemptWindowStart,LastPasswordChangedDate,Gender,CreatedBy,ApprovedBy,ApprovedDate,DepartmentType,Zone,Territory,Unit,CRMCenter,ROCName) VALUES (1,'5063','Shoikat','shoikat','',1,2051,570,0,1,'sse','shoikathossain@g.com','-1230641906','^?qz????sBfv?\n??!]?',1,'','','','','','3552','0',0,{ts '2013-06-27 12:57:41.'},1,null,{ts '2014-11-03 12:07:18.'},1,{ts '2014-09-21 16:51:15.'},{ts '2012-05-22 12:33:26.'},'0',null,1,{ts '2013-02-26 15:25:09.'},1,'0','0','0','','0');
INSERT INTO "dbo"."UserInfo" (UserID,PIN,FullName,UserName,Password,RoleID,DepartmentID,WingID,SupervisorUserID,IsActive,Designation,Email,PasswordSalt,SaltedHash,BranchId,HomeAddress,HomePhone,OfficePhone,MobileNumber,OfficeAddress,ThemeName,ProductTitle,IsLocked,LastLockoutDate,IsApproved,CreateDate,LastLoginDate,FailedPasswordAttemptCount,FailedPasswordAttemptWindowStart,LastPasswordChangedDate,Gender,CreatedBy,ApprovedBy,ApprovedDate,DepartmentType,Zone,Territory,Unit,CRMCenter,ROCName) VALUES (2,'dsfsdf','Mr. Anwar','anwar','',1,0,0,0,1,'','mohammadshohel.anwar@bracbank.com','613637299','#^?z???b?''???C?',3,null,null,null,null,null,'3552','3554',0,{ts '2012-03-18 11:30:39.'},1,null,{ts '2014-02-16 18:35:21.'},1,{ts '2014-04-01 16:10:30.'},{ts '2014-02-16 18:35:02.'},'0',null,1,{ts '2013-02-26 15:25:09.'},null,'0','0','0',null,'0');
INSERT INTO "dbo"."UserInfo" (UserID,PIN,FullName,UserName,Password,RoleID,DepartmentID,WingID,SupervisorUserID,IsActive,Designation,Email,PasswordSalt,SaltedHash,BranchId,HomeAddress,HomePhone,OfficePhone,MobileNumber,OfficeAddress,ThemeName,ProductTitle,IsLocked,LastLockoutDate,IsApproved,CreateDate,LastLoginDate,FailedPasswordAttemptCount,FailedPasswordAttemptWindowStart,LastPasswordChangedDate,Gender,CreatedBy,ApprovedBy,ApprovedDate,DepartmentType,Zone,Territory,Unit,CRMCenter,ROCName) VALUES (3,'dfsd','ewrwe ','prince','',10,1932,2535,22,1,'','dfdsfsd@dsfsdf.com','2131311761','?	??c??w?????4a????',147,'ghdhf hgcfhgf ghfhgf','53643','35435','8758865','hjbjh jhhjvh','3552','3553',1,{ts '2013-04-21 13:09:03.'},1,null,null,6,{ts '2013-04-21 13:08:45.'},{ts '2012-02-14 15:56:32.'},'2',null,1,{ts '2013-02-26 15:25:09.'},null,'0','0','0',null,'0');






