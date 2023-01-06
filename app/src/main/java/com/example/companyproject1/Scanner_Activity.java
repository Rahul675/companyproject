package com.example.companyproject1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class Scanner_Activity extends AppCompatActivity {

    private SurfaceView surfaceView;
    private TextView textView;
    Button button;
    String intentData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        surfaceView = findViewById(R.id.surface_view);
        textView = findViewById(R.id.text_view);
        Scanner();
//        button = findViewById(R.id.scan);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(Scanner_Activity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//
//                }
//            }
//        });


    }

    public void Scanner(){
        Toast.makeText(this, "Scanner called...", Toast.LENGTH_SHORT).show();
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        CameraSource cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(38,38)
                .setAutoFocusEnabled(true)
                .build();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ContextCompat.checkSelfPermission(Scanner_Activity.this,
                            Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(@NonNull Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            for (int index = 0; index < barcodes.size(); index++) {
                                Barcode code = barcodes.valueAt(index);
                                textView.setText(textView.getText() + "\n" + code.displayValue + "\n");

                                int type = barcodes.valueAt(index).valueFormat;
                                switch (type) {
                                    case Barcode.CONTACT_INFO:
                                        textView.setText(code.contactInfo.title);
                                        break;
                                    case Barcode.EMAIL:
                                        textView.setText(code.displayValue);
                                        break;
                                    case Barcode.ISBN:
                                        textView.setText(code.rawValue);
                                        break;
                                    case Barcode.PHONE:
                                        textView.setText(code.phone.number);
                                        break;
                                    case Barcode.PRODUCT:
                                        textView.setText(code.rawValue);
                                        break;
                                    case Barcode.SMS:
                                        textView.setText(code.sms.message);
                                        break;
                                    case Barcode.TEXT:
                                        textView.setText(code.displayValue);
                                        break;
                                    case Barcode.URL:
                                        textView.setText("url: " + code.displayValue);
                                        break;
                                    case Barcode.WIFI:
                                        textView.setText(code.wifi.ssid);
                                        break;
                                    case Barcode.GEO:
                                        textView.setText(code.geoPoint.lat + ":" + code.geoPoint.lng);
                                        break;
                                    case Barcode.CALENDAR_EVENT:
                                        textView.setText(code.calendarEvent.description);
                                        break;
                                    case Barcode.DRIVER_LICENSE:
                                        textView.setText(code.driverLicense.licenseNumber);
                                        break;
                                    default:
                                        textView.setText(code.rawValue);
                                        break;
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}