USE [master]
GO
/****** Object:  Database [J3.L.P0038]    Script Date: 01/22/2018 09:59:18 ******/
CREATE DATABASE [J3.L.P0038] ON  PRIMARY 
( NAME = N'J3.L.P0038', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\J3.L.P0038.mdf' , SIZE = 2048KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'J3.L.P0038_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\J3.L.P0038_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [J3.L.P0038] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [J3.L.P0038].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [J3.L.P0038] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [J3.L.P0038] SET ANSI_NULLS OFF
GO
ALTER DATABASE [J3.L.P0038] SET ANSI_PADDING OFF
GO
ALTER DATABASE [J3.L.P0038] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [J3.L.P0038] SET ARITHABORT OFF
GO
ALTER DATABASE [J3.L.P0038] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [J3.L.P0038] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [J3.L.P0038] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [J3.L.P0038] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [J3.L.P0038] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [J3.L.P0038] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [J3.L.P0038] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [J3.L.P0038] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [J3.L.P0038] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [J3.L.P0038] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [J3.L.P0038] SET  DISABLE_BROKER
GO
ALTER DATABASE [J3.L.P0038] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [J3.L.P0038] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [J3.L.P0038] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [J3.L.P0038] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [J3.L.P0038] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [J3.L.P0038] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [J3.L.P0038] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [J3.L.P0038] SET  READ_WRITE
GO
ALTER DATABASE [J3.L.P0038] SET RECOVERY SIMPLE
GO
ALTER DATABASE [J3.L.P0038] SET  MULTI_USER
GO
ALTER DATABASE [J3.L.P0038] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [J3.L.P0038] SET DB_CHAINING OFF
GO
USE [J3.L.P0038]
GO
/****** Object:  Table [dbo].[tbl_Role]    Script Date: 01/22/2018 09:59:20 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Role](
	[RoleID] [nvarchar](50) NOT NULL,
	[RoleName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Role] PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tbl_Role] ([RoleID], [RoleName]) VALUES (N'Ad', N'Administrator')
INSERT [dbo].[tbl_Role] ([RoleID], [RoleName]) VALUES (N'Sub', N'Subscriber')
INSERT [dbo].[tbl_Role] ([RoleID], [RoleName]) VALUES (N'Us', N'User')
/****** Object:  Table [dbo].[tbl_Account]    Script Date: 01/22/2018 09:59:20 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Account](
	[Username] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NULL,
	[Firstname] [nvarchar](50) NULL,
	[Lastname] [nvarchar](50) NULL,
	[Password] [nvarchar](50) NULL,
	[Website] [nvarchar](50) NULL,
	[SendNotification] [bit] NULL,
	[RoleID] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tbl_Account] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tbl_Account] ([Username], [Email], [Firstname], [Lastname], [Password], [Website], [SendNotification], [RoleID]) VALUES (N'S01', N'huyheoh@yahoo.com', N'Huy', N'Nguyen', N'123', N'huynd.com.vn', 1, N'Ad')
INSERT [dbo].[tbl_Account] ([Username], [Email], [Firstname], [Lastname], [Password], [Website], [SendNotification], [RoleID]) VALUES (N'S02', N'a@gmai.com', N'Binh', N'Thanh', N'123', N'a.org', 0, N'Sub')
INSERT [dbo].[tbl_Account] ([Username], [Email], [Firstname], [Lastname], [Password], [Website], [SendNotification], [RoleID]) VALUES (N'S03', N'b@gmail.com', N'Go', N'Vap', N'123', N'b.net', 1, N'Sub')
INSERT [dbo].[tbl_Account] ([Username], [Email], [Firstname], [Lastname], [Password], [Website], [SendNotification], [RoleID]) VALUES (N'S04', N'c@gmail.com', N'Minh', N'Beo', N'123', N'a.op', 0, N'Sub')
INSERT [dbo].[tbl_Account] ([Username], [Email], [Firstname], [Lastname], [Password], [Website], [SendNotification], [RoleID]) VALUES (N'S05', N'abc@gmail.com', N'Lin', N'Min', N'123', N'aca.co', 0, N'Us')
INSERT [dbo].[tbl_Account] ([Username], [Email], [Firstname], [Lastname], [Password], [Website], [SendNotification], [RoleID]) VALUES (N'S06', N'binh@gmail.com', N'Pop', N'Qop', N'123', N'456.com.vn', 0, N'Us')
INSERT [dbo].[tbl_Account] ([Username], [Email], [Firstname], [Lastname], [Password], [Website], [SendNotification], [RoleID]) VALUES (N'S07', N'zxcv@gmail.com', N'Hin', N'Full', N'123', N'ass.vn', 1, N'Ad')
/****** Object:  ForeignKey [FK_tbl_Account_tbl_Role]    Script Date: 01/22/2018 09:59:20 ******/
ALTER TABLE [dbo].[tbl_Account]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Account_tbl_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[tbl_Role] ([RoleID])
GO
ALTER TABLE [dbo].[tbl_Account] CHECK CONSTRAINT [FK_tbl_Account_tbl_Role]
GO
