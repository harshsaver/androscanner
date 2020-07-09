package in.inator.greenscreenman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView;
    String url = "https://inator.in/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
    }

    @Override
    public void handleResult(Result result) {
        if(result.toString().equals(url+"qr")){
            //Intent intent = new Intent(ScanActivity.this,VideoActivity.class);
            Intent intent = new Intent(ScanActivity.this,arAcrivity.class);
            //passing the url from qr code to videoactivity
            intent.putExtra("model","0");
            startActivity(intent);
            //MainActivity.textView.setText(result.getText());
            //onBackPressed();
        }else if(result.toString().equals(url+"qr2")){
            Intent intent = new Intent(ScanActivity.this,arAcrivity.class);
            intent.putExtra("model","1");
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"Invalid QR code", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ScanActivity.this,MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }
}