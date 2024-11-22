package com.tekidoer.sockshttp.util;

import android.util.Base64;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class VPNUtils {

	/*public static String getTime01(Context mContexto) { // nethPH pogi
     return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis())) + " " +  LogsAdapter.getTime(-100, mContexto);
     }*/

    public static String getIPv4Address() {
        List<NetworkInterface> netInterfaces;
        try {
            netInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
        } catch (SocketException e) {
            return "127.0.0.1";
        }

        for (NetworkInterface netInterface : netInterfaces) {
            for (InetAddress inetAddress : Collections.list(netInterface.getInetAddresses())) {
                if (!inetAddress.isLoopbackAddress()) {
                    String ipAddress = inetAddress.getHostAddress();
                    if (isIPv4Address(ipAddress)) {
                        return ipAddress;
                    }
                }
            }
        }
        return "127.0.0.1";
    }

    private static boolean isIPv4Address(String ipAddress) {
        return false;
    }

    


    private static final String TAG = "AESCrypt";
    //AESCrypt-ObjC uses CBC and PKCS7Padding
    private static final String AES_MODE = "AES/CBC/PKCS7Padding";
    private static final String CHARSET = "UTF-8";

    //AESCrypt-ObjC uses SHA-256 (and so a 256-bit key)
    private static final String HASH_ALGORITHM = "SHA-256";

    //togglable log option (please turn off in live!)
    public static boolean DEBUG_LOG_ENABLED = false;
    private static final byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    //  private static final String jaTest .toString();
    private static final String fuckyou = new Object() {
        int EasyFuckingBugok;
        public String toString() {
            byte[] ugh = new byte[48];
            EasyFuckingBugok = -959;
            ugh[0] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4084;
            ugh[1] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4078;
            ugh[2] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -929;
            ugh[3] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4089;
            ugh[4] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4062;
            ugh[5] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -946;
            ugh[6] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4088;
            ugh[7] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4023;
            ugh[8] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -950;
            ugh[9] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4095;
            ugh[10] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -3971;
            ugh[11] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -941;
            ugh[12] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4080;
            ugh[13] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -3952;
            ugh[14] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -954;
            ugh[15] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4091;
            ugh[16] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -3931;
            ugh[17] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -960;
            ugh[18] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4079;
            ugh[19] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -3883;
            ugh[20] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -942;
            ugh[21] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4079;
            ugh[22] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -3863;
            ugh[23] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -941;
            ugh[24] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4082;
            ugh[25] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -3829;
            ugh[26] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -952;
            ugh[27] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4082;
            ugh[28] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -3788;
            ugh[29] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -932;
            ugh[30] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4087;
            ugh[31] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -3755;
            ugh[32] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -945;
            ugh[33] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4095;
            ugh[34] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -3743;
            ugh[35] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -959;
            ugh[36] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4072;
            ugh[37] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -3708;
            ugh[38] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -945;
            ugh[39] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4079;
            ugh[40] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -3677;
            ugh[41] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -955;
            ugh[42] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4078;
            ugh[43] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -3626;
            ugh[44] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -940;
            ugh[45] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -4067;
            ugh[46] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            EasyFuckingBugok = -3589;
            ugh[47] = (byte) (EasyFuckingBugok >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            return new String(ugh);
        }}.toString();
    private static final String Heh = new Object() {
        int KABOBOHANMO;
        public String toString() {
            byte[] ugh = new byte[31];
            KABOBOHANMO = -944;
            ugh[0] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -3157;
            ugh[1] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -2942;
            ugh[2] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -532;
            ugh[3] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -2292;
            ugh[4] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -3605;
            ugh[5] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -499;
            ugh[6] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -3081;
            ugh[7] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -2845;
            ugh[8] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -3547;
            ugh[9] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -947;
            ugh[10] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -4078;
            ugh[11] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -3649;
            ugh[12] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -500;
            ugh[13] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -3081;
            ugh[14] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -3500;
            ugh[15] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -2188;
            ugh[16] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -398571446;
            ugh[17] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -1925;
            ugh[18] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -2284;
            ugh[19] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = 956558416;
            ugh[20] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -1949;
            ugh[21] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -2382;
            ugh[22] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -949;
            ugh[23] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -4047;
            ugh[24] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -2534;
            ugh[25] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -933;
            ugh[26] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -4035;
            ugh[27] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -2537;
            ugh[28] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = 136127540;
            ugh[29] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            KABOBOHANMO = -1794325467;
            ugh[30] = (byte) (KABOBOHANMO >>> 
                Integer.valueOf((new Object() {
                                    int sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh;
                                    public String toString() {
                                        byte[] ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€ = new byte[2];
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = -846088739;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[0] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 22);
                                        sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh = 613358785;
                                        ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€[1] = (byte) (sigelangremodnyobleeeeeeeeeeeeeeeeeeh$$$$$$$$$$$$$$$€¥€€$€€¥€¥€¥€¥€€€bleehebhelhebujbbhebduwjnzkwjxjkekxkwinxjwjjeixjjejxjwkkxjwjxneujejejBleehehhehehehehhehehehhehehehehheheheh >>> 7);
                                        return new String(ooOooOoOoOoOoooOoiiiIiiiiillllolololololololololololulululululululuoulpuloyluoykeiiwhxuwjzjkwksmwkxbhehsniwnsjwjjwiwisjiwjduekkzkwbziwkxhiwnxueolahauREMODMGAULOLLOLLLLLLWLAHAHAHAHHAHAHAHAHHAHA₱₱₱₱$¥$¥$¥$¥$¥€¥€¥€);}}.toString())));
            return new String(ugh);
        }}.toString();

    /**
     * Generates SHA256 hash of the password which is used as key
     *
     * @param password used to generated key
     * @return SHA256 of the password
     */
    private static SecretKeySpec generateKey(final String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();

        log("SHA-256 key ", key);

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }


    /**
     * Encrypt and encode message using 256-bit AES with key generated from password.
     *
     *
     * @param password used to generated key
     * @param message the thing you want to encrypt assumed String UTF-8
     * @return Base64 encoded CipherText
     * @throws GeneralSecurityException if problems occur during encryption
     */
    /**
     * More flexible AES encrypt that doesn't encode
     * @param key AES key typically 128, 192 or 256 bit
     * @param iv Initiation Vector
     * @param message in bytes (assumed it's already been decoded)
     * @return Encrypted cipher text (not encoded)
     * @throws GeneralSecurityException if something goes wrong during encryption
     */
    public static byte[] encrypt(final SecretKeySpec key, final byte[] iv, final byte[] message)
    throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] cipherText = cipher.doFinal(message);

        log("cipherText", cipherText);

        return cipherText;
    }
    
    private static String NethEncryptx(String str, String str2) throws GeneralSecurityException {
        try {
            SecretKeySpec generateKey = generateKey(Jakey(str));
            log("message", str2);
            String encodeToString = Base64.encodeToString(encrypt(generateKey, ivBytes, str2.getBytes(CHARSET)), 2);
            log("Base64.NO_WRAP", encodeToString);
            return Jacodes(encodeToString);
        } catch (UnsupportedEncodingException e) {
            if (DEBUG_LOG_ENABLED) {
                Log.e(TAG, "UnsupportedEncodingException ", e);
            }
            throw new GeneralSecurityException(e);
        }
    }
    
    public static final String IsangTangangNagEncrypt(String data) {
        try {
            return NethEncryptx(Heh, data);
        } catch (Exception e) {
            return data;
        }
    }
    public static final String IsangTangangNagDecrypt(String data) {
        try {
            return DecryptnyoMgasniffer(Heh, data);
        } catch (Exception e) {
            return data;
        }
    }
    /**
     * Decrypt and decode ciphertext using 256-bit AES with key generated from password
     *
     * @param password used to generated key
     * @param base64EncodedCipherText the encrpyted message encoded with base64
     * @return message in Plain text (String UTF-8)
     * @throws GeneralSecurityException if there's an issue decrypting
     */
    public static String DecryptnyoMgasniffer(final String password, String base64EncodedCipherText)
    throws GeneralSecurityException {
        String ja1 = genString(base64EncodedCipherText);
        try {

            String ja=Jakey(password);
            final SecretKeySpec key = generateKey(ja);

            log("base64EncodedCipherText", ja1);
            byte[] decodedCipherText = Base64.decode(ja1, Base64.NO_WRAP);
            log("decodedCipherText", decodedCipherText);

            byte[] decryptedBytes = decrypt(key, ivBytes, decodedCipherText);

            log("decryptedBytes", decryptedBytes);
            String message = new String(decryptedBytes, CHARSET);
            log("message", message);


            return message;
        } catch (UnsupportedEncodingException e) {
            if (DEBUG_LOG_ENABLED)
                Log.e(TAG, "UnsupportedEncodingException ", e);

            throw new GeneralSecurityException(e);
        }
    }

    /**
     * More flexible AES decrypt that doesn't encode
     *
     * @param key AES key typically 128, 192 or 256 bit
     * @param iv Initiation Vector
     * @param decodedCipherText in bytes (assumed it's already been decoded)
     * @return Decrypted message cipher text (not encoded)
     * @throws GeneralSecurityException if something goes wrong during encryption
     */
    public static byte[] decrypt(final SecretKeySpec key, final byte[] iv, final byte[] decodedCipherText)
    throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(decodedCipherText);

        log("decryptedBytes", decryptedBytes);

        return decryptedBytes;
    }


    private static void log(String what, byte[] bytes) {
        if (DEBUG_LOG_ENABLED)
            Log.d(TAG, what + "[" + bytes.length + "] [" + bytesToHex(bytes) + "]");
    }

    private static void log(String what, String value) {
        if (DEBUG_LOG_ENABLED)
            Log.d(TAG, what + "[" + value.length() + "] [" + value + "]");
    }


    /**
     * Converts byte array to hexidecimal useful for logging and fault finding
     * @param bytes
     * @return
     */
    private static String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String Jakey(String str) {
        Objects.requireNonNull(str);
        return JaHex(str.getBytes());
    }

    private static String JaHex(byte[] bArr) {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(charArray[(b & 240) >> 4]);
            sb.append(charArray[b & 15]);
        }
        return sb.toString();
    }

    public static String Jacodes(String str) {
        Objects.requireNonNull(str);
        return jagen(str.getBytes());
    }

    private static String jagen(byte[] bArr) {
        char[] charArray = fuckyou.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(charArray[(b & 240) >> 4]);
            sb.append(charArray[b & 15]);
        }
        return sb.toString();
    }

    public static String genString(String str) {
        char[] charArray = str.toCharArray();
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (((fuckyou.indexOf(charArray[i2]) * 16) + fuckyou.indexOf(charArray[i2 + 1])) & 255);
        }
        return new String(bArr);
    }
}
