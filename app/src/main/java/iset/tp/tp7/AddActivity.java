package iset.tp.tp7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText nom, prenom, moyenne;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nom = findViewById(R.id.input_newNom);
        prenom = findViewById(R.id.input_newPrenom);
        moyenne = findViewById(R.id.input_newMoyenne);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBaseManager db = new MyDataBaseManager(AddActivity.this);
                db.addResult(new Resultat(nom.getText().toString().trim(),
                        prenom.getText().toString().trim(),
                        Float.valueOf(moyenne.getText().toString().trim())));

                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}