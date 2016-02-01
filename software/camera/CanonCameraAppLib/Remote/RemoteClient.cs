using System;
using System.Net.Sockets;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;

namespace CanonCameraAppLib.Remote
{
    public class RemoteClient
    {
        public static void sendMessage(RemoteMessage message, String host, int port)
        {
            TcpClient myclient = new TcpClient(host, port);

            BinaryFormatter bf = new BinaryFormatter();

            bf.Serialize(myclient.GetStream(), message);

            myclient.Close();
        }
    }
}