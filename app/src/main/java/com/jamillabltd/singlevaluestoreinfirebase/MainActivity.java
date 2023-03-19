package com.jamillabltd.singlevaluestoreinfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();

        //save button
        Button saveButton = findViewById(R.id.saveButtonId);
        saveButton.setOnClickListener(view -> {

            EditText name = findViewById(R.id.nameEditTextId);
            String getName = name.getText().toString().trim();

            if (getName.isEmpty()) {
                name.setError("Please Enter Your Name!");
                name.requestFocus();
            } else {
                databaseReference = firebaseDatabase.getReference("Single Name Store");
                UserInfo userInfo = new UserInfo(getName);
                databaseReference.child("All Name").setValue(userInfo);
                Toast.makeText(MainActivity.this, "Successfully Saved", Toast.LENGTH_SHORT).show();
            }

        });


        //set name from firebase
        databaseReference = firebaseDatabase.getReference("Single Name Store");
        databaseReference.child("All Name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserInfo userInfo = snapshot.getValue(UserInfo.class);
                if (userInfo != null) {
                    String getNameFromFirebase = userInfo.getNameTitle();
                    TextView setName = findViewById(R.id.userNameDisplayId);
                    setName.setText(getNameFromFirebase);
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}