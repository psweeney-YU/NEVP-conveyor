using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Resources;
using System.Text;
using System.Windows.Forms;

using CanonCameraAppLib;
using com.waynehartman.util.enums;

namespace CanonCameraApp
{
    public partial class CameraProperties : Form
    {
        private Camera camera;

        public CameraProperties(Camera camera)
        {
            this.Camera = camera;
            InitializeComponent();

            init();
        }

        private void init()
        {
            this.Text = this.Camera.Name + " Properties";
            lblName.Text = this.Camera.Name;
            lblSerialNumber.Text = this.Camera.SerialNumber;
            lblFirmwareVersion.Text = this.Camera.FirmwareVersion;
            if (this.Camera.StorageMedia != null)
            {
                lblFreeSpace.Text = this.Camera.StorageMedia.FreeSpace.ToString();
                lblVolumeLabel.Text = this.Camera.StorageMedia.Label;
                lblCapacity.Text = this.Camera.StorageMedia.Capacity.ToString();
                lblStorageMediaType.Text = EnumUtils.stringValueOf(this.Camera.StorageMedia.StorageMediaType);
            }
            displayBatteryPower(this.Camera.BatteryState);
        }

        private void displayBatteryPower(BatteryState batteryState)
        {
            Image batteryImage = null;

            System.Reflection.Assembly myAssembly = System.Reflection.Assembly.GetExecutingAssembly();

            switch (batteryState)
            {
                case BatteryState.AC:
                    batteryImage = Image.FromStream(myAssembly.GetManifestResourceStream("CanonCameraApp.images.batteryAC.png"));
                    break;
                case BatteryState.Empty:
                    batteryImage = Image.FromStream(myAssembly.GetManifestResourceStream("CanonCameraApp.images.batteryEmpty.png"));
                    break;
                case BatteryState.Full:
                    batteryImage = Image.FromStream(myAssembly.GetManifestResourceStream("CanonCameraApp.images.batteryFull.png"));
                    break;
                case BatteryState.Half:
                    batteryImage = Image.FromStream(myAssembly.GetManifestResourceStream("CanonCameraApp.images.batteryHalf.png"));
                    break;
                case BatteryState.Low:
                    batteryImage = Image.FromStream(myAssembly.GetManifestResourceStream("CanonCameraApp.images.batteryLow.png"));
                    break;
            }

            pbBattery.Image = batteryImage;
        }

        public CanonCameraAppLib.Camera Camera
        {
            get { return this.camera; }
            set { this.camera = value; }
        }
    }
}