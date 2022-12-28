package com.example.fortunatefuture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myfirebaseadapter extends FirebaseRecyclerAdapter<Userhelperclass2, myfirebaseadapter.myviewholder> {
    public myfirebaseadapter(@NonNull FirebaseRecyclerOptions<Userhelperclass2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Userhelperclass2 model) {


        holder.name.append(model.getName());
        holder.email.append(model.getEmail());
        holder.phone.append(model.getPhone());
        holder.amount.append(model.getAmount());
        holder.story.append(model.getStory());
        holder.location.append(model.getLocation());
        System.out.println("hii");

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_donorsinglerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name,email,phone,amount,story,location;
        Button donate;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            email=(TextView)itemView.findViewById(R.id.email);
            phone=(TextView)itemView.findViewById(R.id.phone);
            amount=(TextView)itemView.findViewById(R.id.amount);
            story=(TextView)itemView.findViewById(R.id.story);
            location=(TextView)itemView.findViewById(R.id.location);
        }
    }

}
