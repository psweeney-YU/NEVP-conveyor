<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="BasicControlPanel.ascx.cs" Inherits="CanonWebApp.Controls.BasicControlPanel" %>
<div style="text-align:center; color:#FFF; font-family:Verdana; font-weight:bold;">
<br />
    <div style="">
        <table style="width:100%; text-align:center;">
            <tr>
                <td><asp:ImageButton ID="imgButton" runat="server" ImageUrl="~/images/2.png" CommandArgument="2000" Height="73px" Width="73px" OnClick="takeDelayedPhoto" /></td>
                <td><asp:ImageButton ID="ImageButton1" runat="server" ImageUrl="~/images/5.png" CommandArgument="5000" Height="73px" Width="73px" OnClick="takeDelayedPhoto" /></td>
                <td><asp:ImageButton ID="ImageButton2" runat="server" ImageUrl="~/images/10.png" CommandArgument="10000" Height="73px" Width="73px" OnClick="takeDelayedPhoto" /></td>
            </tr>
        </table>
    </div>
    <div style="">
        <td><asp:ImageButton ID="ImageButton3" runat="server" ImageUrl="~/images/star.png" CommandArgument="0" Height="190px" Width="190px" OnClick="takeDelayedPhoto" /></td>
    </div>
</div>