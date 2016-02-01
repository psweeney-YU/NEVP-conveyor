using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing.Imaging;

using com.waynehartman.util.graphics;
using CanonCameraAppLib;

namespace CanonCameraApp
{
    public partial class PhotoPreview : Form
    {
        private CapturedItem item;

        public PhotoPreview()
        {
            
            InitializeComponent();
            this.pictureBox1.ContextMenuStrip = contextMenuStrip1;
        }

        public PhotoPreview(CapturedItem item, bool maximize)
        {
            
            InitializeComponent();
            this.item = item;
            this.statusName.Text = item.Name;
            this.statusSize.Text = (((double)item.Size) / 1024 / 1024).ToString("0.00") + "MB";

            if (maximize)
            {
                this.WindowState = FormWindowState.Maximized;
            }

            scaleImage();
        }

        private void scaleImage()
        {
            try
            {
                this.statusStrip1.Visible = this.WindowState != FormWindowState.Maximized;

                if (pictureBox1.Width > 0)
                {
                    Dimension frame = new Dimension(pictureBox1.Width, pictureBox1.Height);

                    int desiredWidth = ImageScaler.getDesiredWidth(item.Dimensions, frame);

                    ImageScaler scaler = new ImageScaler(desiredWidth);

                    MemoryStream streamIn = new MemoryStream(item.Image);

                    MemoryStream streamOut = new MemoryStream();

                    scaler.scaleImage(streamIn, streamOut, ImageFormat.Bmp);

                    Image bitmap = Image.FromStream(streamOut);

                    pictureBox1.Image = bitmap;
                }
            }
            catch (System.Exception e)
            {
                System.Console.WriteLine(e.Message);
            }
        }

        private void PhotoPreview_SizeChanged(object sender, EventArgs e)
        {
            scaleImage();
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            saveImage();
        }

        public void saveImage()
        {
            if (DialogResult.OK == saveFileDialog1.ShowDialog())
            {
                com.waynehartman.util.IO.SaveFileToDisk.saveBinary(saveFileDialog1.FileName, item.Image);
            }
        }

        private void saveToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            saveImage();
        }

        private void closeToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void pictureBox1_MouseClick(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Right)
            {
                contextMenuStrip1.Show(e.Location);
            }
        }

        private void PhotoPreview_KeyDown(object sender, KeyEventArgs e)
        {
            switch (e.KeyData)
            {
                case Keys.Escape:
                    this.WindowState = FormWindowState.Normal;
                    break;
                default:
                    break;
            }
        }

        private void PhotoPreview_FormClosed(object sender, FormClosedEventArgs e)
        {
            this.pictureBox1.Image.Dispose();
        }
    }
}