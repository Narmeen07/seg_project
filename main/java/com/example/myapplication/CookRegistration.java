package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CookRegistration extends AppCompatActivity {
    private static final String TAG= "CookRegistration";
    private EditText etNom;
    private EditText etPrenom;
    private EditText etEmail;
    private EditText etAdresse;
    private EditText etPassword;
    private EditText etDescription;
    private Button BnRegister;
    private String Cuisinier="Cuisinier";
    private Button BnCheque;



    //firebase variables declared in here
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDataBase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private static final int SELECT_PICTURE = 0;
    private ImageView imageView;



    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
    // [END on_start_check_user]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // setting all the variables
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_registration);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_cook_registration);
        etNom = findViewById(R.id.ETNom2);
        etPrenom = findViewById(R.id.ETPrenom2);
        etEmail = findViewById(R.id.ETEmail2);
        etAdresse = findViewById(R.id.ETAdresse2);
        etPassword = findViewById(R.id.ETPassword2);
        etDescription = findViewById(R.id.ETDescription);
        BnRegister = findViewById(R.id.BtnRegister2);
        BnCheque = findViewById(R.id.Image_cheque);



        imageView = (ImageView) findViewById(android.R.id.icon);

        //AuthListener for firebase
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged( FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in" + user.getUid());
                } else {
                    //User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        // Lets us read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //OnClick of the register with all the tests
        BnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //putting this on the bottom of the page
               // Intent intent = new Intent(CookRegistration.this, LoggedInPage.class);
                //Checker que le prénom n'est pas vide
                etPrenom.setText(etPrenom.getText().toString().trim());
                if(etPrenom.getText().toString().isEmpty()){
                    Toast.makeText(CookRegistration.this,"Le prénom n'a pas une valeur acceptable", Toast.LENGTH_LONG).show();
                    return;
                }
                //Checker que le nom n'est pas vide
                etNom.setText(etNom.getText().toString().trim());
                if(etNom.getText().toString().isEmpty()) {
                    Toast.makeText(CookRegistration.this, "Le nom n'a pas une valeur acceptable", Toast.LENGTH_LONG).show();
                    return;
                }
                //Checker que l'email est acceptable
                etEmail.setText(etEmail.getText().toString().trim());
                if(etEmail.getText().toString().isEmpty()){
                    Toast.makeText(CookRegistration.this,"L'email n'a pas une valeur acceptable", Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    Character s = '@';
                    Boolean flag = false;
                    Boolean flag2 = false;
                    Integer i = 0;
                    while (i < etEmail.getText().toString().length()) {
                        if (etEmail.getText().toString().charAt(i) == s) {
                            flag = true;
                        }
                        if(i==etEmail.getText().toString().length()-4 && '.'==etEmail.getText().toString().charAt(i)){
                            if(('c'==etEmail.getText().toString().charAt(i+1) && 'o'==etEmail.getText().toString().charAt(i+2) && 'm'==etEmail.getText().toString().charAt(i+3))
                                    || ('n'==etEmail.getText().toString().charAt(i+1) && 'e'==etEmail.getText().toString().charAt(i+2) && 't'==etEmail.getText().toString().charAt(i+3))){
                                flag2=true;
                                break;
                            }
                        }
                        if(i==etEmail.getText().toString().length()-3 && '.'==etEmail.getText().toString().charAt(i)){
                            if('c'==etEmail.getText().toString().charAt(i+1) && 'a'==etEmail.getText().toString().charAt(i+2)){
                                flag2 = true;
                                break;
                            }
                        }
                        i += 1;
                    }
                    if(flag==false || flag2==false){
                        etEmail.setText("");
                        Toast.makeText(CookRegistration.this,"L'email n'a pas une valeure acceptable", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                catch(Exception e){
                    etEmail.setText("");
                    Toast.makeText(CookRegistration.this,"L'email a causé une erreur, entrez un email valide", Toast.LENGTH_LONG).show();
                    return;
                }
                //Checker que le mot de passe est d'au moins 8 charactères
                if(etPassword.getText().toString().length()<8){
                    Toast.makeText(CookRegistration.this,"Veuillez créer un mot de passe d'au moins 8 charactères", Toast.LENGTH_LONG).show();
                    return;
                }
                //Checker que l'adresse n'est pas vide
                etAdresse.setText(etAdresse.getText().toString().trim());
                if(etAdresse.getText().toString().isEmpty()){
                    Toast.makeText(CookRegistration.this,"Veuillez remplir le champ d'adresse", Toast.LENGTH_LONG).show();
                    return;
                }
                //Checker que une brève description a été faîtes
                if(etDescription.getText().toString().length()<40){
                    Toast.makeText(CookRegistration.this,"Veuillez écrire une description d'au moins 40 charactères", Toast.LENGTH_LONG).show();
                    return;
                }

                //create a cuisinier

                AbstractPerson x = new Cuisinier(etNom.getText().toString(), etPrenom.getText().toString(),etEmail.getText().toString(),
                        etAdresse.getText().toString(), etPassword.getText().toString(), etDescription.getText().toString());

                //populate the logged in page
                Bundle status = new Bundle();

                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();




                // ------------------Switch to the logged in page ----------------------//
                Intent intent = new Intent(CookRegistration.this, logged_in_cook.class);


                //bundle to send the status
                status.putString("status", Cuisinier);
                intent.putExtras(status);
                //signs in with email and password
                mAuth.createUserWithEmailAndPassword(email,password);





            }









        });

    }

    public void OnClickCheque(View v){
        clicked(v);
    }

    public void clicked (View v){
        setContentView(R.layout.activity_cheque_registration);
        imageView = (ImageView) findViewById(android.R.id.icon);
        Intent intent = new Intent(getApplicationContext(), ChequeRegistration.class);


    }
    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }
}