package com.zyl.library.Utils;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Administrator on 2016-05-05.
 */
public class NetworkUtil {
    private Context context;
    private NetworkReceiver wifiReceiver;
    // 定义WifiManager对象
    private WifiManager mWifiManager;
    // 定义WifiInfo对象
    private WifiInfo mWifiInfo;

    public NetworkUtil(Context context) {
        this.context = context;
        // 取得WifiManager对象
        mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        // 取得WifiInfo对象
        mWifiInfo = mWifiManager.getConnectionInfo();
    }

    /**
     * 注册Wifi监听广播
     *
     * @param listener
     */
    public void register(NetworkListener listener) {
        if (wifiReceiver == null) {
            wifiReceiver = new NetworkReceiver(listener);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.STATE_CHANGE");
            context.registerReceiver(wifiReceiver, intentFilter);
        }
    }

    /**
     * 取消注册wifi监听广播
     */
    public void Unregister() {
        if (wifiReceiver != null) {
            context.unregisterReceiver(wifiReceiver);
        }
    }

    /**
     * 打开WIFI
     */
    public void OpenWifi() {
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
        }
    }

    /**
     * 关闭WIFI
     */
    public void CloseWifi() {
        if (mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(false);
        }
    }

    /**
     * 得到MAC地址
     *
     * @return
     */
    public String GetMacAddress() {
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
    }

    /**
     * 得到接入点的BSSID
     *
     * @return
     */
    public String GetBSSID() {
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
    }

    /**
     * 得到IP地址
     *
     * @return
     */
    public int GetIPAddress() {
        return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();
    }

    /**
     * 得到连接的ID
     *
     * @return
     */
    public int GetNetworkId() {
        return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
    }

    /**
     * 得到WifiInfo的所有信息包
     *
     * @return
     */
    public String GetWifiInfo() {
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
    }

    /**
     * 判断当前网络是否已连接
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        boolean result;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }


    /**
     * 判断当前的网络连接方式是否为WIFI
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
