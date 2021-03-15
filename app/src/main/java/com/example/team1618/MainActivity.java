package com.example.team1618;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
TextView a, b, c, d;
Button btn;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = findViewById(R.id.speedTextView);
        b = findViewById(R.id.accTextView);
        c = findViewById(R.id.rangeTextView);
        d = findViewById(R.id.batvolTextView);
        btn = findViewById(R.id.getvalueButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference();
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String speed = dataSnapshot.child("DataNum").getValue().toString();
                        a.setText(speed);
                        String accelaration = dataSnapshot.child("Acceleration").getValue().toString();
                        b.setText(accelaration);
                        String range = dataSnapshot.child("Range").getValue().toString();
                        c.setText(range);
                        String batt_vol = dataSnapshot.child("AccumulatorSoC").getValue().toString();
                        d.setText(batt_vol);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}