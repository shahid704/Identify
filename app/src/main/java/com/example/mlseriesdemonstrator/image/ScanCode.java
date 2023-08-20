package com.example.mlseriesdemonstrator.image;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mlseriesdemonstrator.R;

import eu.livotov.labs.android.camview.ScannerLiveView;
import eu.livotov.labs.android.camview.scanner.decoder.zxing.ZXDecoder;

public class ScanCode extends AppCompatActivity {

    private ScannerLiveView scannerLiveView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);
        scannerLiveView = findViewById(R.id.camera);
        textView = findViewById(R.id.text);

        scannerLiveView.setScannerViewEventListener(new ScannerLiveView.ScannerViewEventListener() {
            @Override
            public void onScannerStarted(ScannerLiveView scanner) {
                Toast.makeText(ScanCode.this, "Scanner Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScannerStopped(ScannerLiveView scanner) {
                Toast.makeText(ScanCode.this, "Scanner Stoped", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onScannerError(Throwable err) {

            }

            @Override
            public void onCodeScanned(String data) {
                textView.setText(data);

            }
        });
    }

    private boolean checkPermission(){
        int camera_permission = ContextCompat.checkSelfPermission(getApplicationContext(),CAMERA_SERVICE);
        int vibrate_permission = ContextCompat.checkSelfPermission(getApplicationContext(),VIBRATOR_SERVICE);
        return camera_permission == PackageManager.PERMISSION_GRANTED && vibrate_permission == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission(){
        int Permission_code=100;
        ActivityCompat.requestPermissions(this,new String[]{CAMERA_SERVICE,VIBRATOR_SERVICE},Permission_code);
    }

    @Override
    protected void onPause() {
        scannerLiveView.stopScanner();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ZXDecoder decoder = new ZXDecoder();
        decoder.setScanAreaPercent(0.8);
        scannerLiveView.setDecoder(decoder);
        scannerLiveView.startScanner();
    }
}