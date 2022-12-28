package com.example.callapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_call;
    AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_call=(Button)findViewById(R.id.btn_call);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_dropdown_item_1line, COUNTRIES);

        autoCompleteTextView = findViewById(R.id.tv_number);
        autoCompleteTextView.setAdapter(adapter);



        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCall= new Intent(Intent.ACTION_CALL);
                String number=autoCompleteTextView.getText().toString();

                if(number.trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "please enter your number", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    intentCall.setData(Uri.parse("tel:"+number));
                }

                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this,"please grant permission",Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else{
                    startActivity(intentCall);
                }
            }
        });


    }
    private  void  requestPermission(){
        ActivityCompat.requestPermissions(MainActivity.this,new String[]
                {Manifest.permission.CALL_PHONE},1);
    }
    private static final String[] COUNTRIES = new String[] {
            "+880"
    };
}
