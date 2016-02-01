namespace CanonCameraApp
{
    partial class CameraProperties
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
            this.pbBattery = new System.Windows.Forms.PictureBox();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.label1 = new System.Windows.Forms.Label();
            this.lblName = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.lblSerialNumber = new System.Windows.Forms.Label();
            this.gbStorageMedia = new System.Windows.Forms.GroupBox();
            this.lblVolumeLabel = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.lblStorageMediaType = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.lblFreeSpace = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.lblCapacity = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.lblFirmwareVersion = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.pbBattery)).BeginInit();
            this.groupBox1.SuspendLayout();
            this.gbStorageMedia.SuspendLayout();
            this.SuspendLayout();
            // 
            // pbBattery
            // 
            this.pbBattery.Location = new System.Drawing.Point(6, 19);
            this.pbBattery.Name = "pbBattery";
            this.pbBattery.Size = new System.Drawing.Size(73, 35);
            this.pbBattery.TabIndex = 0;
            this.pbBattery.TabStop = false;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.pbBattery);
            this.groupBox1.Location = new System.Drawing.Point(227, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(86, 67);
            this.groupBox1.TabIndex = 2;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Battery";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(12, 12);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(89, 13);
            this.label1.TabIndex = 3;
            this.label1.Text = "Camera Name:";
            // 
            // lblName
            // 
            this.lblName.AutoSize = true;
            this.lblName.Location = new System.Drawing.Point(12, 25);
            this.lblName.Name = "lblName";
            this.lblName.Size = new System.Drawing.Size(24, 13);
            this.lblName.TabIndex = 4;
            this.lblName.Text = "n/a";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(12, 38);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(55, 13);
            this.label3.TabIndex = 5;
            this.label3.Text = "Serial #:";
            // 
            // lblSerialNumber
            // 
            this.lblSerialNumber.AutoSize = true;
            this.lblSerialNumber.Location = new System.Drawing.Point(12, 51);
            this.lblSerialNumber.Name = "lblSerialNumber";
            this.lblSerialNumber.Size = new System.Drawing.Size(24, 13);
            this.lblSerialNumber.TabIndex = 6;
            this.lblSerialNumber.Text = "n/a";
            // 
            // gbStorageMedia
            // 
            this.gbStorageMedia.Controls.Add(this.lblVolumeLabel);
            this.gbStorageMedia.Controls.Add(this.label6);
            this.gbStorageMedia.Controls.Add(this.lblStorageMediaType);
            this.gbStorageMedia.Controls.Add(this.label5);
            this.gbStorageMedia.Controls.Add(this.lblFreeSpace);
            this.gbStorageMedia.Controls.Add(this.label4);
            this.gbStorageMedia.Controls.Add(this.lblCapacity);
            this.gbStorageMedia.Controls.Add(this.label2);
            this.gbStorageMedia.Location = new System.Drawing.Point(12, 93);
            this.gbStorageMedia.Name = "gbStorageMedia";
            this.gbStorageMedia.Size = new System.Drawing.Size(301, 91);
            this.gbStorageMedia.TabIndex = 7;
            this.gbStorageMedia.TabStop = false;
            this.gbStorageMedia.Text = "Storage Media Info";
            // 
            // lblVolumeLabel
            // 
            this.lblVolumeLabel.AutoSize = true;
            this.lblVolumeLabel.Location = new System.Drawing.Point(166, 69);
            this.lblVolumeLabel.Name = "lblVolumeLabel";
            this.lblVolumeLabel.Size = new System.Drawing.Size(24, 13);
            this.lblVolumeLabel.TabIndex = 7;
            this.lblVolumeLabel.Text = "n/a";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(166, 56);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(87, 13);
            this.label6.TabIndex = 6;
            this.label6.Text = "Volume Label:";
            // 
            // lblStorageMediaType
            // 
            this.lblStorageMediaType.AutoSize = true;
            this.lblStorageMediaType.Location = new System.Drawing.Point(163, 33);
            this.lblStorageMediaType.Name = "lblStorageMediaType";
            this.lblStorageMediaType.Size = new System.Drawing.Size(24, 13);
            this.lblStorageMediaType.TabIndex = 5;
            this.lblStorageMediaType.Text = "n/a";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(163, 20);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(125, 13);
            this.label5.TabIndex = 4;
            this.label5.Text = "Storage Media Type:";
            // 
            // lblFreeSpace
            // 
            this.lblFreeSpace.AutoSize = true;
            this.lblFreeSpace.Location = new System.Drawing.Point(7, 69);
            this.lblFreeSpace.Name = "lblFreeSpace";
            this.lblFreeSpace.Size = new System.Drawing.Size(24, 13);
            this.lblFreeSpace.TabIndex = 3;
            this.lblFreeSpace.Text = "n/a";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(7, 56);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(76, 13);
            this.label4.TabIndex = 2;
            this.label4.Text = "Free Space:";
            // 
            // lblCapacity
            // 
            this.lblCapacity.AutoSize = true;
            this.lblCapacity.Location = new System.Drawing.Point(7, 33);
            this.lblCapacity.Name = "lblCapacity";
            this.lblCapacity.Size = new System.Drawing.Size(24, 13);
            this.lblCapacity.TabIndex = 1;
            this.lblCapacity.Text = "n/a";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(7, 20);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(60, 13);
            this.label2.TabIndex = 0;
            this.label2.Text = "Capacity:";
            // 
            // lblFirmwareVersion
            // 
            this.lblFirmwareVersion.AutoSize = true;
            this.lblFirmwareVersion.Location = new System.Drawing.Point(12, 77);
            this.lblFirmwareVersion.Name = "lblFirmwareVersion";
            this.lblFirmwareVersion.Size = new System.Drawing.Size(24, 13);
            this.lblFirmwareVersion.TabIndex = 9;
            this.lblFirmwareVersion.Text = "n/a";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label8.Location = new System.Drawing.Point(12, 64);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(107, 13);
            this.label8.TabIndex = 8;
            this.label8.Text = "Firmware Version:";
            // 
            // CameraProperties
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(325, 194);
            this.Controls.Add(this.lblFirmwareVersion);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.gbStorageMedia);
            this.Controls.Add(this.lblSerialNumber);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.lblName);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.groupBox1);
            this.DoubleBuffered = true;
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "CameraProperties";
            this.ShowIcon = false;
            this.ShowInTaskbar = false;
            this.SizeGripStyle = System.Windows.Forms.SizeGripStyle.Hide;
            ((System.ComponentModel.ISupportInitialize)(this.pbBattery)).EndInit();
            this.groupBox1.ResumeLayout(false);
            this.gbStorageMedia.ResumeLayout(false);
            this.gbStorageMedia.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox pbBattery;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label lblName;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label lblSerialNumber;
        private System.Windows.Forms.GroupBox gbStorageMedia;
        private System.Windows.Forms.Label lblCapacity;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label lblFreeSpace;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label lblStorageMediaType;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label lblVolumeLabel;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label lblFirmwareVersion;
        private System.Windows.Forms.Label label8;
    }
}