package com.caloriecalculator.calorie_calculator;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Scanner;
public class HomeActivity extends AppCompatActivity implements View.OnClickListener
{
    static EditText et;
    TextView t1;
    String calories="";
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        et=(EditText)findViewById(R.id.editText3);
        t1=(TextView)findViewById(R.id.textView6);
        final Button button = findViewById(R.id.button);
    }
    public void onClink1(View view) {
        Intent intent = new Intent(HomeActivity.this, OcrstartActivity.class);
        startActivity(intent);
    }
        public void sendPostRequest(View View) {
        new HomeActivity.PostClass(this).execute();
    }
    public void onClink(View view)
    {
        et.setText("Potato Raw");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(HomeActivity.this, OcrstartActivity.class);
        startActivity(intent);

    }

    private class PostClass extends AsyncTask<String, Void, Void> {

        private final Context context;
        public PostClass(Context c){
            this.context = c;
        }
        protected void onPreExecute(){
            progress= new ProgressDialog(this.context);
            progress.setMessage("Loading");
            progress.show();
        }
        @Override
        protected Void doInBackground(String... params) {
            String query=et.getText()+"";
            String query1="";
            Scanner in=new Scanner(query);
            while (in.hasNext()==true)
            {
                String G=in.next();
                query1+=G+"+";
            }
            query1=query1.substring(0,query1.length()-1);
            query1=query1.toLowerCase();
            calories=web_Scraping.getCalories(query1);
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    t1.setText(calories);
                    progress.dismiss();
                }
            });
            return null;
        }
        protected void onPostExecute() {
            progress.dismiss();
        }
    }
}
