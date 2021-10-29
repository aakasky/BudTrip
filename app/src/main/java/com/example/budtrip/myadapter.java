package com.example.budtrip;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model, myadapter.myviewholder> {

    Context applicationContext;

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options, Context applicationContext) {
        super(options);

        this.applicationContext = applicationContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {

        final String name, place, state, purl, about;

        name = model.getName();
        place = model.getPlace();
        state = model.getState();
        purl = model.getPurl();
        about = model.getAbout();

        holder.name.setText(model.getName());
        holder.place.setText(model.getPlace());
        holder.state.setText(model.getState());
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(applicationContext, travelPlace.class);
                intent.putExtra("name", name);
                intent.putExtra("place", place);
                intent.putExtra("state", state);
                intent.putExtra("purl", purl);
                intent.putExtra("about", about);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                applicationContext.startActivity(intent);
            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(applicationContext, travelPlace.class);
                intent.putExtra("name", name);
                intent.putExtra("place", place);
                intent.putExtra("state", state);
                intent.putExtra("purl", purl);
                intent.putExtra("about", about);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                applicationContext.startActivity(intent);
            }
        });
        holder.place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(applicationContext, travelPlace.class);
                intent.putExtra("name", name);
                intent.putExtra("place", place);
                intent.putExtra("state", state);
                intent.putExtra("purl", purl);
                intent.putExtra("about", about);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                applicationContext.startActivity(intent);
            }
        });
        holder.state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(applicationContext, travelPlace.class);
                intent.putExtra("name", name);
                intent.putExtra("place", place);
                intent.putExtra("state", state);
                intent.putExtra("purl", purl);
                intent.putExtra("about", about);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                applicationContext.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView name, place, state;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.nametext);
            place = (TextView) itemView.findViewById(R.id.placetext);
            state = (TextView) itemView.findViewById(R.id.statetext);
        }
    }
}