package com.usr.getqrcode;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private Button QRcodeBtn;
    private ImageView QRcodeImage;
    private BarcodeEncoder encoder;
    private String webAddress = "https://www.youtube.com/watch?v=hLouSMzxzK0&t=3957s";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QRcodeBtn = findViewById(R.id.QRcodeButton);
        QRcodeImage = findViewById(R.id.QRcodeImage);
        encoder = new BarcodeEncoder();
    }

    public void GetQRcode(View view) {
        try {
            Bitmap bitmap = encoder.encodeBitmap(webAddress, BarcodeFormat.QR_CODE, 250, 250);
            QRcodeImage.setImageBitmap(bitmap);
        }catch (WriterException e){
            Log.e("GetQRcode", "ERROR: "+e.toString());
        }
    }
}
