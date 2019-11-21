package com.example.hotelbilling;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ProgressBar progressBar;
    private int progressSatus = 0;
    private TextView textView, textViewDate, textViewDate1;
    private Handler handler = new Handler();
    EditText number1;
    EditText number2;
    EditText number3;
    EditText number4;

    Button btnCalculate;
    TextView result;
    int ans=0;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }
    private void loadDatePicker(){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog( this, this,year,month,day);
        datePickerDialog.show();



            progressBar = findViewById(R.id.progressBar);
            textView = findViewById(R.id.textView);

            textViewDate = findViewById(R.id.textViewDate);
            textViewDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadDatePicker();
                }
            });
            textViewDate1 = findViewById(R.id.textViewDate1);

            textViewDate1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadDatePicker();
                }
            });

        datePicker =(DatePicker) findViewById(R.id.datePicker);

        number1 = (EditText) findViewById(R.id.editText_first_no);
        number2 = (EditText) findViewById(R.id.editText_second_no);
        number3 = (EditText) findViewById(R.id.editText_VAT);
        number4 = (EditText) findViewById(R.id.editText_service_charge);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        result = (TextView) findViewById(R.id.textView_answer);


        // Start long running operation
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressSatus < 100){
                    progressSatus += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressSatus);
                            textView.setText(progressSatus+"/"+progressBar.getMax());
                        }
                    });
                    try{
                        Thread.sleep(200);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        btnCalculate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double num1 = Double.parseDouble(number1.getText().toString());
            double num2 = Double.parseDouble(number2.getText().toString());
            double num3 = Double.parseDouble(number3.getText().toString());
            double num4 = Double.parseDouble(number4.getText().toString());
            double sum = num1 + num2 + num3 + num4;
            result.setText(Double.toString(sum));
        }
    });
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "Month/Day/Year : " + month + "/" + dayOfMonth + "/" + year;
        textViewDate.setText(date);
        textViewDate1.setText(date);

    }

}
