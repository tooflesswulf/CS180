package xu.albert.project_5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_ADDCOUNTER = 1;
    static final int REQUEST_COUNTCOUNTER = 2;
    private LinearLayout counterDisp;
    ArrayList<Counter> counters = new ArrayList<>();;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterDisp = findViewById(R.id.counterList);

        addCounter("Default");

        Button addNew = findViewById(R.id.AddNew);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewCounterName.class);
                startActivityForResult(intent, REQUEST_ADDCOUNTER);
            }
        });

        Button incrementAll = findViewById(R.id.Auto);
        incrementAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i<counters.size(); ++i) {
                    Counter c = counters.get(i);
                    c.increment();
                    ((Button)counterDisp.getChildAt(i)).setText(c.toString());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case REQUEST_ADDCOUNTER:
                if(resultCode != Activity.RESULT_OK) break;

                String name = data.getStringExtra("name");
                addCounter(name);
                break;
            case REQUEST_COUNTCOUNTER:
                if(resultCode != Activity.RESULT_OK) break;

                int index = data.getIntExtra("iof", -1);
                int val = data.getIntExtra("val", 0);
                counters.get(index).setVal(val);
                ((Button)counterDisp.getChildAt(index)).setText(counters.get(index).toString());
                break;
        }
    }

    void addCounter(String name) {
        final int iof = counters.size();
        counters.add(new Counter(name));

        Button toAdd = new Button(getApplicationContext());
        toAdd.setText(counters.get(iof).toString());
        counterDisp.addView(toAdd);

        toAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CountCounter.class);
                intent.putExtra("from", iof);
                intent.putExtra("val", counters.get(iof).getVal());
                startActivityForResult(intent, REQUEST_COUNTCOUNTER);
            }
        });
    }
}
