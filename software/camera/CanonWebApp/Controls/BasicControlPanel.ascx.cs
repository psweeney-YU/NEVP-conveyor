using System;
using System.Collections;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;

using CanonCameraAppLib.Remote;

namespace CanonWebApp.Controls
{
    public partial class BasicControlPanel : System.Web.UI.UserControl
    {
        private string host = ConfigurationManager.AppSettings["RemoteHost"];
        private int port = Convert.ToInt32(ConfigurationManager.AppSettings["RemotePort"]);

        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void takeDelayedPhoto(object s, EventArgs e)
        {
            try
            {
                int delay = Convert.ToInt32(((ImageButton)s).CommandArgument);

                RemoteMessage message = new RemoteMessage(delay, RemoteCommand.TakePhoto);

                RemoteClient.sendMessage(message, host, port);
            }
            catch (Exception ex)
            {
                Response.Write("<script language=\"javascript\" type=\"text/javascript\">alert('Unable to take photo because " + ex.Message + "');</script>");
            }
        }
    }
}