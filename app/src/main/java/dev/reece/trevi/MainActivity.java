package dev.reece.trevi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_COLUMN = "key_column";
    public static final String KEY_ROW = "key_row";

    private EditText mColumnInput;
    private EditText mRowInput;
    private Button mSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColumnInput = findViewById(R.id.main_column_input);
        mRowInput = findViewById(R.id.main_row_input);
        mSubmitBtn = findViewById(R.id.main_submit_btn);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String column = mColumnInput.getText().toString();
                String row = mRowInput.getText().toString();

                if(!"".equals(column) && !"".equals(row)) {
                    Intent intent = new Intent(MainActivity.this, RandomActivity.class);
                    intent.putExtra(KEY_COLUMN, Integer.parseInt(column));
                    intent.putExtra(KEY_ROW, Integer.parseInt(row));
                    startActivity(intent);
                }
            }
        });
    }
}
