/*******************************************************************************
*
* Example program:
*   GlobalContinuousAI_USB
*
* Description:
*   This example shows how to load a continuous analog input task from the Measurement & 
*   Automation Explorer (MAX) and use it to acquire and plot samples from a USB device.
*   This example should also work with E-Series and M-Series devices.
*
* Instructions for running:
*   1.  Create a continuous analog input NI-DAQmx global task in MAX. For help, refer to 
*       "Creating Tasks and Channels" in the Measurement & Automation Explorer Help. 
*       To access this help, select Start>>All Programs>>National Instruments>>
*       Measurement & Automation. In MAX, select Help>>MAX Help.
*
*       Note: If you prefer, you can import a continuous AI task and a simulated USB
*       device into MAX from the GlobalContinuousAI_USB.nce file, which is located in the 
*       example directory. Refer to "Using the Configuration Import Wizard" in the 
*       Measurement & Automation Explorer Help for more information.
*
*   2.  Run the application, select the task from the drop-down list, and click 
*       the Start button.
*
* Steps:
*   1.  Load the task from MAX.
*   2.  Read the data from all of the channels in the task.
*   3.  Stop reading data once the user clicks the "Stop" button.
*   4.  Initialize an array of colors so that if the task has more than one channel
*       then the corresponding plots can be distinguished on the graph. 
*       Assign color(s) to the plot(s) and create a legend.
*   5.  Plot the data on a waveform graph.
*******************************************************************************/

using NationalInstruments;
using NationalInstruments.DAQmx;
using NationalInstruments.UI;
using NationalInstruments.UI.WindowsForms;
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Data;
using System.Threading;

namespace NationalInstruments.Examples.GlobalContinuousAI_USB
{
    public class MainForm 
    {
        private static Task runningTask;
        private static Task continuousTask;
        private static AnalogMultiChannelReader reader;
        private static AsyncCallback callBack;
        private static AnalogWaveform<double>[] data;
        private static string taskName;
        private static int counter = 0;
        static UdpClient udpClient0 = new UdpClient();
        static UdpClient udpClient = new UdpClient(11007);
        static UdpClient udpClient1 = new UdpClient(11009);
        /// <summary>
        /// Required designer variable.
        /// </summary>
        

       

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
       

       
        
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            init();
        }

        public static void init()
        {
            foreach (string s in DaqSystem.Local.Tasks)
            {
                try
                {
                    using (Task t = DaqSystem.Local.LoadTask(s))
                    {
                        //MessageBox.Show(t.AOChannels.Count + " " + t.AOChannels.Count + " " + t.CIChannels.Count + " " + t.COChannels.Count);
                        t.Control(TaskAction.Verify);

                        if (t.AIChannels.Count > 0)
                        {
                            taskName = s;
                            break;
                        }
                    }
                }
                catch (DaqException)
                {
                    // Ignore invalid tasks
                }
            }
            System.Console.WriteLine("begin");
            Thread thread = new Thread(new ThreadStart(HeartbeatFunction));
            thread.Start();
            startButton_Click();
            
        }

        public static void HeartbeatFunction()
        {
            System.Console.WriteLine("start");
            try
            {
                udpClient0.Connect("localhost", 60109);
                Byte[] sendBytes = Encoding.ASCII.GetBytes("heart beat AI");
                while (true) {
                    int sentLength = udpClient0.Send(sendBytes, sendBytes.Length);
                    Thread.Sleep(3000);
                    System.Console.WriteLine(sentLength);
                }

            }
            catch (Exception ex)
            {
                System.Console.WriteLine(ex.Message);
            }
        }

        private static void startButton_Click()
        {


            try
            {
                continuousTask = DaqSystem.Local.LoadTask(taskName);



                runningTask = continuousTask;
                continuousTask.Start();
                reader = new AnalogMultiChannelReader(continuousTask.Stream);

                callBack = new AsyncCallback(ReadCallBack);

                    

                reader.SynchronizeCallbacks = true;
                reader.BeginReadWaveform(Convert.ToInt32(continuousTask.Timing.SamplesPerChannel), callBack, continuousTask);
                while (true)
                {
                }
                
            }
            catch (DaqException ex)
            {
                continuousTask.Dispose();
            }
        }

        public static void ReadCallBack(IAsyncResult ar)
        {
            try
            {
                if (runningTask != null && runningTask == ar.AsyncState)
                {
                    data = reader.EndReadWaveform(ar);
                    counter++;        
                    double value = data[0].GetRawData()[0];
                    double value1 = data[1].GetRawData()[0];
                    udpClient.Connect("localhost", 60105);
                    udpClient1.Connect("localhost", 60107);
                    Byte[] sendBytes = Encoding.ASCII.GetBytes("conveyor success");
                    
                    if (value > 4.93 && value < 5.05) {
                        System.Console.WriteLine("data length = " + value + " " + counter + " " + data[0].GetRawData().Length);
                        System.Console.WriteLine("data1 length = " + value1 + " " + counter + " " + data[1].GetRawData().Length);
                        int sentLength = udpClient.Send(sendBytes, sendBytes.Length);
                        //if (sentLength > 0) udpClient.Close();
                    }
                    if (value1 > 5.5) {
                        Byte[] sendBytes1 = Encoding.ASCII.GetBytes("contrast sensor value = " + value1);
                        int sentLength = udpClient1.Send(sendBytes1, sendBytes1.Length);
                    }

                    reader.BeginMemoryOptimizedReadWaveform(Convert.ToInt32(continuousTask.Timing.SamplesPerChannel), callBack, continuousTask, data);
                }
            }
            catch (DaqException ex)
            {
               
                continuousTask.Dispose();

                runningTask = null;
                
            }
        }

        
    }
}
