================================================================
-EntryUser-1/Input Interface Beta V1_Yiming_Update
Entry User software Built with Visual Basic by Aimee Jones, Nik Bosnyak and Yiming Xu. 
Need to use correspond platform software 1 in main computer.
================================================================
-EntryUser-2
Updated Entry User Software with Eclipse RCP.
Need to use correspond platform software 2 in main computer.
================================================================
-HerbClientRCP-Platform-1
Main computer UI software. it will need following components to run:
--HerbService
--Conveyor Controller (NI-DAQ)
--Canon Camera Application (camera)
================================================================
-HerbClientRCP-Platform-2	
Main computer UI software for updated UI.
================================================================
-HerbService
WebService program for main computer software's database access, need to run in tomcat container. 
================================================================	
-NI-DAQ	
NI-DAQ controller which is using NI measurement studio libraries and API. It provide wrapped interface to main computer software.
================================================================
-Camera	
Canon camera component which is using Canon camera driver and Canon EDSDK2.1 API.
================================================================
-sql
MySQL database schema 
================================================================
-README.txt
Code released under the MIT license. 
