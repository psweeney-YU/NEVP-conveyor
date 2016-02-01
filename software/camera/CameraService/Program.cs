using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceProcess;
using System.Net;
using System.Net.Sockets;

namespace CameraService
{
    class Program : ServiceBase
    {
        static void Main(string[] args)
        {
            ServiceBase[] ServicesToRun;
            ServicesToRun = new ServiceBase[] 
            { 
                new Program()
            };
            ServiceBase.Run(ServicesToRun);
        }

        public Program()
        {
            this.ServiceName = "Camera Service";
        }

        protected override void OnStart(string[] args)
        {
            base.OnStart(args);
            int recv;
            byte[] data = new byte[1024];

            UdpClient udpClient = new UdpClient(11000);
            udpClient.Connect("localhost", 60003);
            Byte[] sendBytes = Encoding.ASCII.GetBytes("Is anybody there?");
            udpClient.Send(sendBytes, sendBytes.Length);
            

            IPEndPoint ip = new IPEndPoint(IPAddress.Any, 60002);
            Socket newsock = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);

            newsock.Bind(ip);
            IPEndPoint sender = new IPEndPoint(IPAddress.Any, 0);
            EndPoint Remote = (EndPoint)(sender);
            recv = newsock.ReceiveFrom(data, ref Remote);
            //TODO: place your start code here
        }

        protected override void OnStop()
        {
            base.OnStop();

            //TODO: clean up any variables and stop any threads
        }
    }
}
