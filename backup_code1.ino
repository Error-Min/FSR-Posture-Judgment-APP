//#include <SoftwareSerial.h>
#include <stdio.h>
/*===Pin 설정 Start!!===*/

//==블루투스 설정 시작==
//SoftwareSerial mySerial(0, 1);
//==블루투스 설정 끝==

int En0 = 7;  //  Low enabled
int En1 = 6;  //  Low enabled
 
int S0  = 5;
int S1  = 4;
int S2  = 3;
int S3  = 2;

int x = 0; 
int SIG_pin = A3;
 
 
void setup() {
  Serial.begin(115200);
  //mySerial.begin(115200);
  pinMode(En0, OUTPUT);
  pinMode(En1, OUTPUT);
 
  pinMode(S0, OUTPUT);
  pinMode(S1, OUTPUT);
  pinMode(S2, OUTPUT);
  pinMode(S3, OUTPUT); 
}
/*===Pin 설정 End!!===*/
 
void loop() {

  for(int j = 0; j < 31; j++){
    readMux(j);
  }
    //Serial.print(String(readMux(0)));
  String inString = "z,z";
  int index1 = inString.indexOf(',');
  int inString1 = inString.substring(0, index1).toInt();
  for(int z = 0; z < 31; z++)
  {
    Serial.print(String(readMux(z)));
    Serial.print(",");
  }
  Serial.print("\n");
 delay(500);
}
 

int readMux(int channel){
  int controlPin[] = {S0,S1,S2,S3,En0,En1};
  int muxChannel[31][6]={
    {0,0,0,0,0,1}, //channel 0
    {0,0,0,1,0,1}, //channel 1
    {0,0,1,0,0,1}, //channel 2
    {0,0,1,1,0,1}, //channel 3
    {0,1,0,0,0,1}, //channel 4
    {0,1,0,1,0,1}, //channel 5
    {0,1,1,0,0,1}, //channel 6
    {0,1,1,1,0,1}, //channel 7
    {1,0,0,0,0,1}, //channel 8
    {1,0,0,1,0,1}, //channel 9
    {1,0,1,0,0,1}, //channel 10
    {1,0,1,1,0,1}, //channel 11
    {1,1,0,0,0,1}, //channel 12
    {1,1,0,1,0,1}, //channel 13
    {1,1,1,0,0,1}, //channel 14
    {1,1,1,1,0,1}, //channel 15
    {0,0,0,0,1,0}, //channel 16
    {0,0,0,1,1,0}, //channel 17
    {0,0,1,0,1,0}, //channel 18
    {0,0,1,1,1,0}, //channel 19
    {0,1,0,0,1,0}, //channel 20
    {0,1,0,1,1,0}, //channel 21
    {0,1,1,0,1,0}, //channel 22
    {0,1,1,1,1,0}, //channel 23
    {1,0,0,0,1,0}, //channel 24
    {1,0,0,1,1,0}, //channel 25
    {1,0,1,0,1,0}, //channel 26
    {1,0,1,1,1,0}, //channel 27
    {1,1,0,0,1,0}, //channel 28
    {1,1,0,1,1,0}, //channel 29
    {1,1,1,0,1,0}, //channel 30
  };
  
  //loop through the 6 sig
  for(int i = 0; i < 6; i ++){
    digitalWrite(controlPin[i], muxChannel[channel][i]);
  }
 
  //read the value at the SIG pin
  int val = analogRead(SIG_pin);
 
  //return the value
  return val;
}
