package xu.albert.project_5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class CountCounter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_counter);

        Log.d("COUNTER", "Getting `from`");
        final int from = getIntent().getIntExtra("from", -1);
        final ArrayList<Counter> counters = getIntent().getParcelableArrayListExtra("counters");
        if(from == -1) {
            throw new IllegalArgumentException("fromID was not passed properly.");
        }

        Log.d("COUNTER", "from = "+from);
        EditText countDisp = findViewById(R.id.countNumDisp);

        Log.d("COUNTER", "Setting text to value");
        countDisp.setText(String.valueOf(counters.get(from).getVal()));

        Log.d("COUNTER", "Starting click listener");
        Button done = findViewById(R.id.countDone);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                EditText valueContainer = findViewById(R.id.countNumDisp);
                int value = Integer.valueOf(valueContainer.getText().toString());

                Counter thisCounter = new Counter(counters.get(from).getName(), value);
                counters.set(from, thisCounter);

                intent.putExtra("counters", counters);
                startActivity(intent);
            }
        });
    }
}
