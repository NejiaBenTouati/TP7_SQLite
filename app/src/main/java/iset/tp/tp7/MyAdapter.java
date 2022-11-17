package iset.tp.tp7;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<Resultat> resultatList;

    public MyAdapter(Context context, List<Resultat> resultatList) {
        this.context = context;
        this.resultatList = resultatList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Resultat res=resultatList.get(position);
        holder.id.setText(String.valueOf(res.getId()));
        holder.nom.setText(res.getNom());
        holder.prenom.setText(res.getPrenom());
        holder.moyenne.setText(String.valueOf(res.getMoyenne()));
        if(res.getMoyenne()>=10.0){
            holder.image.setImageResource(R.drawable.ok);
        }
        else {
            holder.image.setImageResource(R.drawable.ko);
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", String.valueOf(resultatList.get(position).getId()));
                intent.putExtra("nom", String.valueOf(resultatList.get(position).getNom()));
                intent.putExtra("prenom", String.valueOf(resultatList.get(position).getPrenom()));
                intent.putExtra("moyenne", String.valueOf(resultatList.get(position).getMoyenne()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultatList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id,nom,prenom,moyenne;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.txt_id);
            nom=itemView.findViewById(R.id.txt_nom);
            prenom=itemView.findViewById(R.id.txt_prenom);
            moyenne=itemView.findViewById(R.id.txt_moyenne);
            image=itemView.findViewById(R.id.imageView);
        }
    }
}
