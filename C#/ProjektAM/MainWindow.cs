using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net;
using System.Timers;
using System.IO;
using System.Net.Sockets;
using System.Text.RegularExpressions;
using Newtonsoft.Json;

namespace ProjektAM
{
    public partial class MainWindow : Form
    {
        private System.Timers.Timer downloadTimer = new System.Timers.Timer();
       
        public MainWindow()
        {
            InitializeComponent();
            downloadTimer.AutoReset = true;
            downloadTimer.Interval = 500; //0.5s
            downloadTimer.Elapsed += OnDownloadTimerEvent;
            downloadTimer.SynchronizingObject = this;
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
            printStates();
        }
        private void printStates()
        {
            try
            {
                JsnData data = getJsnObject(textBoxUrl.Text);
                Console.Text = "";
                List<String> measurments = new List<String>();
                foreach (int s in data.random)
                {
                    measurments.Add(s.ToString());
                }
                Console.Lines = measurments.ToArray();
 
            }
            catch (Exception ex)
            {
                Console.Text = ex.Message;
            }
        }

         private void OnDownloadTimerEvent(Object source, System.Timers.ElapsedEventArgs e)
        {
             printStates();
        }

        private void checkBoxAuto_CheckedChanged(object sender, EventArgs e)
        {
            if (checkBoxAuto.Checked) downloadTimer.Start();
            else downloadTimer.Stop();
        }
    }

    
}
