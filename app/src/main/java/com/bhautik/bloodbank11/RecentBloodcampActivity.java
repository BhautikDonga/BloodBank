package com.bhautik.bloodbank11;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecentBloodcampActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_bloodcamp);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        //setTitle
        actionBar.setTitle("BloodCamp Lists");
        //Recycler View
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        //set Layout as linear layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send query to firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("BloodCamp");

    }
    //load data into recycler view onStart

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<BloodCampInformation,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<BloodCampInformation, ViewHolder>(
                        BloodCampInformation.class,
                        R.layout.recyclerview,
                        ViewHolder.class,
                        databaseReference
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, BloodCampInformation model, int position) {
                        viewHolder.setDetails(getApplicationContext(),model.getDownloadUrl(),model.getOrganizerName(),model.getDate(),model.getPlace());
                    }
                };

        //set adapter to recyclerView
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
