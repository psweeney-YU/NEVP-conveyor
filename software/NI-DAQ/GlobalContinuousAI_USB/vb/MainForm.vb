'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'
' Example program:
'   GlobalContinuousAI_USB
'
' Description:
'   This example shows how to load a continuous analog input task from the Measurement & 
'   Automation Explorer (MAX) and use it to acquire and plot samples from a USB device.
'   The example should also work with E-Series and M-Series devices.
'
' Instructions for running:
'   1.  Create a continuous analog input NI-DAQmx global task in MAX. For help, refer to 
'       "Creating Tasks and Channels" in the Measurement & Automation Explorer Help. 
'       To access this help, select Start>>All Programs>>National Instruments>>
'       Measurement & Automation. In MAX, select Help>>MAX Help.
'
'       Note: If you prefer, you can import a continuous AI task and a simulated USB
'       device into MAX from the GlobalContinuousAI_USB.nce file, which is located in the 
'       example directory. Refer to "Using the Configuration Import Wizard" in the 
'       Measurement & Automation Explorer Help for more information.
'
'   2.  Run the application, select the task from the drop-down list, and click 
'       the Start button.
'
' Steps:
'   1.  Load the task from MAX.
'   2.  Read the data from all of the channels in the task.
'   3.  Stop reading data once the user clicks the "Stop" button.
'   4.  Initialize an array of colors so that if the task has more than one channel
'       then the corresponding plots can be distinguished on the graph. 
'       Assign color(s) to the plot(s) and create a legend.
'   5.  Plot the data on a waveform graph.
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

Imports NationalInstruments
Imports NationalInstruments.DAQmx
Imports NationalInstruments.UI
Imports NationalInstruments.UI.WindowsForms
Imports System
Imports System.Drawing
Imports System.Collections
Imports System.ComponentModel
Imports System.Windows.Forms
Imports System.Data


Public Class MainForm
    Inherits System.Windows.Forms.Form
    Private xAxis1 As NationalInstruments.UI.XAxis
    Private yAxis1 As NationalInstruments.UI.YAxis
    Private waveformPlot1 As NationalInstruments.UI.WaveformPlot
    Private WithEvents startButton As System.Windows.Forms.Button
    Private WithEvents stopButton As System.Windows.Forms.Button
    Private taskComboBox As System.Windows.Forms.ComboBox
    Private globalContinuousAIWaveformGraph As NationalInstruments.UI.WindowsForms.WaveformGraph
    Private runningTask As Task
    Private continuousTask As Task
    Private reader As AnalogMultiChannelReader
    Private callBack As AsyncCallback
    Private daqmxTaskGroupBox As System.Windows.Forms.GroupBox
    Private daqmxTaskLabel As System.Windows.Forms.Label
    Private channelLegend As NationalInstruments.UI.WindowsForms.Legend
    Private infoLabel As System.Windows.Forms.Label
    Private components As System.ComponentModel.Container = Nothing
    Private data As AnalogWaveform(Of Double)()

    Public Sub New()
        MyBase.New()
        Application.EnableVisualStyles()
        Application.DoEvents()
        '
        ' Required for Windows Form Designer support
        '
        InitializeComponent()

        startButton.Enabled = False
        stopButton.Enabled = False

        Dim t As Task = Nothing

        For Each s As String In DaqSystem.Local.Tasks
            Try
                t = DaqSystem.Local.LoadTask(s)
                t.Control(TaskAction.Verify)
                If ((t.AIChannels.Count > 0) _
                            AndAlso (t.Timing.SampleQuantityMode = SampleQuantityMode.ContinuousSamples)) Then
                    taskComboBox.Items.Add(s)
                End If
            Catch ex As DaqException
                ' Ignore invalid tasks
            Finally
                t.Dispose()
            End Try
        Next

        If (taskComboBox.Items.Count > 0) Then
            taskComboBox.SelectedIndex = 0
            startButton.Enabled = True
        End If

    End Sub

    Protected Overloads Overrides Sub Dispose(ByVal disposing As Boolean)
        If disposing Then
            If (Not (components) Is Nothing) Then
                components.Dispose()
            End If
            If Not (continuousTask Is Nothing) Then
                runningTask = Nothing
                continuousTask.Dispose()
            End If
        End If
        MyBase.Dispose(disposing)
    End Sub



#Region "Windows Form Designer generated code"
    Private Sub InitializeComponent()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(MainForm))
        Me.globalContinuousAIWaveformGraph = New NationalInstruments.UI.WindowsForms.WaveformGraph
        Me.waveformPlot1 = New NationalInstruments.UI.WaveformPlot
        Me.xAxis1 = New NationalInstruments.UI.XAxis
        Me.yAxis1 = New NationalInstruments.UI.YAxis
        Me.startButton = New System.Windows.Forms.Button
        Me.stopButton = New System.Windows.Forms.Button
        Me.taskComboBox = New System.Windows.Forms.ComboBox
        Me.daqmxTaskGroupBox = New System.Windows.Forms.GroupBox
        Me.daqmxTaskLabel = New System.Windows.Forms.Label
        Me.channelLegend = New NationalInstruments.UI.WindowsForms.Legend
        Me.infoLabel = New System.Windows.Forms.Label
        CType(Me.globalContinuousAIWaveformGraph, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.daqmxTaskGroupBox.SuspendLayout()
        CType(Me.channelLegend, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'globalContinuousAIWaveformGraph
        '
        Me.globalContinuousAIWaveformGraph.Anchor = CType((((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Bottom) _
                    Or System.Windows.Forms.AnchorStyles.Left) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.globalContinuousAIWaveformGraph.Location = New System.Drawing.Point(8, 107)
        Me.globalContinuousAIWaveformGraph.Name = "globalContinuousAIWaveformGraph"
        Me.globalContinuousAIWaveformGraph.Plots.AddRange(New NationalInstruments.UI.WaveformPlot() {Me.waveformPlot1})
        Me.globalContinuousAIWaveformGraph.Size = New System.Drawing.Size(504, 304)
        Me.globalContinuousAIWaveformGraph.TabIndex = 3
        Me.globalContinuousAIWaveformGraph.TabStop = False
        Me.globalContinuousAIWaveformGraph.UseColorGenerator = True
        Me.globalContinuousAIWaveformGraph.XAxes.AddRange(New NationalInstruments.UI.XAxis() {Me.xAxis1})
        Me.globalContinuousAIWaveformGraph.YAxes.AddRange(New NationalInstruments.UI.YAxis() {Me.yAxis1})
        '
        'waveformPlot1
        '
        Me.waveformPlot1.XAxis = Me.xAxis1
        Me.waveformPlot1.YAxis = Me.yAxis1
        '
        'startButton
        '
        Me.startButton.FlatStyle = System.Windows.Forms.FlatStyle.System
        Me.startButton.Location = New System.Drawing.Point(352, 40)
        Me.startButton.Name = "startButton"
        Me.startButton.TabIndex = 1
        Me.startButton.Text = "Start"
        '
        'stopButton
        '
        Me.stopButton.FlatStyle = System.Windows.Forms.FlatStyle.System
        Me.stopButton.Location = New System.Drawing.Point(437, 40)
        Me.stopButton.Name = "stopButton"
        Me.stopButton.TabIndex = 2
        Me.stopButton.Text = "Stop"
        '
        'taskComboBox
        '
        Me.taskComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.taskComboBox.Location = New System.Drawing.Point(104, 32)
        Me.taskComboBox.Name = "taskComboBox"
        Me.taskComboBox.Size = New System.Drawing.Size(216, 21)
        Me.taskComboBox.TabIndex = 1
        '
        'daqmxTaskGroupBox
        '
        Me.daqmxTaskGroupBox.Controls.Add(Me.daqmxTaskLabel)
        Me.daqmxTaskGroupBox.Controls.Add(Me.taskComboBox)
        Me.daqmxTaskGroupBox.FlatStyle = System.Windows.Forms.FlatStyle.System
        Me.daqmxTaskGroupBox.Location = New System.Drawing.Point(8, 8)
        Me.daqmxTaskGroupBox.Name = "daqmxTaskGroupBox"
        Me.daqmxTaskGroupBox.Size = New System.Drawing.Size(328, 72)
        Me.daqmxTaskGroupBox.TabIndex = 0
        Me.daqmxTaskGroupBox.TabStop = False
        Me.daqmxTaskGroupBox.Text = "Global DAQmx Task"
        '
        'daqmxTaskLabel
        '
        Me.daqmxTaskLabel.FlatStyle = System.Windows.Forms.FlatStyle.System
        Me.daqmxTaskLabel.Location = New System.Drawing.Point(8, 32)
        Me.daqmxTaskLabel.Name = "daqmxTaskLabel"
        Me.daqmxTaskLabel.Size = New System.Drawing.Size(80, 24)
        Me.daqmxTaskLabel.TabIndex = 0
        Me.daqmxTaskLabel.Text = "DAQmx Task:"
        '
        'channelLegend
        '
        Me.channelLegend.Anchor = CType(((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Bottom) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.channelLegend.Location = New System.Drawing.Point(520, 107)
        Me.channelLegend.Name = "channelLegend"
        Me.channelLegend.Size = New System.Drawing.Size(264, 304)
        Me.channelLegend.TabIndex = 4
        Me.channelLegend.TabStop = False
        '
        'infoLabel
        '
        Me.infoLabel.Anchor = CType((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.infoLabel.FlatStyle = System.Windows.Forms.FlatStyle.System
        Me.infoLabel.Location = New System.Drawing.Point(520, 9)
        Me.infoLabel.Name = "infoLabel"
        Me.infoLabel.Size = New System.Drawing.Size(264, 96)
        Me.infoLabel.TabIndex = 5
        Me.infoLabel.Text = resources.GetString("infoLabel.Text")
        '
        'MainForm
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(96.0!, 96.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi
        Me.ClientSize = New System.Drawing.Size(792, 406)
        Me.Controls.Add(Me.infoLabel)
        Me.Controls.Add(Me.channelLegend)
        Me.Controls.Add(Me.daqmxTaskGroupBox)
        Me.Controls.Add(Me.startButton)
        Me.Controls.Add(Me.globalContinuousAIWaveformGraph)
        Me.Controls.Add(Me.stopButton)
        Me.Icon = CType(resources.GetObject("$this.Icon"), System.Drawing.Icon)
        Me.Name = "MainForm"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Global Continuous Analog Input - USB"
        CType(Me.globalContinuousAIWaveformGraph, System.ComponentModel.ISupportInitialize).EndInit()
        Me.daqmxTaskGroupBox.ResumeLayout(False)
        CType(Me.channelLegend, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub startButton_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles startButton.Click
        channelLegend.Items.Clear()
        globalContinuousAIWaveformGraph.ClearData()
        globalContinuousAIWaveformGraph.Plots.Clear()

        Try
            Dim currentTask As String = taskComboBox.SelectedItem.ToString
            continuousTask = DaqSystem.Local.LoadTask(currentTask)

            SetupUI()

            runningTask = continuousTask
            reader = New AnalogMultiChannelReader(continuousTask.Stream)

            callBack = New AsyncCallback(AddressOf ReadCallBack)

            reader.SynchronizeCallbacks = True
            reader.BeginReadWaveform(Convert.ToInt32(continuousTask.Timing.SamplesPerChannel), callBack, continuousTask)

            stopButton.Enabled = True
            startButton.Enabled = False
            taskComboBox.Enabled = False
        Catch ex As DaqException
            MessageBox.Show(ex.Message)
            continuousTask.Dispose()
        End Try
    End Sub

    Public Sub ReadCallBack(ByVal ar As IAsyncResult)
        Try
            If (Not (runningTask Is Nothing)) AndAlso runningTask Is ar.AsyncState Then
                data = reader.EndReadWaveform(ar)
                globalContinuousAIWaveformGraph.PlotWaveformsAppend(data)
                reader.BeginMemoryOptimizedReadWaveform(Convert.ToInt32(continuousTask.Timing.SamplesPerChannel), callBack, continuousTask, data)
            End If
        Catch ex As DaqException
            MessageBox.Show(ex.Message)
            continuousTask.Dispose()

            runningTask = Nothing
            startButton.Enabled = True
            stopButton.Enabled = False
            taskComboBox.Enabled() = True
        End Try
    End Sub

    Private Sub stopButton_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles stopButton.Click
        stopButton.Enabled = False
        runningTask = Nothing

        Try
            continuousTask.Stop()
        Catch ex As DaqException
            MessageBox.Show(ex.Message)
        End Try

        continuousTask.Dispose()
        startButton.Enabled = True
        taskComboBox.Enabled = True
    End Sub

    Private Sub SetupUI()
        continuousTask.Control(TaskAction.Verify)
        Dim i As Integer = 0
        For Each chan As AIChannel In continuousTask.AIChannels
            Dim plot As WaveformPlot = New WaveformPlot
            globalContinuousAIWaveformGraph.Plots.Add(plot)
            channelLegend.Items.Add(New LegendItem(plot, (chan.VirtualName + (": " + chan.PhysicalName))))
            i = (i + 1)
        Next
    End Sub
End Class
