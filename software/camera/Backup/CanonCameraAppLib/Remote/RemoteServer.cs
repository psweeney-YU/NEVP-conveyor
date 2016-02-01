using System;
using System.Net.Sockets;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO ;

namespace CanonCameraAppLib.Remote
{
    public delegate void TakePhoto(RemoteCommandEventArgs e);
    public delegate void DepressButton();

    public class RemoteServer
    {
        public event TakePhoto OnTakePhotoCommandEvent;
        public event DepressButton OnDepressButtonCommandEvent;

        private int port;
        private bool isListening;
        private TcpListener tcpListener;

        public RemoteServer(int port)
        {
            this.port = port;
            this.IsListening = false;
        }

        /// <summary>
        /// Start the listener for incoming messages
        /// </summary>
        public void Start()
        {
            tcpListener = new TcpListener(System.Net.IPAddress.Any, this.port);
            tcpListener.Start();
            Console.WriteLine("Server Started") ;
            this.IsListening = true;

            try
            {
                while (isListening)
                {
                    Console.WriteLine("Server listening...");
                    Socket socketForClient = tcpListener.AcceptSocket();	
                    Console.WriteLine("Client connected");
                    NetworkStream networkStream = new NetworkStream(socketForClient);

                    BinaryFormatter bf = new BinaryFormatter();
                    Object message = (RemoteMessage)bf.Deserialize(networkStream);

                    networkStream.Flush();

                    interpretMessage((RemoteMessage)message);

                    socketForClient.Disconnect(true);
                }
            }
            catch(Exception e)
            {
	            Console.WriteLine(e.ToString()) ;
            }
        }

        /// <summary>
        /// Stop the listener from receiveing messages
        /// </summary>
        public void Stop()
        {
            if (tcpListener != null)
            {
                this.isListening = false;
                tcpListener.Stop();                
            }
        }

        private void interpretMessage(RemoteMessage message)
        {
            switch (message.RemoteCommand)
            {
                case RemoteCommand.TakePhoto:
                    Console.WriteLine("Waiting for "+message.Delay+"ms");
                    TakePhoto(message.Delay);
                    break;
                case RemoteCommand.DepressButton:
                    DepressButton();
                    break;
                default:
                    throw new NotImplementedException("No code has been written to handle the [" + message + "] command!");
            }
        }

        private void TakePhoto(int delay)
        {
            if (OnTakePhotoCommandEvent != null)
            {
                OnTakePhotoCommandEvent(new RemoteCommandEventArgs(delay));
            }
        }

        private void DepressButton()
        {
            if (OnDepressButtonCommandEvent != null)
            {
                OnDepressButtonCommandEvent();
            }
        }

        public int Port
        {
            get { return this.port; }
        }

        public bool IsListening
        {
            get { return this.isListening; }
            private set { this.isListening = value; }
        }
    }
}