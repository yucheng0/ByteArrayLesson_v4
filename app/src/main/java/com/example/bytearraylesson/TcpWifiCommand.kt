package com.example.bytearraylesson

import android.util.Log

class TcpWifiCommand {
    var commandHead = 0x55            //固定
    var protocol = 0xFE               //固定
    var protocolLength = 0x71
    var operation = 0xfe              //固定
    var operationLength = 0x6F        // Protocollength -2
    var sendType = 1                  //固定
    var wifiVersion = ArrayList<Int>()
    var macAddress = ArrayList<Int>()
    var bandSwitch2_4G = 0x01                         // Enabled
    var ssid_2_4GLength = 0x0c
    var ssid_2_4G = ArrayList<Int>()
    var password_2_4GLength = 0x12
    var password_2_4G = ArrayList<Int>()
    var bandSwitch_5G = 0x01
    var ssid_5GLength = 0x0f
    var ssid_5G = ArrayList<Int>()
    var password_5GLength = 0x08
    var password_5G = ArrayList<Int>()
    var eapMethod = 0
    var eapInnerMethod = 0
    var eapUserIdLength = 0x0c
    var eapUserId = ArrayList<Int>()
    var eapUserPasswordLength = 0x0f
    var eapUserPassword = ArrayList<Int>()
    var sgIP = ArrayList<Int>()   //資料從低byte送
    var serialNumber = ArrayList<Int>()
    var cs = 0x71
    val commandEnd = 0x90

    //
    init {
        wifiVersion.add(0x31)                             // 這部分會由轉板收到
        wifiVersion.add(0x37)
        wifiVersion.add(0x38)

        macAddress.add(0x88)                                //這部分會由轉板收到
        macAddress.add(0xda)
        macAddress.add(0x1a)
        macAddress.add(0xf9)
        macAddress.add(0x57)
        macAddress.add(0x68)

        //SportsArt_10   fix 12byte
        ssid_2_4G.add('S'.toInt())
        ssid_2_4G.add('p'.toInt())
        ssid_2_4G.add('o'.toInt())
        ssid_2_4G.add('r'.toInt())
        ssid_2_4G.add('t'.toInt())
        ssid_2_4G.add('s'.toInt())
        ssid_2_4G.add('A'.toInt())
        ssid_2_4G.add('r'.toInt())
        ssid_2_4G.add('t'.toInt())
        ssid_2_4G.add('_'.toInt())
        ssid_2_4G.add('1'.toInt())
        ssid_2_4G.add('0'.toInt())
//sportsart063840888  fix:  18byte
        password_2_4G.add('s'.toInt())
        password_2_4G.add('p'.toInt())
        password_2_4G.add('o'.toInt())
        password_2_4G.add('r'.toInt())
        password_2_4G.add('t'.toInt())
        password_2_4G.add('s'.toInt())
        password_2_4G.add('a'.toInt())
        password_2_4G.add('r'.toInt())
        password_2_4G.add('t'.toInt())
        password_2_4G.add('0'.toInt())
        password_2_4G.add('6'.toInt())
        password_2_4G.add('3'.toInt())
        password_2_4G.add('8'.toInt())
        password_2_4G.add('4'.toInt())
        password_2_4G.add('0'.toInt())
        password_2_4G.add('8'.toInt())
        password_2_4G.add('8'.toInt())
        password_2_4G.add('8'.toInt())
//SportArt_10_5G     fix = 15byte
        ssid_5G.add('S'.toInt())
        ssid_5G.add('p'.toInt())
        ssid_5G.add('o'.toInt())
        ssid_5G.add('r'.toInt())
        ssid_5G.add('t'.toInt())
        ssid_5G.add('s'.toInt())
        ssid_5G.add('A'.toInt())
        ssid_5G.add('r'.toInt())
        ssid_5G.add('t'.toInt())
        ssid_5G.add('_'.toInt())
        ssid_5G.add('1'.toInt())
        ssid_5G.add('0'.toInt())
        ssid_5G.add('_'.toInt())
        ssid_5G.add('5'.toInt())
        ssid_5G.add('G'.toInt())
//00000000     fix:8byte
        password_5G.add('0'.toInt())
        password_5G.add('0'.toInt())
        password_5G.add('0'.toInt())
        password_5G.add('0'.toInt())
        password_5G.add('0'.toInt())
        password_5G.add('0'.toInt())
        password_5G.add('0'.toInt())
        password_5G.add('0'.toInt())

// //   var eapUserId = "SportsArtEAP"   fix:12byte
        eapUserId.add('S'.toInt())
        eapUserId.add('p'.toInt())
        eapUserId.add('o'.toInt())
        eapUserId.add('r'.toInt())
        eapUserId.add('t'.toInt())
        eapUserId.add('s'.toInt())
        eapUserId.add('A'.toInt())
        eapUserId.add('r'.toInt())
        eapUserId.add('t'.toInt())
        eapUserId.add('E'.toInt())
        eapUserId.add('A'.toInt())
        eapUserId.add('P'.toInt())

//  var eapUserPassword = "sa.eap063840888"     fix:15byte
        eapUserPassword.add('s'.toInt())
        eapUserPassword.add('a'.toInt())
        eapUserPassword.add('.'.toInt())
        eapUserPassword.add('e'.toInt())
        eapUserPassword.add('a'.toInt())
        eapUserPassword.add('p'.toInt())
        eapUserPassword.add('0'.toInt())
        eapUserPassword.add('6'.toInt())
        eapUserPassword.add('3'.toInt())
        eapUserPassword.add('8'.toInt())
        eapUserPassword.add('4'.toInt())
        eapUserPassword.add('0'.toInt())
        eapUserPassword.add('8'.toInt())
        eapUserPassword.add('8'.toInt())
        eapUserPassword.add('8'.toInt())

        sgIP.add(0xc0)
        sgIP.add(0xa8)
        sgIP.add(0x00)
        sgIP.add(0x0e)
        serialNumber.add(0x31)
        serialNumber.add(0x32)
        serialNumber.add(0x33)
        serialNumber.add(0x34)
        serialNumber.add(0x35)
        serialNumber.add(0x36)
        serialNumber.add(0x37)
    }


    fun tcpWifiSend() {             //當你的資料備好時, 我只負責送不負責計算？ cs是否例外
        val dataIntArray = ArrayList<Int>()
        dataIntArray.add(commandHead)
        dataIntArray.add(protocol)
        dataIntArray.add(protocolLength)                       //     dataIntArray.add()      //Protol Length
        dataIntArray.add(operation)
        dataIntArray.add(operationLength)   // Operation Lenght
        dataIntArray.add(sendType)   // Sender = Client

        for (i in wifiVersion) {                   // 字元 Ascii 轉數字
            dataIntArray.add(i.toInt())
        }

        for (i in macAddress) {                   // 字元 Ascii 轉數字
            dataIntArray.add(i.toInt())
        }

        dataIntArray.add(bandSwitch2_4G)

        dataIntArray.add(ssid_2_4GLength)

        for (i in ssid_2_4G) {                          // 字元 Ascii 轉數字
            dataIntArray.add(i.toInt())
            Log.d(TAG, "dataIntArray:${dataIntArray}")
        }

        dataIntArray.add(password_2_4GLength)

        for (i in password_2_4G) {                          // 字元 Ascii 轉數字
            dataIntArray.add(i.toInt())
        }

        dataIntArray.add(bandSwitch_5G)
        dataIntArray.add(ssid_5GLength)

        for (i in ssid_5G) {                          // 字元 Ascii 轉數字
            dataIntArray.add(i.toInt())
        }

        dataIntArray.add(password_5GLength)

        for (i in password_5G) {                          // 字元 Ascii 轉數字
            dataIntArray.add(i.toInt())
        }

        dataIntArray.add(eapMethod)
        dataIntArray.add(eapInnerMethod)

        dataIntArray.add(eapUserIdLength)

        for (i in eapUserId) {                          // 字元 Ascii 轉數字
            dataIntArray.add(i.toInt())
        }

        dataIntArray.add(eapUserPasswordLength)

        for (i in eapUserPassword) {                          // 字元 Ascii 轉數字
            dataIntArray.add(i.toInt())
        }

        for (i in sgIP) {                   // 字元 Ascii 轉數字
            dataIntArray.add(i.toInt())
        }

        for (i in serialNumber) {                   // 字元 Ascii 轉數字
            dataIntArray.add(i.toInt())
        }

//cs 是算出來的
        cs = 0
        for (i in 1..dataIntArray.size - 1) {            // 從1是去頭55, 去尾90再算裡面
            cs = cs + dataIntArray[i]
        }
        Log.d(TAG, "原cs: ${cs}")

        var css = cs.toString(16)
        Log.d(TAG, "css: ${css}")                           // 16f
        var cs1 = css.subSequence((css.length) - 2, (css.length))   //6f 取後面2位數
        cs = cs1.toString().toInt(16)
        Log.d(TAG, "果cs: ${cs}")

        dataIntArray.add(cs)

        dataIntArray.add(commandEnd)
        //
        val dataByteArray = ByteArray(dataIntArray.size)
        for (i in 0..dataIntArray.size - 1) {
            dataByteArray[i] = dataIntArray[i].toByte()
            //           Log.d(TAG, "dataByteArray[i]: ${dataByteArray[i]} ")
        }

        Log.d(TAG, "dataIntArray: ${dataIntArray}")

        // test 測試, 轉成16進制
        val dataIntArrayHex = ArrayList<String>()
        val x = dataIntArray[0].toString(16)
        //      Log.d(TAG, "tcpWifisendx: $x")
        for (i in 0..dataByteArray.size - 1) {
            dataIntArrayHex.add(dataIntArray[i].toString(16))
            //         Log.d(TAG, "Hex:${dataIntArrayHex[i]}")
        }
        Log.d(TAG, "dataIntArrayHex: ${dataIntArrayHex}")

    }

    //  mode1 - Connection Status
    fun tcpWifiReceiverModeConnectioStatus() {
        var rDataByteArray = ByteArray(1024)    // 收到的資料, 它會告訴我幾筆
        rDataByteArray.set(0, 0x55)
        rDataByteArray.set(1, 0xfe - 256)
        rDataByteArray.set(2, 0x04)
        rDataByteArray.set(3, 0xff - 256)
        rDataByteArray.set(4, 0x02)
        rDataByteArray.set(5, 0x00)
        rDataByteArray.set(6, 0x01)
        rDataByteArray.set(7, 0x04)
        rDataByteArray.set(8, 0x90 - 256)

        val size = 9
        val rDataIntArray = ArrayList<Int>()

        // Byte -> Int
        for (i in 0..size - 1) { //先知道nubBytes的數字再去讀
            if (rDataByteArray[i].toInt() >= 0) {
                rDataIntArray.add(rDataByteArray[i].toString().toInt())
            } else { //負數處理
                rDataIntArray.add(256 + rDataByteArray[i].toInt())
            }
        }
        //--------基本過濾  --------------
        if (rDataIntArray.contains(0x55) && rDataIntArray.contains(0xfe) && rDataIntArray.contains(
                0xff
            ) && rDataIntArray.contains(0x90) && size >= 9
        ) {
            val x = rDataIntArray.indexOf(0x55)   // 取到初值
            if (rDataIntArray[x + 1] == 0xFe && rDataIntArray[x + 3] == 0xff) {
                if (x + rDataIntArray[x + 2] + 5 <= size && rDataIntArray[x + 4] == rDataIntArray[x + 2] - 2)
                    if (rDataIntArray[x + rDataIntArray[x + 2] + 5 - 1] == 0x90) {
                        //判斷cs 是否正確(範例:8f)
                        var y = 0
                        for (i in x + 1..rDataIntArray[x + 2] + 2) {
                            y = y + rDataIntArray[i]
                            //                   Log.d(TAG, "i,$i: ${rDataIntArray[i]} = , y: $y")
                        }

                        var z = y.toString(16)
                        var k = z.subSequence(z.length - 2, z.length)
                        Log.d(TAG, "k: $k")
// 處理
                        if (rDataIntArray[x + 5] == 0x00 && rDataIntArray[x + 6] == 0x01) {
                            Log.d(TAG, "tcpWifiReceiverMode1: I am pass")
                        }
                    } else Log.d(TAG, "tcpWifiReceiverMode1: I am fail")
            }
        }
    }        //end


    /*     接收
    Ex: totol 118byte, 71hex = 113 , 113+1(本身）+1（55)+1(fe)+1(cs)+1(end)
55 FE 71 FE 6F 00 31 37 38 88
DA 1A F9 57 68 01 0C 53 70 6f
72 74 73 41 72 74 5f 31 30 12
73 70 6f 72 74 73 61 72 74 30
36 33 38 34 30 38 38 38 01 0F
53 70 6f 72 74 73 41 72 74 5f
31 30 5f 35 47 08 30 30 30 30
30 30 30 30 00 00 0C 53 70 6f
72 74 73 41 72 74 45 41 50 0F
73 61 2e 65 61 70 30 36 33 38
34 30 38 38 38 C0 A8 00 0E 31
32 33 34 35 36 37 8F 90


  */
    // mode2_1 - WifiParameter
    fun tcpWifiReceiverModeWifiParameter() {
        var rDataByteArray = ByteArray(1024)    // 收到的資料, 它會告訴我幾筆
        //  55 FE 71 FE 6F 00 31 37 38 88
        rDataByteArray.set(0, 0x55)
        rDataByteArray.set(1, 0xfe - 256)
        rDataByteArray.set(2, 0x71)
        rDataByteArray.set(3, 0xfe - 256)
        rDataByteArray.set(4, 0x6f)
        rDataByteArray.set(5, 0x00)
        rDataByteArray.set(6, 0x31)
        rDataByteArray.set(7, 0x37)
        rDataByteArray.set(8, 0x38)
        rDataByteArray.set(9, 0x88 - 256)
        //DA 1A F9 57 68 01 0C 53 70 6f
        rDataByteArray.set(10, 0xda - 256)
        rDataByteArray.set(11, 0x1a)
        rDataByteArray.set(12, 0xf9 - 256)
        rDataByteArray.set(13, 0x57)
        rDataByteArray.set(14, 0x68)
        rDataByteArray.set(15, 0x01)
        rDataByteArray.set(16, 0x0c)
        rDataByteArray.set(17, 0x53)
        rDataByteArray.set(18, 0x70)
        rDataByteArray.set(19, 0x6f)
        //72 74 73 41 72 74 5f 31 30 12
        rDataByteArray.set(20, 0x72)
        rDataByteArray.set(21, 0x74)
        rDataByteArray.set(22, 0x73)
        rDataByteArray.set(23, 0x41)
        rDataByteArray.set(24, 0x72)
        rDataByteArray.set(25, 0x74)
        rDataByteArray.set(26, 0x5f)
        rDataByteArray.set(27, 0x31)
        rDataByteArray.set(28, 0x30)     //?
        rDataByteArray.set(29, 0x12)
        //  73 70 6f 72 74 73 61 72 74 30
        rDataByteArray.set(30, 0x73)
        rDataByteArray.set(31, 0x70)
        rDataByteArray.set(32, 0x6f)
        rDataByteArray.set(33, 0x72)
        rDataByteArray.set(34, 0x74)
        rDataByteArray.set(35, 0x73)
        rDataByteArray.set(36, 0x61)
        rDataByteArray.set(37, 0x72)
        rDataByteArray.set(38, 0x74)
        rDataByteArray.set(39, 0x30)
        //      36 33 38 34 30 38 38 38 01 0F
        rDataByteArray.set(40, 0x36)
        rDataByteArray.set(41, 0x33)
        rDataByteArray.set(42, 0x38)
        rDataByteArray.set(43, 0x34)
        rDataByteArray.set(44, 0x30)
        rDataByteArray.set(45, 0x38)
        rDataByteArray.set(46, 0x38)
        rDataByteArray.set(47, 0x38)   //?
        rDataByteArray.set(48, 0x01)
        rDataByteArray.set(49, 0x0f)
//53 70 6f 72 74 73 41 72 74 5f
        rDataByteArray.set(50, 0x53)
        rDataByteArray.set(51, 0x70)
        rDataByteArray.set(52, 0x6f)
        rDataByteArray.set(53, 0x72)
        rDataByteArray.set(54, 0x74)
        rDataByteArray.set(55, 0x73)
        rDataByteArray.set(56, 0x41)
        rDataByteArray.set(57, 0x72)
        rDataByteArray.set(58, 0x74)
        rDataByteArray.set(59, 0x5f)
//   31 30 5f 35 47 08 30 30 30 30
        rDataByteArray.set(60, 0x31)
        rDataByteArray.set(61, 0x30)
        rDataByteArray.set(62, 0x5f)
        rDataByteArray.set(63, 0x35)
        rDataByteArray.set(64, 0x47)
        rDataByteArray.set(65, 0x08)
        rDataByteArray.set(66, 0x30)
        rDataByteArray.set(67, 0x30)
        rDataByteArray.set(68, 0x30)
        rDataByteArray.set(69, 0x30)
//30 30 30 30 00 00 0C 53 70 6f
        rDataByteArray.set(70, 0x30)
        rDataByteArray.set(71, 0x30)
        rDataByteArray.set(72, 0x30)
        rDataByteArray.set(73, 0x30)
        rDataByteArray.set(74, 0x00)
        rDataByteArray.set(75, 0x00)
        rDataByteArray.set(76, 0x0c)
        rDataByteArray.set(77, 0x53)
        rDataByteArray.set(78, 0x70)
        rDataByteArray.set(79, 0x6f)
//72 74 73 41 72 74 45 41 50 0F
        rDataByteArray.set(80, 0x72)
        rDataByteArray.set(81, 0x74)
        rDataByteArray.set(82, 0x73)
        rDataByteArray.set(83, 0x41)
        rDataByteArray.set(84, 0x72)
        rDataByteArray.set(85, 0x74)
        rDataByteArray.set(86, 0x45)
        rDataByteArray.set(87, 0x41)
        rDataByteArray.set(88, 0x50)
        rDataByteArray.set(89, 0x0f)

        //       73 61 2e 65 61 70 30 36 33 38
        rDataByteArray.set(90, 0x73)
        rDataByteArray.set(91, 0x61)
        rDataByteArray.set(92, 0x2e)
        rDataByteArray.set(93, 0x65)
        rDataByteArray.set(94, 0x61)
        rDataByteArray.set(95, 0x70)
        rDataByteArray.set(96, 0x30)
        rDataByteArray.set(97, 0x36)
        rDataByteArray.set(98, 0x33)
        rDataByteArray.set(99, 0x38)
        //     34 30 38 38 38 C0 A8 00 0E 31
        rDataByteArray.set(100, 0x34)
        rDataByteArray.set(101, 0x30)
        rDataByteArray.set(102, 0x38)
        rDataByteArray.set(103, 0x38)
        rDataByteArray.set(104, 0x38)
        rDataByteArray.set(105, 0xc0 - 256)
        rDataByteArray.set(106, 0xa8 - 256)
        rDataByteArray.set(107, 0x00)
        rDataByteArray.set(108, 0x0e)
        rDataByteArray.set(109, 0x31)
        //   32 33 34 35 36 37 8F 90
        rDataByteArray.set(110, 0x32)
        rDataByteArray.set(111, 0x33)
        rDataByteArray.set(112, 0x34)
        rDataByteArray.set(113, 0x35)
        rDataByteArray.set(114, 0x36)
        rDataByteArray.set(115, 0x37)
        rDataByteArray.set(116, 0x8f - 256)
        rDataByteArray.set(117, 0x90 - 256)
        val size = 118
        val rDataIntArray = ArrayList<Int>()

        // Byte -> Int
        for (i in 0..size - 1) { //先知道nubBytes的數字再去讀
            if (rDataByteArray[i].toInt() >= 0) {
                rDataIntArray.add(rDataByteArray[i].toString().toInt())
            } else { //負數處理
                rDataIntArray.add(256 + rDataByteArray[i].toInt())
            }
        }

        Log.d(TAG, "rDataIntArray: ${rDataIntArray}")

//找出固定關鍵字才去parser  55, fe, 90  & >=34
// >= protocol length + 5

        //第2種：wifi parameter 基本的過濾器
        if (rDataIntArray.contains(0x55) && rDataIntArray.contains(0xfe) && rDataIntArray.contains(
                0x90
            ) && size >= 34
        ) {
            //基本通過就可以開始解析了
            val x = rDataIntArray.indexOf(0x55)
            if (rDataIntArray[x + 1] == 0xfe && rDataIntArray[x + 3] == 0xFE) {
                if (x + rDataIntArray[x + 2] + 5 <= size && rDataIntArray[x + 4] == rDataIntArray[x + 2] - 2) {
                    Log.d(TAG, "parser1: Found ： ${x + rDataIntArray[x + 2] + 5 - 1}")
                    if (rDataIntArray[x + rDataIntArray[x + 2] + 5 - 1] == 0x90) {
                        //判斷cs 是否正確(範例:8f)
                        var y = 0
                        for (i in x + 1..rDataIntArray[x + 2] + 2) {
                            y = y + rDataIntArray[i]
                            //                   Log.d(TAG, "i,$i: ${rDataIntArray[i]} = , y: $y")
                        }
                        var z = y.toString(16)
                        var k = z.subSequence(z.length - 2, z.length)

                        Log.d(TAG, "k: $k")

                        /*所有判斷均完成, 搬到對應的尺寸   */
                        Log.d(
                            TAG,
                            "90: ${rDataIntArray[x + rDataIntArray[x + 2] + 5 - 1]} "
                        )

                        val b1 = rDataIntArray.listIterator(x + 6)
                        //  sendType = rDataIntArray[x+5]
                        wifiVersion.clear()
                        for (i in 0..2) {               // Wifi version
                            wifiVersion.add(b1.next())
                        }

                        Log.d(TAG, "wifiVersion: ${wifiVersion}")
                        macAddress.clear()
                        for (i in 0..5) {               //MAC Adadres
                            macAddress.add(b1.next())
                        }
                        //                                                  }
                        Log.d(TAG, "macAddress : ${macAddress} ")
                        bandSwitch2_4G = b1.next()
                        ssid_2_4GLength = b1.next()
                        ssid_2_4G.clear()
                        for (i in 0..ssid_2_4GLength - 1) {
                            ssid_2_4G.add(b1.next())
                        }
                        Log.d(TAG, "ssid_2_4G: $ssid_2_4G")

                        password_2_4GLength = b1.next()
                        Log.d(TAG, "password_2_4GLength: $password_2_4GLength")
                        password_2_4G.clear()
                        for (i in 0..password_2_4GLength - 1) {
                            password_2_4G.add(b1.next())
                        }
                        Log.d(TAG, "password_2_4G: $password_2_4G")

                        bandSwitch_5G = b1.next()
                        ssid_5GLength = b1.next()
                        ssid_5G.clear()
                        for (i in 0..ssid_5GLength - 1) {
                            ssid_5G.add(b1.next())
                        }
                        Log.d(TAG, "ssid_5G: $ssid_5G")

                        password_5GLength = b1.next()
                        password_5G.clear()
                        for (i in 0..password_5GLength - 1) {
                            password_5G.add(b1.next())
                        }
                        Log.d(TAG, "password_5G: $password_5G")

                        eapMethod = b1.next()
                        eapInnerMethod = b1.next()
                        eapUserIdLength = b1.next()
                        eapUserId.clear()
                        for (i in 0..eapUserIdLength - 1) {
                            eapUserId.add(b1.next())
                        }
                        Log.d(TAG, "eapUserId: $eapUserId")

                        eapUserPasswordLength = b1.next()
                        eapUserPassword.clear()
                        for (i in 0..eapUserPasswordLength - 1) {
                            eapUserPassword.add(b1.next())
                        }
                        Log.d(TAG, "eapUserPassword: $eapUserPassword")

                        for (i in 0..3) {
                            sgIP.add(b1.next())
                        }
                        Log.d(TAG, "sgIP: $sgIP")

                        for (i in 0..6) {
                            serialNumber.add(b1.next())
                        }
                        Log.d(TAG, "serialNumber: $serialNumber")
                    }    //    90end
                }

            }

        } else Log.d(TAG, "parser1: Not Found")


        // ie. 模擬的資料已在Bytearray內
        val commandHeadIndex = rDataByteArray.indexOf(85)
        var dataHasCommandHead = false
        if (rDataByteArray.contains(85)) {
            dataHasCommandHead = true
        }


        //         if (dataHasCommandHead && )
        val x = rDataByteArray[commandHeadIndex + 1]
        if (rDataByteArray[commandHeadIndex + 1] == (256 - 0xfe).toByte()) {
            //            if (rDataByteArray[commandHeadIndex + 1] == rDataByteArray[rDataByteArray[commandHe]])             //長度


        }


    }

    // keey Alive
    fun tcpWifiReceiverMode3() {
        var rDataByteArray = ByteArray(1024)    // 收到的資料, 它會告訴我幾筆
        rDataByteArray.set(0, 0x55)
        rDataByteArray.set(1, 0xfe - 256)
        rDataByteArray.set(2, 0x04)
        rDataByteArray.set(3, 0xfd - 256)
        rDataByteArray.set(4, 0x02)
        rDataByteArray.set(5, 0x00)
        rDataByteArray.set(6, 0x00)
        rDataByteArray.set(7, 0x02)
        rDataByteArray.set(8, 0x90 - 256)

        val size = 9
        val rDataIntArray = ArrayList<Int>()

        // Byte -> Int
        for (i in 0..size - 1) { //先知道nubBytes的數字再去讀
            if (rDataByteArray[i].toInt() >= 0) {
                rDataIntArray.add(rDataByteArray[i].toString().toInt())
            } else { //負數處理
                rDataIntArray.add(256 + rDataByteArray[i].toInt())
            }
        }
        //--------基本過濾  --------------
        if (rDataIntArray.contains(0x55) && rDataIntArray.contains(0xfe) && rDataIntArray.contains(
                0xfd
            ) && rDataIntArray.contains(0x90) && size >= 9
        ) {
            val x = rDataIntArray.indexOf(0x55)   // 取到初值
            if (rDataIntArray[x + 1] == 0xFe && rDataIntArray[x + 3] == 0xfd) {
                if (x + rDataIntArray[x + 2] + 5 <= size && rDataIntArray[x + 4] == rDataIntArray[x + 2] - 2)
                    if (rDataIntArray[x + rDataIntArray[x + 2] + 5 - 1] == 0x90) {
                        //判斷cs 是否正確(範例:8f)
                        var y = 0
                        for (i in x + 1..rDataIntArray[x + 2] + 2) {
                            y = y + rDataIntArray[i]
                            //                   Log.d(TAG, "i,$i: ${rDataIntArray[i]} = , y: $y")
                        }

                        var z = y.toString(16)
                        var k = z.subSequence(z.length - 2, z.length)
                        Log.d(TAG, "k: $k")
// 解析成功後之處理流程在此
                        if (rDataIntArray[x + 5] == 0x00)  //有個值會變要和你送的順序有關
                        {
                            Log.d(TAG, "tcpWifiReceiverMode3: I am pass")
                        }
                    } else Log.d(TAG, "tcpWifiReceiverMode3: I am fail")
            }
        }
    }  // fun end

    // ------  Reset Reuest mode 4
    fun tcpWifiReceiverMode4() {
        var rDataByteArray = ByteArray(1024)    // 收到的資料, 它會告訴我幾筆
        rDataByteArray.set(0, 0x55)
        rDataByteArray.set(1, 0xfe - 256)
        rDataByteArray.set(2, 0x03)
        rDataByteArray.set(3, 0xfc - 256)
        rDataByteArray.set(4, 0x01)
        rDataByteArray.set(5, 0xfe - 256)
        rDataByteArray.set(6, 0xfc - 256)
        rDataByteArray.set(7, 0x90 - 256)


        val size = 8
        val rDataIntArray = ArrayList<Int>()

        // Byte -> Int
        for (i in 0..size - 1) { //先知道nubBytes的數字再去讀
            if (rDataByteArray[i].toInt() >= 0) {
                rDataIntArray.add(rDataByteArray[i].toString().toInt())
            } else { //負數處理
                rDataIntArray.add(256 + rDataByteArray[i].toInt())
            }
        }
        //--------基本過濾  --------------
        if (rDataIntArray.contains(0x55) && rDataIntArray.contains(0xfe) && rDataIntArray.contains(
                0xfc
            ) && rDataIntArray.contains(0x90) && size >= 8
        ) {
            val x = rDataIntArray.indexOf(0x55)   // 取到初值
            if (rDataIntArray[x + 1] == 0xFe && rDataIntArray[x + 3] == 0xfc) {
                if (x + rDataIntArray[x + 2] + 5 <= size && rDataIntArray[x + 4] == rDataIntArray[x + 2] - 2)
                    if (rDataIntArray[x + rDataIntArray[x + 2] + 5 - 1] == 0x90) {
                        //判斷cs 是否正確(範例:8f)
                        var y = 0
                        for (i in x + 1..rDataIntArray[x + 2] + 2) {
                            y = y + rDataIntArray[i]
                            //                   Log.d(TAG, "i,$i: ${rDataIntArray[i]} = , y: $y")
                        }

                        var z = y.toString(16)
                        var k = z.subSequence(z.length - 2, z.length)
                        Log.d(TAG, "k: $k")
// 解析成功後之處理流程在此
                        if (rDataIntArray[x + 5] == 0xfe)  //有個值會變要和你送的順序有關
                        {
                            Log.d(TAG, "tcpWifiReceiverMode4: I am pass")
                        }
                    } else Log.d(TAG, "tcpWifiReceiverMode4: I am fail")
            }
        }
    }  // fun end


    //mode2_2
// ------  wifi-parameter mode 5
    fun tcpWifiReceiverMode5() {
        var rDataByteArray = ByteArray(1024)    // 收到的資料, 它會告訴我幾筆
        rDataByteArray.set(0, 0x55)
        rDataByteArray.set(1, 0xfe - 256)
        rDataByteArray.set(2, 0x03)
        rDataByteArray.set(3, 0xfe - 256)
        rDataByteArray.set(4, 0x01)
        rDataByteArray.set(5, 0x90 - 256)     //client cs
        rDataByteArray.set(6, 0x90 - 256)     //cs
        rDataByteArray.set(7, 0x90 - 256)
        Log.d(TAG, "tcpWifiReceiverMode5: HI i am mode5")

        val size = 8
        val rDataIntArray = ArrayList<Int>()

        // Byte -> Int
        for (i in 0..size - 1) { //先知道nubBytes的數字再去讀
            if (rDataByteArray[i].toInt() >= 0) {
                rDataIntArray.add(rDataByteArray[i].toString().toInt())
            } else { //負數處理
                rDataIntArray.add(256 + rDataByteArray[i].toInt())
            }
        }
        //--------基本過濾  --------------
        /*  val comandHeadFilterValue = 0x55
          val protocolFilterValue = 0xfe
          val operationFilterValue = 0Xfe
          val commandEndFilterValue = 0x55 */

        if (rDataIntArray.contains(0x55) && rDataIntArray.contains(0xfe) && rDataIntArray.contains(
                0xfe
            ) && rDataIntArray.contains(0x90) && size >= 8
        ) {
            val x = rDataIntArray.indexOf(0x55)   // 取到初值
            if (rDataIntArray[x + 1] == 0xFe && rDataIntArray[x + 3] == 0xfe) {
                if (x + rDataIntArray[x + 2] + 5 <= size && rDataIntArray[x + 4] == rDataIntArray[x + 2] - 2)
                    if (rDataIntArray[x + rDataIntArray[x + 2] + 5 - 1] == 0x90) {
                        //判斷cs 是否正確(範例90)
                        var y = 0
                        for (i in x + 1..rDataIntArray[x + 2] + 2) {
                            y = y + rDataIntArray[i]
                            //                   Log.d(TAG, "i,$i: ${rDataIntArray[i]} = , y: $y")
                        }

                        var z = y.toString(16)
                        var k = z.subSequence(z.length - 2, z.length)
                        Log.d(TAG, "k: $k")
// 解析成功後之處理流程在此
                        if (rDataIntArray[x + 5] == 0x90)  //有個值會變要和你送的順序有關
                        {
                            Log.d(TAG, "tcpWifiReceiverMode5: I am pass")
                        }
                    } else Log.d(TAG, "tcpWifiReceiverMode5: I am fail")
            }
        }
    }  // fun end


    //mode2_2
/* ------  Parser Function
* return true = suceessful
* return false = fail
 */

    fun tcpWifiReceiverParserCheck(
        rDataByteArray: ByteArray,              // 傳入接收的位元陣列   （可以由接收端知道）
        size: Int,                              // 接收到的資料總byte （可以由接收端知道）
        minSizeLimit: Int,                      // 最小可以接受的byte(先判斷）有8,9,34
        protocolFilterValue: Int,              // 協定命令值目前都是Fe
        operationFilterValue: Int              //　操作元命令值目前有ff,fe,fd, fc
    ): Boolean {                               // 回傳布林值, ture表示成功 , false表示失敗
        var retureState = false
        val rDataIntArray = ArrayList<Int>()
        // Byte -> Int  將位元陣列轉成整數陣列方便運算用
        for (i in 0..size - 1) {                  // 一定先知道nubBytes的數字再去讀
            if (rDataByteArray[i].toInt() >= 0) {
                rDataIntArray.add(rDataByteArray[i].toString().toInt())
            } else {                            //byte值有負數必須先處理到正數
                rDataIntArray.add(256 + rDataByteArray[i].toInt())
            }
        }
        //--------基本過濾法  --------------
        if (rDataIntArray.contains(0x55) && rDataIntArray.contains(protocolFilterValue) && rDataIntArray.contains(
                operationFilterValue
            ) && rDataIntArray.contains(0x90) && size >= minSizeLimit
        ) {
            val x = rDataIntArray.indexOf(0x55)   // 取到初值索引值
            if (rDataIntArray[x + 1] == protocolFilterValue && rDataIntArray[x + 3] == operationFilterValue) {
                if (x + rDataIntArray[x + 2] + 5 <= size) {
                    if (rDataIntArray[x + 4] == rDataIntArray[x + 2] - 2) {
                        if (rDataIntArray[x + rDataIntArray[x + 2] + 5 - 1] == 0x90) {
                            //  計算cs值
                            var y = 0
                            for (i in x + 1..rDataIntArray[x + 2] + 2) {
                                y = y + rDataIntArray[i]
                                //   Log.d(TAG, "i,$i: ${rDataIntArray[i]} = , y: $y")
                            }
                            //取16進制最後2byte值
                            var z = y.toString(16)
                            var k = z.subSequence(z.length - 2, z.length).toString().toInt(16)
                            //開始判斷cs 是否正確(範例90)
                            if (k == rDataIntArray[x + rDataIntArray[x + 2] + 5 - 2]) {
           //                     Log.d(TAG, "k: $k")
// 解析成功後之處理流程在此
                                Log.d(TAG, "檢查方法全通過")
                                retureState = true
                            } else {
                                Log.d(TAG, "錯誤：檢查碼有誤")
                            }
                        }  else {
                            Log.d(TAG, "錯誤：結束碼 90有誤")}

                    } else {
                        Log.d(TAG, "錯誤：協定長度  & 運算長度不合")}
                } else {
                    Log.d(TAG, "錯誤:長度太短")
                }
            } else {
                Log.d(TAG, "錯誤：協定命令 & 運算命令不合 ")
            }
        } else {                                            //ok
            Log.d(TAG, "過濾基本規格不合跳開")
        }
        return retureState
    }  // fun end



}  // class end

