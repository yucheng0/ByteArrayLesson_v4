package com.example.bytearraylesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bytearraylesson.Parser.ParserKeepAliveWithCounter
import com.example.bytearraylesson.Parser.ParserResponseClientSendDataWithClientCs
import com.example.bytearraylesson.Parser.ParserWifiParameterResponse
import kotlinx.android.synthetic.main.activity_main.*

val TAG = "myTag"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val tcpWifiCommand = TcpWifiCommand()
        //      length(tcpWifiCommand)
        //        tcpWifiCommand.tcpWifiSend()
        //       tcpWifiCommand.tcpWifiReceiverModeWifiParameter()

        //     updatData(tcpWifiCommand)
//        tcpWifiCommand.tcpWifiReceiverMode5()
//Receiver - mode1:
        /*       val parserConnectionStatus = ParserConnectionStatus()
               val r = parserConnectionStatus.tcpWifiReceiverParserCheck(9)  //之後要Data
               Log.d(TAG, "基本檢查結果r: $r")
               //只能說資料在rDataByteArray內, 而且55是第1個出現的去直接取出值來
       //--------------reset

               val parserResetHasClientCS = ParserResetHasClientCS()
               val r1 = parserResetHasClientCS.tcpWifiReceiverParserCheck(8,0xFe)  //之後要Data
               Log.d(TAG, "基本檢查結果r1: $r1") */

// —------ keep alive
/*        val parserKeepAliveWithCounter = ParserKeepAliveWithCounter()
        val r1 = parserKeepAliveWithCounter.tcpWifiReceiverParserCheck(9,0)  //之後要Data
        Log.d(TAG, "基本檢查結果r1: $r1")
    } */

// —----- wifi parameter
/*        val parserWifiParameterResponse = ParserWifiParameterResponse()
        val r1 = parserWifiParameterResponse.tcpWifiReceiverParserCheck(118)  //之後要Data
        Log.d(TAG, "基本檢查結果r1: $r1")
    }  */

// Respose client send data

    val parserResponseClientSendDataWithClientCs = ParserResponseClientSendDataWithClientCs()
    val r2 = parserResponseClientSendDataWithClientCs.tcpWifiReceiverParserCheck(8,0x90)  //之後要Data
    Log.d(TAG, "基本檢查結果r2: $r2")
}



fun length(tcpWifiCommand: TcpWifiCommand) {
    tcpWifiCommand.ssid_2_4GLength = editTextSsid_2_4G.text.toString().length
    tcpWifiCommand.password_2_4GLength = editTextPassword_2_4G.text.toString().length
    tcpWifiCommand.ssid_5GLength = editTextSsid_5G.text.toString().length
    tcpWifiCommand.password_5GLength = editTextPassword_5G.text.toString().length
    tcpWifiCommand.eapUserIdLength = editTextEapUserId.text.toString().length
    tcpWifiCommand.eapUserPasswordLength = editTextEapUserPassword.text.toString().length

//        Log.d(TAG, "tcpWifiCommand.ssid_2_4GLength:${tcpWifiCommand.ssid_2_4GLength} ")
    //       Log.d(TAG, "tcpWifiCommand.password_2_4GLength:${tcpWifiCommand.password_2_4GLength} ")
    //      Log.d(TAG, "tcpWifiCommand.ssid_5GLength:${tcpWifiCommand.ssid_5GLength} ")

    //     Log.d(TAG, " tcpWifiCommand.password_5GLength:${tcpWifiCommand.password_5GLength} ")
    //     Log.d(TAG, "tcpWifiCommand.eapUserIdLength:${tcpWifiCommand.eapUserIdLength} ")
    //     Log.d(TAG, "tcpWifiCommand.eapUserPasswordLength:${tcpWifiCommand.eapUserPasswordLength} ")

    //資料段的長度 111
    tcpWifiCommand.operationLength =
        1 +  //tcpWifiCommand.sendType
                3 +  //wifi version
                6 +  //mac address
                1 +    //BandSwitch
                1 +   // ssid_2.4g length
                tcpWifiCommand.ssid_2_4GLength +
                1 +    //password_2.4g length
                tcpWifiCommand.password_2_4GLength +
                1 +   //bandswitch 5g
                1 +   //ssid_5g length
                tcpWifiCommand.ssid_5GLength +
                1 +   //password_5g length
                tcpWifiCommand.password_5GLength +
                1 +    //eap method
                1 +     //eap innerMethod
                1 +     // eap user id length
                tcpWifiCommand.eapUserIdLength +
                1 +     //eap password length
                tcpWifiCommand.eapUserPasswordLength +
                4 +      //SGIP
                7      // Serial Number

    Log.d(TAG, "operationLength: ${tcpWifiCommand.operationLength} ")
    tcpWifiCommand.protocolLength = tcpWifiCommand.operationLength + 2
    Log.d(TAG, "protocolLength: ${tcpWifiCommand.protocolLength}")

    //cs 是算出來的
    //      tcpWifiCommand.cs = 0
    //       for (i in 1..2) {
    //           tcpWifiCommand.cs = tcpWifiCommand.cs + tcpWifiCommand.dataIntArray[i]
    //       }


}

fun updatData(tcpWifiCommand: TcpWifiCommand) {
    //
    var s = ""
    for (i in 0..tcpWifiCommand.ssid_2_4GLength - 1) {
        val s1 = tcpWifiCommand.ssid_2_4G[i].toChar()
        s = s + s1
    }
    Log.d(TAG, "ssid_2.4g: $s")
    editTextSsid_2_4G.setText(s)

//--------------- password 2.4 G ---------------
    s = ""
    for (i in 0..tcpWifiCommand.password_2_4GLength - 1) {
        val s1 = tcpWifiCommand.password_2_4G[i].toChar()
        s = s + s1
    }
    Log.d(TAG, "password2.4g: $s")
    editTextPassword_2_4G.setText(s)

//------------ ssid 5g ------------------
    s = ""
    for (i in 0..tcpWifiCommand.ssid_5GLength - 1) {
        val s1 = tcpWifiCommand.ssid_5G[i].toChar()
        s = s + s1
    }
    Log.d(TAG, "ssid_5g: $s")
    editTextSsid_5G.setText(s)


//---------------password 5g---------
    s = ""
    for (i in 0..tcpWifiCommand.password_5GLength - 1) {
        val s1 = tcpWifiCommand.password_5G[i].toChar()
        s = s + s1
    }
    Log.d(TAG, "password_5g: $s")
    editTextPassword_5G.setText(s)

// ---- eap method ---------
    tcpWifiCommand.eapMethod

    //  --- eap inner method  ---------
    tcpWifiCommand.eapInnerMethod

    //---------------eap user id---------
    s = ""
    for (i in 0..tcpWifiCommand.eapUserIdLength - 1) {
        val s1 = tcpWifiCommand.eapUserId[i].toChar()
        s = s + s1
    }
    Log.d(TAG, "eapuserid $s")
    editTextEapUserId.setText(s)

    //---------------eap user password---------
    s = ""
    for (i in 0..tcpWifiCommand.eapUserPasswordLength - 1) {
        val s1 = tcpWifiCommand.eapUserPassword[i].toChar()
        s = s + s1
    }
    Log.d(TAG, "eapuserpassword $s")
    editTextEapUserPassword.setText(s)

    //---------------SGIP---------
    s = ""
    for (i in 0..3) {                       //非字元, 它是數值
        val s1 = tcpWifiCommand.sgIP[i]
        if (i != 3) {
            s = s + s1.toString() + "."
        }             //處理後面多了點
        else s = s + s1.toString()               // 不要點

    }
    Log.d(TAG, "sgip $s")
    textViewSgIp.setText(s)

    //---------------Serial Numbe---------
    s = ""
    for (i in 0..6) {
        val s1 = tcpWifiCommand.serialNumber[i].toChar()
        s = s + s1
    }
    Log.d(TAG, "serialNumber $s")
    textViewSerialNumber.setText(s)


}

}