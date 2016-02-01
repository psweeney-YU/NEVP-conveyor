<%@ Page Language="C#" MasterPageFile="~/MasterPage.Master" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="CanonWebApp.Default" Title="Canon Camera Remote Control" %>
<%@ Register src="Controls/BasicControlPanel.ascx" tagname="BasicControlPanel" tagprefix="uc1" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <uc1:BasicControlPanel ID="BasicControlPanel1" runat="server" />
</asp:Content>