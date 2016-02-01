/*******************************************************************************
*
* Example program:
*   GlobalContinuousAO
*
* Description:
*   This example shows how to load a continuous analog output task from the Measurement & 
*   Automation Explorer (MAX) and use it to write generated data to a DAQ device and plot the data.
*
* Instructions for running:
*   1.  Create a continuous analog output NI-DAQmx global task in MAX. For help, refer to 
*       "Creating Tasks and Channels" in the Measurement & Automation Explorer Help. 
*       To access this help, select Start>>All Programs>>National Instruments>>
*       Measurement & Automation. In MAX, select Help>>MAX Help.
*
*       Note: If you prefer, you can import a continuous AO task and a simulated 
*       device into MAX from the GlobalContinuousAO.nce file, which is located in the 
*       example directory. Refer to "Using the Configuration Import Wizard" in the 
*       Measurement & Automation Explorer Help for more information.
*
*   2.  Run the application, select the task from the drop-down list, and click 
*       the Start button.
*
* Steps:
*   1.  Load the task from MAX.
*   2.  Generate data for the task.
*   3.  Write data to the channel in the task
*   4.  Plot the generated data on a waveform graph.
*******************************************************************************/
using NationalInstruments.Analysis.SignalGeneration;
using NationalInstruments.Analysis;
using NationalInstruments;
using NationalInstruments.DAQmx;
using NationalInstruments.UI;
using NationalInstruments.UI.WindowsForms;
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.Net;
using System.Net.Sockets;
using System.Text;

namespace NationalInstruments.Examples.GlobalContinuousAO
{
    /// <summary>
    /// Summary description for Form1.
    /// </summary>
    public class MainForm
    {

        private static Task counterTask;


        private static string taskName;
        /// <summary>
        /// Required designer variable.
        /// </summary>

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

                        
                        
                        if (t.COChannels.Count > 0 )
                        {
                            taskName = s;
                            System.Console.WriteLine(taskName);
                            break;
                        }
                    }
                }
                catch (DaqException)
                {
                    // Ignore invalid tasks
                }
            }
            startButton_Click();

        }

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
       

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
       
        [STAThread]
        static void Main() 
        {
            init();
        }

        private static void startButton_Click()
        {
            try
            {
                // Get task from combox box and initialize the analog writer

                counterTask = DaqSystem.Local.LoadTask(taskName);
                int recv;
                byte[] data = new byte[1024];

                IPEndPoint ip = new IPEndPoint(IPAddress.Any, 60011);
                Socket newsock = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);

                newsock.Bind(ip);
                IPEndPoint sender = new IPEndPoint(IPAddress.Any, 0);
                EndPoint Remote = (EndPoint)(sender);
                
                for (int i = 0; i < 10000000; i++)
                {
                    
                    //System.Threading.Thread.Sleep(5000);
                    
                    recv = newsock.ReceiveFrom(data, ref Remote);

                    counterTask.Start();
                    System.Threading.Thread.Sleep(500);
                    counterTask.Stop();

                    //UdpClient udpClient = new UdpClient(11001);
                    //udpClient.Connect("localhost", 60006);
                    //Byte[] sendBytes = Encoding.ASCII.GetBytes("conveyor success");
                    //int sentLength = udpClient.Send(sendBytes, sendBytes.Length);
                    //if (sentLength > 0) udpClient.Close();
                    System.Console.WriteLine("loop " + i);
                }


                //coWriter = new CounterSingleChannelWriter(counterTask.Stream);
                //counterTask.Control(TaskAction.Verify);
                //coWriter.WriteSingleSample(true,new CODataFrequency(1,0.001));

                //continuousTask.Control(TaskAction.Verify);

                // Generate the sine wave data
                //SignalGenerator fgen = null;
                //fgen = new SignalGenerator(continuousTask.Timing.SampleClockRate, continuousTask.Timing.SamplesPerChannel, new SineSignal());
                //double[] generatedData = fgen.Generate();


                // Convert the generated data to an AnalogWaveform
                //                AnalogWaveform<double> data = AnalogWaveform<double>.FromArray1D(generatedData);

                // Write and plot the generated data
                //WaveformPlot plot = new WaveformPlot();
                //globalContinuousAOWaveformGraph.Plots.Add(plot);

                //writer.WriteWaveform<double>(true, data);
                //globalContinuousAOWaveformGraph.PlotWaveform<double>(data);
                //channelLegend.Items.Add(new LegendItem(plot, continuousTask.AOChannels[0].VirtualName + ": " + continuousTask.AOChannels[0].PhysicalName));


                //stopButton.Enabled = true;
                //taskComboBox.Enabled = false;
            }
            catch (DaqException ex)
            {

            }

        }
    }
}
