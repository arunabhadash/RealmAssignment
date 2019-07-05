package com.example.realmassignment;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText dept;
    EditText roll;
    EditText phone;
    Switch aSwitch;
    Button mSave;
    Button mDisplay;
    Context context;
    String gender="Male";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        name=findViewById(R.id.editText);
        dept=findViewById(R.id.editText2);
        roll=findViewById(R.id.editText3);
        phone=findViewById(R.id.editText4);
        aSwitch=findViewById(R.id.switch1);
        mSave=findViewById(R.id.button);
        mDisplay=findViewById(R.id.button2);

    }
    public void onSwitch(View view){
        if(aSwitch.isChecked()){
            gender="Female";
        }
        else
            gender="Male";

    }
    public void onSave(View view){
        Realm realm=Realm.getDefaultInstance();
        realm.beginTransaction();
        try {
            Student student = realm.createObject(Student.class,System.currentTimeMillis()/1000);
            student.setName(name.getText().toString());
            student.setDept(dept.getText().toString());
            student.setPhone(Long.parseLong(phone.getText().toString()));
            student.setRoll(Integer.parseInt(roll.getText().toString()));
            student.setGender(gender);
            realm.commitTransaction();
            Toast.makeText(context, "Sucessfully Stored", Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            realm.cancelTransaction();
            Toast.makeText(context, "Failed to Store", Toast.LENGTH_SHORT).show();
        }

    }
    public void onDisplay(View view){
        Intent intent=new Intent(this,DisplayActivity.class);
        startActivity(intent);
    }
}
