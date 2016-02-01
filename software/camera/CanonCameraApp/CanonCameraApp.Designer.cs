namespace CanonCameraApp
{
    partial class CanonCameraApp
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(CanonCameraApp));
            this.btnTakePhoto = new System.Windows.Forms.Button();
            this.cbCameras = new System.Windows.Forms.ComboBox();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.fileToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.quitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.aCToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.scanForCamerasToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.helpToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.aboutToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.btnProperties = new System.Windows.Forms.Button();
            this.chbPreview = new System.Windows.Forms.CheckBox();
            //this.menuStrip1.SuspendLayout();
            //this.SuspendLayout();
            // 
            // btnTakePhoto
            // 
            this.btnTakePhoto.Enabled = false;
            this.btnTakePhoto.Location = new System.Drawing.Point(14, 77);
            this.btnTakePhoto.Name = "btnTakePhoto";
            this.btnTakePhoto.Size = new System.Drawing.Size(126, 23);
            this.btnTakePhoto.TabIndex = 0;
            this.btnTakePhoto.Text = "Take Photo";
            this.btnTakePhoto.UseVisualStyleBackColor = true;
            // 
            // cbCameras
            // 
            this.cbCameras.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbCameras.FormattingEnabled = true;
            this.cbCameras.Location = new System.Drawing.Point(14, 50);
            this.cbCameras.Name = "cbCameras";
            this.cbCameras.Size = new System.Drawing.Size(263, 21);
            this.cbCameras.TabIndex = 1;

            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fileToolStripMenuItem,
            this.aCToolStripMenuItem,
            this.helpToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(289, 24);
            this.menuStrip1.TabIndex = 2;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // fileToolStripMenuItem
            // 
            this.fileToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.quitToolStripMenuItem});
            this.fileToolStripMenuItem.Name = "fileToolStripMenuItem";
            this.fileToolStripMenuItem.Size = new System.Drawing.Size(37, 20);
            this.fileToolStripMenuItem.Text = "File";
            // 
            // quitToolStripMenuItem
            // 
            this.quitToolStripMenuItem.Name = "quitToolStripMenuItem";
            this.quitToolStripMenuItem.Size = new System.Drawing.Size(97, 22);
            this.quitToolStripMenuItem.Text = "Quit";
            this.quitToolStripMenuItem.Click += new System.EventHandler(this.quitToolStripMenuItem_Click);
            // 
            // aCToolStripMenuItem
            // 
            this.aCToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.scanForCamerasToolStripMenuItem});
            this.aCToolStripMenuItem.Name = "aCToolStripMenuItem";
            this.aCToolStripMenuItem.Size = new System.Drawing.Size(59, 20);
            this.aCToolStripMenuItem.Text = "Actions";
            // 
            // scanForCamerasToolStripMenuItem
            // 
            this.scanForCamerasToolStripMenuItem.Name = "scanForCamerasToolStripMenuItem";
            this.scanForCamerasToolStripMenuItem.Size = new System.Drawing.Size(164, 22);
            this.scanForCamerasToolStripMenuItem.Text = "Scan for cameras";
            this.scanForCamerasToolStripMenuItem.Click += new System.EventHandler(this.scanForCamerasToolStripMenuItem_Click);
            // 
            // helpToolStripMenuItem
            // 
            this.helpToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.aboutToolStripMenuItem});
            this.helpToolStripMenuItem.Name = "helpToolStripMenuItem";
            this.helpToolStripMenuItem.Size = new System.Drawing.Size(44, 20);
            this.helpToolStripMenuItem.Text = "Help";
            // 
            // aboutToolStripMenuItem
            // 
            this.aboutToolStripMenuItem.Name = "aboutToolStripMenuItem";
            this.aboutToolStripMenuItem.Size = new System.Drawing.Size(107, 22);
            this.aboutToolStripMenuItem.Text = "About";
            this.aboutToolStripMenuItem.Click += new System.EventHandler(this.aboutToolStripMenuItem_Click);
            // 
            // btnProperties
            // 
            this.btnProperties.Location = new System.Drawing.Point(151, 77);
            this.btnProperties.Name = "btnProperties";
            this.btnProperties.Size = new System.Drawing.Size(126, 23);
            this.btnProperties.TabIndex = 3;
            this.btnProperties.Text = "Popout Properties >";
            this.btnProperties.UseVisualStyleBackColor = true;
            this.btnProperties.Click += new System.EventHandler(this.btnProperties_Click);
            // 
            // chbPreview
            // 
            this.chbPreview.AutoSize = true;
            this.chbPreview.Checked = true;
            this.chbPreview.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chbPreview.Location = new System.Drawing.Point(14, 27);
            this.chbPreview.Name = "chbPreview";
            this.chbPreview.Size = new System.Drawing.Size(136, 17);
            this.chbPreview.TabIndex = 4;
            this.chbPreview.Text = "Show Captured Photo?";
            this.chbPreview.UseVisualStyleBackColor = true;
            // 
            // CanonCameraApp
            // 
            //this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            //this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(0, 0);
            this.Controls.Add(this.chbPreview);
            this.Controls.Add(this.btnProperties);
            this.Controls.Add(this.cbCameras);
            this.Controls.Add(this.btnTakePhoto);
            this.Controls.Add(this.menuStrip1);
            //this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            //this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            //this.MainMenuStrip = this.menuStrip1;
            this.MaximizeBox = false;
            this.MinimizeBox = false;

            //this.Name = "CanonCameraApp";
            //this.Text = "Canon Camera Controller";
            //this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.CanonCameraApp_FormClosing);
            // this.menuStrip1.ResumeLayout(false);
            //this.menuStrip1.PerformLayout();
            //this.ResumeLayout(false);
            //this.PerformLayout();
            this.Visible = false;
            this.ShowInTaskbar = false;
            this.Hide();


        }


        #endregion

        private System.Windows.Forms.Button btnTakePhoto;
        private System.Windows.Forms.ComboBox cbCameras;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem fileToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem quitToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem aCToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem scanForCamerasToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem helpToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem aboutToolStripMenuItem;
        private System.Windows.Forms.Button btnProperties;
        private System.Windows.Forms.CheckBox chbPreview;
    }
}