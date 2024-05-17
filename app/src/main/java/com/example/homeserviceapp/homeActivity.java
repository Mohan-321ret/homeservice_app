package com.example.homeserviceapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.homeserviceapp.adapters.catadapter;
import com.example.homeserviceapp.adapters.myadapter;
import com.example.homeserviceapp.datamodels.catmodel;
import com.example.homeserviceapp.datamodels.model;
import com.example.homeserviceapp.serviceactivity.allserviceActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class homeActivity extends AppCompatActivity {
    //google account sign in
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    //image slider
    ImageSlider imgslider;
    Button btn;
    //recycler view
    RecyclerView recview,recview1;
    myadapter adapter;
    //cateroy recyclerview
    catadapter catadapter;
    List<catmodel> catmodelList;



    //all activity
    //TextView allservicetxtbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(ContextCompat.getColor(homeActivity.this, R.color.black));





        //code for recyclerview 1
        //code for implement recyclerview
        recview=(RecyclerView)findViewById(R.id.recycle);
        // this line is used to avoid restarting of recyclerview on everytime when closed
        recview.setItemAnimator(null);
        recview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //code for recycler firebase
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students1"), model.class)
                        .build();

        //declaring adapter in home activit
        adapter=new myadapter(options);
        recview.setAdapter(adapter);
        //code for recyclerview1 ends here



        //code for recyclerview 2
        //code for implement recyclerview
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        recview1 = findViewById(R.id.recycle1);
        recview1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        catmodelList = new ArrayList<>();
        catadapter = new catadapter(this, catmodelList);
        recview1.setAdapter(catadapter);
        db.collection("category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                catmodel Catmodel = document.toObject(catmodel.class);
                                catmodelList.add(Catmodel);
                                catadapter.notifyDataSetChanged();
                            }
                        } else {

                        }
                    }
                });

        //code for recyclerview2 ends here
























        // CODE FOR IMAGESLIDER
        imgslider = findViewById(R.id.imgslider);
        ArrayList <SlideModel> slidemodel = new ArrayList<>();
        slidemodel.add(new SlideModel(R.drawable.img, ScaleTypes.FIT));
        slidemodel.add(new SlideModel(R.drawable.img_1, ScaleTypes.FIT));
        slidemodel.add(new SlideModel(R.drawable.img_2, ScaleTypes.FIT));
        slidemodel.add(new SlideModel(R.drawable.img_3, ScaleTypes.FIT));
        slidemodel.add(new SlideModel(R.drawable.img_4, ScaleTypes.FIT));
        imgslider.setImageList(slidemodel,ScaleTypes.FIT);


        //code for moving to all services activity
        TextView allservicetxtbtn = (TextView) findViewById(R.id.allservicebtn);
        allservicetxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(homeActivity.this, allserviceActivity.class);
                startActivity(intents);
            }
        });



        /*btn = findViewById(R.id.button);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();

        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });*/



    }

    /*void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(homeActivity.this,googleloginActivity.class));
            }
        });
    }*/


    //on start & off start of recycler firebase(adapter 1)
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}