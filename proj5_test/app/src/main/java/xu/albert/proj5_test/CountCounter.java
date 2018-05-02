package xu.albert.project_5;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CountCounter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_counter);

        final int iof = getIntent().getIntExtra("from", -1);
        final int val = getIntent().getIntExtra("val", 0);
        if(iof == -1) { throw new IllegalArgumentException("fromID was not passed properly."); }

        EditText countDisp = findViewById(R.id.countNumDisp);
        countDisp.setText(String.valueOf(val));

        Button done = findViewById(R.id.countDone);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                EditText valueContainer = findViewById(R.id.countNumDisp);
                int value;
                try {
                    value = Integer.valueOf(valueContainer.getText().toString());
                } catch (NumberFormatException e) { value = 0; }
                intent.putExtra("iof", iof);
                intent.putExtra("val", value);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        Button increment = findViewById(R.id.countCount);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText valueContainer = findViewById(R.id.countNumDisp);
                int value = Integer.valueOf(valueContainer.getText().toString());
                valueContainer.setText(String.valueOf(value+1));
            }
        });
    }
}
