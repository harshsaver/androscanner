package in.inator.greenscreenman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class arAcrivity extends AppCompatActivity {
    private SurfaceView preview=null;
    private SurfaceHolder previewHolder=null;
    private Camera camera=null;
    private boolean inPreview=false;
    private boolean cameraConfigured=false;
    String model = "null";
    GifImageView giffer;
    LinearLayout top,bottom;
    Button t1,t2,t3,t4,b1,b2,b3,b4;
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