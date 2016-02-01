Public Class CameraPosition

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        InputForm.Text1.Size = New Size(109, 146)
        InputForm.Text2.Size = New Size(109, 146)
        InputForm.Text3.Size = New Size(109, 146)
        InputForm.Text4.Size = New Size(109, 146)
        InputForm.Text5.Size = New Size(109, 146)
        InputForm.Pic1.Size = New Size(109, 146)
        InputForm.Pic2.Size = New Size(109, 146)
        InputForm.Pic3.Size = New Size(109, 146)
        InputForm.Pic4.Size = New Size(109, 146)
        InputForm.Pic5.Size = New Size(109, 146)
        InputForm.Pic6.Size = New Size(109, 146)
        InputForm.Pic7.Size = New Size(109, 146)


        If CameraPositionSelect.Text = 3 Then
            InputForm.CameraLabel.Location = New Point(571, 248)
            InputForm.CameraPartitionLeft.X1 = 535
            InputForm.CameraPartitionLeft.X2 = 535
            InputForm.CameraPartitionRight.X1 = 732
            InputForm.CameraPartitionRight.X2 = 732
            InputForm.Text5.Size = New Size(0, 0)
            InputForm.Text4.Size = New Size(0, 0)
            InputForm.Pic1.Size = New Size(0, 0)
            InputForm.Pic2.Size = New Size(0, 0)
        ElseIf CameraPositionSelect.Text = 4 Then
            InputForm.CameraLabel.Location = New Point(571 + 204, 248)
            InputForm.CameraPartitionLeft.X1 = 535 + 204
            InputForm.CameraPartitionLeft.X2 = 535 + 204
            InputForm.CameraPartitionRight.X1 = 732 + 204
            InputForm.CameraPartitionRight.X2 = 732 + 204
            InputForm.Text5.Size = New Size(0, 0)
            InputForm.Pic1.Size = New Size(0, 0)
            InputForm.Pic2.Size = New Size(0, 0)
            InputForm.Pic3.Size = New Size(0, 0)
        ElseIf CameraPositionSelect.Text = 2 Then
            InputForm.CameraLabel.Location = New Point(571 - 204, 248)
            InputForm.CameraPartitionLeft.X1 = 535 - 204
            InputForm.CameraPartitionLeft.X2 = 535 - 204
            InputForm.CameraPartitionRight.X1 = 732 - 204
            InputForm.CameraPartitionRight.X2 = 732 - 204
            InputForm.Text5.Size = New Size(0, 0)
            InputForm.Text4.Size = New Size(0, 0)
            InputForm.Text3.Size = New Size(0, 0)
            InputForm.Pic1.Size = New Size(0, 0)
        ElseIf CameraPositionSelect.Text = 1 Then
            InputForm.CameraLabel.Location = New Point(571 - (2 * 204), 248)
            InputForm.CameraPartitionLeft.X1 = 535 - (2 * 204)
            InputForm.CameraPartitionLeft.X2 = 535 - (2 * 204)
            InputForm.CameraPartitionRight.X1 = 732 - (2 * 204)
            InputForm.CameraPartitionRight.X2 = 732 - (2 * 204)
            InputForm.Text5.Size = New Size(0, 0)
            InputForm.Text4.Size = New Size(0, 0)
            InputForm.Text3.Size = New Size(0, 0)
            InputForm.Text2.Size = New Size(0, 0)
        ElseIf CameraPositionSelect.Text = 5 Then
            InputForm.CameraLabel.Location = New Point(571 + (2 * 204), 248)
            InputForm.CameraPartitionLeft.X1 = 535 + (2 * 204)
            InputForm.CameraPartitionLeft.X2 = 535 + (2 * 204)
            InputForm.CameraPartitionRight.X1 = 732 + (2 * 204)
            InputForm.CameraPartitionRight.X2 = 732 + (2 * 204)
            InputForm.Pic1.Size = New Size(0, 0)
            InputForm.Pic2.Size = New Size(0, 0)
            InputForm.Pic3.Size = New Size(0, 0)
            InputForm.Pic4.Size = New Size(0, 0)
        End If
        Me.Hide()
        Testing1.InputForm.CameraPositionValue = Val(CameraPositionSelect.Text)
    End Sub
End Class