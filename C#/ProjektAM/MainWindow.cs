using System;
using System.Collections.Generic;
using System.Windows.Forms;
using System.Net;
using System.IO;
using Newtonsoft.Json;

namespace ProjektAM
{


    public partial class MainWindow : Form
    {
        private List<int> measurements = new List<int>();
        private System.Timers.Timer downloadTimer = new System.Timers.Timer();

        public MainWindow()
        {
            InitializeComponent();
            downloadTimer.AutoReset = true;
            downloadTimer.Interval = 500; //0.5s
            downloadTimer.Elapsed += OnDownloadTimerEvent;
            downloadTimer.SynchronizingObject = this;
            numericUpDownDataCount.Value = 100;
            numericUpDownFreq.Value = 10;
        }

        static public String get(String url)
        {
            WebRequest r = HttpWebRequest.Create(url);
            var response = r.GetResponse();
            var responseStream = response.GetResponseStream();
            StreamReader reader = new StreamReader(responseStream);
            return reader.ReadToEnd();
        }

        private JsnData getJsnObject(String url)
        {
            return JsonConvert.DeserializeObject<JsnData>(get(url));
        }

        private void buttonGet_Click(object sender, EventArgs e)
        {
            UpdateData();
        }
        private void UpdateData()
        {
            try
            {
                JsnData data = getJsnObject(textBoxUrl.Text + "/measurements/getLast?length="
                                    + numericUpDownDataCount.Value.ToString());
                Console.Text = "";
                this.measurements = data.measurements;
                List<String> lines = new List<String>();
                chart1.Series["Series1"].Points.Clear();
                for (int i = 0; i<measurements.Count; ++i)
                {
                    lines.Add(measurements[i].ToString());  
                    chart1.Series["Series1"].Points.AddXY(i, measurements[i]);
                }
                Console.Lines = lines.ToArray();
            }
            catch (Exception ex)
            {
                Console.Text = ex.Message;
            }
        }


         private void OnDownloadTimerEvent(Object source, System.Timers.ElapsedEventArgs e)
        {
             UpdateData();
        }

        private void checkBoxAuto_CheckedChanged(object sender, EventArgs e)
        {
            if (checkBoxAuto.Checked) downloadTimer.Start();
            else downloadTimer.Stop();
        }

        private void numericUpDownFreq_ValueChanged(object sender, EventArgs e)
        {
            downloadTimer.Interval = 1000 * 1 / (float)numericUpDownFreq.Value;
            System.Console.WriteLine("New interval: " + downloadTimer.Interval);
        }
    }

    
}
