﻿namespace ProjektAM
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
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea1 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Series series1 = new System.Windows.Forms.DataVisualization.Charting.Series();
            this.Console = new System.Windows.Forms.TextBox();
            this.buttonGet = new System.Windows.Forms.Button();
            this.textBoxUrl = new System.Windows.Forms.TextBox();
            this.checkBoxAuto = new System.Windows.Forms.CheckBox();
            this.chart1 = new System.Windows.Forms.DataVisualization.Charting.Chart();
            this.numericUpDownDataCount = new System.Windows.Forms.NumericUpDown();
            this.labelDataCount = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.chart1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownDataCount)).BeginInit();
            this.SuspendLayout();
            // 
            // Console
            // 
            this.Console.Location = new System.Drawing.Point(27, 309);
            this.Console.Multiline = true;
            this.Console.Name = "Console";
            this.Console.Size = new System.Drawing.Size(396, 33);
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
            // chart1
            // 
            this.chart1.BackColor = System.Drawing.Color.Transparent;
            chartArea1.Name = "ChartArea1";
            this.chart1.ChartAreas.Add(chartArea1);
            this.chart1.Location = new System.Drawing.Point(0, 77);
            this.chart1.Name = "chart1";
            this.chart1.Palette = System.Windows.Forms.DataVisualization.Charting.ChartColorPalette.Bright;
            this.chart1.RightToLeft = System.Windows.Forms.RightToLeft.No;
            series1.ChartArea = "ChartArea1";
            series1.ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;
            series1.Name = "Series1";
            this.chart1.Series.Add(series1);
            this.chart1.Size = new System.Drawing.Size(423, 223);
            this.chart1.TabIndex = 4;
            this.chart1.Text = "Przebieg pomiaru";
            // 
            // numericUpDownDataCount
            // 
            this.numericUpDownDataCount.Location = new System.Drawing.Point(27, 22);
            this.numericUpDownDataCount.Name = "numericUpDownDataCount";
            this.numericUpDownDataCount.Size = new System.Drawing.Size(120, 20);
            this.numericUpDownDataCount.TabIndex = 5;
            // 
            // labelDataCount
            // 
            this.labelDataCount.AutoSize = true;
            this.labelDataCount.Location = new System.Drawing.Point(24, 6);
            this.labelDataCount.Name = "labelDataCount";
            this.labelDataCount.Size = new System.Drawing.Size(65, 13);
            this.labelDataCount.TabIndex = 6;
            this.labelDataCount.Text = "Ilość próbek";
            // 
            // MainWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(455, 354);
            this.Controls.Add(this.labelDataCount);
            this.Controls.Add(this.numericUpDownDataCount);
            this.Controls.Add(this.chart1);
            this.Controls.Add(this.checkBoxAuto);
            this.Controls.Add(this.textBoxUrl);
            this.Controls.Add(this.buttonGet);
            this.Controls.Add(this.Console);
            this.Name = "MainWindow";
            this.Text = "Projekt AM";
            ((System.ComponentModel.ISupportInitialize)(this.chart1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownDataCount)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox Console;
        private System.Windows.Forms.Button buttonGet;
        private System.Windows.Forms.TextBox textBoxUrl;
        private System.Windows.Forms.CheckBox checkBoxAuto;
        private System.Windows.Forms.DataVisualization.Charting.Chart chart1;
        private System.Windows.Forms.NumericUpDown numericUpDownDataCount;
        private System.Windows.Forms.Label labelDataCount;
    }
}

