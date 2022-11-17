package iset.tp.tp7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<Resultat> lesresultats=new ArrayList<>();
    MyDataBaseManager db;
    FloatingActionButton fab_add,fab_deleteAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab_add=findViewById(R.id.add_floatting_button);
        fab_deleteAll=findViewById(R.id.delete_all_floatting_button);
        recyclerView=findViewById(R.id.recyclerView);

        db=new MyDataBaseManager(this);
        lesresultats=db.readAllResult();
        if(lesresultats.size()==0)
            Toast.makeText(this,"No Data",Toast.LENGTH_LONG).show();

        myAdapter=new MyAdapter(this,lesresultats);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,AddActivity.class);
                startActivity(i);
            }
        });

        fab_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteAllResult();
                recreate();
            }
        });

    }
}