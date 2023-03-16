package com.example.usermangmet;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class FireBaseServices {
    private static FireBaseServices instance;
    private  FirebaseAuth auth;
    private  FirebaseFirestore fire;
    private FirebaseStorage storage;

    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseFirestore getFire() {
        return fire;
    }

    public FireBaseServices(){
        auth=FirebaseAuth.getInstance();
        fire=FirebaseFirestore.getInstance();
        storage=FirebaseStorage.getInstance();

    }


    public FirebaseStorage getStorage() {
        return storage;
    }


    public  static FireBaseServices getInstance() {
     if (instance==null){
         instance=new  FireBaseServices();
     }
     return instance;
    }

}
