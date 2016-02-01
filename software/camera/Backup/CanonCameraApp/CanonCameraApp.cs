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

namespace CanonCameraApp
{
    public partial class CanonCameraApp : Form
    {
        public delegate void TakePhotoDelegate(int delay);

        private CameraAPI api;
        private RemoteServer server;
        private TakePhotoDelegate takePhotoDelegate;        

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
                camera.OnNewItemCreated += new CanonCameraAppLib.Events.NewItemCreatedEventHandler(camera_OnNewItemCreated);
            }
        }

        void camera_OnNewItemCreated(Camera sender, CanonCameraAppLib.Events.NewItemCreatedEventArgs e)
        {
            if (chbPreview.Checked)
            {
                PhotoPreview preview = new PhotoPreview(e.Item, true);
                preview.Show();
            }
        }

        private void takePhotograph(int delay)
        {
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

            private void button1_Click(object sender, EventArgs e)
            {
                takePhotograph(0);
            }

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