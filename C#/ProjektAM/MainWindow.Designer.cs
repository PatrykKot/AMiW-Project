namespace ProjektAM
{
    partial class MainWindow
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
            this.Console = new System.Windows.Forms.TextBox();
            this.buttonGet = new System.Windows.Forms.Button();
            this.textBoxUrl = new System.Windows.Forms.TextBox();
            this.checkBoxAuto = new System.Windows.Forms.CheckBox();
            this.SuspendLayout();
            // 
            // Console
            // 
            this.Console.Location = new System.Drawing.Point(27, 86);
            this.Console.Multiline = true;
            this.Console.Name = "Console";
            this.Console.Size = new System.Drawing.Size(396, 256);
            this.Console.TabIndex = 0;
            // 
            // buttonGet
            // 
            this.buttonGet.Location = new System.Drawing.Point(348, 48);
            this.buttonGet.Name = "buttonGet";
            this.buttonGet.Size = new System.Drawing.Size(75, 23);
            this.buttonGet.TabIndex = 1;
            this.buttonGet.Text = "Get";
            this.buttonGet.UseVisualStyleBackColor = true;
            this.buttonGet.Click += new System.EventHandler(this.buttonGet_Click);
            // 
            // textBoxUrl
            // 
            this.textBoxUrl.Location = new System.Drawing.Point(27, 48);
            this.textBoxUrl.Name = "textBoxUrl";
            this.textBoxUrl.Size = new System.Drawing.Size(315, 20);
            this.textBoxUrl.TabIndex = 2;
            // 
            // checkBoxAuto
            // 
            this.checkBoxAuto.AutoSize = true;
            this.checkBoxAuto.Location = new System.Drawing.Point(375, 25);
            this.checkBoxAuto.Name = "checkBoxAuto";
            this.checkBoxAuto.Size = new System.Drawing.Size(48, 17);
            this.checkBoxAuto.TabIndex = 3;
            this.checkBoxAuto.Text = "Auto";
            this.checkBoxAuto.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.checkBoxAuto.UseVisualStyleBackColor = true;
            this.checkBoxAuto.CheckedChanged += new System.EventHandler(this.checkBoxAuto_CheckedChanged);
            // 
            // MainWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(455, 354);
            this.Controls.Add(this.checkBoxAuto);
            this.Controls.Add(this.textBoxUrl);
            this.Controls.Add(this.buttonGet);
            this.Controls.Add(this.Console);
            this.Name = "MainWindow";
            this.Text = "Projekt AM";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox Console;
        private System.Windows.Forms.Button buttonGet;
        private System.Windows.Forms.TextBox textBoxUrl;
        private System.Windows.Forms.CheckBox checkBoxAuto;
    }
}

