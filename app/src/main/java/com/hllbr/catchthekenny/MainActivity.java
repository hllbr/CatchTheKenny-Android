package com.hllbr.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText ;
    TextView timeText ;
    int score ;//scoru tutmak için
    //kenny hareket ettirmem gerekiyor bunun için loop kullanabirim.
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView [] imageArray;
    Handler handler ;
    Runnable runnable ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize =
        scoreText = (TextView) findViewById(R.id.scoreText);
        timeText = (TextView) findViewById(R.id.timeText);
        score = 0 ;
        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView15 = findViewById(R.id.imageView15);


        imageArray = new ImageView[] {imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,
        imageView11,imageView12,imageView13,imageView14,imageView15};

        hideImages();


        imageView = findViewById(R.id.imageView);

        //sayacı oncreate ile uygulama açıldığında başlatmak istiyorum
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                //bu alanda sadece timer text güncellesek yeter .
                timeText.setText("Time "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
            //kullanıcıya tekrar soru soruyoruz tekrar oynaka ister misin şeklinde bulu yapacağım alan
                timeText.setText("Time off");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("do you want to play again ? ");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //restart area
                        Intent intent = getIntent();
                        finish();//uygulamayı çok yormamak için güncel activiteyi tamamen bitirmem destroyed etmem gerekiyor.bunun için finish kullanıyorum.
                        startActivity(intent);//kendi activitemi baştan başlatmak için kullandım
                        //çok kullanılan bir töntem değildir.
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();

    }
    public void increaseScore(View view){
        //görünümlere her tıklandığında score artsın istiyorum

        //geri sayım işlemini gerçekleştirmek için countDownTimer kullanmam gerekiyor

        score++;
        scoreText.setText("Score " +score);

    }
    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(16);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);//bu alanda this runnable işaret eder.

            }
        };
        handler.post(runnable);

    }
}