package xu.albert.project_5;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        ArrayList<Counter> counters;

        try {
            counters = bundle.getParcelableArrayList("counters");
            if(counters == null) {
                throw new NullPointerException("gib new pl0x");
            }
        } catch (NullPointerException e) {
            counters = new ArrayList<>();
            counters.add(new Counter("Default", 0));
        }

        Log.d("COUNTERS", counters.toString());

        final ArrayList<Counter> finalCounters = counters;

        LinearLayout counterList = findViewById(R.id.counterList);
        for(int i=0; i<counters.size(); ++i) {
            Counter c = counters.get(i);

            Button toAdd = new Button(getApplicationContext());
//            TextView toAdd = new TextView(getApplicationContext());
            toAdd.setText(c.toString());
            counterList.addView(toAdd);

            final int finalI = i;
            toAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), CountCounter.class);
                    intent.putExtra("from", finalI);
                    intent.putParcelableArrayListExtra("counters", finalCounters);
                    Log.d("COUNTER", "Starting count counter!");
                    startActivity(intent);
                }
            });
        }

        Button addNew = findViewById(R.id.AddNew);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewCounterName.class);
                intent.putParcelableArrayListExtra("counters", finalCounters);
                startActivity(intent);
            }
        });
    }
}
