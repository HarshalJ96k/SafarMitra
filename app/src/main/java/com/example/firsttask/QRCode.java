package com.example.firsttask;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QRCode extends AppCompatActivity {


    ImageView ivqrcode;
    private static final int QR_CODE_WIDTH = 500;
    private static final int QR_CODE_HEIGHT = 500;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Bitmap bitmap;
    String strusername,strimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        setTitle("Qr Code ");

        preferences= PreferenceManager.getDefaultSharedPreferences(this);

        strusername=preferences.getString("username","");
        ivqrcode=findViewById(R.id.ivQR);


        try {
            createQrCode();
        }
        catch (WriterException e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void createQrCode() throws WriterException
    {
        bitmap=textToImageEncode(strusername);
        ivqrcode.setImageBitmap(bitmap);
        

    }

    private Bitmap textToImageEncode(String strusername) throws WriterException {
        BitMatrix bitMatrix;
        bitMatrix=new MultiFormatWriter().encode(strusername, BarcodeFormat.QR_CODE,QR_CODE_WIDTH,QR_CODE_HEIGHT);

        int pixels[]=new int[QR_CODE_WIDTH*QR_CODE_HEIGHT];
        for (int y=0;y<QR_CODE_HEIGHT;y++)
        {
            int offset=y*QR_CODE_WIDTH;
            for (int x=0;x<QR_CODE_WIDTH;x++)
            {

                if (bitMatrix.get(x,y))
                    pixels[offset+x]= bitMatrix.get(x,y) ? getResources().getColor(R.color.black) :getResources().getColor(R.color.white);

            }
        }

        bitmap=Bitmap.createBitmap(QR_CODE_WIDTH,QR_CODE_HEIGHT,Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels,0,QR_CODE_WIDTH,0,0,QR_CODE_WIDTH,QR_CODE_HEIGHT);



        return bitmap;
    }


}