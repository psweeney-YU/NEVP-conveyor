''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'
' Example program:
'   GlobalContinuousAO
'
' Description:
'   This example shows how to load a continuous analog output task from the Measurement & 
'   Automation Explorer (MAX) and use it to write generated data to a DAQ device and plot the data.
'   This example should also work with E-Series and M-Series devices.
'
' Instructions for running:
'   1.  Create a continuous analog output NI-DAQmx global task in MAX. For help, refer to 
'       "Creating Tasks and Channels" in the Measurement & Automation Explorer Help. 
'       To access this help, select Start>>All Programs>>National Instruments>>
'       Measurement & Automation. In MAX, select Help>>MAX Help.
'
'       Note: If you prefer, you can import a continuous AO task and a simulated 
'       device into MAX from the GlobalContinuousAO.nce file, which is located in the 
'       example directory. Refer to "Using the Configuration Import Wizard" in the 
'       Measurement & Automation Explorer Help for more information.
'
'   2.  Run the application, select the task from the drop-down list, and click 
'       the Start button.
'
' Steps:
'   1.  Load the task from MAX.
'   2.  Generate data for the task.
'   3.  Write data to the channel in the task
'   4.  Plot the generated data on a waveform graph.
''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

Imports NationalInstruments.Analysis.SignalGeneration
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
    Private daqmxTaskGroupBox As System.Windows.Forms.GroupBox
    Private daqmxTaskLabel As System.Windows.Forms.Label
    Private taskComboBox As System.Windows.Forms.ComboBox
    Private WithEvents startButton As System.Windows.Forms.Button
    Private WithEvents stopButton As System.Windows.Forms.Button
    Private infoLabel As System.Windows.Forms.Label
    Private globalContinuousAOWaveformGraph As NationalInstruments.UI.WindowsForms.WaveformGraph
    Private waveformPlot2 As NationalInstruments.UI.WaveformPlot
    Private xAxis2 As NationalInstruments.UI.XAxis
    Private yAxis2 As NationalInstruments.UI.YAxis
    Private channelLegend As NationalInstruments.UI.WindowsForms.Legend
    Private continuousTask As Task
    Private writer As AnalogSingleChannelWriter
    Private components As System.ComponentModel.Container = Nothing

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

        ' Add valid continuous analog output tasks to the combo box
        Dim t As Task = Nothing

        For Each s As String In DaqSystem.Local.Tasks
            Try
                t = DaqSystem.Local.LoadTask(s)
                t.Control(TaskAction.Verify)
                If ((t.AOChannels.Count > 0) _
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
        End If
        MyBase.Dispose(disposing)
    End Sub


#Region "Windows Form Designer generated code"
    Private Sub InitializeComponent()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(MainForm))
        Me.xAxis1 = New NationalInstruments.UI.XAxis
        Me.yAxis1 = New NationalInstruments.UI.YAxis
        Me.waveformPlot1 = New NationalInstruments.UI.WaveformPlot
        Me.daqmxTaskGroupBox = New System.Windows.Forms.GroupBox
        Me.daqmxTaskLabel = New System.Windows.Forms.Label
        Me.taskComboBox = New System.Windows.Forms.ComboBox
        Me.startButton = New System.Windows.Forms.Button
        Me.stopButton = New System.Windows.Forms.Button
        Me.infoLabel = New System.Windows.Forms.Label
        Me.globalContinuousAOWaveformGraph = New NationalInstruments.UI.WindowsForms.WaveformGraph
        Me.waveformPlot2 = New NationalInstruments.UI.WaveformPlot
        Me.xAxis2 = New NationalInstruments.UI.XAxis
        Me.yAxis2 = New NationalInstruments.UI.YAxis
        Me.channelLegend = New NationalInstruments.UI.WindowsForms.Legend
        Me.daqmxTaskGroupBox.SuspendLayout()
        CType(Me.globalContinuousAOWaveformGraph, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.channelLegend, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'waveformPlot1
        '
        Me.waveformPlot1.XAxis = Me.xAxis1
        Me.waveformPlot1.YAxis = Me.yAxis1
        '
        'daqmxTaskGroupBox
        '
        Me.daqmxTaskGroupBox.Controls.Add(Me.daqmxTaskLabel)
        Me.daqmxTaskGroupBox.Controls.Add(Me.taskComboBox)
        Me.daqmxTaskGroupBox.FlatStyle = System.Windows.Forms.FlatStyle.System
        Me.daqmxTaskGroupBox.Location = New System.Drawing.Point(8, 8)
        Me.daqmxTaskGroupBox.Name = "daqmxTaskGroupBox"
        Me.daqmxTaskGroupBox.Size = New System.Drawing.Size(328, 72)
        Me.daqmxTaskGroupBox.TabIndex = 3
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
        'taskComboBox
        '
        Me.taskComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.taskComboBox.Location = New System.Drawing.Point(104, 32)
        Me.taskComboBox.Name = "taskComboBox"
        Me.taskComboBox.Size = New System.Drawing.Size(216, 21)
        Me.taskComboBox.TabIndex = 1
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
        'infoLabel
        '
        Me.infoLabel.Anchor = CType((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.infoLabel.FlatStyle = System.Windows.Forms.FlatStyle.System
        Me.infoLabel.Location = New System.Drawing.Point(520, 8)
        Me.infoLabel.Name = "infoLabel"
        Me.infoLabel.Size = New System.Drawing.Size(264, 96)
        Me.infoLabel.TabIndex = 5
        Me.infoLabel.Text = resources.GetString("infoLabel.Text")
        '
        'globalContinuousAOWaveformGraph
        '
        Me.globalContinuousAOWaveformGraph.Anchor = CType((((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Bottom) _
                    Or System.Windows.Forms.AnchorStyles.Left) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.globalContinuousAOWaveformGraph.Location = New System.Drawing.Point(8, 107)
        Me.globalContinuousAOWaveformGraph.Name = "globalContinuousAOWaveformGraph"
        Me.globalContinuousAOWaveformGraph.Plots.AddRange(New NationalInstruments.UI.WaveformPlot() {Me.waveformPlot2})
        Me.globalContinuousAOWaveformGraph.Size = New System.Drawing.Size(504, 293)
        Me.globalContinuousAOWaveformGraph.TabIndex = 3
        Me.globalContinuousAOWaveformGraph.TabStop = False
        Me.globalContinuousAOWaveformGraph.XAxes.AddRange(New NationalInstruments.UI.XAxis() {Me.xAxis2})
        Me.globalContinuousAOWaveformGraph.YAxes.AddRange(New NationalInstruments.UI.YAxis() {Me.yAxis2})
        '
        'waveformPlot2
        '
        Me.waveformPlot2.XAxis = Me.xAxis2
        Me.waveformPlot2.YAxis = Me.yAxis2
        '
        'channelLegend
        '
        Me.channelLegend.Anchor = CType(((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Bottom) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.channelLegend.Location = New System.Drawing.Point(520, 107)
        Me.channelLegend.Name = "channelLegend"
        Me.channelLegend.Size = New System.Drawing.Size(264, 293)
        Me.channelLegend.TabIndex = 4
        Me.channelLegend.TabStop = False
        '
        'MainForm
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(96.0!, 96.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi
        Me.ClientSize = New System.Drawing.Size(792, 406)
        Me.Controls.Add(Me.channelLegend)
        Me.Controls.Add(Me.globalContinuousAOWaveformGraph)
        Me.Controls.Add(Me.infoLabel)
        Me.Controls.Add(Me.daqmxTaskGroupBox)
        Me.Controls.Add(Me.startButton)
        Me.Controls.Add(Me.stopButton)
        Me.Icon = CType(resources.GetObject("$this.Icon"), System.Drawing.Icon)
        Me.Name = "MainForm"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Global Continuous Analog Output"
        Me.daqmxTaskGroupBox.ResumeLayout(False)
        CType(Me.globalContinuousAOWaveformGraph, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.channelLegend, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub startButton_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles startButton.Click
        startButton.Enabled = False
        channelLegend.Items.Clear()
        globalContinuousAOWaveformGraph.ClearData()
        globalContinuousAOWaveformGraph.Plots.Clear()

        Try
            ' Get task from combox box and initialize the analog writer
            Dim taskName As String = taskComboBox.SelectedItem.ToString
            continuousTask = DaqSystem.Local.LoadTask(taskName)
            writer = New AnalogSingleChannelWriter(continuousTask.Stream)

            continuousTask.Control(TaskAction.Verify)

            ' Generate the sine wave data
            Dim fgen As SignalGenerator = Nothing
            fgen = New SignalGenerator(continuousTask.Timing.SampleClockRate, continuousTask.Timing.SamplesPerChannel, New SineSignal)
            Dim generatedData() As Double = fgen.Generate

            ' Convert the generated data to an AnalogWaveform
            Dim data As AnalogWaveform(Of Double) = AnalogWaveform(Of Double).FromArray1D(generatedData)

            ' Write and plot the generated data
            Dim plot As WaveformPlot = New WaveformPlot
            globalContinuousAOWaveformGraph.Plots.Add(plot)

            writer.WriteWaveform(Of Double)(True, data)
            globalContinuousAOWaveformGraph.PlotWaveform(Of Double)(data)

            channelLegend.Items.Add(New LegendItem(plot, (continuousTask.AOChannels(0).VirtualName + (": " + continuousTask.AOChannels(0).PhysicalName))))

            stopButton.Enabled = True
            taskComboBox.Enabled = False
        Catch ex As DaqException
            MessageBox.Show(ex.Message)
            continuousTask.Dispose()
            startButton.Enabled = True
        End Try
    End Sub

    Private Sub stopButton_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles stopButton.Click
        stopButton.Enabled = False

        Try
            continuousTask.Stop()
        Catch ex As DaqException
            MessageBox.Show(ex.Message)
        End Try

        continuousTask.Dispose()
        startButton.Enabled = True
        taskComboBox.Enabled = True
    End Sub
End Class

