Imports System.Net.Sockets
Imports System.Net
Imports System.IO
Imports System.Threading

Public Class Login
    Private _Connection As ConnectionInfo1
    Private _ServerAddress As IPAddress

    Private Sub LoginButton_Click(sender As Object, e As EventArgs) Handles LoginButton.Click
        'Don't allow login without username
        If LoginTextBox.Text = "" Then
            MessageBox.Show("Please enter a username.")
        End If

        StatusLabel1.Visible = True

        'Attempt to connect to server
        StatusLabel1.Text = "Connecting to server..."
        ConnectButton.PerformClick()

        'If there is a server connection then attempt to login
        If ConnectButton.Checked Then
            'Check username And password
            'If username and password are accepted, then load form
            ProgressBar1.Visible = True
            StatusLabel1.Text = "Loading..."
            Testing1.InputForm.Show()
            Testing1.TheTester.Show()
            ProgressBar1.Visible = False
            StatusLabel1.Visible = False
            Testing1.InputForm.WindowState = FormWindowState.Maximized
            Me.Hide()
        Else
            StatusLabel1.Text = "No connection to server"
        End If


        'Send info to server
        'If getConnection() IsNot Nothing AndAlso getConnection.Client.Connected AndAlso getConnection.Stream IsNot Nothing Then
        '    Dim buffer() As Byte = System.Text.Encoding.ASCII.GetBytes("Username=" & LoginTextBox.Text & "Password=" & PasswordTextBox.Text)
        '    getConnection.Stream.Write(buffer, 0, buffer.Length)
        'End If
    End Sub

    Private Sub Login_Load(sender As Object, e As EventArgs) Handles MyBase.Load

        'Validate children
        ValidateChildren()

        Me.Size = Me.MinimumSize


    End Sub

    Private Sub andvanced_Click(sender As Object, e As EventArgs) Handles andvanced.Click
        If Me.Size = MinimumSize Then
            Me.Size = MaximumSize
        Else
            Me.Size = MinimumSize
        End If
    End Sub
    Private Sub ConnectButton_Click(sender As Object, e As EventArgs) Handles ConnectButton.Click
        If ConnectButton.Checked Then
            ConnectButton.Checked = False
        Else
            ConnectButton.Checked = True
        End If
    End Sub

    Private Sub ConnectButton_CheckedChanged(sender As Object, e As EventArgs) Handles ConnectButton.CheckedChanged
        If ConnectButton.Checked Then
            If _ServerAddress IsNot Nothing Then
                Testing1.InputForm.RichTextBox1.Text = "Connected"
                ConnectButton.Text = "Disconnect"
                ConnectButton.Image = My.Resources._1385_Disable_16x16_72
                Try
                    _Connection = New ConnectionInfo1(_ServerAddress, CInt(PortTextBox.Text), AddressOf InvokeAppendOutput)
                    Dim buffer() As Byte = System.Text.Encoding.ASCII.GetBytes("Username=" & LoginTextBox.Text & ",Password=" & PasswordTextBox.Text & Chr(13) + Chr(10))
                    _Connection.Stream.Write(buffer, 0, buffer.Length)
                    _Connection.AwaitData()
                Catch ex As Exception
                    MessageBox.Show(ex.Message, "Error Connecting to Server", MessageBoxButtons.OK, MessageBoxIcon.Exclamation)
                    ConnectButton.Checked = False
                End Try
            Else
                MessageBox.Show("Invalid server name or address.", "Cannot Connect to Server", MessageBoxButtons.OK, MessageBoxIcon.Exclamation)
                ConnectButton.Checked = False
            End If
        Else
            Testing1.InputForm.RichTextBox1.Text = "NOT CONNECTED!"
            ConnectButton.Text = "Connect"
            ConnectButton.Image = My.Resources.command_link_16x16
            If _Connection IsNot Nothing Then _Connection.Close()
            _Connection = Nothing
        End If
    End Sub

    Private Sub ServerTextBox_Validating(sender As Object, e As System.ComponentModel.CancelEventArgs) Handles ServerTextBox.Validating
        _ServerAddress = Nothing
        Dim remoteHost As IPHostEntry = Dns.GetHostEntry(ServerTextBox.Text)
        If remoteHost IsNot Nothing AndAlso remoteHost.AddressList.Length > 0 Then
            For Each deltaAddress As IPAddress In remoteHost.AddressList
                If deltaAddress.AddressFamily = AddressFamily.InterNetwork Then
                    _ServerAddress = deltaAddress
                    Exit For
                End If
            Next
        End If
        If _ServerAddress Is Nothing Then
            MessageBox.Show("Cannot resolove server address.", "Invalid Server", MessageBoxButtons.OK, MessageBoxIcon.Exclamation)
            PortTextBox.SelectAll()
            e.Cancel = True
        End If
    End Sub

    Private Sub PortTextBox_Validating(sender As Object, e As System.ComponentModel.CancelEventArgs) Handles PortTextBox.Validating
        Dim deltaPort As Integer
        If Not Integer.TryParse(PortTextBox.Text, deltaPort) OrElse deltaPort < 1 OrElse deltaPort > 65535 Then
            MessageBox.Show("Port number must be an integer between 1 and 65535.", "Invalid Port Number", MessageBoxButtons.OK, MessageBoxIcon.Exclamation)
            PortTextBox.SelectAll()
            e.Cancel = True
        End If
    End Sub

    'The InvokeAppendOutput method could easily be replaced with a lambda method passed
    'to the ConnectionInfo contstructor in the ConnectButton_CheckChanged event handler
    Private Sub InvokeAppendOutput(message As String)
        Dim doAppendOutput As New Action(Of String)(AddressOf InputForm.AppendOutput)
        Me.Invoke(doAppendOutput, message)
    End Sub

    Public Function getConnection() As ConnectionInfo1
        Return _Connection
    End Function

    Public Sub AppendOut()

    End Sub

    Public Function ServerConnected() As Boolean
        If _Connection IsNot Nothing AndAlso _Connection.Client.Connected AndAlso _Connection.Stream IsNot Nothing Then
            If ConnectButton.Checked = False Then
                ConnectButton.Checked = True
            End If
            Return True
        Else
            If ConnectButton.Checked = True Then
                ConnectButton.Checked = False
            End If
            Return False
        End If
    End Function


End Class

'Encapuslates the client connection and provides a state object for async read operations
Public Class ConnectionInfo1
    Private _AppendMethod As Action(Of String)
    Public ReadOnly Property AppendMethod As Action(Of String)
        Get
            Return _AppendMethod
        End Get
    End Property

    Private _Client As TcpClient
    Public ReadOnly Property Client As TcpClient
        Get
            Return _Client
        End Get
    End Property

    Private _Stream As NetworkStream
    Public ReadOnly Property Stream As NetworkStream
        Get
            Return _Stream
        End Get
    End Property

    Public _LastReadLength As Integer
    Public ReadOnly Property LastReadLength As Integer
        Get
            Return _LastReadLength
        End Get
    End Property
    Public UserID As String
    Public str As String
    Private count As Integer
    Private strlen As Integer
    Private _Buffer(63) As Byte
    Private _Buffer2() As Byte
    Private _Offset As Integer
    Private _Offset2 As Integer
    Public _Connection As ConnectionInfo2
    Public _trd As Thread

    Public Sub New(address As IPAddress, port As Integer, append As Action(Of String))
        _AppendMethod = append
        _Client = New TcpClient
        _Client.Connect(address, port)
        Dim addressStr As String
        addressStr = _Client.Client.LocalEndPoint.ToString
        addressStr = addressStr.Split(":")(1)
        'MsgBox(addressStr)
        'Dim localport = CInt(addressStr) + 1
        '_Connection = New ConnectionInfo2(address, port, localport, AddressOf InvokeAppendOutput)
        '_trd = New Thread(AddressOf ReceiveFiles)
        '_trd.IsBackground = True
        '_trd.Start()


        _Stream = _Client.GetStream
        count = 0
    End Sub

    Private Sub ReceiveFiles()
        _Connection.AwaitData()
    End Sub

    Private Sub InvokeAppendOutput(message As String)
        Dim doAppendOutput As New Action(Of String)(AddressOf InputForm.AppendOutput)
    End Sub

    Public Sub AwaitData()
        _Stream.BeginRead(_Buffer, 0, _Buffer.Length, AddressOf DoReadData, Me)
    End Sub

    Public Sub Close()
        If _Client IsNot Nothing Then _Client.Close()
        _Client = Nothing
        _Stream = Nothing
    End Sub

    'function for image receiving
    Public Sub AwaitData2()
        'put the receive data into the buffer with _Offset2 which indicate the offset after last data receive
        _Stream.BeginRead(_Buffer2, _Offset2, _Buffer2.Length - _Offset2, AddressOf DoReadData2, Me)
    End Sub

    Private Sub DoReadData2(ByVal result As IAsyncResult)
        Dim info As ConnectionInfo1 = CType(result.AsyncState, ConnectionInfo1)
        Try
            If info._Stream IsNot Nothing AndAlso info._Stream.CanRead Then
                info._LastReadLength = info._Stream.EndRead(result)
                If info._LastReadLength > 0 Then
                    _Offset2 += info.LastReadLength
                    If _Offset2 < strlen Then
                        info.AwaitData2()

                    ElseIf _Offset2 = strlen Then
                        'convert the byte to jpg file, added a counter for received pictures.
                        ConvertBytesToImageFile(info._Buffer2, "C:\Users\TouchScreen\Documents\HerbariumPictures\test" + CStr(count) + ".jpg")
                        'reply the image receive notification to server
                        Dim bb() As Byte = System.Text.Encoding.ASCII.GetBytes("received" + Chr(13) + Chr(10))
                        info.Stream.Write(bb, 0, bb.Length)
                        'reset parameters for next picture receive
                        _Offset2 = 0
                        _Offset = 0
                        ReDim _Buffer(31)
                        'back to AwaitData() to receive the length string
                        info.AwaitData()

                        count += 1
                    ElseIf _Offset2 > strlen Then
                        MsgBox("receive exceed")

                    End If
                End If
            End If

        Catch ex As Exception
            info._LastReadLength = -1
            info._AppendMethod(ex.Message)
        End Try
    End Sub

    Public Function ConvertBytesToImageFile(ByVal ImageData As Byte(), ByVal FilePath As String)
        If IsNothing(ImageData) = True Then

            'Throw New ArgumentNullException("Image Binary Data Cannot be Null or Empty", "ImageData")
        End If
        Try
            Dim fs As IO.FileStream = New IO.FileStream(FilePath, IO.FileMode.OpenOrCreate, IO.FileAccess.Write)
            Dim bw As IO.BinaryWriter = New IO.BinaryWriter(fs)
            bw.Write(ImageData)
            bw.Flush()
            bw.Close()
            fs.Close()
            bw = Nothing
            fs.Dispose()

        Catch ex As Exception

        End Try
    End Function

    Private Sub DoReadData(ByVal result As IAsyncResult)
        Dim info As ConnectionInfo1 = CType(result.AsyncState, ConnectionInfo1)
        Try
            If info._Stream IsNot Nothing AndAlso info._Stream.CanRead Then
                info._LastReadLength = info._Stream.EndRead(result)
                If info._LastReadLength > 0 Then

                    _Offset += info.LastReadLength
                    If _Offset < 32 Then

                        info.AwaitData()
                    Else

                        Dim message As String = System.Text.Encoding.Unicode.GetString(info._Buffer)

                        Dim length As String = Mid(message, 9)
                        Dim type As String = Mid(message, 1, 8)


                        Dim j As Integer
                        strlen = CInt(length)
                        ReDim _Buffer2(strlen)
                        j = CInt(type)
                        If j = 1 Then
                            Dim bb() As Byte = System.Text.Encoding.ASCII.GetBytes("received" + Chr(13) + Chr(10))
                            info.Stream.Write(bb, 0, bb.Length)
                            info._AppendMethod(message)
                            info.str = message
                            info.AwaitData2()
                        ElseIf j = 2 Then
                            info.UserID = Mid(message, 9)
                        End If


                    End If

                End If
            End If
        Catch ex As Exception
            info._LastReadLength = -1
            info._AppendMethod(ex.Message)
        End Try

    End Sub



End Class

Public Class ConnectionInfo2
    Private _AppendMethod As Action(Of String)
    Public ReadOnly Property AppendMethod As Action(Of String)
        Get
            Return _AppendMethod
        End Get
    End Property

    Private _Client As TcpClient
    Public ReadOnly Property Client As TcpClient
        Get
            Return _Client
        End Get
    End Property

    Private _Stream As NetworkStream
    Public ReadOnly Property Stream As NetworkStream
        Get
            Return _Stream
        End Get
    End Property

    Public _LastReadLength As Integer
    Public ReadOnly Property LastReadLength As Integer
        Get
            Return _LastReadLength
        End Get
    End Property
    Public UserID As String
    Public str As String
    Private count As Integer
    Private strlen As Integer
    Private _Buffer(63) As Byte
    Private _Buffer2() As Byte
    Private _Offset As Integer
    Private _Offset2 As Integer


    Public Sub New(address As IPAddress, port As Integer, localport As Integer, append As Action(Of String))
        _AppendMethod = append
        Dim ipLocalEndPoint As New IPEndPoint(0, localport)
        _Client = New TcpClient(ipLocalEndPoint)
        _Client.Connect(address, port)
        _Stream = _Client.GetStream
        count = 0
    End Sub

    Public Sub AwaitData()
        _Stream.BeginRead(_Buffer, 0, _Buffer.Length, AddressOf DoReadData, Me)
    End Sub

    Public Sub Close()
        If _Client IsNot Nothing Then _Client.Close()
        _Client = Nothing
        _Stream = Nothing
    End Sub

    'function for image receiving
    Public Sub AwaitData2()
        'put the receive data into the buffer with _Offset2 which indicate the offset after last data receive
        _Stream.BeginRead(_Buffer2, _Offset2, _Buffer2.Length - _Offset2, AddressOf DoReadData2, Me)
    End Sub

    Private Sub DoReadData2(ByVal result As IAsyncResult)
        Dim info As ConnectionInfo2 = CType(result.AsyncState, ConnectionInfo2)
        Try
            If info._Stream IsNot Nothing AndAlso info._Stream.CanRead Then
                info._LastReadLength = info._Stream.EndRead(result)
                If info._LastReadLength > 0 Then
                    _Offset2 += info.LastReadLength
                    If _Offset2 < strlen Then
                        info.AwaitData2()

                    ElseIf _Offset2 = strlen Then
                        'convert the byte to jpg file, added a counter for received pictures.
                        ConvertBytesToImageFile(info._Buffer2, "C:\Users\TouchScreen\Documents\HerbariumPictures\test" + CStr(count) + ".jpg")
                        'reply the image receive notification to server
                        Console.WriteLine(_Offset2)
                        Dim bb() As Byte = System.Text.Encoding.ASCII.GetBytes("received" + Chr(13) + Chr(10))
                        info.Stream.Write(bb, 0, bb.Length)
                        'reset parameters for next picture receive
                        _Offset2 = 0
                        _Offset = 0
                        ReDim _Buffer(31)
                        'back to AwaitData() to receive the length string
                        info.AwaitData()

                        count += 1
                    ElseIf _Offset2 > strlen Then
                        MsgBox("receive exceed")

                    End If
                End If
            End If

        Catch ex As Exception
            info._LastReadLength = -1
            info._AppendMethod(ex.Message)
        End Try
    End Sub

    Public Function ConvertBytesToImageFile(ByVal ImageData As Byte(), ByVal FilePath As String)
        If IsNothing(ImageData) = True Then

            'Throw New ArgumentNullException("Image Binary Data Cannot be Null or Empty", "ImageData")
        End If
        Try
            Dim fs As IO.FileStream = New IO.FileStream(FilePath, IO.FileMode.OpenOrCreate, IO.FileAccess.Write)
            Dim bw As IO.BinaryWriter = New IO.BinaryWriter(fs)
            bw.Write(ImageData)
            bw.Flush()
            bw.Close()
            fs.Close()
            bw = Nothing
            fs.Dispose()
        Catch ex As Exception

        End Try
    End Function

    Private Sub DoReadData(ByVal result As IAsyncResult)
        Dim info As ConnectionInfo2 = CType(result.AsyncState, ConnectionInfo2)
        Try
            If info._Stream IsNot Nothing AndAlso info._Stream.CanRead Then
                info._LastReadLength = info._Stream.EndRead(result)
                If info._LastReadLength > 0 Then

                    _Offset += info.LastReadLength
                    If _Offset < 32 Then

                        info.AwaitData()
                    ElseIf _Offset = 32 Then

                        Dim message As String = System.Text.Encoding.Unicode.GetString(info._Buffer)

                        Dim length As String = Mid(message, 9)
                        Dim type As String = Mid(message, 1, 8)


                        Dim j As Integer
                        Console.WriteLine(111 + length)
                        Console.WriteLine(_Offset)
                        strlen = CInt(length)
                        'MsgBox(strlen)

                        ReDim _Buffer2(strlen)
                        j = CInt(type)
                        If j = 1 Then
                            Dim bb() As Byte = System.Text.Encoding.ASCII.GetBytes("received" + Chr(13) + Chr(10))
                            info.Stream.Write(bb, 0, bb.Length)
                            'info._AppendMethod(message)
                            'info.str = message
                            info.AwaitData2()
                        ElseIf j = 2 Then
                            info.UserID = Mid(message, 9)
                        End If
                    ElseIf _Offset2 > strlen Then
                        MsgBox("receive exceed")

                    End If

                End If
            End If
        Catch ex As Exception
            info._LastReadLength = -1
            info._AppendMethod(ex.Message)
        End Try

    End Sub
End Class
