package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class UserRegistration extends AppCompatActivity {
    private EditText etPrenom;
    private EditText etNom;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etAddresse;
    private EditText etCardName;
    private EditText etCardNumber;
    private EditText etDate;
    private EditText etSNumber;
    private Button BnFinish;
    private String Client="Client";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDataBase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private static String TAG = "UserRegistration";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        //variables that will store user details
        etPrenom = findViewById(R.id.ETPrenom);
        etNom = findViewById(R.id.ETNom);
        etEmail = findViewById(R.id.ETEmail);
        etPassword = findViewById(R.id.ETPassword);
        etAddresse = findViewById(R.id.ETAdresse);
        etCardName = findViewById(R.id.ETCardName);
        etCardNumber = findViewById(R.id.ETCardNumber);
        etDate = findViewById(R.id.ETDate);
        etSNumber = findViewById(R.id.ETSNumber);
        BnFinish = findViewById(R.id.BtnFinish);
        // [START initialize_auth]
        // Initialize Firebase Auth

        //Check Firebase connection












        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        //Initializing the other Firebase variables
        mFirebaseDataBase=FirebaseDatabase.getInstance();
        myRef=mFirebaseDataBase.getReference();

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

        // Read from the database
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

        //TextWatcher pour créer les espaces dans l'écriture du cardNumber
        etCardNumber.addTextChangedListener(new TextWatcher() {
            int count=0;
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) { /*Empty*/}

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) { /*Empty*/ }

            @Override
            public void afterTextChanged(Editable s) {

                int inputlength = etCardNumber.getText().toString().length();

                if (count <= inputlength && inputlength == 4 ||
                        inputlength == 9 || inputlength == 14){

                    etCardNumber.setText(etCardNumber.getText().toString() + " ");

                    int pos = etCardNumber.getText().length();
                    etCardNumber.setSelection(pos);

                } else if (count >= inputlength && (inputlength == 4 ||
                        inputlength == 9 || inputlength == 14)) {
                    etCardNumber.setText(etCardNumber.getText().toString()
                            .substring(0, etCardNumber.getText()
                                    .toString().length() - 1));

                    int pos = etCardNumber.getText().length();
                    etCardNumber.setSelection(pos);
                }
                count = etCardNumber.getText().toString().length();
            }
        });

        //TextWatcher pour checker que l'input est correcte pour notre Date
        etDate.addTextChangedListener(new TextWatcher() {
            int count2=0;
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) { /*Empty*/}

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) { /*Empty*/ }

            @Override
            public void afterTextChanged(Editable s) {

                int inputlength = etDate.getText().toString().length();

                if (inputlength == 1 && (etDate.getText().toString().charAt(0)!=('0') && etDate.getText().toString().charAt(0)!=('1'))){

                    etDate.setText("");

                    int pos = etDate.getText().length();
                    etDate.setSelection(pos);

                } else if(inputlength == 2){

                    etDate.setText(etDate.getText().toString()+"/");

                    int pos = etDate.getText().length();
                    etDate.setSelection(pos);
                }

            }
        });

        BnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create an instance to the logged in page
                Intent intent = new Intent(UserRegistration.this, LoggedInPage.class);
                // On trim le prénom puis teste si il est vide
                etPrenom.setText(etPrenom.getText().toString().trim());
                if(etPrenom.getText().toString().isEmpty()){
                    Toast.makeText(UserRegistration.this,"Le prénom n'a pas une valeur acceptable", Toast.LENGTH_LONG).show();
                    return;
                }
                // On trim le nom puis teste si il est vide
                etNom.setText(etNom.getText().toString().trim());
                if(etNom.getText().toString().isEmpty()){
                    Toast.makeText(UserRegistration.this,"Le nom n'a pas une valeur acceptable", Toast.LENGTH_LONG).show();
                    return;
                }

                // On trim l'email puis teste si il est vide
                etEmail.setText(etEmail.getText().toString().trim());
                if(etEmail.getText().toString().isEmpty()){
                    Toast.makeText(UserRegistration.this,"L'email n'a pas une valeur acceptable", Toast.LENGTH_LONG).show();
                    return;
                }
                // On cherche l'email finit avec soit .com, .net ou .ca vu que l'application est canadienne, et si il contient bien un @
                try {
                    Character s = '@';
                    Boolean flag = false, flag2=false;
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
                        Toast.makeText(UserRegistration.this,"L'email n'a pas une valeur acceptable", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                catch(Exception e){
                    etEmail.setText("");
                    Toast.makeText(UserRegistration.this,"L'email a causé une erreur, entrez un email valide", Toast.LENGTH_LONG).show();
                    return;
                }
                //On prend le mot de passe est check qu'il a au moins une taille de 8 charactères
                if(etPassword.getText().toString().length()<8){
                    Toast.makeText(UserRegistration.this,"Veuillez créer un mot de passe d'au moins 8 charactères", Toast.LENGTH_LONG).show();
                    return;
                }
                //On trim l'adresse puis teste si elle est vide
                etAddresse.setText(etAddresse.getText().toString().trim());
                if(etAddresse.getText().toString().isEmpty()){
                    Toast.makeText(UserRegistration.this,"Veuillez remplir le champ d'adresse", Toast.LENGTH_LONG).show();
                    return;
                }
                //check que le nom de carte n'est pas vide
                if(etCardName.getText().toString().isEmpty()){
                    Toast.makeText(UserRegistration.this,"Veuillez remplir le nom sur la carte", Toast.LENGTH_LONG).show();
                }

                // vérifie que le numéro de carte est effectivement de 16 chiffres
                if(etCardNumber.getText().toString().length()<19){
                    Toast.makeText(UserRegistration.this,"Veuillez mettre un numéro de carte valide", Toast.LENGTH_LONG).show();
                    return;
                }
                //Check que la taille de la date est bien de 4 chiffres
                if(etDate.getText().toString().length()<5){
                    Toast.makeText(UserRegistration.this,"Veuillez mettre une date d'expiration valide", Toast.LENGTH_LONG).show();
                    return;
                }
                //Check que la taille du numéro de sécurité est bien de 3 chiffres
                if(etSNumber.getText().toString().length()<3){
                    Toast.makeText(UserRegistration.this,"Veuillez mettre un numéro de sécurité valide", Toast.LENGTH_LONG).show();
                    return;
                }

                //taking out the little added syntaxe


                //creating the Client to send to database
                AbstractPerson x = new Client(etNom.getText().toString(), etPrenom.getText().toString(),etEmail.getText().toString(), etAddresse.getText().toString(),
                        etPassword.getText().toString(), etCardName.getText().toString(), etCardNumber.getText().toString().replace(" ", ""),
                        etSNumber.getText().toString(), etDate.getText().toString().replace("/", ""));
                // Write a message to the database


                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                //create the user
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {
                        Toast.makeText(UserRegistration.this, "WE are close",
                                Toast.LENGTH_SHORT).show();
                        if (task.isSuccessful()) {
                            // Sign in success, send user to the login page
                            Bundle status = new Bundle();
                            status.putString("status", Client);
                            Toast.makeText(UserRegistration.this, "wtf",
                                    Toast.LENGTH_SHORT).show();
                            intent.putExtras(status);

                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(UserRegistration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }


    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }

}