Imports System.IO
Imports System.Text.RegularExpressions
Imports System.Net
Imports System.Net.Sockets


Public Class InputForm
    Public Divided As Array
    Public BarcodeString As String
    Public sendString As String
    Public TextBoxArray As TextBox()
    Private states_of_towns As String()
    Private towns As String()
    Private collectorsList As String()
    Dim day, month, year As String
    Dim months As String() = {"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec", "sept"}
    Dim monthsNumeric As String() = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "09"}
    Dim days As String() = {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th", "20th", "21st", "22nd", "23rd", "24th", "25th", "26th", "27th", "28th", "29th", "30th", "31st", "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eigth", "ninth", "tenth", "eleventh", "twelfth", "thirteenth", "fourteenth", "fifthteenth", "sixteenth", "seventeenth", "eighteenth", "nineteenth", "twentieth"}
    Dim daysNumeric As String() = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}
    Public CameraPositionValue As Integer = Val(Testing1.CameraPosition.CameraPositionSelect.Text)
    Dim firstPicLocal As New Point
    Dim firstPicBorderLocal As New Point


    Private Sub InputForm_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        'If the camera position parameter changes, the location of the top textboxes, pictures, and partition changes
        If CameraPositionValue = 3 Then
            CameraLabel.Location = New Point(571, 248)
            CameraPartitionLeft.X1 = 535
            CameraPartitionLeft.X2 = 535
            CameraPartitionRight.X1 = 732
            CameraPartitionRight.X2 = 732
            Text5.Size = New Size(0, 0)
            Text4.Size = New Size(0, 0)
            Pic1.Size = New Size(0, 0)
            Pic2.Size = New Size(0, 0)
        ElseIf CameraPositionValue = 4 Then
            CameraLabel.Location = New Point(571 + 204, 248)
            CameraPartitionLeft.X1 = 535 + 204
            CameraPartitionLeft.X2 = 535 + 204
            CameraPartitionRight.X1 = 732 + 204
            CameraPartitionRight.X2 = 732 + 204
            Text5.Size = New Size(0, 0)
            Pic1.Size = New Size(0, 0)
            Pic2.Size = New Size(0, 0)
            Pic3.Size = New Size(0, 0)
        ElseIf CameraPositionValue = 2 Then
            CameraLabel.Location = New Point(571 - 204, 248)
            CameraPartitionLeft.X1 = 535 - 204
            CameraPartitionLeft.X2 = 535 - 204
            CameraPartitionRight.X1 = 732 - 204
            CameraPartitionRight.X2 = 732 - 204
            Text5.Size = New Size(0, 0)
            Text4.Size = New Size(0, 0)
            Text3.Size = New Size(0, 0)
            Pic1.Size = New Size(0, 0)
        ElseIf CameraPositionValue = 1 Then
            CameraLabel.Location = New Point(571 - (2 * 204), 248)
            CameraPartitionLeft.X1 = 535 - (2 * 204)
            CameraPartitionLeft.X2 = 535 - (2 * 204)
            CameraPartitionRight.X1 = 732 - (2 * 204)
            CameraPartitionRight.X2 = 732 - (2 * 204)
            Text5.Size = New Size(0, 0)
            Text4.Size = New Size(0, 0)
            Text3.Size = New Size(0, 0)
            Text2.Size = New Size(0, 0)
        ElseIf CameraPositionValue = 5 Then
            CameraLabel.Location = New Point(571 + (2 * 204), 248)
            CameraPartitionLeft.X1 = 535 + (2 * 204)
            CameraPartitionLeft.X2 = 535 + (2 * 204)
            CameraPartitionRight.X1 = 732 + (2 * 204)
            CameraPartitionRight.X2 = 732 + (2 * 204)
            Pic1.Size = New Size(0, 0)
            Pic2.Size = New Size(0, 0)
            Pic3.Size = New Size(0, 0)
            Pic4.Size = New Size(0, 0)
        End If

        'Clears out picture folder everytime the input form loads 
        For Each s In System.IO.Directory.GetFiles("C:\Users\TouchScreen\Documents\HerbariumPictures")
            System.IO.File.Delete(s)
        Next s
        My.Computer.FileSystem.CopyFile( _
"C:\Users\TouchScreen\Documents\EmptyandFolderPics\White.jpg", _
"C:\Users\TouchScreen\Documents\HerbariumPictures\White.jpg")
        'Clears out .txt file
        Dim stream As New IO.StreamWriter("C:\Users\TouchScreen\Documents\TestingOutput\testing.txt", False)
        stream.WriteLine("")
        stream.Close()

        'Array that holds each of the input textboxes
        TextBoxArray = {m1p, m2v, ScientificName, Genus, Species, Ir, i, Author, cc, RecordEnteredBy, Barcode, State, County, Town, Collector, RecordNumber, Qualifier, VerbatimCollectionDate}

        'Fills the "recorded By" textbox with the login name
        RecordEnteredBy.Text = Testing1.Login.LoginTextBox.Text

        'Validate children (??)
        ValidateChildren()

        'Creates a loading bar while logging in and loading the input form
        Login.ProgressBar1.Increment(33)

        'Load states, counties, and cities in autocomplete custom source 
        ' Create a string collection for states fro autocomplete
        Dim states As New AutoCompleteStringCollection
        states.Add("Connecticut")
        states.Add("Maine")
        states.Add("Massachusetts")
        states.Add("New Hampshire")
        states.Add("Rhode Island")
        states.Add("Vermont")
        State.AutoCompleteMode = AutoCompleteMode.SuggestAppend
        State.AutoCompleteSource = AutoCompleteSource.CustomSource
        State.AutoCompleteCustomSource = states

        Login.ProgressBar1.Increment(33)
        'Create a string collection for counties for autocomplete
        Dim countiesFile As String = "C:\Users\TouchScreen\Documents\Lists\FW__Apparatus_database_and_UI\Counties_20121114.txt"
        If System.IO.File.Exists(countiesFile) = True Then
            'Read the textfile:
            Dim objReader As New System.IO.StreamReader(countiesFile)
            Dim MyFileLine As String = objReader.ReadToEnd
            objReader.Close()

            Dim Arg() As String = Split(MyFileLine, ",")    'Split file contents at ,
            Dim Counties As New AutoCompleteStringCollection
            County.AutoCompleteMode = AutoCompleteMode.SuggestAppend
            County.AutoCompleteSource = AutoCompleteSource.CustomSource
            County.AutoCompleteCustomSource = Counties
            For index = 0 To Arg.Length - 1
                Counties.Add(Arg(index))
            Next
        Else
            MessageBox.Show("File 'C:\test.txt' not found!", "www.interloper.nl", MessageBoxButtons.OK, MessageBoxIcon.Error)
        End If

        Login.ProgressBar1.Increment(33)
        'Create a string collection for cities for autcomplete
        Dim citiesFile As String = "C:\Users\TouchScreen\Documents\Lists\FW__Apparatus_database_and_UI\cities_20121114.txt"
        If System.IO.File.Exists(citiesFile) = True Then
            'Read the textfile:
            Dim objReader As New System.IO.StreamReader(citiesFile)
            Dim MyFileLine As String = objReader.ReadToEnd
            objReader.Close()
            MyFileLine = MyFileLine.Replace(vbCr, ",").Replace(vbLf, ",")
            Dim subStrings() As String = Regex.Split(MyFileLine, ",")
            ReDim towns((subStrings.Length - 1) / 3 - 2)
            For i As Integer = 3 To subStrings.Length - 2
                If i Mod 3 = 0 Then
                    towns(i / 3 - 1) = subStrings(i)
                End If
            Next
            ReDim states_of_towns((subStrings.Length - 1) / 3 - 2)
            For i As Integer = 3 To subStrings.Length - 2
                If i Mod 3 = 0 Then
                    states_of_towns(i / 3 - 1) = subStrings(i + 2)
                End If
            Next

            Dim Cities As New AutoCompleteStringCollection
            Town.AutoCompleteMode = AutoCompleteMode.SuggestAppend
            Town.AutoCompleteSource = AutoCompleteSource.CustomSource
            Town.AutoCompleteCustomSource = Cities
            Cities.AddRange(towns)
        Else
            MessageBox.Show("File 'C:\Users\TouchScreen\Documents\Lists\FW__Apparatus_database_and_UI\cities_20121114.txt' not found!", "File not found", MessageBoxButtons.OK, MessageBoxIcon.Error)
        End If

        'Autocomplete Collector Names
        Dim collectorFile As String = "C:\Users\TouchScreen\Documents\Lists\NamesOnly.txt"
        If System.IO.File.Exists(collectorFile) = True Then
            'Read the textfile:
            Dim objReader As New System.IO.StreamReader(collectorFile)
            Dim MyFileLine As String = objReader.ReadToEnd
            objReader.Close()
            Dim divided() As String = Split(MyFileLine, vbNewLine)
            ReDim collectorsList(divided.Length - 1)
            For i As Integer = 0 To divided.Length - 1
                collectorsList(i) = divided(i)
            Next
            Dim Collectors As New AutoCompleteStringCollection
            Collector.AutoCompleteMode = AutoCompleteMode.SuggestAppend
            Collector.AutoCompleteSource = AutoCompleteSource.CustomSource
            Collector.AutoCompleteCustomSource = Collectors
            Collectors.AddRange(collectorsList)

        Else
            MessageBox.Show("File 'C:\test.txt' not found!", "www.interloper.nl", MessageBoxButtons.OK, MessageBoxIcon.Error)
        End If

        ScientificName.Select()

        'Disable send button if barcode textbox empty 
        If Barcode.Text = "" Or ScientificName.Text = "" Then
            sendButton.Enabled = False
        End If
    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles sendButton.Click
        'Moves cursor to barcode textbox
        Barcode.Focus()
        'Breaks the input info into QR format and Sends the information to the .txt file
        sendString = ""
        sendString = sendString & "{"
        sendString = sendString & """m1p"":""" & m1p.Text & """$"
        sendString = sendString & """m2v"":""" & m2v.Text & ""","
        sendString = sendString & """sn"":""" & ScientificName.Text & """$"
        sendString = sendString & """g"":""" & Genus.Text & """$"
        sendString = sendString & """s"":""" & Species.Text & """$"
        sendString = sendString & """ir"":""" & Ir.Text & """$"
        sendString = sendString & """i"":""" & i.Text & """$"
        sendString = sendString & """a"":""" & Author.Text & """$"
        sendString = sendString & """st"":""" & State.Text & """$"
        sendString = sendString & """ct"":""" & County.Text & """$"
        sendString = sendString & """cl"":""" & Collector.Text & """$"
        sendString = sendString & """record"":""" & RecordNumber.Text & """$"
        sendString = sendString & """cc"":""" & cc.Text & """$"
        sendString = sendString & """RecordEnteredBy"":""" & RecordEnteredBy.Text & """$"
        sendString = sendString & """Town"":""" & Town.Text & """$"
        sendString = sendString & """IDqual"":""" & Qualifier.Text & """$"
        sendString = sendString & """BeginDate"":""" & dateLabel.Text & """$"
        sendString = sendString & """EndDate"":""" & EndingDate.Text & """$"
        sendString = sendString & """Verbatim"":""" & VerbatimCollectionDate.Text & """$"
        sendString = sendString & """Barcode"":""" & Barcode.Text & """$"

        'Sends the tag statuses. $ to separat them. 1 indicates checked, and 0 indicates not checked.
        'If MissingInformation checked then send 1
        sendString = sendString & """MI"":""" & MissingInformation.CheckState & """$"
        'If IllegibleInformation.Checked Then send 1
        sendString = sendString & """IllInfo"":""" & IllegibleInformation.CheckState & """$"
        'If Unreadable.Checked Then send 1
        sendString = sendString & """UR"":""" & Unreadable.CheckState & """$"
        'If UncertainDateCheckBox.Checked Then send 1
        sendString = sendString & """UD"":""" & UncertainDateCheckBox.CheckState & """$"
        'If Empty.Checked Then send 1
        sendString = sendString & """Emp"":""" & Empty.CheckState & """$"
        'If Folder.Checked Then send 1
        sendString = sendString & """Fol"":""" & Folder.CheckState & """$"
        'If Other is Checked Then send 1
        sendString = sendString & """Oth"":""" & Other.CheckState & """$"
        'send user id
        sendString = sendString & "#" & Login.getConnection.UserID
        sendString = sendString & "}"
        'Must end with new line in order to send to server
        sendString = sendString & vbNewLine
        My.Computer.FileSystem.WriteAllText("C:\Users\TouchScreen\Documents\TestingOutput\testing.txt", sendString, True)

        'Send info to server
        If Login.getConnection IsNot Nothing AndAlso Login.getConnection.Client.Connected AndAlso Login.getConnection.Stream IsNot Nothing Then
            Dim buffer() As Byte = System.Text.Encoding.ASCII.GetBytes(sendString)
            Login.getConnection.Stream.Write(buffer, 0, buffer.Length)

            'Stops and starts timer
            Timer1.Stop()
            TimerLabel.Text = "00"
            Timer1.Start()

            'Creating border and pic arrays
            Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
            Dim Pics = {Pic1, Pic2, Pic3, Pic4, Pic5, Pic6, Pic7}

            'Make sure correct number of textboxes and pics
            'Fill picture boxes with most recent pictures
            For index = 0 To Pics.Length - 1
                If Pics(index).Enabled = True Then
                    If Pics(index).Size <> New Size(0, 0) Then
                        Pics(index).Left = Pics(index).Left + 204
                    End If
                End If
            Next

            Dim fileList As New List(Of FileInfo)
            fileList.AddRange(New DirectoryInfo("C:\Users\TouchScreen\Documents\HerbariumPictures\").GetFiles("*.jpg"))

            Dim newestFiles As System.Collections.Generic.IEnumerable(Of FileInfo) = From file In fileList Order By file.CreationTime Descending Take 1

            Dim fileInfoList As List(Of FileInfo) = newestFiles.ToList

            'Determine where first picture appears
            If CameraPositionValue = 3 Then
                firstPicLocal = New Point(793, 81)
                firstPicBorderLocal = New Point(763, 58)
            ElseIf CameraPositionValue = 4 Then
                firstPicLocal = New Point(997, 81)
                firstPicBorderLocal = New Point(967, 58)
            ElseIf CameraPositionValue = 5 Then
                firstPicLocal = New Point(1201, 81)
                firstPicBorderLocal = New Point(1171, 58)
            ElseIf CameraPositionValue = 1 Then
                firstPicLocal = New Point(385, 81)
                firstPicBorderLocal = New Point(355, 58)
            ElseIf CameraPositionValue = 2 Then
                firstPicLocal = New Point(589, 81)
                firstPicBorderLocal = New Point(559, 58)
            End If

            'Move next picture into correct spot
            For index = 0 To Pics.Length - 1
                If Pics(index).Size <> New Size(0, 0) Then
                    If Pics(index).Location = New Point(1813, 81) Then
                        Pics(index).Location = firstPicLocal
                        Pics(index).Load("C:\Users\TouchScreen\Documents\HerbariumPictures\" + fileInfoList.Item(0).Name)
                    End If
                End If
            Next

            'Create a folder picture
            For index1 = 0 To Borders.Length - 1
                For index2 = 0 To Pics.Length - 1
                    If Borders(index1).Location = firstPicBorderLocal And Borders(index1).FillColor = Folder.BackColor And Pics(index2).Location = firstPicLocal Then
                        Pics(index2).Load("C:\Users\TouchScreen\Documents\EmptyandFolderPics\Folder.jpg")
                    End If
                Next
            Next

            'Create an empty holding place
            For index1 = 0 To Borders.Length - 1
                For index2 = 0 To Pics.Length - 1
                    If Borders(index1).Location = firstPicBorderLocal And Borders(index1).FillColor = Empty.BackColor And Pics(index2).Location = firstPicLocal Then
                        Pics(index2).Load("C:\Users\TouchScreen\Documents\EmptyandFolderPics\Folder.jpg")
                    End If
                Next
            Next


            'After info is sent, clear checkboxes
            Dim CheckBoxes = {MissingInformation, IllegibleInformation, Unreadable, UncertainDateCheckBox, Empty, Folder, Other}
            For index = 0 To CheckBoxes.Length - 1
                If CheckBoxes(index).CheckState = CheckState.Checked Then
                    CheckBoxes(index).CheckState = CheckState.Unchecked
                End If
            Next


            'Move TextBoxes at top to follow slot on conveyor
            Dim TextBoxes = {Text1, Text2, Text3, Text4, Text5}
            For index = 0 To TextBoxes.Length - 1
                If TextBoxes(index).Size <> New Size(0, 0) Then
                    TextBoxes(index).Left = TextBoxes(index).Left + 204
                End If
            Next

            For index = 0 To TextBoxes.Length - 1
                If TextBoxes(index).Size <> New Size(0, 0) Then
                    If CameraPositionValue = 3 Then
                        If TextBoxes(index).Location = New Point(793, 81) Then
                            TextBoxes(index).Location = New Point(181, 81)
                        End If
                    ElseIf CameraPositionValue = 4 Then
                        If TextBoxes(index).Location = New Point(997, 81) Then
                            TextBoxes(index).Location = New Point(181, 81)
                        End If
                    ElseIf CameraPositionValue = 5 Then
                        If TextBoxes(index).Location = New Point(1201, 81) Then
                            TextBoxes(index).Location = New Point(181, 81)
                        End If
                    ElseIf CameraPositionValue = 1 Then
                        If TextBoxes(index).Location = New Point(385, 81) Then
                            TextBoxes(index).Location = New Point(181, 81)
                        End If
                    ElseIf CameraPositionValue = 2 Then
                        If TextBoxes(index).Location = New Point(589, 81) Then
                            TextBoxes(index).Location = New Point(181, 81)
                        End If
                    End If
                End If
            Next

            'Make sure textboxes say barcode numbers
            For index = 0 To TextBoxes.Length - 1
                If TextBoxes(index).Location = New Point(181, 81) Then
                    TextBoxes(index).Text = Barcode.Text
                End If
            Next

            'Move Borders with textboxes and pictures as conveyor moves
            For index = 0 To Borders.Length - 1
                Borders(index).Left = Borders(index).Left + 204
            Next

            For index = 0 To Borders.Length - 1
                If Borders(index).Location = New Point(1783, 58) Then
                    Borders(index).Location = New Point(151, 58)
                    Borders(index).FillColor = Color.Black
                End If
            Next

            For Each i As TextBox In TextBoxArray
                If i.BackColor <> Color.Beige Then
                    i.Clear()
                End If
            Next
            dateLabel.Text = ""

            'Move cursor back to barcode
            Barcode.Select()
        Else
            'If not connected to server, alert user
            MessageBox.Show("The server has disconnected! Please reconnect the server before continuing.")
        End If




    End Sub

    'This was created to not allow the user to press send until all necessary textboxes are filled. We commented this out because we often won't have all of this information. 
    'Maybe we should consider using this just for barcode and scientific name
    Private Sub CheckCompletedness()
        'If MissingInformation.Checked = False Then
        '    Dim TextBoxCheck As Array = {Genus, Species, Ir, i, Author, cc, RecordEnteredBy, State, County, Town, Collector, VerbatimCollectionDate}
        '    For Each i As TextBox In TextBoxCheck
        '        If i.Text = "" Then
        '            If Not i.Equals(Qualifier) Then
        '                sendButton.Enabled = False
        '                Exit Sub
        '            End If
        '        End If
        '    Next
        'End If
        sendButton.Enabled = True
    End Sub

    'Change the border color as tags checked
    Private Sub IllegibleInformation_CheckedChange(sender As Object, e As EventArgs)
        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(151, 58) Then
                Borders(index).FillColor = IllegibleInformation.BackColor
            End If
        Next
    End Sub

    Private Sub Unreadable_CheckedChanged(sender As Object, e As EventArgs)

        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(151, 58) Then
                Borders(index).FillColor = Unreadable.BackColor
            End If
        Next
    End Sub

    Private Sub Folder_CheckedChanged(sender As Object, e As EventArgs)
        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(151, 58) Then
                Borders(index).FillColor = Folder.BackColor
            End If
        Next
        Dim texts = {Text1, Text2, Text3, Text4, Text5}
        If Folder.Checked = True Then
            For index = 0 To texts.Length - 1
                If texts(index).Location = New Point(181, 81) Then
                    texts(index).Text = "Folder"
                End If
            Next
        End If

        'After info is sent, clear textboxes
        For Each i As TextBox In TextBoxArray
            i.Clear()
            If Not (i.Name = "m1p" Or i.Name = "m2v") Then
                i.TabStop = True
            End If
        Next
        BarCodeTextBox.Clear()
        RecordEnteredBy.Text = Testing1.Login.LoginTextBox.Text
        For Each i As TextBox In TextBoxArray
            i.BackColor = RecordNumber.BackColor
        Next
        Genus.Focus()

    End Sub
    'change colors of borders based on flag
    Private Sub MissingInformation_CheckedChanged(sender As Object, e As EventArgs)
        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(151, 58) Then
                Borders(index).FillColor = MissingInformation.BackColor
            End If
        Next

        CheckCompletedness()
    End Sub

    Private Sub Empty_CheckedChanged(sender As Object, e As EventArgs)
        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(151, 58) Then
                Borders(index).FillColor = Empty.BackColor
            End If
        Next
        Dim texts = {Text1, Text2, Text3}
        If Empty.Checked = True Then
            For index = 0 To texts.Length - 1
                If texts(index).Location = New Point(181, 81) Then
                    texts(index).Text = "Empty Space"
                End If
            Next
        End If
    End Sub

    Private Sub UncertainDateCheckBox_CheckedChanged(sender As Object, e As EventArgs)
        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(151, 58) Then
                Borders(index).FillColor = UncertainDateCheckBox.BackColor
            End If
        Next
    End Sub

    Private Sub Other_CheckedChanged(sender As Object, e As EventArgs)
        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(151, 58) Then
                Borders(index).FillColor = Other.BackColor
            End If
        Next
    End Sub

    'Separates QR code information into corrct textboxes. BarCodeTextBox is a box of sixe 0,0 when running.
    Private Sub TextBox_TextChanged(sender As Object)
        If sender.Text = "{" Then
            sender.Text = "[TAB] to Cancel"
            BarCodeTextBox.Clear()
            BarCodeTextBox.Select()
            For Each i As TextBox In TextBoxArray
                i.Enabled = False
            Next
        End If
        CheckCompletedness()
    End Sub

    Private Sub ScientificName_TextChanged(sender As Object, e As EventArgs) Handles ScientificName.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    Private Sub Genus_TextChanged(sender As Object, e As EventArgs) Handles Genus.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    Private Sub Species_TextChanged(sender As Object, e As EventArgs) Handles Species.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    Private Sub Ir_TextChanged(sender As Object, e As EventArgs) Handles Ir.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    Private Sub i_TextChanged(sender As Object, e As EventArgs) Handles i.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    Private Sub Author_TextChanged(sender As Object, e As EventArgs) Handles Author.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    Private Sub cc_TextChanged(sender As Object, e As EventArgs) Handles cc.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    Private Sub RecordEnteredBy_TextChanged(sender As Object, e As EventArgs) Handles RecordEnteredBy.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    'Makes sure genus and species get put in scientific name
    Private Sub Species_Leave(sender As Object, e As EventArgs) Handles Species.Leave
        If ScientificName.Text = "" Then
            ScientificName.Text = Genus.Text & "" & Species.Text
        End If
    End Sub

    'Autocomplete town
    Private Sub State_Leave(sender As Object, e As EventArgs) Handles State.Leave
        Dim acs As New AutoCompleteStringCollection
        Dim count As Integer
        For Each i As String In states_of_towns
            If State.Text.ToLower = i.ToLower Then
                count += 1
            End If
        Next
        Dim NewNameString(count - 1) As String
        Dim index As Integer = 0
        For j = 0 To states_of_towns.Length - 1
            If states_of_towns(j).ToLower = State.Text.ToLower Then
                NewNameString(index) = towns(j)
                index += 1
            End If
        Next

        Town.AutoCompleteMode = AutoCompleteMode.SuggestAppend
        Town.AutoCompleteSource = AutoCompleteSource.CustomSource
        Town.AutoCompleteCustomSource = acs
        acs.AddRange(NewNameString)
    End Sub

    Private Sub State_TextChanged(sender As Object, e As EventArgs) Handles State.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    Private Sub County_TextChanged(sender As Object, e As EventArgs) Handles County.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    Private Sub Town_TextChanged(sender As Object, e As EventArgs) Handles Town.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    Private Sub Collector_TextChanged(sender As Object, e As EventArgs) Handles Collector.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    Private Sub Qualifier_TextChanged(sender As Object, e As EventArgs) Handles Qualifier.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    Private Sub VerbatimCollectionDate_TextChanged(sender As Object, e As EventArgs) Handles VerbatimCollectionDate.TextChanged
        TextBox_TextChanged(sender)
    End Sub

    Private Sub Barcode_TextChanged(sender As Object, e As EventArgs) Handles Barcode.TextChanged
        TextBox_TextChanged(sender)
        If Barcode.Text <> "" Then
            sendButton.Enabled = True
        End If
    End Sub

    'Create a timer
    Private Sub Timer1_Tick(sender As Object, e As EventArgs) Handles Timer1.Tick
        If TimerLabel.Text < 1000 Then
            TimerLabel.Text = Val(TimerLabel.Text) + 1
        Else
            Timer1.Stop()
            TimerLabel.Text = "Late"
        End If
    End Sub

    'Move to empty textbox
    Private Sub MoveToNextEmptyTextbox()
        For Each i As TextBox In TextBoxArray
            If i.Text = "" Then
                i.Select()
                Exit For
            End If
        Next
    End Sub

    Private Sub InputForm_Disposed(sender As Object, e As EventArgs) Handles Me.Disposed
        Application.Exit()
    End Sub


    'Places the QR information in the correct textbox
    Private Sub BarCodeTextBox_Leave(sender As Object, e As EventArgs) Handles BarCodeTextBox.Leave
        For Each i As TextBox In TextBoxArray
            i.Clear()
        Next

        BarCodeTextBox.Text = "{" & BarCodeTextBox.Text

        Divided = BarCodeTextBox.Text.Split(",")
        Try
            For index = 0 To Divided.Length - 1
                Divided(index) = Divided(index).Replace("""", "")
                Divided(index) = Divided(index).Replace("{", "")
                Divided(index) = Divided(index).Replace("}", "")
                Dim Subdivided As Array
                Subdivided = Divided.GetValue(index).Split(":")

                Select Case Subdivided(0)
                    Case "m1p"
                        m1p.Text = Subdivided(1)
                    Case "m2v"
                        m2v.Text = Subdivided(1)
                    Case "g"
                        Genus.Text = Subdivided(1)
                    Case "s"
                        Species.Text = Subdivided(1)
                    Case "ir"
                        Ir.Text = Subdivided(1)
                    Case "i"
                        i.Text = Subdivided(1)
                    Case "a"
                        Author.Text = Subdivided(1)
                    Case "st"
                        State.Text = Subdivided(1)
                    Case "ct"
                        County.Text = Subdivided(1)
                    Case "cl"
                        Collector.Text = Subdivided(1)
                    Case "record"
                        RecordNumber.Text = Subdivided(1)
                    Case "cc"
                        cc.Text = Subdivided(1)
                    Case "sn"
                        ScientificName.Text = Subdivided(1)


                End Select
            Next index
            'Changes the background color of the textboxes that were filled with the QR code to beige
            'This helps distinguish information manually entered from automatically entered
            For Each i As TextBox In TextBoxArray
                i.Enabled = True
                If i.Text <> "" Then
                    i.TabStop = False
                    i.BackColor = Color.Beige
                End If
                ScientificName.BackColor = Color.Beige
                RecordEnteredBy.BackColor = Color.Beige
            Next
        Catch ex As Exception

        End Try

        RecordEnteredBy.Text = Testing1.Login.LoginTextBox.Text()

        'fill in scientific name is genus and species filled in
        If ScientificName.Text = "" Then
            ScientificName.Text = Genus.Text & " " & Species.Text
        End If

        'Moves the curser to the barocde textbox to prepare for next barcode
        Barcode.Select()
    End Sub


    'Fills in beginning and ending dates with verbatim date information in ISO 8601 format
    Private Sub VerbatimCollectionDate_Leave(sender As Object, e As EventArgs) Handles VerbatimCollectionDate.Leave
        day = ""
        month = ""
        year = ""
        Dim subStrings As String()
        Dim sDate As String = VerbatimCollectionDate.Text

        dateLabel.Text = ""

        If sDate <> "" Then

            sDate = sDate.ToLower
            sDate = OnlyAlphaNumericChars(sDate)
            subStrings = sDate.Split(" ")


            'Search for one and only one
            For index As Integer = 0 To subStrings.Length - 1
                If isYear(subStrings(index)) And Not isMonth(subStrings(index)) And Not isDay(subStrings(index)) Then
                    year = subStrings(index)
                    subStrings(index) = ""
                ElseIf Not isYear(subStrings(index)) And isMonth(subStrings(index)) And Not isDay(subStrings(index)) Then
                    month = subStrings(index)
                    subStrings(index) = ""
                ElseIf Not isYear(subStrings(index)) And Not isMonth(subStrings(index)) And isDay(subStrings(index)) Then
                    day = subStrings(index)
                    subStrings(index) = ""
                End If
            Next

            'Search for if other possibilities are already matched
            pullOutSingles(day, month, year, subStrings)

            'Order of presedence
            For index As Integer = 0 To subStrings.Length - 1
                If isYear(subStrings(index)) And year = "" Then
                    year = subStrings(index)
                    subStrings(index) = ""
                ElseIf isYear(subStrings(index)) And month = "" And isMonth(year) Then
                    month = year
                    year = subStrings(index)
                    subStrings(index) = ""
                ElseIf isYear(subStrings(index)) And isDay(year) And day = "" Then
                    day = year
                    year = subStrings(index)
                    subStrings(index) = ""
                ElseIf isMonth(subStrings(index)) And isDay(subStrings(index)) And month = "" And day = "" Then
                    month = subStrings(index)
                    subStrings(index) = ""
                End If
            Next

            'Search for if other possibilities are already matched
            pullOutSingles(day, month, year, subStrings)

            For index As Integer = 0 To subStrings.Length - 1
                If subStrings(index) <> "" Then
                    'ErrorText.Text &= vbNewLine & "Unused tokens"
                    IllegibleInformation.Checked = True
                End If
            Next

            'Remove unusable information
            If year = "" Then
                day = ""
                month = ""
            ElseIf month = "" Then
                day = ""
            End If

            'Convert to standard
            day = toDay(day)
            month = toMonth(month)
            year = toYear(year)

            'Display information
            dateLabel.Text = year & "-" & month & "-" & day
        End If
    End Sub

    'The rest helps pull ISO date from verbatim
    Private Sub pullOutSingles(ByRef day As String, ByRef month As String, ByRef year As String, ByRef subStrings As String())
        For index As Integer = 0 To subStrings.Length - 1
            If isYear(subStrings(index)) And (Not isMonth(subStrings(index)) Or month <> "") And isDay(subStrings(index)) And year <> "" And day = "" Then
                day = subStrings(index)
                subStrings(index) = ""
            ElseIf isYear(subStrings(index)) And (Not isMonth(subStrings(index)) Or month <> "") And isDay(subStrings(index)) And year = "" And day <> "" Then
                year = subStrings(index)
                subStrings(index) = ""
            ElseIf isYear(subStrings(index)) And isMonth(subStrings(index)) And (Not isDay(subStrings(index)) Or day <> "") And year = "" And month <> "" Then
                year = subStrings(index)
                subStrings(index) = ""
            ElseIf isYear(subStrings(index)) And isMonth(subStrings(index)) And (Not isDay(subStrings(index)) Or day <> "") And year <> "" And month = "" Then
                month = subStrings(index)
                subStrings(index) = ""
            ElseIf (Not isYear(subStrings(index)) Or year <> "") And isMonth(subStrings(index)) And isDay(subStrings(index)) And month = "" And day <> "" Then
                month = subStrings(index)
                subStrings(index) = ""
            ElseIf (Not isYear(subStrings(index)) Or year <> "") And isMonth(subStrings(index)) And isDay(subStrings(index)) And month <> "" And day = "" Then
                day = subStrings(index)
                subStrings(index) = ""
            End If
        Next
    End Sub

    Private Function toYear(token As String) As String

        If IsNumeric(token) Then
            Dim number = CInt(token)
            If number < 100 Then
                If number <= (Now.Year - 2000) Then
                    If number < 10 Then
                        Return "200" & number
                    Else
                        Return "20" & number
                    End If
                Else
                    If number < 10 Then
                        Return "190" & number
                    Else
                        Return "19" & number
                    End If
                End If
            ElseIf number > Now.Year Then
                Return "> now error"
            End If
        End If

        Return token
    End Function

    Private Function toMonth(token As String) As String
        If Not IsNumeric(token) Then
            For m = 0 To months.Length - 1
                'Set month to this month
                If token.Equals(months(m)) Then
                    Return monthsNumeric.ElementAt(m)
                End If
            Next
        ElseIf IsNumeric(token) Then
            Dim number = CInt(token)
            If number < 10 Then
                Return ("0" & number)
            Else : Return number
            End If
        End If

        Return token
    End Function

    Private Function toDay(token As String) As String

        If IsNumeric(token) Then
            Dim number = CInt(token)
            If number < 10 Then
                Return "0" & number
            End If
        ElseIf Not IsNumeric(token) Then
            'Loop to find which month it is
            For d = 0 To days.Length - 1
                'Set month to this month
                If token.Equals(days(d)) Then
                    Return daysNumeric(d)
                End If
            Next
        End If

        Return token
    End Function

    Private Function isYear(token As String) As Boolean
        If IsNumeric(token) Then
            Dim number = CInt(token)
            If number < Now.Year Then
                If number > 99 And number < 1600 Then
                    'ErrorText.Text = vbNewLine & "Warning: range of year may be bad"
                    IllegibleInformation.Checked = True
                End If
                Return True
            Else
                'ErrorText.Text = vbNewLine & "Number greater than current year"
                IllegibleInformation.Checked = True
            End If
        End If
        Return False
    End Function

    Private Function isMonth(token As String) As Boolean

        If Not IsNumeric(token) Then
            'Loop to find which month it is
            For m = 0 To months.Length - 1
                'Set month to this month
                If token.Equals(months(m)) Then
                    Return True
                    Exit For
                End If
            Next
        ElseIf IsNumeric(token) Then
            Dim number = CInt(token)
            If number > 0 And number <= 12 Then
                Return True
            End If
        End If
        Return False

    End Function

    Private Function isDay(token As String) As Boolean
        If IsNumeric(token) Then
            Dim number = CInt(token)
            If number > 0 And number <= 31 Then
                Return True
            End If
        ElseIf Not IsNumeric(token) Then
            'Loop to find which month it is
            For d = 0 To days.Length - 1
                'Set month to this month
                If token.Equals(days(d)) Then
                    Return True
                    Exit For
                End If
            Next
        End If

        Return False
    End Function

    Public Function OnlyAlphaNumericChars(ByVal OrigString As _
String) As String
        '***********************************************************
        'INPUT:  Any String
        'OUTPUT: The Input String with all non-alphanumeric characters 
        '        removed
        'EXAMPLE Debug.Print OnlyAlphaNumericChars("Hello World!")
        'output = "HelloWorld")
        'NOTES:  Not optimized for speed and will run slow on long
        '        strings.  If you plan on using long strings, consider 
        '        using alternative method of appending to output string,
        '        such as the method at
        '***********************************************************
        Dim lLen As Long
        Dim sAns As String
        Dim lCtr As Long
        Dim sChar As String

        OrigString = Trim(OrigString)
        lLen = Len(OrigString)
        For lCtr = 1 To lLen
            sChar = Mid(OrigString, lCtr, 1)
            If IsAlphaNumeric(Mid(OrigString, lCtr, 1)) Then
                sAns = sAns & sChar
            Else
                sAns = sAns & " "
            End If
        Next
        OnlyAlphaNumericChars = sAns
    End Function

    Private Function IsAlphaNumeric(sChr As String) As Boolean
        IsAlphaNumeric = sChr Like "[0-9 A-Z a-z ]"
    End Function

    Shared Sub AppendOutput()

    End Sub

    'Open camera position change form
    Private Sub CameraLocationToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles CameraLocationToolStripMenuItem.Click
        Testing1.CameraPosition.Show()
    End Sub
    'Open about form
    Private Sub LearnAboutInputApplicationToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles LearnAboutInputApplicationToolStripMenuItem.Click
        Testing1.About.Show()
    End Sub
    'Open login form
    Private Sub LogoutAndReturnToLoginToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles LogoutAndReturnToLoginToolStripMenuItem.Click
        Login.getConnection.Close()
        Testing1.Login.Show()
        Me.Hide()
    End Sub
End Class