package dev.siro256.androidapp.nowgps

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import dev.siro256.androidapp.nowgps.listener.UpdateButtonListener

class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Viewを設定
        setContentView(R.layout.activity_main)

        //権限の確認・要求
        isPermitted()

        //ボタンのリスナー登録
        findViewById<Button>(R.id.update).setOnClickListener(UpdateButtonListener)
    }

    private fun isPermitted() {
        //ACCESS_FINE_LOCATIONが付与されているか確認
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
        }
    }

    companion object {
        //このクラスのインスタンス
        lateinit var instance: MainActivity
    }
}