package iset.tp.tp7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText nom, prenom, moyenne;
    Button update_button,delete_button;
    String _id,_nom,_prenom,_moyenne;
    MyDataBaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        nom = findViewById(R.id.edit_nom);
        prenom = findViewById(R.id.edit_prenom);
        moyenne = findViewById(R.id.edit_moyenne);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _nom=nom.getText().toString();
                _prenom=prenom.getText().toString();
                _moyenne=moyenne.getText().toString();
                db=new MyDataBaseManager(EditActivity.this);
                db.updateResult(new Resultat(Integer.valueOf(_id),_nom,_prenom,Float.valueOf(_moyenne)));
                startActivity(new Intent(EditActivity.this,MainActivity.class));
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=new MyDataBaseManager(EditActivity.this);
                db.deleteResult(_id);
                startActivity(new Intent(EditActivity.this,MainActivity.class));
            }
        });



    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nom") &&
                getIntent().hasExtra("prenom") && getIntent().hasExtra("moyenne")){
            //Getting Data from Intent
            _id = getIntent().getStringExtra("id");
            _nom = getIntent().getStringExtra("nom");
            _prenom = getIntent().getStringExtra("prenom");
            _moyenne = getIntent().getStringExtra("moyenne");

            //Setting Intent Data
            nom.setText(_nom);
            prenom.setText(_prenom);
            moyenne.setText(_moyenne);
            Log.d("stev", _nom+" "+_prenom+" "+_moyenne);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}