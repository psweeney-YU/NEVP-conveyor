Imports System.IO
Imports System.Text.RegularExpressions

Public Class InputForm
    Public Divided As Array
    Public BarcodeString As String
    Public sendString As String

    Private Sub Genus_Leave(sender As Object, e As EventArgs) Handles Genus.Leave
        Divided = Genus.Text.Split(",")

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
                Case "cc"
                    cc.Text = Subdivided(1)

            End Select

        Next index


    End Sub




    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles sendButton.Click
        'Stops and starts timer
        Timer1.Stop()
        TimerLabel.Text = "00"
        Timer1.Start()

        'Sends the information to the .txt file
        sendString = ""
        sendString = sendString & "{"
        sendString = sendString & """m1p"":""" & m1p.Text & ""","
        sendString = sendString & """m2v"":""" & m2v.Text & ""","
        sendString = sendString & """g"":""" & Genus.Text & ""","
        sendString = sendString & """s"":""" & Species.Text & ""","
        sendString = sendString & """ir"":""" & Ir.Text & ""","
        sendString = sendString & """i"":""" & i.Text & ""","
        sendString = sendString & """a"":""" & Author.Text & ""","
        sendString = sendString & """st"":""" & State.Text & ""","
        sendString = sendString & """ct"":""" & County.Text & ""","
        sendString = sendString & """cl"":""" & Collector.Text & ""","
        sendString = sendString & """cc"":""" & cc.Text & """"
        sendString = sendString & """RecordEnteredBy"":""" & RecordEnteredBy.Text & """"
        sendString = sendString & """Town"":""" & Town.Text & """"
        sendString = sendString & """IDqual"":""" & Qualifier.Text & """"
        sendString = sendString & """Record"":""" & Record.Text & """"
        sendString = sendString & """BeginDate"":""" & BeginningDateCollectedYear.Text & "/" & BeginningDateMon.Text & "/" & BeginningDateDay.Text & """"
        sendString = sendString & """EndDate"":""" & EndingDateCollectedYear.Text & "/" & EndingDateMon.Text & "/" & EndingDateDay.Text & """"
        sendString = sendString & """Verbatim"":""" & VerbatimCollectionDate.Text & """"
        sendString = sendString & "}"
        If MissingInformation.Checked Then
            sendString = sendString & " Missing Information"
        End If
        If IllegibleInformation.Checked Then
            sendString = sendString & " Illegible Information"
        End If
        If Unreadable.Checked Then
            sendString = sendString & " Barcode Unreadable"
        End If
        If Empty.Checked Then
            sendString = sendString & " Incorrect Information"
        End If
        If Folder.Checked Then
            sendString = sendString & " Folder"
        End If
        If Other.Checked Then
            sendString = sendString & " Other Problem"
        End If

        sendString = sendString & vbNewLine
        My.Computer.FileSystem.WriteAllText("C:\Users\TouchScreen\Documents\TestingOutput\testing.txt", sendString, True)

        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        Dim Pics = {Pic1, Pic2, Pic3, Pic4, Pic5}

        'Fill picture boxes with most recent pictures
        For index = 0 To Pics.Length - 1
            Pics(index).Left = Pics(index).Left + 204
        Next

        Dim fileList As New List(Of FileInfo)
        fileList.AddRange(New DirectoryInfo("C:\Users\TouchScreen\Documents\HerbariumPictures").GetFiles("*.jpg"))

        Dim newestFiles As System.Collections.Generic.IEnumerable(Of FileInfo) = From file In fileList Order By file.CreationTime Descending Take 1

        Dim fileInfoList As List(Of FileInfo) = newestFiles.ToList

        For index = 0 To Pics.Length - 1
            If Pics(index).Location = New Point(1813, 27) Then
                Pics(index).Location = New Point(793, 27)
                Pics(index).Load("C:\Users\TouchScreen\Documents\HerbariumPictures\" + fileInfoList.Item(0).Name)
            End If
        Next



        'Create a folder picture
        For index1 = 0 To Borders.Length - 1
            For index2 = 0 To Pics.Length - 1
                If Borders(index1).Location = New Point(559, 4) And Borders(index1).FillColor = Folder.BackColor And Pics(index2).Location = New Point(793, 27) Then
                    Pics(index2).Load("C:\Users\TouchScreen\Documents\HerbariumPictures\Folder.jpg")
                End If
            Next
        Next

        'Create an empty holding place
        For index1 = 0 To Borders.Length - 1
            For index2 = 0 To Pics.Length - 1
                If Borders(index1).Location = New Point(559, 4) And Borders(index1).FillColor = Empty.BackColor And Pics(index2).Location = New Point(793, 27) Then
                    Pics(index2).Load("C:\Users\TouchScreen\Documents\HerbariumPictures\Folder.jpg")
                End If
            Next
        Next

        'After info is sent, clear textboxes
        m1p.Clear()
        m2v.Clear()
        Genus.Clear()
        Species.Clear()
        Ir.Clear()
        i.Clear()
        Author.Clear()
        State.Clear()
        County.Clear()
        Collector.Clear()
        Town.Clear()
        Qualifier.Clear()
        Record.Clear()
        BeginningDateCollectedYear.Clear()
        BeginningDateDay.Clear()
        BeginningDateMon.Clear()
        EndingDateDay.Clear()
        EndingDateMon.Clear()
        EndingDateCollectedYear.Clear()
        VerbatimCollectionDate.Clear()

        'After info is sent, clear checkboxes
        Dim CheckBoxes = {MissingInformation, IllegibleInformation, Unreadable, Empty, Folder, Other}
        For index = 0 To CheckBoxes.Length - 1
            If CheckBoxes(index).CheckState = CheckState.Checked Then
                CheckBoxes(index).CheckState = CheckState.Unchecked
            End If
        Next


        'Move TextBoxes
        Dim TextBoxes = {Text1, Text2, Text3}
        For index = 0 To TextBoxes.Length - 1
            TextBoxes(index).Left = TextBoxes(index).Left + 204
        Next

        For index = 0 To TextBoxes.Length - 1
            If TextBoxes(index).Location = New Point(793, 27) Then
                TextBoxes(index).Location = New Point(181, 27)
            End If
        Next

        'Make sure first textbox says "Current Input"
        For index = 0 To TextBoxes.Length - 1
            If TextBoxes(index).Location = New Point(181, 27) Then
                TextBoxes(index).Text = "Current Input"
            End If
        Next

        'Move Borders
        For index = 0 To Borders.Length - 1
            Borders(index).Left = Borders(index).Left + 204
        Next

        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(1783, 4) Then
                Borders(index).Location = New Point(151, 4)
                Borders(index).FillColor = Color.Black
            End If
        Next


    End Sub


    Private Sub Record_Leave(sender As Object, e As EventArgs) Handles Record.Leave
        If Text1.Location = New Point(181, 27) Then
            Text1.Text = Record.Text

        ElseIf Text2.Location = New Point(181, 27) Then
            Text2.Text = Record.Text

        ElseIf Text3.Location = New Point(181, 27) Then
            Text3.Text = Record.Text
        End If




    End Sub

    'Change the border color as tags checked
    Private Sub IllegibleInformation_CheckedChange(sender As Object, e As EventArgs) Handles IllegibleInformation.CheckedChanged
        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(151, 4) Then
                Borders(index).FillColor = IllegibleInformation.BackColor
            End If
        Next
    End Sub

    Private Sub Unreadable_CheckedChanged(sender As Object, e As EventArgs) Handles Unreadable.CheckedChanged

        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(151, 4) Then
                Borders(index).FillColor = Unreadable.BackColor
            End If
        Next
    End Sub

    Private Sub Folder_CheckedChanged(sender As Object, e As EventArgs) Handles Folder.CheckedChanged
        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(151, 4) Then
                Borders(index).FillColor = Folder.BackColor
            End If
        Next
    End Sub

    Private Sub MissingInformation_CheckedChanged(sender As Object, e As EventArgs) Handles MissingInformation.CheckedChanged
        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(151, 4) Then
                Borders(index).FillColor = MissingInformation.BackColor
            End If
        Next

        If MissingInformation.Checked = True Then
            sendButton.Enabled = True
        End If
    End Sub

    Private Sub Incorrect_CheckedChanged(sender As Object, e As EventArgs) Handles Empty.CheckedChanged
        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(151, 4) Then
                Borders(index).FillColor = Empty.BackColor
            End If
        Next
    End Sub

    Private Sub Other_CheckedChanged(sender As Object, e As EventArgs) Handles Other.CheckedChanged
        Dim Borders = {Border1, Border2, Border3, Border4, Border5, Border6, Border7, Border8}
        For index = 0 To Borders.Length - 1
            If Borders(index).Location = New Point(151, 4) Then
                Borders(index).FillColor = Other.BackColor
            End If
        Next
    End Sub




    'Make sure can't click send unless all filled or missing info checked

    Private Sub sendButton_MouseEnter(sender As Object, e As EventArgs) Handles sendButton.MouseEnter
        Dim Texts As Array = {Genus, Species, Ir, i, Author, cc, State, County, Town, Collector, Qualifier, Record, BeginningDateCollectedYear, BeginningDateMon, BeginningDateDay, EndingDateCollectedYear, EndingDateMon, EndingDateDay, VerbatimCollectionDate, RecordEnteredBy}
        For index1 = 0 To Texts.Length - 1
            If Texts(index1).text.length = 0 And MissingInformation.Checked = False Then
                sendButton.Enabled = False
            End If
        Next

    End Sub

    Private Sub Genus_TextChanged(sender As Object, e As EventArgs) Handles Genus.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If


    End Sub

    Private Sub Species_TextChanged(sender As Object, e As EventArgs) Handles Species.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub Ir_TextChanged(sender As Object, e As EventArgs) Handles Ir.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub i_TextChanged(sender As Object, e As EventArgs) Handles i.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub Author_TextChanged(sender As Object, e As EventArgs) Handles Author.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub cc_TextChanged(sender As Object, e As EventArgs) Handles cc.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub RecordEnteredBy_TextChanged(sender As Object, e As EventArgs) Handles RecordEnteredBy.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub State_TextChanged(sender As Object, e As EventArgs) Handles State.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub County_TextChanged(sender As Object, e As EventArgs) Handles County.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub Town_TextChanged(sender As Object, e As EventArgs) Handles Town.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub Collector_TextChanged(sender As Object, e As EventArgs) Handles Collector.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub Qualifier_TextChanged(sender As Object, e As EventArgs) Handles Qualifier.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub Record_TextChanged(sender As Object, e As EventArgs) Handles Record.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub EndingDateDay_TextChanged(sender As Object, e As EventArgs) Handles EndingDateDay.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub VerbatimCollectionDate_TextChanged(sender As Object, e As EventArgs) Handles VerbatimCollectionDate.TextChanged
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    'Make sure date is in ISO 8601 Format
    Private Sub BeginningDateCollectedYear_TextChanged(sender As Object, e As EventArgs) Handles BeginningDateCollectedYear.TextChanged
        If BeginningDateCollectedYear.TextLength = 4 Then
            BeginningDateMon.Focus()
        End If
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub BeginningDateMon_TextChanged(sender As Object, e As EventArgs) Handles BeginningDateMon.TextChanged
        If BeginningDateMon.TextLength = 2 Then
            BeginningDateDay.Focus()
        End If
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub BEginningDateDay_TextChanged(sender As Object, e As EventArgs) Handles BeginningDateDay.TextChanged
        If BeginningDateDay.TextLength = 2 Then
            EndingDateCollectedYear.Focus()
        End If
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub EndingDateCollectedYear_TextChanged(sender As Object, e As EventArgs) Handles EndingDateCollectedYear.TextChanged
        If EndingDateCollectedYear.TextLength = 4 Then
            EndingDateMon.Focus()
        End If
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    Private Sub EndingDateMon_TextChanged(sender As Object, e As EventArgs) Handles EndingDateMon.TextChanged
        If EndingDateMon.TextLength = 2 Then
            EndingDateDay.Focus()
        End If
        If Genus.Text = "" Or Species.Text = "" Or Ir.Text = "" And i.Text = "" Or Author.Text = "" Or cc.Text = "" Or State.Text = "" Or County.Text = "" Or Town.Text = "" Or Collector.Text = "" Or Qualifier.Text = "" Or Record.Text = "" Or BeginningDateCollectedYear.Text = "" Or BeginningDateMon.Text = "" Or BeginningDateDay.Text = "" Or EndingDateCollectedYear.Text = "" Or EndingDateMon.Text = "" Or EndingDateDay.Text = "" Or VerbatimCollectionDate.Text = "" Or RecordEnteredBy.Text = "" Then
            sendButton.Enabled = False
        Else : sendButton.Enabled = True
        End If
    End Sub

    'Create a timer
    Private Sub Timer1_Tick(sender As Object, e As EventArgs) Handles Timer1.Tick
        If TimerLabel.Text < 100 Then
            TimerLabel.Text = Val(TimerLabel.Text) + 1
        Else
            Timer1.Stop()
            TimerLabel.Text = "Late"
        End If
    End Sub


    Private Sub InputForm_Load(sender As Object, e As EventArgs) Handles MyBase.Load

        'Make sure you cannot type in record number boxes
        Text1.ReadOnly = True
        Text2.ReadOnly = True
        Text3.ReadOnly = True

        'Load states, counties, and cities in autocomplete custom source 
        ' Create a string collection for states
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

        'Create a string collection for counties
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

        'Create a string collection for cities
        Dim citiesFile As String = "C:\Users\TouchScreen\Documents\Lists\FW__Apparatus_database_and_UI\cities_20121114.txt"
        If System.IO.File.Exists(citiesFile) = True Then
            'Read the textfile:
            Dim objReader As New System.IO.StreamReader(citiesFile)
            Dim MyFileLine As String = objReader.ReadToEnd
            objReader.Close()
            Dim NoNumber As String = Regex.Replace(MyFileLine, "[0-9]", "")
            Dim NoState As String = Replace(NoNumber, "Connecticut", "")
            Dim NoState1 As String = Replace(NoState, "Maine", "")
            Dim NoState2 As String = Replace(NoState1, "Massachusetts", "")
            Dim NoState3 As String = Replace(NoState2, "New Hampshire", "")
            Dim NoState4 As String = Replace(NoState3, "Rhode Island", "")
            Dim NoState5 As String = Replace(NoState4, "Vermont", "")
            Dim NoComma As String = Replace(NoState5, ",,", "")
            Dim towns() As String = Regex.Split(NoComma, "\n")
            Dim Cities As New AutoCompleteStringCollection
            Town.AutoCompleteMode = AutoCompleteMode.SuggestAppend
            Town.AutoCompleteSource = AutoCompleteSource.CustomSource
            Town.AutoCompleteCustomSource = Cities
            For index = 0 To towns.Length - 1
                Cities.Add(towns(index))
            Next
        Else
            MessageBox.Show("File 'C:\test.txt' not found!", "www.interloper.nl", MessageBoxButtons.OK, MessageBoxIcon.Error)
        End If

    End Sub
End Class
