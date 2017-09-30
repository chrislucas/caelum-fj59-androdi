package br.com.caelum.chrislucas.casadocodigo.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.caelum.chrislucas.casadocodigo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_email)
    EditText username;
    @BindView(R.id.login_senha)
    EditText password;


    private boolean flag;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null && ! flag) {
                    flag = true;
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                else {
                    Snackbar.make(username, "Não temos um usuário em cache", Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @OnClick(R.id.login_novo)
    public void criar() {
        String email    = username.getText().toString();
        String passwd   = password.getText().toString();
        if( ! email.isEmpty() && ! passwd.isEmpty()) {
            create(email, passwd);
        }
        else {
            Snackbar.make(password, "Preencha todos os campos", Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    @OnClick(R.id.login_logar)
    public void logar() {
        String email    = username.getText().toString();
        String passwd   = password.getText().toString();
        if( ! email.isEmpty() && ! passwd.isEmpty()) {
            signIn(email, passwd);
        }
        else {
            Snackbar.make(password, "Preencha todos os campos", Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    public void signIn(String email, String passwd) {
        firebaseAuth.signInWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Snackbar.make(username
                                , String.format("Acesso %s autorizado", task.isSuccessful() ? "" : "não")
                                , Snackbar.LENGTH_SHORT)
                                .show();

                    }
                });
    }

    public void create(String email, String passwd) {
        firebaseAuth.createUserWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Snackbar.make(username
                                , String.format("Usuário %s foi criado.", task.isSuccessful() ? "" : "não")
                                , Snackbar.LENGTH_SHORT)
                                .show();

                    }
                });
    }
}
