package hu.meiit.mobilprogapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity2 extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<String> data1 = new ArrayList<String>();
    private ArrayList<String> data2 = new ArrayList<String>();
    private ArrayList<String> data3 = new ArrayList<String>();
    String ServerURL = "http://192.168.100.7/get_data.php" ;


    private TableLayout tableLayout;

    private EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10;
    private Button b1,bt3;
    @Getter @Setter
    private String TempName ;
    @Getter @Setter
    private int prod_cat;
    @Getter @Setter
    private int weight;
    @Getter @Setter
    private int VAT;
    @Getter @Setter
    private int gross_price;
    @Getter @Setter
    private int net_price;
    @Getter @Setter
    private int customs_regime;
    @Getter @Setter
    private  String purchasing_date;
    @Getter @Setter
    private  String barcode;
    @Getter @Setter
    private int qty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ed1= findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3= findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);
        ed5 = findViewById(R.id.ed5);
        ed6 = findViewById(R.id.ed6);
        ed7 = findViewById(R.id.ed7);
        ed8 = findViewById(R.id.ed8);
        ed9 = findViewById(R.id.ed9);
        ed10 = findViewById(R.id.ed10);


        b1 = findViewById(R.id.bt1);
        bt3 = findViewById(R.id.bt3);
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
        int weight = Integer.parseInt(ed3.getText().toString());
        int qty = Integer.parseInt(ed10.getText().toString());
        String barcode = ed9.getText().toString();

        data.add(prodname);
        data1.add(String.valueOf(weight));
        data2.add(String.valueOf(qty));
        data3.add(barcode);

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
           String weight1 = data1.get(i);
            String quantit = data2.get(i);
            String barcode1 = data3.get(i);
            t1.setText(pname);
            t2.setText(weight1);
            t3.setText(quantit);
            t4.setText(barcode1);

            //sum = sum + Integer.parseInt(data3.get(i).toString());
        }
        row.addView(t1);
        row.addView(t2);
        row.addView(t3);
        row.addView(t4);
        tableLayout.addView(row);
        //ed4.setText(String.valueOf(sum));
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        ed5.setText("");
        ed6.setText("");
        ed7.setText("");
        ed8.setText("");
        ed9.setText("");
        ed10.setText("");

        ed1.requestFocus();


        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();

                InsertData();

            }
        });

    }
    public void GetData(){
        MainActivity2 mainActivity2 = new MainActivity2();
        mainActivity2.TempName = ed1.getText().toString();
        mainActivity2.prod_cat = Integer.parseInt(ed2.getText().toString());
        mainActivity2.weight = Integer.parseInt(ed3.getText().toString());
        mainActivity2.VAT = Integer.parseInt(ed4.getText().toString());
        mainActivity2.gross_price = Integer.parseInt(ed5.getText().toString());
        mainActivity2.net_price = Integer.parseInt(ed6.getText().toString());
        mainActivity2.customs_regime = Integer.parseInt(ed7.getText().toString());
        mainActivity2.purchasing_date = ed8.getText().toString();
        mainActivity2.barcode = ed9.getText().toString();
        mainActivity2.qty = Integer.parseInt(ed10.getText().toString());


    }
    public void InsertData(){
        MainActivity2 mainActivity2 = new MainActivity2();
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("name", mainActivity2.TempName));
                nameValuePairs.add(new BasicNameValuePair("prod_cat", String.valueOf(mainActivity2.prod_cat)));
                nameValuePairs.add(new BasicNameValuePair("weight", String.valueOf(mainActivity2.weight)));
                nameValuePairs.add(new BasicNameValuePair("VAT", String.valueOf(mainActivity2.VAT)));
                nameValuePairs.add(new BasicNameValuePair("gross_price", String.valueOf(mainActivity2.gross_price)));
                nameValuePairs.add(new BasicNameValuePair("net_price", String.valueOf(mainActivity2.net_price)));
                nameValuePairs.add(new BasicNameValuePair("customs_regime", String.valueOf(mainActivity2.customs_regime)));
                nameValuePairs.add(new BasicNameValuePair("purchasing_date", String.valueOf(mainActivity2.purchasing_date)));
                nameValuePairs.add(new BasicNameValuePair("barcode", String.valueOf(mainActivity2.barcode)));
                nameValuePairs.add(new BasicNameValuePair("qty", String.valueOf(mainActivity2.qty)));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(ServerURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Feltöltve";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(MainActivity2.this, "Megvagyunk", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(mainActivity2.TempName, String.valueOf(mainActivity2.weight),String.valueOf(qty));
    }


}