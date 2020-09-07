package in.inator.greenscreenman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import pl.droidsonroids.gif.GifImageView;

public class arAcrivity extends AppCompatActivity {
    private SurfaceView preview=null;
    private SurfaceHolder previewHolder=null;
    private Camera camera=null;
    private boolean inPreview=false;
    private boolean cameraConfigured=false;
    private Camera.PictureCallback pictureCallback;
    String model = "null";
    GifImageView giffer;
    LinearLayout top,bottom;
    Button t1,t2,t3,t4,b1,b2,b3,b4,takeScreenShot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_acrivity);
        Intent intent = getIntent();
        model = intent.getExtras().getString("model");
        preview=(SurfaceView)findViewById(R.id.preview);
        previewHolder=preview.getHolder();
        previewHolder.addCallback(surfaceCallback);
        previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        giffer = findViewById(R.id.giffer);
        top = findViewById(R.id.lin_bt_top);
        bottom = findViewById(R.id.lin_bt_bot);
        t1 = findViewById(R.id.bt_t1);
        t2 = findViewById(R.id.bt_t2);
        t3 = findViewById(R.id.bt_t3);
        t4 = findViewById(R.id.bt_t4);
        b1 = findViewById(R.id.bt_b1);
        b2 = findViewById(R.id.bt_b2);
        b3 = findViewById(R.id.bt_b3);
        b4 = findViewById(R.id.bt_b4);
        if(model.equals("0")){
            giffer.setImageResource(R.drawable.clap);
            top.setVisibility(View.GONE);
            bottom.setVisibility(View.GONE);
            top.setEnabled(false);
            bottom.setEnabled(false);
        }else if(model.equals("1")){
            top.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.VISIBLE);
            giffer.setImageResource(R.drawable.i_idle);
            top.setEnabled(true);
            bottom.setEnabled(true);
            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    giffer.setImageResource(R.drawable.i_idle);
                }
            });
            t2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    giffer.setImageResource(R.drawable.i_falldown);
                }
            });
            t3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    giffer.setImageResource(R.drawable.i_question);
                }
            });
            t4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    giffer.setImageResource(R.drawable.i_spiderman);
                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    giffer.setImageResource(R.drawable.i_falldown2);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    giffer.setImageResource(R.drawable.i_kungfu);
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    giffer.setImageResource(R.drawable.i_naked);
                }
            });
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    giffer.setImageResource(R.drawable.i_wardrobe);
                }
            });
        }
        takeScreenShot = findViewById(R.id.take_ss);
        takeScreenShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null,null,pictureCallback);
            }
        });
        pictureCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Bitmap surfaceTop = takeScreenshot();
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
                Bitmap cameraPreview = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix ,true);
                cameraPreview = Bitmap.createScaledBitmap(cameraPreview,surfaceTop.getWidth(),surfaceTop.getHeight(),true);
                
                saveBitmap(overlay(cameraPreview,surfaceTop));
                startPreview();
            }
        };
    }

    //to combine two bitmap images
    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp2.getWidth(), bmp2.getHeight(), bmp2.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        return bmOverlay;
    }

    public Bitmap takeScreenshot() {
        View rootView = findViewById(R.id.fr);
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot"+System.currentTimeMillis()+".jpeg");
        try {
            FileOutputStream fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Toast.makeText(this, "Screnshot saved", Toast.LENGTH_SHORT).show();
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("FileNotFound", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("Exception", e.getMessage(), e);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        camera=Camera.open();
        camera.setDisplayOrientation(90);
        startPreview();
    }

    @Override
    public void onPause() {
        if (inPreview) {
            camera.stopPreview();
        }

        camera.release();
        camera=null;
        inPreview=false;

        super.onPause();
    }

    private Camera.Size getBestPreviewSize(int width, int height,
                                           Camera.Parameters parameters) {
        Camera.Size result=null;

        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width<=width && size.height<=height) {
                if (result==null) {
                    result=size;
                }
                else {
                    int resultArea=result.width*result.height;
                    int newArea=size.width*size.height;

                    if (newArea>resultArea) {
                        result=size;
                    }
                }
            }
        }

        return(result);
    }

    private void initPreview(int width, int height) {
        if (camera!=null && previewHolder.getSurface()!=null) {
            try {
                camera.setPreviewDisplay(previewHolder);
            }
            catch (Throwable t) {

                Toast
                        .makeText(arAcrivity.this, t.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }

            if (!cameraConfigured) {
                Camera.Parameters parameters=camera.getParameters();
                Camera.Size size=getBestPreviewSize(width, height,
                        parameters);

                if (size!=null) {
                    parameters.setPreviewSize(size.width, size.height);
                    camera.setParameters(parameters);
                    cameraConfigured=true;
                }
            }
        }
    }

    private void startPreview() {
        if (cameraConfigured && camera!=null) {
            camera.startPreview();
            inPreview=true;
        }
    }

    SurfaceHolder.Callback surfaceCallback=new SurfaceHolder.Callback() {
        public void surfaceCreated(SurfaceHolder holder) {
            // no-op -- wait until surfaceChanged()
        }

        public void surfaceChanged(SurfaceHolder holder,
                                   int format, int width,
                                   int height) {
            initPreview(width, height);
            startPreview();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            // no-op
        }
    };
}