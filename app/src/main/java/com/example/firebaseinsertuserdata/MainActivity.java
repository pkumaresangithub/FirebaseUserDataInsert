package com.example.firebaseinsertuserdata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText username,emailid,contact,password, dob;
    Button save;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        emailid = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        password = findViewById(R.id.password);
        dob = findViewById(R.id.dob);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String USERNAME = username.getText().toString();
            String EMAILID = emailid.getText().toString();
            String CONTACT = contact.getText().toString();
            String PASSWORD = password.getText().toString();
            String DOB= dob.getText().toString();

            firebaseAuth = FirebaseAuth.getInstance();
            databaseReference = FirebaseDatabase.getInstance().getReference("user");

            if (USERNAME.isEmpty()){
                username.setError("User Name is required");
                username.requestFocus();
                return;
            }

                if (EMAILID.isEmpty()){
                    emailid.setError("Email id is required");
                    emailid.requestFocus();
                    return;
                }
                if (CONTACT.isEmpty()){
                    contact.setError("Contact Number is required");
                    contact.requestFocus();
                    return;
                }
                if (PASSWORD.isEmpty()){
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }
                if (DOB.isEmpty()){
                    dob.setError("Date of birth is required");
                    dob.requestFocus();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(EMAILID,PASSWORD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(getApplicationContext(), "User Added ", Toast.LENGTH_SHORT).show();

 //                         Insert data
                            String ID = databaseReference.push().getKey();
                             Model model = new Model(ID,USERNAME,EMAILID,CONTACT,PASSWORD,DOB);
                             databaseReference.child(ID).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     if (task.isSuccessful()) {
                                         Toast.makeText(getApplicationContext(), "User Added", Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             }).addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {
                                     Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                 }
                             });


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });


    }
}