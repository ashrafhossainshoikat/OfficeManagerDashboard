namespace BioStarCSharp
{
    partial class NetworkConfig
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
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.connect = new System.Windows.Forms.Button();
            this.refreshConfig = new System.Windows.Forms.Button();
            this.writeConfig = new System.Windows.Forms.Button();
            this.port = new System.Windows.Forms.TextBox();
            this.serverIP = new System.Windows.Forms.TextBox();
            this.subnetMask = new System.Windows.Forms.TextBox();
            this.gateway = new System.Windows.Forms.TextBox();
            this.ipAddr = new System.Windows.Forms.TextBox();
            this.label8 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.synchTime = new System.Windows.Forms.CheckBox();
            this.useServer = new System.Windows.Forms.CheckBox();
            this.DHCP = new System.Windows.Forms.CheckBox();
            this.deviceInfo = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.refreshButton = new System.Windows.Forms.Button();
            this.FWVersion = new System.Windows.Forms.TextBox();
            this.MAC = new System.Windows.Forms.TextBox();
            this.deviceID = new System.Windows.Forms.TextBox();
            this.closeButton = new System.Windows.Forms.Button();
            this.groupBox2.SuspendLayout();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.connect);
            this.groupBox2.Controls.Add(this.refreshConfig);
            this.groupBox2.Controls.Add(this.writeConfig);
            this.groupBox2.Controls.Add(this.port);
            this.groupBox2.Controls.Add(this.serverIP);
            this.groupBox2.Controls.Add(this.subnetMask);
            this.groupBox2.Controls.Add(this.gateway);
            this.groupBox2.Controls.Add(this.ipAddr);
            this.groupBox2.Controls.Add(this.label8);
            this.groupBox2.Controls.Add(this.label7);
            this.groupBox2.Controls.Add(this.label6);
            this.groupBox2.Controls.Add(this.label5);
            this.groupBox2.Controls.Add(this.label4);
            this.groupBox2.Controls.Add(this.synchTime);
            this.groupBox2.Controls.Add(this.useServer);
            this.groupBox2.Controls.Add(this.DHCP);
            this.groupBox2.Location = new System.Drawing.Point(12, 160);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(380, 200);
            this.groupBox2.TabIndex = 6;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Network";
            // 
            // connect
            // 
            this.connect.Location = new System.Drawing.Point(294, 109);
            this.connect.Name = "connect";
            this.connect.Size = new System.Drawing.Size(75, 23);
            this.connect.TabIndex = 14;
            this.connect.Text = "Connect";
            this.connect.UseVisualStyleBackColor = true;
            this.connect.Click += new System.EventHandler(this.connect_Click);
            // 
            // refreshConfig
            // 
            this.refreshConfig.Location = new System.Drawing.Point(294, 80);
            this.refreshConfig.Name = "refreshConfig";
            this.refreshConfig.Size = new System.Drawing.Size(75, 23);
            this.refreshConfig.TabIndex = 13;
            this.refreshConfig.Text = "Refresh";
            this.refreshConfig.UseVisualStyleBackColor = true;
            this.refreshConfig.Click += new System.EventHandler(this.refreshConfig_Click);
            // 
            // writeConfig
            // 
            this.writeConfig.Location = new System.Drawing.Point(294, 52);
            this.writeConfig.Name = "writeConfig";
            this.writeConfig.Size = new System.Drawing.Size(75, 23);
            this.writeConfig.TabIndex = 12;
            this.writeConfig.Text = "Write";
            this.writeConfig.UseVisualStyleBackColor = true;
            this.writeConfig.Click += new System.EventHandler(this.writeConfig_Click);
            // 
            // port
            // 
            this.port.Location = new System.Drawing.Point(108, 167);
            this.port.Name = "port";
            this.port.Size = new System.Drawing.Size(167, 21);
            this.port.TabIndex = 11;
            // 
            // serverIP
            // 
            this.serverIP.Location = new System.Drawing.Point(108, 138);
            this.serverIP.Name = "serverIP";
            this.serverIP.Size = new System.Drawing.Size(167, 21);
            this.serverIP.TabIndex = 10;
            // 
            // subnetMask
            // 
            this.subnetMask.Location = new System.Drawing.Point(108, 110);
            this.subnetMask.Name = "subnetMask";
            this.subnetMask.Size = new System.Drawing.Size(167, 21);
            this.subnetMask.TabIndex = 9;
            // 
            // gateway
            // 
            this.gateway.Location = new System.Drawing.Point(108, 81);
            this.gateway.Name = "gateway";
            this.gateway.Size = new System.Drawing.Size(167, 21);
            this.gateway.TabIndex = 8;
            // 
            // ipAddr
            // 
            this.ipAddr.Location = new System.Drawing.Point(108, 53);
            this.ipAddr.Name = "ipAddr";
            this.ipAddr.Size = new System.Drawing.Size(167, 21);
            this.ipAddr.TabIndex = 7;
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(11, 172);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(27, 12);
            this.label8.TabIndex = 7;
            this.label8.Text = "Port";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(9, 142);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(56, 12);
            this.label7.TabIndex = 6;
            this.label7.Text = "Server IP";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(9, 114);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(79, 12);
            this.label6.TabIndex = 5;
            this.label6.Text = "Subnet Mask";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(9, 85);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(55, 12);
            this.label5.TabIndex = 4;
            this.label5.Text = "Gateway";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(9, 57);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(16, 12);
            this.label4.TabIndex = 3;
            this.label4.Text = "IP";
            // 
            // synchTime
            // 
            this.synchTime.AutoSize = true;
            this.synchTime.Location = new System.Drawing.Point(213, 21);
            this.synchTime.Name = "synchTime";
            this.synchTime.Size = new System.Drawing.Size(93, 16);
            this.synchTime.TabIndex = 2;
            this.synchTime.Text = "Synch Time";
            this.synchTime.UseVisualStyleBackColor = true;
            // 
            // useServer
            // 
            this.useServer.AutoSize = true;
            this.useServer.Location = new System.Drawing.Point(108, 21);
            this.useServer.Name = "useServer";
            this.useServer.Size = new System.Drawing.Size(86, 16);
            this.useServer.TabIndex = 1;
            this.useServer.Text = "Use Server";
            this.useServer.UseVisualStyleBackColor = true;
            // 
            // DHCP
            // 
            this.DHCP.AutoSize = true;
            this.DHCP.Location = new System.Drawing.Point(9, 21);
            this.DHCP.Name = "DHCP";
            this.DHCP.Size = new System.Drawing.Size(57, 16);
            this.DHCP.TabIndex = 0;
            this.DHCP.Text = "DHCP";
            this.DHCP.UseVisualStyleBackColor = true;
            this.DHCP.CheckedChanged += new System.EventHandler(this.DHCP_CheckedChanged);
            // 
            // deviceInfo
            // 
            this.deviceInfo.CausesValidation = false;
            this.deviceInfo.Font = new System.Drawing.Font("굴림", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.deviceInfo.Location = new System.Drawing.Point(12, 12);
            this.deviceInfo.Name = "deviceInfo";
            this.deviceInfo.ReadOnly = true;
            this.deviceInfo.Size = new System.Drawing.Size(380, 26);
            this.deviceInfo.TabIndex = 4;
            this.deviceInfo.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(7, 23);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(16, 12);
            this.label1.TabIndex = 0;
            this.label1.Text = "ID";
            this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(9, 51);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(33, 12);
            this.label2.TabIndex = 1;
            this.label2.Text = "MAC";
            this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(11, 79);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(69, 12);
            this.label3.TabIndex = 2;
            this.label3.Text = "FW Version";
            this.label3.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.refreshButton);
            this.groupBox1.Controls.Add(this.FWVersion);
            this.groupBox1.Controls.Add(this.MAC);
            this.groupBox1.Controls.Add(this.deviceID);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Location = new System.Drawing.Point(12, 45);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(380, 108);
            this.groupBox1.TabIndex = 5;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Sys Info";
            // 
            // refreshButton
            // 
            this.refreshButton.Location = new System.Drawing.Point(294, 18);
            this.refreshButton.Name = "refreshButton";
            this.refreshButton.Size = new System.Drawing.Size(75, 23);
            this.refreshButton.TabIndex = 6;
            this.refreshButton.Text = "Refresh";
            this.refreshButton.UseVisualStyleBackColor = true;
            this.refreshButton.Click += new System.EventHandler(this.refreshButton_Click);
            // 
            // FWVersion
            // 
            this.FWVersion.Location = new System.Drawing.Point(108, 75);
            this.FWVersion.Name = "FWVersion";
            this.FWVersion.ReadOnly = true;
            this.FWVersion.Size = new System.Drawing.Size(167, 21);
            this.FWVersion.TabIndex = 5;
            // 
            // MAC
            // 
            this.MAC.Location = new System.Drawing.Point(108, 47);
            this.MAC.Name = "MAC";
            this.MAC.ReadOnly = true;
            this.MAC.Size = new System.Drawing.Size(167, 21);
            this.MAC.TabIndex = 4;
            // 
            // deviceID
            // 
            this.deviceID.Location = new System.Drawing.Point(108, 19);
            this.deviceID.Name = "deviceID";
            this.deviceID.ReadOnly = true;
            this.deviceID.Size = new System.Drawing.Size(167, 21);
            this.deviceID.TabIndex = 3;
            // 
            // closeButton
            // 
            this.closeButton.Location = new System.Drawing.Point(160, 370);
            this.closeButton.Name = "closeButton";
            this.closeButton.Size = new System.Drawing.Size(75, 23);
            this.closeButton.TabIndex = 7;
            this.closeButton.Text = "Close";
            this.closeButton.UseVisualStyleBackColor = true;
            this.closeButton.Click += new System.EventHandler(this.closeButton_Click);
            // 
            // NetworkConfig
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(405, 401);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.deviceInfo);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.closeButton);
            this.Name = "NetworkConfig";
            this.Text = "Network Config";
            this.Load += new System.EventHandler(this.NetworkConfig_Load);
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.Button connect;
        private System.Windows.Forms.Button refreshConfig;
        private System.Windows.Forms.Button writeConfig;
        private System.Windows.Forms.TextBox port;
        private System.Windows.Forms.TextBox serverIP;
        private System.Windows.Forms.TextBox subnetMask;
        private System.Windows.Forms.TextBox gateway;
        private System.Windows.Forms.TextBox ipAddr;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.CheckBox synchTime;
        private System.Windows.Forms.CheckBox useServer;
        private System.Windows.Forms.CheckBox DHCP;
        private System.Windows.Forms.TextBox deviceInfo;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button refreshButton;
        private System.Windows.Forms.TextBox FWVersion;
        private System.Windows.Forms.TextBox MAC;
        private System.Windows.Forms.TextBox deviceID;
        private System.Windows.Forms.Button closeButton;
    }
}