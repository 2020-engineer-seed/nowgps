package dev.siro256.androidapp.nowgps.listener

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.view.View
import android.widget.Button
import android.widget.TextView
import dev.siro256.androidapp.nowgps.MainActivity
import dev.siro256.androidapp.nowgps.R
import java.text.SimpleDateFormat
import java.util.*

object UpdateButtonListener: View.OnClickListener, LocationListener {
    //LocationManager
    private val locationManager = MainActivity.instance.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @SuppressLint("MissingPermission")
    override fun onClick(view: View) {
        view.isClickable = false
        MainActivity.instance.findViewById<TextView>(R.id.lastUpdate).text = "取得中..."
        //位置情報の取得
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0F, this)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onLocationChanged(location: Location) {
        MainActivity.instance.apply {
            locationManager.removeUpdates(this@UpdateButtonListener)
            findViewById<TextView>(R.id.latitude).text = location.latitude.toString()
            findViewById<TextView>(R.id.longitude).text = location.longitude.toString()
            findViewById<TextView>(R.id.lastUpdate).text = SimpleDateFormat("dd日HH時mm分ss秒")
                .apply { timeZone = TimeZone.getTimeZone("Asia/Tokyo") }.format(System.currentTimeMillis())
            findViewById<Button>(R.id.update).isClickable = true 
        }
    }
}
