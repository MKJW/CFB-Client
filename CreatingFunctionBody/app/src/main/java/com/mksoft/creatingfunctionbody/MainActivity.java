package com.mksoft.creatingfunctionbody;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MakeFunctionFragment makeFunctionFragment;
    InputMethodManager imm;
    FrameLayout mainContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        makeFunctionFragment = new MakeFunctionFragment();
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        mainContainer = findViewById(R.id.mainContainer);
        mainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, makeFunctionFragment).commit();
    }
}
