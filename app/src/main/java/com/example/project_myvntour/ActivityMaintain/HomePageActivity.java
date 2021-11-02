package com.example.project_myvntour.ActivityMaintain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;


import com.example.project_myvntour.Adapter.SliderAdapter;
import com.example.project_myvntour.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class HomePageActivity extends AppCompatActivity {
    private SliderView imageSlider;
    private Button btLogin;
    SliderView sliderView;
    int[] images = {R.drawable.anhthuysi1,
            R.drawable.anhthuysi4,
            R.drawable.anhtuysi5,
            R.drawable.anhthuysi2,
            R.drawable.anhthuysi3,
            R.drawable.anhtuysi4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        sliderView = findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();


        imageSlider = (SliderView) findViewById(R.id.image_slider);
        btLogin = (Button) findViewById(R.id.btLogin);

        btLogin.setOnClickListener(view ->{
            startActivity(new Intent(HomePageActivity.this , LoginActivity.class));
        });

    }
}