namespace BioStarCSharp
{
    partial class LogManagement
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
            this.tna = new System.Windows.Forms.ColumnHeader();
            this.deleteAllButton = new System.Windows.Forms.Button();
            this.readerID = new System.Windows.Forms.ColumnHeader();
            this.readButton = new System.Windows.Forms.Button();
            this.logEvent = new System.Windows.Forms.ColumnHeader();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.label1 = new System.Windows.Forms.Label();
            this.numOfLog = new System.Windows.Forms.TextBox();
            this.deviceInfo = new System.Windows.Forms.TextBox();
            this.userID = new System.Windows.Forms.ColumnHeader();
            this.eventTime = new System.Windows.Forms.ColumnHeader();
            this.closeButton = new System.Windows.Forms.Button();
            this.logList = new System.Windows.Forms.ListView();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tna
            // 
            this.tna.Text = "TNA";
            this.tna.Width = 40;
            // 
            // deleteAllButton
            // 
            this.deleteAllButton.Location = new System.Drawing.Point(235, 535);
            this.deleteAllButton.Name = "deleteAllButton";
            this.deleteAllButton.Size = new System.Drawing.Size(75, 23);
            this.deleteAllButton.TabIndex = 9;
            this.deleteAllButton.Text = "Delete All";
            this.deleteAllButton.UseVisualStyleBackColor = true;
            this.deleteAllButton.Click += new System.EventHandler(this.deleteAllButton_Click);
            // 
            // readerID
            // 
            this.readerID.Text = "Reader ID";
            this.readerID.Width = 71;
            // 
            // readButton
            // 
            this.readButton.Location = new System.Drawing.Point(117, 536);
            this.readButton.Name = "readButton";
            this.readButton.Size = new System.Drawing.Size(75, 23);
            this.readButton.TabIndex = 8;
            this.readButton.Text = "Read";
            this.readButton.UseVisualStyleBackColor = true;
            this.readButton.Click += new System.EventHandler(this.readButton_Click);
            // 
            // logEvent
            // 
            this.logEvent.Text = "Event";
            this.logEvent.Width = 186;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.numOfLog);
            this.groupBox1.Controls.Add(this.deviceInfo);
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(529, 58);
            this.groupBox1.TabIndex = 6;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Device";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(340, 25);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(69, 12);
            this.label1.TabIndex = 2;
            this.label1.Text = "log records";
            // 
            // numOfLog
            // 
            this.numOfLog.Location = new System.Drawing.Point(265, 21);
            this.numOfLog.Name = "numOfLog";
            this.numOfLog.ReadOnly = true;
            this.numOfLog.Size = new System.Drawing.Size(66, 21);
            this.numOfLog.TabIndex = 1;
            this.numOfLog.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // deviceInfo
            // 
            this.deviceInfo.Location = new System.Drawing.Point(16, 21);
            this.deviceInfo.Name = "deviceInfo";
            this.deviceInfo.ReadOnly = true;
            this.deviceInfo.Size = new System.Drawing.Size(235, 21);
            this.deviceInfo.TabIndex = 0;
            // 
            // userID
            // 
            this.userID.Text = "User ID";
            this.userID.Width = 58;
            // 
            // eventTime
            // 
            this.eventTime.Text = "Time";
            this.eventTime.Width = 153;
            // 
            // closeButton
            // 
            this.closeButton.Location = new System.Drawing.Point(353, 534);
            this.closeButton.Name = "closeButton";
            this.closeButton.Size = new System.Drawing.Size(75, 23);
            this.closeButton.TabIndex = 10;
            this.closeButton.Text = "Close";
            this.closeButton.UseVisualStyleBackColor = true;
            this.closeButton.Click += new System.EventHandler(this.closeButton_Click);
            // 
            // logList
            // 
            this.logList.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.eventTime,
            this.userID,
            this.logEvent,
            this.tna,
            this.readerID});
            this.logList.GridLines = true;
            this.logList.Location = new System.Drawing.Point(12, 87);
            this.logList.Name = "logList";
            this.logList.Size = new System.Drawing.Size(529, 439);
            this.logList.TabIndex = 7;
            this.logList.UseCompatibleStateImageBehavior = false;
            this.logList.View = System.Windows.Forms.View.Details;
            // 
            // LogManagement
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(555, 570);
            this.Controls.Add(this.deleteAllButton);
            this.Controls.Add(this.readButton);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.closeButton);
            this.Controls.Add(this.logList);
            this.Name = "LogManagement";
            this.Text = "Log Management";
            this.Load += new System.EventHandler(this.LogManagement_Load);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ColumnHeader tna;
        private System.Windows.Forms.Button deleteAllButton;
        private System.Windows.Forms.ColumnHeader readerID;
        private System.Windows.Forms.Button readButton;
        private System.Windows.Forms.ColumnHeader logEvent;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox numOfLog;
        private System.Windows.Forms.TextBox deviceInfo;
        private System.Windows.Forms.ColumnHeader userID;
        private System.Windows.Forms.ColumnHeader eventTime;
        private System.Windows.Forms.Button closeButton;
        private System.Windows.Forms.ListView logList;
    }
}