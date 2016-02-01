/*
 * test.c
 *
 *  Created on: Nov 25, 2013
 *      Author: Yiming
 */

#include <Winsock2.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(void) {

	SOCKADDR_IN stLocalAddr, stRemoteAddr;
	SOCKET stServerSocket;
	char buffer[4096];
	char acRealRouterIpAddress[] = "127.0.0.1";
	int lSentLen, lRecvLen;
	stLocalAddr.sin_family = AF_INET;
	stLocalAddr.sin_port = htons(60888);
	stLocalAddr.sin_addr.S_un.S_addr = INADDR_ANY;
	//
	WSADATA wsaData;

	if (WSAStartup(MAKEWORD(2,2), &wsaData)) {
		printf("[ERROR]: Winsock initialize error\n");
		WSACleanup();
		return -1;
	}

	if ((stServerSocket = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) == -1) {
		printf("[ERROR]: SOCKET CREATE ERROR.\n");
		closesocket(stServerSocket);
		return -1;
	}

	if (bind(stServerSocket, (struct sockaddr*) &stLocalAddr,
			sizeof(stLocalAddr)) == SOCKET_ERROR) {
		printf("bind failed! EXIT!\n");
		WSACleanup();
		return -1;
	}

	memset(buffer,0,sizeof(buffer));
	while (1) {
		lRecvLen = recvfrom(stServerSocket,buffer,sizeof(buffer),0,NULL,NULL);
		printf("lRecvLen = %d\n",lRecvLen);
		if(lRecvLen==-1) continue;
		else{
			system("taskkill /F /T /IM CanonCameraApp.exe");
			system("start CanonCameraApp");
			printf("restarted\n");
			memset(buffer,0,sizeof(buffer));
		}
	}
	exit(1);
}
