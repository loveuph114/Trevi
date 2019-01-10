package dev.reece.trevi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dev.reece.trevi.views.RandomView;

/**
 * Created by reececheng on 2019/1/9.
 */

public class RandomActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_layout);

        int column = 0;
        int row = 0;

        if(getIntent().getExtras() != null) {
            column = getIntent().getExtras().getInt(MainActivity.KEY_COLUMN, 0);
            row = getIntent().getExtras().getInt(MainActivity.KEY_ROW, 0);
        }

        RandomView randomView = findViewById(R.id.random_layout);
        randomView.setColumnAndRow(column, row);
        randomView.random();
    }


}
