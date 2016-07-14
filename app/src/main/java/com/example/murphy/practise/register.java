package com.example.murphy.practise;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Murphy on 16/7/7.
 */
public class register extends Activity {
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        dbHelper = new MyDatabaseHelper(this,"Vsense.db", null, 1);

        final Button register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edt1 = (EditText)findViewById(R.id.username);
                EditText edt2 = (EditText)findViewById(R.id.password);
                EditText edt3 = (EditText)findViewById(R.id.phonenum);
                EditText edt4 = (EditText)findViewById(R.id.email);
                String username = edt1.getText().toString();
                String password = edt2.getText().toString();
                String phoneNum = edt3.getText().toString();
                String email = edt4.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if("".equals(username.trim())||"".equals(password.trim())||
                        "".equals(phoneNum.trim())||"".equals(email.trim())){
                    Toast.makeText(register.this, "Please input username and password",
                            Toast.LENGTH_SHORT).show();
                }else{
                    db.execSQL("insert into Vsense (username, password, phoneNum, email) values(?,?,?,?)",
                            new String[]{username, password, phoneNum, email});
                    edt1.setText("");
                    edt2.setText("");
                    edt3.setText("");
                    edt4.setText("");
                    AlertDialog.Builder dialog = new AlertDialog.Builder(register.this);
                    dialog.setTitle("register succeed");
                    dialog.setMessage("Register Succeed!");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(register.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    dialog.show();
                }

            }
        });
    }
}
