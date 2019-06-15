package com.usr.getqrcode;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button QRcodeBtn;
    private ImageView QRcodeImage;
    private BarcodeEncoder encoder;
    private String driver = "1";
    private EditText boxedLunchID;
    private String webAddress = "https://www.youtube.com/watch?v=hLouSMzxzK0&t=3957s";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QRcodeBtn = findViewById(R.id.QRcodeButton);
        QRcodeImage = findViewById(R.id.QRcodeImage);
        boxedLunchID = findViewById(R.id.boxedLunchID);
        encoder = new BarcodeEncoder();
    }

    public void GetQRcode(View view) {
        try {
            String result = "";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy\\MM\\dd\\ HH:mm:ss");
            Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
            String time = formatter.format(curDate);
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("driver", driver);
                jsonObject.put("boxedLunchID", boxedLunchID.getText());
                jsonObject.put("time", time);
                jsonObject.put("web", webAddress);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Bitmap bitmap = encoder.encodeBitmap(jsonObject.toString(), BarcodeFormat.QR_CODE, 500, 500);
            QRcodeImage.setImageBitmap(bitmap);
        }catch (WriterException e){
            Log.e("GetQRcode", "ERROR: "+e.toString());
        }
    }
}
