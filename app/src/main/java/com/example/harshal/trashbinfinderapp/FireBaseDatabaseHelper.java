package com.example.harshal.trashbinfinderapp;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private List<TrashCan>  trashCans = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<TrashCan> trashCans, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FireBaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("trashCans");
    }

    public void readTrashCans(final DataStatus dataStatus){
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                trashCans.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    TrashCan trashCan = keyNode.getValue(TrashCan.class);
                    trashCans.add(trashCan);
                }
                dataStatus.DataIsLoaded(trashCans, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
