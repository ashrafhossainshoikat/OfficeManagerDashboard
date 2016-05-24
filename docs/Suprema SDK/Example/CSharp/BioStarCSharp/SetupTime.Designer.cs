namespace BioStarCSharp
{
    partial class SetupTime
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
            this.setTime = new System.Windows.Forms.Button();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.getTime = new System.Windows.Forms.Button();
            this.timePicker = new System.Windows.Forms.DateTimePicker();
            this.datePicker = new System.Windows.Forms.DateTimePicker();
            this.closeButton = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // setTime
            // 
            this.setTime.Location = new System.Drawing.Point(324, 20);
            this.setTime.Name = "setTime";
            this.setTime.Size = new System.Drawing.Size(49, 23);
            this.setTime.TabIndex = 3;
            this.setTime.Text = "Set";
            this.setTime.UseVisualStyleBackColor = true;
            this.setTime.Click += new System.EventHandler(this.setTime_Click);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.setTime);
            this.groupBox1.Controls.Add(this.getTime);
            this.groupBox1.Controls.Add(this.timePicker);
            this.groupBox1.Controls.Add(this.datePicker);
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(386, 55);
            this.groupBox1.TabIndex = 2;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Time";
            // 
            // getTime
            // 
            this.getTime.Location = new System.Drawing.Point(269, 20);
            this.getTime.Name = "getTime";
            this.getTime.Size = new System.Drawing.Size(49, 23);
            this.getTime.TabIndex = 2;
            this.getTime.Text = "Get";
            this.getTime.UseVisualStyleBackColor = true;
            this.getTime.Click += new System.EventHandler(this.getTime_Click);
            // 
            // timePicker
            // 
            this.timePicker.Format = System.Windows.Forms.DateTimePickerFormat.Time;
            this.timePicker.Location = new System.Drawing.Point(137, 21);
            this.timePicker.Name = "timePicker";
            this.timePicker.Size = new System.Drawing.Size(120, 21);
            this.timePicker.TabIndex = 1;
            // 
            // datePicker
            // 
            this.datePicker.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.datePicker.Location = new System.Drawing.Point(11, 21);
            this.datePicker.Name = "datePicker";
            this.datePicker.Size = new System.Drawing.Size(120, 21);
            this.datePicker.TabIndex = 0;
            this.datePicker.ValueChanged += new System.EventHandler(this.datePicker_ValueChanged);
            // 
            // closeButton
            // 
            this.closeButton.Location = new System.Drawing.Point(172, 77);
            this.closeButton.Name = "closeButton";
            this.closeButton.Size = new System.Drawing.Size(75, 23);
            this.closeButton.TabIndex = 3;
            this.closeButton.Text = "Close";
            this.closeButton.UseVisualStyleBackColor = true;
            this.closeButton.Click += new System.EventHandler(this.closeButton_Click);
            // 
            // SetupTime
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(410, 111);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.closeButton);
            this.Name = "SetupTime";
            this.Text = "Setup Time";
            this.Load += new System.EventHandler(this.SetupTime_Load);
            this.groupBox1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button setTime;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button getTime;
        private System.Windows.Forms.DateTimePicker timePicker;
        private System.Windows.Forms.DateTimePicker datePicker;
        private System.Windows.Forms.Button closeButton;
    }
}