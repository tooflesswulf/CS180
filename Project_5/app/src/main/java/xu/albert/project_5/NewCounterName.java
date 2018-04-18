package xu.albert.project_5;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class NewCounterName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_counter_name);

        Button set = findViewById(R.id.newNameSet);
        final ArrayList<Counter> counters = getIntent().getParcelableArrayListExtra("counters");

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameTextField = findViewById(R.id.newName);

                String newName = nameTextField.getText().toString();

                counters.add(new Counter(newName));

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putParcelableArrayListExtra("counters", counters);
                startActivity(intent);
            }
        });
    }

}
