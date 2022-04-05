package com.cst2335.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    EditText editText;
    private ImageButton imageButton;
    private ImageView imgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editTextTextEmailAddress);
        Intent fromMain = getIntent();
        String eml = fromMain.getStringExtra("email");
        if (TestToolBar.finishTestToolbar == true)
        {
            finish();
            Intent intent = new Intent(MainActivity.this, TestToolBar.class);
            TestToolBar.finishTestToolbar = false;
        }


        Button button4 = findViewById(R.id.toolbarPageButton);
        button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,TestToolBar.class);
                startActivity(intent);

            }

        });

        editText.setText(eml);
        imgview = (ImageView) findViewById(R.id.ImageV);
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton2);
        imageButton.setOnClickListener(View -> dispatchTakePictureIntent());
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            myPictureTakerLauncher.launch(takePictureIntent);
        }
    }

    ActivityResultLauncher<Intent> myPictureTakerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bitmap imgbitmap = (Bitmap) data.getExtras().get("data");
                        imgview.setImageBitmap(imgbitmap);
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED)
                        Log.e(TAG, "User refused to capture picture");
                }
            });


}