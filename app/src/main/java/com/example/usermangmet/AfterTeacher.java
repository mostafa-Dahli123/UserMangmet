package com.example.usermangmet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AfterTeacher#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AfterTeacher extends Fragment {
FireBaseServices fbs;
User user;
Button btn12;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AfterTeacher() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AfterTeacher.
     */
    // TODO: Rename and change types and number of parameters
    public static AfterTeacher newInstance(String param1, String param2) {
        AfterTeacher fragment = new AfterTeacher();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_after_teacher, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        btn12=getView().findViewById(R.id.btn12);
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayoutMain, new TheMeeting());
                ft.commit();
            }
        });
        fbs=FireBaseServices.getInstance();
        fbs.getFire().collection("Users").whereEqualTo("email",fbs.getAuth().getCurrentUser().getEmail())
                .get()
                .addOnSuccessListener((QuerySnapshot querySnapshot)->{
            if (querySnapshot.isEmpty()){
                System.out.println("No users found.");
                return;
            }
            System.out.println("Number of users:"+querySnapshot.size());
            for (DocumentSnapshot doc:querySnapshot.getDocuments()){
                String userId = doc.getId();
                user=doc.toObject(User.class);

            }
       })
        .addOnFailureListener(e ->{
            System.out.println("Error retrieving users:"+e.getMessage());

        });

    }

}