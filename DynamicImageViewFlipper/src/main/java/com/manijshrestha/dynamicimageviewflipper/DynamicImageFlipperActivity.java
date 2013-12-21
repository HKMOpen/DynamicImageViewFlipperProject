package com.manijshrestha.dynamicimageviewflipper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.koushikdutta.ion.Ion;

import java.util.Arrays;
import java.util.List;

public class DynamicImageFlipperActivity extends Activity {

    private List<String> imageURLs = Arrays.asList(new String[]{
            "http://manijshrestha.github.io/android-icon.png",
            "http://manijshrestha.github.io/iPhone-icon.png",
            "http://manijshrestha.github.io/windows-icon.png"});

    private int index = 0;

    private ViewFlipper mViewFlipper;
    private Button mPreviousButton;
    private Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamicimageflipper);
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        mPreviousButton = (Button) findViewById(R.id.previousButton);
        mNextButton = (Button) findViewById(R.id.nextButton);

        ImageView image = getNewImageView();
        image.setImageResource(R.drawable.ic_launcher);

        mViewFlipper.addView(image);
        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ImageView imageView = getNewImageView(); // Where we will place the image
                Ion.with(imageView)
                        .placeholder(R.drawable.loading)
                        .load(getNextImage());

                mViewFlipper.addView(imageView); // Adding the image to the flipper
                mViewFlipper.showNext();
            }
        });

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewFlipper.showPrevious();
            }
        });

    }

    protected ImageView getNewImageView() {
        ImageView image = new ImageView(getApplicationContext());
        image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return image;
    }

    protected String getNextImage() {
        if (index == imageURLs.size())
            index = 0;
        return imageURLs.get(index++);
    }
}
