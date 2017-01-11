﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net;
using System.IO;
using System.Net.Sockets;
using System.Text.RegularExpressions;
using Newtonsoft.Json;

namespace ProjektAM
{
    public partial class MainWindow : Form
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        static public String get(String url)
        {
            WebRequest r = HttpWebRequest.Create(url);
            var response = r.GetResponse();
            var responseStream = response.GetResponseStream();
            StreamReader reader = new StreamReader(responseStream);
            return reader.ReadToEnd();
        }

        private static JsnData getJsnObject(String url)
        {
            return JsonConvert.DeserializeObject<JsnData>(get(url));
        }

        private void buttonGet_Click(object sender, EventArgs e)
        {
            try
            {
                JsnData data = getJsnObject(textBoxUrl.Text);
                Console.Text = "";
                List < String > states = new List<String>();
                foreach (bool s in data.states)
                {
                    if (s) states.Add("zielona");
                    else states.Add("czerwona");
                }
                Console.Lines = states.ToArray();

            }
            catch(Exception ex)
            {
                Console.Text = ex.Message;
            }
        }
    }

}
