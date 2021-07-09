package sg.edu.rp.c346.id20043679.p08demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks, btnDelete;
    TextView tvResults;
    ListView lvTask;
    EditText etTask, etDate;

    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTasks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lvTask = findViewById(R.id.lv);
        etDate = findViewById(R.id.editTextDate);
        etTask = findViewById(R.id.editTextTask);
        btnDelete = findViewById(R.id.btnDelete);

        alTasks = new ArrayList<String>();
        aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);

        lvTask.setAdapter(aaTasks);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(etTask.getText().toString(),etDate.getText().toString());

            }


        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<Task> data = db.getTasks();
                db.close();

                aaTasks.clear();

                for (int i = 0; i < data.size(); i++) {
                    alTasks.add(data.get(i).toString());


                }


                aaTasks.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                db.clearDatabase();
                db.close();

                alTasks.clear();
                aaTasks.notifyDataSetChanged();
            }
        });




    }
}