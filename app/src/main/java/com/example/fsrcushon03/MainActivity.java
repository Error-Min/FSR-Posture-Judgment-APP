package com.example.fsrcushon03;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {
    /*====타이머 시작====*/
    TextView myOutput;
    TextView myRec;
    Button myBtnStart;
    Button myBtnRec;

    ProgressBar progressBar;

    TextView score_view;

    TextView m_TextViewLog;
    final static int Init =0;
    final static int Run =1;
    final static int Pause =2;

    int cur_Status = Init; //현재의 상태를 저장할변수를 초기화함.
    int myCount=1;
    long myBaseTime;
    long myPauseTime;

    String[] array;

    int textColorTest[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30
    };

    int Gaugecolor[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};

    int score = 100;
    /*====타이머 끝====*/
    TextView mTvBluetoothStatus;
    TextView mTvReceiveData;
    TextView mTvSendData;
    Button mBtnBluetoothOn;
    Button mBtnBluetoothOff;
    Button mBtnConnect;
    Button mBtnSendData;
    /*========데이터 나누기=======*/
    TextView split_text30;
    TextView split_text29;
    TextView split_text28;
    TextView split_text27;
    TextView split_text26;

    TextView split_text4;
    TextView split_text3;
    TextView split_text2;
    TextView split_text1;
    TextView split_text0;

    TextView split_text5;
    TextView split_text6;
    TextView split_text7;
    TextView split_text8;
    TextView split_text9;
    TextView split_text11;
    TextView split_text13;
    TextView split_text15;
    TextView split_text17;
    TextView split_text19;
    TextView split_text21;
    TextView split_text22;
    TextView split_text23;
    TextView split_text24;
    TextView split_text25;

    TextView split_text10;
    TextView split_text12;
    TextView split_text14;

    TextView split_text16;
    TextView split_text18;
    TextView split_text20;
    /*========데이터 나누기=======*/
    /*========게이지 =======*/
    TextView gauge0;
    TextView gauge1;
    TextView gauge2;
    TextView gauge3;
    TextView gauge4;
    TextView gauge5;
    TextView gauge6;
    TextView gauge7;
    TextView gauge8;
    TextView gauge9;
    TextView gauge10;
    TextView gauge11;
    TextView gauge12;
    TextView gauge13;
    TextView gauge14;
    TextView gauge15;
    TextView gauge16;
    TextView gauge17;
    TextView gauge18;
    TextView gauge19;
    TextView gauge20;
    TextView gauge21;
    TextView gauge22;
    TextView gauge23;
    TextView gauge24;
    TextView gauge25;
    TextView gauge26;
    TextView gauge27;
    TextView gauge28;
    TextView gauge29;
    TextView gauge30;
    /*========게이지=======*/

    BluetoothAdapter mBluetoothAdapter;
    Set<BluetoothDevice> mPairedDevices;
    List<String> mListPairedDevices;

    Handler mBluetoothHandler;
    ConnectedBluetoothThread mThreadConnectedBluetooth;
    BluetoothDevice mBluetoothDevice;
    BluetoothSocket mBluetoothSocket;

    final static int BT_REQUEST_ENABLE = 1;
    final static int BT_MESSAGE_READ = 2;
    final static int BT_CONNECTING_STATUS = 3;
    final static UUID BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score_view = (TextView)findViewById(R.id.score_view);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);


        m_TextViewLog = (TextView) findViewById(R.id.record);
        m_TextViewLog.setMovementMethod(new ScrollingMovementMethod());

        /*====타이머 시작====*/
        myOutput = (TextView) findViewById(R.id.time_out);
        myRec = (TextView) findViewById(R.id.record);
        myBtnStart = (Button) findViewById(R.id.btn_start);
        myBtnRec = (Button) findViewById(R.id.btn_rec);
        /*====타이머 끝====*/

        mTvBluetoothStatus = (TextView)findViewById(R.id.tvBluetoothStatus);
        //mTvReceiveData = (TextView)findViewById(R.id.tvReceiveData);
        //mTvReceiveData = (TextView)findViewById(R.id.tvReceiveData);
        mTvSendData =  (EditText) findViewById(R.id.tvSendData);

        mBtnBluetoothOn = (Button)findViewById(R.id.btnBluetoothOn);
        mBtnBluetoothOff = (Button)findViewById(R.id.btnBluetoothOff);
        mBtnConnect = (Button)findViewById(R.id.btnConnect);
        mBtnSendData = (Button)findViewById(R.id.btnSendData);
        /*========데이터 나누기=======*/
        final TextView[] split_text = {
                split_text0 = (TextView) findViewById(R.id.split_text0)
                , split_text1 = (TextView) findViewById(R.id.split_text1)
                , split_text2 = (TextView) findViewById(R.id.split_text2)
                , split_text3 = (TextView) findViewById(R.id.split_text3)
                , split_text4 = (TextView) findViewById(R.id.split_text4)
                , split_text5 = (TextView) findViewById(R.id.split_text5)
                , split_text6 = (TextView) findViewById(R.id.split_text6)
                , split_text7 = (TextView) findViewById(R.id.split_text7)
                , split_text8 = (TextView) findViewById(R.id.split_text8)
                , split_text9 = (TextView) findViewById(R.id.split_text9)
                , split_text10 = (TextView) findViewById(R.id.split_text10)
                , split_text11 = (TextView) findViewById(R.id.split_text11)
                , split_text12 = (TextView) findViewById(R.id.split_text12)
                , split_text13 = (TextView) findViewById(R.id.split_text13)
                , split_text14 = (TextView) findViewById(R.id.split_text14)
                , split_text15 = (TextView) findViewById(R.id.split_text15)
                , split_text16 = (TextView) findViewById(R.id.split_text16)
                , split_text17 = (TextView) findViewById(R.id.split_text17)
                , split_text18 = (TextView) findViewById(R.id.split_text18)
                , split_text19 = (TextView) findViewById(R.id.split_text19)
                , split_text20 = (TextView) findViewById(R.id.split_text20)
                , split_text21 = (TextView) findViewById(R.id.split_text21)
                , split_text22 = (TextView) findViewById(R.id.split_text22)
                , split_text23 = (TextView) findViewById(R.id.split_text23)
                , split_text24 = (TextView) findViewById(R.id.split_text24)
                , split_text25 = (TextView) findViewById(R.id.split_text25)
                , split_text26 = (TextView) findViewById(R.id.split_text26)
                , split_text27 = (TextView) findViewById(R.id.split_text27)
                , split_text28 = (TextView) findViewById(R.id.split_text28)
                , split_text29 = (TextView) findViewById(R.id.split_text29)
                , split_text30 = (TextView) findViewById(R.id.split_text30)
        };
        /*========데이터 나누기=======*/
        /*======== 게이지 데이터=======*/
        final TextView[] gauge = {
                gauge0= (TextView) findViewById(R.id.gauge0)
                , gauge1= (TextView) findViewById(R.id.gauge1)
                , gauge2= (TextView) findViewById(R.id.gauge2)
                , gauge3= (TextView) findViewById(R.id.gauge3)
                , gauge4= (TextView) findViewById(R.id.gauge4)
                , gauge5= (TextView) findViewById(R.id.gauge5)
                , gauge6= (TextView) findViewById(R.id.gauge6)
                , gauge7= (TextView) findViewById(R.id.gauge7)
                , gauge8= (TextView) findViewById(R.id.gauge8)
                , gauge9= (TextView) findViewById(R.id.gauge9)
                , gauge10= (TextView) findViewById(R.id.gauge10)
                , gauge11= (TextView) findViewById(R.id.gauge11)
                , gauge12= (TextView) findViewById(R.id.gauge12)
                , gauge13= (TextView) findViewById(R.id.gauge13)
                , gauge14= (TextView) findViewById(R.id.gauge14)
                , gauge15= (TextView) findViewById(R.id.gauge15)
                , gauge16= (TextView) findViewById(R.id.gauge16)
                , gauge17= (TextView) findViewById(R.id.gauge17)
                , gauge18= (TextView) findViewById(R.id.gauge18)
                , gauge19= (TextView) findViewById(R.id.gauge19)
                , gauge20= (TextView) findViewById(R.id.gauge20)
                , gauge21= (TextView) findViewById(R.id.gauge21)
                , gauge22= (TextView) findViewById(R.id.gauge22)
                , gauge23= (TextView) findViewById(R.id.gauge23)
                , gauge24= (TextView) findViewById(R.id.gauge24)
                , gauge25= (TextView) findViewById(R.id.gauge25)
                ,gauge26= (TextView) findViewById(R.id.gauge26)
                ,gauge27= (TextView) findViewById(R.id.gauge27)
                ,gauge28= (TextView) findViewById(R.id.gauge28)
                ,gauge29= (TextView) findViewById(R.id.gauge29)
                ,gauge30= (TextView) findViewById(R.id.gauge30)
        };
        gauge0.bringToFront();
        gauge1.bringToFront();
        gauge2.bringToFront();
        gauge3.bringToFront();
        gauge4.bringToFront();
        gauge5.bringToFront();
        gauge6.bringToFront();
        gauge7.bringToFront();
        gauge8.bringToFront();
        gauge9.bringToFront();
        gauge10.bringToFront();
        gauge11.bringToFront();
        gauge12.bringToFront();
        gauge13.bringToFront();
        gauge14.bringToFront();
        gauge15.bringToFront();
        gauge16.bringToFront();
        gauge17.bringToFront();
        gauge18.bringToFront();
        gauge19.bringToFront();
        gauge20.bringToFront();
        gauge21.bringToFront();
        gauge22.bringToFront();
        gauge23.bringToFront();
        gauge24.bringToFront();
        gauge25.bringToFront();
        gauge26.bringToFront();
        gauge27.bringToFront();
        gauge28.bringToFront();
        gauge29.bringToFront();
        gauge30.bringToFront();
        /*========게이지 데이터=======*/
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBtnBluetoothOn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothOn();
            }
        });
        mBtnBluetoothOff.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothOff();
            }
        });
        mBtnConnect.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                listPairedDevices();
            }
        });
        mBtnSendData.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mThreadConnectedBluetooth != null) {
                    mThreadConnectedBluetooth.write(mTvSendData.getText().toString());
                    mTvSendData.setText("");
                }
            }
        });

        // 불루투스 데이터 받는 구문
        mBluetoothHandler = new Handler(){
            public void handleMessage( Message msg){
                if(msg.what == BT_MESSAGE_READ){
                    String readMessage = null;
                    try {
                        readMessage = new String((byte[]) msg.obj, "UTF-8");

                        array = readMessage.split(",");

                        for (int k = 0; k < 31; k++){
                            textColorTest[k] = Integer.parseInt(array[k]);
                            if (textColorTest[k] > 200){
                                split_text[k].setBackgroundColor(0xff000000 + 0xff0000);
                            }else if (textColorTest[k] > 120){
                                split_text[k].setBackgroundColor(0xff000000 + 0xFAAC58);

                            }else if (textColorTest[k] > 60){
                                split_text[k].setBackgroundColor(0xff000000 + 0x81F7F3);

                            }else if (textColorTest[k] < 4) {
                                split_text[k].setBackgroundColor(0xff000000 + 0xEEEAEA);
                            }
                        }
                        for (int z = 0; z < 31; z++){
                            split_text[z].setText(Integer.toString(z) + " : " + array[z]);
                        }

                        /*===========압력 좌표=============*/
                        for (int c = 0; c < 31; c++){
                            Gaugecolor[c] = Integer.parseInt(array[c]);
                        }

                        // 압력좌표 색상 and 점수
                        if (Gaugecolor[0] > 111 && Gaugecolor[0] < 230) {
                            gauge0.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[0] > 0 && Gaugecolor[0] < 110){
                            gauge0.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[1] > 120 && Gaugecolor[1] < 230) {
                            gauge1.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[1] > 0 && Gaugecolor[1] < 119){
                            gauge1.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[2] > 130 && Gaugecolor[2] < 230) {
                            gauge2.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[2] > 0 && Gaugecolor[2] < 129){
                            gauge2.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[3] > 130 && Gaugecolor[3] < 230) {
                            gauge3.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[3] > 0 && Gaugecolor[3] < 129){
                            gauge3.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[4] > 135 && Gaugecolor[4] < 230) {
                            gauge4.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[4] > 0 && Gaugecolor[4] < 134){
                            gauge4.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[5] > 138 && Gaugecolor[5] < 230) {
                            gauge5.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -3;
                        } else if (Gaugecolor[5] > 0 && Gaugecolor[5] < 137){
                            gauge5.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[6] > 140 && Gaugecolor[6] < 230) {
                            gauge6.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -2;
                        } else if (Gaugecolor[6] > 0 && Gaugecolor[6] < 139){
                            gauge6.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[7] > 160 && Gaugecolor[7] < 230) {
                            gauge7.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[7] > 0 && Gaugecolor[7] < 159){
                            gauge7.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[8] > 180 && Gaugecolor[8] < 230) {
                            gauge8.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[8] > 60 && Gaugecolor[8] < 179){
                            gauge8.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[9] > 175 && Gaugecolor[9] < 230) {
                            gauge9.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[9] > 0 && Gaugecolor[9] < 174){
                            gauge9.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[10] > 110 && Gaugecolor[10] < 230) {
                            gauge10.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[10] > 0 && Gaugecolor[10] < 109){
                            gauge10.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[11] > 150 && Gaugecolor[11] < 230) {
                            gauge11.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[11] > 0 && Gaugecolor[11] < 149){
                            gauge11.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[12] > 100 && Gaugecolor[12] < 230) {
                            gauge12.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[12] > 0 && Gaugecolor[12] < 99){
                            gauge12.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[13] > 120 && Gaugecolor[13] < 230) {
                            gauge13.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score +1;
                        } else if (Gaugecolor[13] > 0 && Gaugecolor[13] < 119){
                            gauge13.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[14] > 81 && Gaugecolor[14] < 230) {
                            gauge14.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[14] > 0 && Gaugecolor[14] < 80){
                            gauge14.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[15] > 50 && Gaugecolor[15] < 120){
                            gauge15.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score +1;
                        } else if(Gaugecolor[15] > 0 && Gaugecolor[15] < 49){
                            gauge15.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[16] > 100 && Gaugecolor[16] < 230) {
                            gauge16.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[16] > 0 && Gaugecolor[16] < 99){
                            gauge16.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[17] > 135 && Gaugecolor[17] < 230) {
                            gauge17.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score +1;
                        } else if (Gaugecolor[17] > 0 && Gaugecolor[17] < 134){
                            gauge17.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[18] > 138 && Gaugecolor[18] < 230) {
                            gauge18.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[18] > 0 && Gaugecolor[18] < 137){
                            gauge18.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[19] > 180 && Gaugecolor[19] < 230) {
                            gauge19.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[19] > 0 && Gaugecolor[19] < 160){
                            gauge19.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[20] > 130 && Gaugecolor[20] < 230) {
                            gauge20.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[20] > 0 && Gaugecolor[20] < 129){
                            gauge20.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[21] > 190 && Gaugecolor[21] < 230) {
                            gauge21.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[21] > 0 && Gaugecolor[21] < 189){
                            gauge21.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[22] > 180 && Gaugecolor[22] < 230) {
                            gauge22.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[22] > 60 && Gaugecolor[22] < 179){
                            gauge22.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[23] > 160 && Gaugecolor[23] < 230) {
                            gauge23.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[23] > 0 && Gaugecolor[23] < 159){
                            gauge23.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[24] > 140 && Gaugecolor[24] < 230) {
                            gauge24.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -2;
                        } else if (Gaugecolor[24] > 0 && Gaugecolor[24] < 139){
                            gauge24.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[25] > 140 && Gaugecolor[25] < 230) {
                            gauge25.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -3;
                        } else if (Gaugecolor[25] > 0 && Gaugecolor[25] < 137){
                            gauge25.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[26] > 131 && Gaugecolor[26] < 230) {
                            gauge26.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[26] > 0 && Gaugecolor[26] < 130){
                            gauge26.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[27] > 122 && Gaugecolor[27] < 230) {
                            gauge27.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[27] > 0 && Gaugecolor[27] < 121){
                            gauge27.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[28] > 135 && Gaugecolor[28] < 230) {
                            gauge28.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[28] > 0 && Gaugecolor[28] < 134){
                            gauge28.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[29] > 128 && Gaugecolor[29] < 230) {
                            gauge29.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[29] > 0 && Gaugecolor[29] < 127){
                            gauge29.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        if (Gaugecolor[30] > 111 && Gaugecolor[30] < 230) {
                            gauge30.setBackgroundColor(0xff000000 + 0xA361AC);
                            score = score -1;
                        } else if (Gaugecolor[30] > 0 && Gaugecolor[30] < 110){
                            gauge30.setBackgroundColor(0xff000000 + 0xEEEAEA);
                        }

                        progressBar.setProgress(score);

                        score_view.setText(Integer.toString(score));

                        /*===========압력 좌표=============*/

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    /*====타이머 시작====*/
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    public void myOnClick(View v){
        switch(v.getId()){
            case R.id.btn_start: //시작버튼을 클릭했을때 현재 상태값에 따라 다른 동작을 할수있게끔 구현.
                switch(cur_Status){
                    case Init:
                        myBaseTime = SystemClock.elapsedRealtime();
                        System.out.println(myBaseTime);
                        //myTimer이라는 핸들러를 빈 메세지를 보내서 호출
                        myTimer.sendEmptyMessage(0);
                        myBtnStart.setText("멈춤"); //버튼의 문자"시작"을 "멈춤"으로 변경
                        myBtnRec.setEnabled(true); //기록버튼 활성
                        cur_Status = Run; //현재상태를 런상태로 변경
                        break;
                    case Run:
                        myTimer.removeMessages(0); //핸들러 메세지 제거
                        myPauseTime = SystemClock.elapsedRealtime();
                        myBtnStart.setText("시작");
                        myBtnRec.setText("리셋");
                        cur_Status = Pause;
                        break;
                    case Pause:
                        long now = SystemClock.elapsedRealtime();
                        myTimer.sendEmptyMessage(0);
                        myBaseTime += (now- myPauseTime);
                        myBtnStart.setText("멈춤");
                        myBtnRec.setText("기록");
                        cur_Status = Run;
                        break;
                }
                break;
            case R.id.btn_rec:
                switch(cur_Status){
                    case Run:
                        String str = myRec.getText().toString();
                        str +=  String.format("%d. %s 동안의 점수는 : %s \n",myCount,getTimeOut(), score);
                        myRec.setText(str);
                        myCount++; //카운트 증가
                        break;
                    case Pause:
                        //핸들러를 멈춤
                        myTimer.removeMessages(0);

                        myBtnStart.setText("시작");
                        myBtnRec.setText("기록");
                        myOutput.setText("00:00:00");

                        cur_Status = Init;
                        myCount = 1;
                        myRec.setText("");
                        myBtnRec.setEnabled(false);
                        break;
                }
                break;
        }
    }
    Handler myTimer = new Handler(){
        public void handleMessage(Message msg){
            myOutput.setText(getTimeOut());
            //sendEmptyMessage 는 비어있는 메세지를 Handler 에게 전송하는겁니다.
            myTimer.sendEmptyMessage(0);
        }
    };
    //현재시간을 계속 구해서 출력하는 메소드
    String getTimeOut(){
        long now = SystemClock.elapsedRealtime(); //애플리케이션이 실행되고나서 실제로 경과된 시간;
        long outTime = now - myBaseTime;
        String easy_outTime = String.format("%02d:%02d:%02d"
                ,outTime/1000 / 60 / 60
                , outTime/1000 / 60
                , (outTime/1000)%60);
        return easy_outTime;
    }
    /*====타이머 끝====*/

    void bluetoothOn() {
        if(mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "블루투스를 지원하지 않는 기기입니다.", Toast.LENGTH_LONG).show();
        }
        else {
            if (mBluetoothAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "블루투스가 이미 활성화 되어 있습니다.", Toast.LENGTH_LONG).show();
                mTvBluetoothStatus.setText("활성화");
            }
            else {
                Toast.makeText(getApplicationContext(), "블루투스가 활성화 되어 있지 않습니다.", Toast.LENGTH_LONG).show();
                Intent intentBluetoothEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intentBluetoothEnable, BT_REQUEST_ENABLE);
            }
        }
    }
    void bluetoothOff() {
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되었습니다.", Toast.LENGTH_SHORT).show();
            mTvBluetoothStatus.setText("비활성화");
        }
        else {
            Toast.makeText(getApplicationContext(), "블루투스가 이미 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case BT_REQUEST_ENABLE:
                if (resultCode == RESULT_OK) { // 블루투스 활성화를 확인을 클릭하였다면
                    Toast.makeText(getApplicationContext(), "블루투스 활성화", Toast.LENGTH_LONG).show();
                    mTvBluetoothStatus.setText("활성화");
                } else if (resultCode == RESULT_CANCELED) { // 블루투스 활성화를 취소를 클릭하였다면
                    Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show();
                    mTvBluetoothStatus.setText("비활성화");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    void listPairedDevices() {
        if (mBluetoothAdapter.isEnabled()) {
            mPairedDevices = mBluetoothAdapter.getBondedDevices();

            if (mPairedDevices.size() > 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("장치 선택");

                mListPairedDevices = new ArrayList<String>();
                for (BluetoothDevice device : mPairedDevices) {
                    mListPairedDevices.add(device.getName());
                    //mListPairedDevices.add(device.getName() + "\n" + device.getAddress());
                }
                final CharSequence[] items = mListPairedDevices.toArray(new CharSequence[mListPairedDevices.size()]);
                mListPairedDevices.toArray(new CharSequence[mListPairedDevices.size()]);

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        connectSelectedDevice(items[item].toString());
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                Toast.makeText(getApplicationContext(), "페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }
    void connectSelectedDevice(String selectedDeviceName) {
        for(BluetoothDevice tempDevice : mPairedDevices) {
            if (selectedDeviceName.equals(tempDevice.getName())) {
                mBluetoothDevice = tempDevice;
                break;
            }
        }
        try {
            mBluetoothSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(BT_UUID);
            mBluetoothSocket.connect();
            mThreadConnectedBluetooth = new ConnectedBluetoothThread(mBluetoothSocket);
            mThreadConnectedBluetooth.start();
            mBluetoothHandler.obtainMessage(BT_CONNECTING_STATUS, 1, -1).sendToTarget();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "블루투스 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
        }
    }

    private class ConnectedBluetoothThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedBluetoothThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "소켓 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }
        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            while (true) {
                try {
                    bytes = mmInStream.available();
                    if (bytes != 0) {
                        SystemClock.sleep(100);
                        bytes = mmInStream.available();
                        bytes = mmInStream.read(buffer, 0, bytes);
                        mBluetoothHandler.obtainMessage(BT_MESSAGE_READ, bytes, -1, buffer).sendToTarget();
                    }
                } catch (IOException e) {
                    break;
                }
            }
        }
        public void write(String str) {
            byte[] bytes = str.getBytes();
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "데이터 전송 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }
        }
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "소켓 해제 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }
}