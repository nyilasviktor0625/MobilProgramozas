package hu.meiit.mobilprogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<String> data1 = new ArrayList<String>();
    private ArrayList<String> data2 = new ArrayList<String>();
    private ArrayList<String> data3 = new ArrayList<String>();

    private TableLayout tableLayout;

    EditText ed1,ed2,ed3,ed4,ed5,ed6;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ed1= findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3= findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);


        b1 = findViewById(R.id.bt1);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                add();
            }
        });
    }
    public void add(){
        int tot;

        String prodname = ed1.getText().toString();
        int price = Integer.parseInt(ed2.getText().toString());
        int qty = Integer.parseInt(ed3.getText().toString());
        tot = price * qty;

        data.add(prodname);
        data1.add(String.valueOf(price));
        data2.add(String.valueOf(qty));
        data3.add(String.valueOf(tot));

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tb1);

        TableRow row = new TableRow(this);
        TextView t1 = new TextView(this);
        TextView t2 = new TextView(this);
        TextView t3 = new TextView(this);
        TextView t4 = new TextView(this);

        String total;
        int sum = 0;
        for (int i = 0; i<data.size(); i++)
        {
            String pname = data.get(i);
            String prc = data1.get(i);
            String quantit = data2.get(i);
            String tott = data3.get(i);
            t1.setText(pname);
            t2.setText(prc);
            t3.setText(quantit);
            t4.setText(tott);

            sum = sum + Integer.parseInt(data3.get(i).toString());
        }
        row.addView(t1);
        row.addView(t2);
        row.addView(t3);
        row.addView(t4);
        tableLayout.addView(row);
        ed4.setText(String.valueOf(sum));
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed1.requestFocus();

    }

}