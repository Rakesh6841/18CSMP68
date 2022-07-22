package com.example.counterapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    TextView txtCounter;
    Button btn_start,btn_stop;
    int count=0;
    Handler customHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCounter= (TextView)findViewById(R.id.textView2);
        btn_start =(Button)findViewById(R.id.button1);
        btn_stop=(Button)findViewById(R.id.button2);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customHandler.postDelayed(updateTimerThread,0);
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customHandler.removeCallbacks(updateTimerThread);
            }
        });
    }
    private final Runnable updateTimerThread =new Runnable() {
        @Override
        public void run() {
            txtCounter.setText(""+count);
            customHandler.postDelayed(this,1000);
            count++;
        }
    };
}
