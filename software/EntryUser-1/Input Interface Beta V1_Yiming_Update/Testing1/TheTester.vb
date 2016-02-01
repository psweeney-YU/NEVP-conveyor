Imports System.Threading

Public Class TheTester

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        test()
    End Sub

    Private Sub once(n As Integer)
        For Each i As TextBox In InputForm.TextBoxArray
            i.Text = "Test Data" & n
        Next

        InputForm.sendButton.PerformClick()

    End Sub

    Private Sub test()
        For i As Integer = 0 To 60
            once(i)
        Next
    End Sub
End Class