Public Class InputForm
    Public Divided As Array
    Public BarcodeString As String
    Public sendString As String

    Private Sub Barcode_Leave(sender As Object, e As EventArgs) Handles Barcode.Leave
        Divided = Barcode.Text.Split(",")

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
        sendString = sendString & "}"
        If MissingInformation.Checked Then
            sendString = sendString & " Missing Information"
        End If
        If Illegible.Checked Then
            sendString = sendString & " Illegible Information"
        End If
        If Unreadable.Checked Then
            sendString = sendString & " Barcode Unreadable"
        End If
        If Incorrect.Checked Then
            sendString = sendString & " Incorrect Information"
        End If
        If Other.Checked Then
            sendString = sendString & " Other Problem"
        End If
        MsgBox(sendString)

        sendString = sendString & vbNewLine
        My.Computer.FileSystem.WriteAllText("C:\Users\TouchScreen\Documents\TestingOutput\testing.txt", sendString, True)


    End Sub


End Class
