package com.google.android.gms.samples.vision.ocrreader;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import java.util.Scanner;

public class CaloriesActivity extends AppCompatActivity implements View.OnClickListener{
    static EditText et;
    static String textt="";
    TextView t1;
    String calories="";
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calories);
        et=(EditText)findViewById(R.id.editText3);
        et.setText(textt);
        t1=(TextView)findViewById(R.id.textView6);
    }

    @Override
    public void onClick(View v) {
        String text="150g butternut squash\n" +
                "300g penne\n" +
                "40g butter\n" +
                "1 small leek\n" +
                "25g flour\n" +
                "600ml milk\n" +
                "100g frozen peas\n" +
                "175g mature Cheddar cheese\n" +
                "1 slice day-old brown bread";
        et.setText(text+"");
    }

    public void onCamera(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void sendPostRequest(View View)throws IOException {
        new CaloriesActivity.PostClass(this).execute();
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
        protected Void doInBackground(String... params){
            String query = et.getText() + "";
            calories="";
            double cal_sum=0;
            String[] lines = query.split("\n");
            for(int i=0;i<lines.length;i++) {
                String query1 = "";
                calories="0";
                Scanner in = new Scanner(lines[i]);
                while (in.hasNext() == true) {
                    String G = in.next();
                    if(G.charAt(G.length()-1)==',' || G.charAt(G.length()-1)=='(')
                    {
                        G=G.substring(0,G.length()-1);
                        query1 += G+ "+";
                        break;
                    }
                    else
                    {
                        query1 += G + "+";
                    }
                }
                query1 = query1.substring(0, query1.length() - 1);
                query1 = query1.toLowerCase();
                try {
                    calories = Web_Scraping.getCalories(query1);
                } catch (Exception ef) {}
                cal_sum+=Double.parseDouble(calories);

            }
            calories=cal_sum+"";
            CaloriesActivity.this.runOnUiThread(new Runnable() {
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
