using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;

using CanonCameraAppLib;
using CanonCameraAppLib.Remote;
using System.Net;
using System.Net.Sockets;

namespace CanonCameraApp
{
    public partial class CanonCameraApp : Form
    {
        public delegate void TakePhotoDelegate(int delay);//EdsDeleteDirectoryItem

        private CameraAPI api;
        private RemoteServer server;
        private TakePhotoDelegate takePhotoDelegate;
        private int i=0;
        DateTime dateTime = DateTime.Now;

        public CanonCameraApp()
        {
            InitializeComponent();
            api = CameraAPI.Instance;
            takePhotoDelegate = new TakePhotoDelegate(takePhotograph);

            init();            
        }

        private void server_OnTakePhotoCommandEvent(RemoteCommandEventArgs e)
        {
            this.Invoke(takePhotoDelegate, e.Delay);
        }

        private void init()
        {
            loadCameras();
            registerEvents();
            if (Convert.ToBoolean(ConfigurationManager.AppSettings["StartServerOnStartup"]))
            {
                startServerListener();
            }

            int recv;
            byte[] data = new byte[1024];

            IPEndPoint ip = new IPEndPoint(IPAddress.Any, 60002);
            Socket newsock = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);

            newsock.Bind(ip);
            IPEndPoint sender = new IPEndPoint(IPAddress.Any, 0);
            EndPoint Remote = (EndPoint)(sender);
            recv = newsock.ReceiveFrom(data, ref Remote);
            
            newsock.Close();
            takePhotograph(0);
            
   
        }

        private void registerEvents()
        {
            CameraAPI.OnCameraAdded += new CameraAddedEventHandler(api_OnCameraAdded);
        }

        private void api_OnCameraAdded(CameraAddedEventArgs e)
        {            
            scanForCamerasToolStripMenuItem_Click(null, new EventArgs());
        }

        private void startServerListener()
        {
            server = new RemoteServer(Convert.ToInt32(ConfigurationManager.AppSettings["ListenPort"]));
            server.OnTakePhotoCommandEvent += new TakePhoto(server_OnTakePhotoCommandEvent);
            new Thread(server.Start).Start();
        }

        private void loadCameras()
        {
            try
            {
                cbCameras.DataSource = api.Cameras;
                cbCameras.DisplayMember = "Name";
                cbCameras.Enabled = true;
                btnTakePhoto.Enabled = true;
                btnProperties.Enabled = true;
                scanForCamerasToolStripMenuItem.Enabled = false;
                subscribeToEvents();
            }
            catch (CameraNotFoundException)
            {
                MessageBox.Show("No cameras were detected.  Please make sure that they are plugged in and are turned on.");
                cbCameras.Enabled = false;
                btnTakePhoto.Enabled = false;
                btnProperties.Enabled = false;
                scanForCamerasToolStripMenuItem.Enabled = true;
            }
        }

        private void subscribeToEvents()
        {
            List<Camera> cameras = getCameraList();

            foreach (Camera camera in cameras)
            {
                UdpClient udpClient = new UdpClient(11000);
                udpClient.Connect("localhost", 60005);
                Byte[] sendBytes = Encoding.ASCII.GetBytes(camera.Name);
                int sentLength = udpClient.Send(sendBytes, sendBytes.Length);
                if (sentLength > 0) udpClient.Close();
                camera.OnNewItemCreated += new CanonCameraAppLib.Events.NewItemCreatedEventHandler(camera_OnNewItemCreated);
            }
        }

        void camera_OnNewItemCreated(Camera sender, CanonCameraAppLib.Events.NewItemCreatedEventArgs e)
        {
            if (chbPreview.Checked)
            {
                if (i % 2 == 0)
                {
                    DateTime dt1 = DateTime.Now;
                    System.IO.File.WriteAllBytes(@"c:\imageStorage\test_" + i / 2 + ".cr2", e.Item.Image);
                    DateTime dt2 = DateTime.Now;
                    TimeSpan ts = dt2 - dt1;
                    System.Console.WriteLine("Data Transmission Time = " + ts.Milliseconds);
                    sender.deleteCapturedItem(e.Item.Ptr);
                }
                else {
                    DateTime dt1 = DateTime.Now;
                    System.IO.File.WriteAllBytes(@"c:\imageStorage\full_" + i / 2 + ".jpg", e.Item.Image);
                    DateTime dt2 = DateTime.Now;
                    TimeSpan ts = dt2 - dt1;
                    System.Console.WriteLine("Data Transmission Time = " + ts.Milliseconds);
                    //takePhotograph(0);
                    //SmallPic(@"c:\imageStorage\full_" + i / 2 + ".jpg", @"c:\imageStorage\display_" + i / 2 + ".jpg",1872,1248);
                    //SmallPic(@"c:\imageStorage\display_" + i / 2 + ".jpg", @"c:\imageStorage\thumbnail_" + i / 2 + ".jpg", 156, 104);

                    sender.deleteCapturedItem(e.Item.Ptr);
                    UdpClient udpClient = new UdpClient();
                    udpClient.Connect("localhost", 60005);
                    Byte[] sendBytes = Encoding.ASCII.GetBytes(@"c:\imageStorage\full_" + i / 2 + ".jpg");
                    int sentLength = udpClient.Send(sendBytes, sendBytes.Length);
                    if (sentLength > 0) udpClient.Close();
                    System.Console.WriteLine("Size = " + ts.Milliseconds);
                }
                i++;
                
                //PhotoPreview preview = new PhotoPreview(e.Item, true);
                //preview.Show();
            }
        }

        private void SmallPic(string strOldPic, string strNewPic, int intWidth, int intHeight)
        {
            System.Drawing.Bitmap objPic, objNewPic;
            try
            {
                objPic = new System.Drawing.Bitmap(strOldPic);
                objNewPic = new System.Drawing.Bitmap(objPic, intWidth, intHeight);
                objNewPic.Save(strNewPic);
            }
            catch (Exception exp) { throw exp; }
            finally
            {
                objPic = null;
                objNewPic = null;
            }
        }  

        private void takePhotograph(int delay)
        {
            DateTime dt2 = DateTime.Now;
            TimeSpan ts = dt2 - dateTime;
            System.Console.WriteLine("Time interval between taking pictures: "+ts.Milliseconds);
            dateTime = DateTime.Now;
            Camera camera = getSelectedCamera();

            camera.takePhotograph(delay);
        }

        private Camera getSelectedCamera()
        {
            return (Camera)cbCameras.SelectedItem;
        }

        private List<Camera> getCameraList()
        {
            List<Camera> cameras = new List<Camera>(cbCameras.Items.Count);

            foreach (object camera in cbCameras.Items)
            {
                cameras.Add((Camera)camera);
            }

            return cameras;
        }

        #region Handled Form Events

            //private void button1_Click(object sender, EventArgs e)
            //{
            //    takePhotograph(0);
            //}

            private void scanForCamerasToolStripMenuItem_Click(object sender, EventArgs e)
            {
                loadCameras();
            }

            private void quitToolStripMenuItem_Click(object sender, EventArgs e)
            {
                quit();
            }

            private void CanonCameraApp_FormClosing(object sender, FormClosingEventArgs e)
            {
                quit();
            }

            private void aboutToolStripMenuItem_Click(object sender, EventArgs e)
            {
                new About().ShowDialog(this);
            }

            /// <summary>
            /// Opens the Camera Properties window
            /// </summary>
            private void btnProperties_Click(object sender, EventArgs e)
            {
                new CameraProperties(getSelectedCamera()).Show();
            }

        #endregion

        private void quit()
        {
            if (server != null)
            {
                server.Stop();
            }
            Application.ExitThread();
            Application.Exit();
        }
    }
}