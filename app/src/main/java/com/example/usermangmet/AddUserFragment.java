package com.example.usermangmet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUserFragment extends Fragment {
private EditText etFirstN,etLASTn,etPhoneNum,etBirthday,etEmail1;
private Button btnAddAddFragment;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FireBaseServices fbs;

    public AddUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment3ADD.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUserFragment newInstance(String param1, String param2) {
        AddUserFragment fragment = new AddUserFragment();
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
    public void onStart() {
        super.onStart();

        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_user_fragment3, container, false);
    }
    private void init(){
        fbs = FireBaseServices.getInstance();
        etFirstN=getView().findViewById(R.id.etFirstN);
        etLASTn=getView().findViewById(R.id.etLASTn);
        etPhoneNum=getView().findViewById(R.id.etPhoneNum);
        etBirthday=getView().findViewById(R.id.etBirthday);
        btnAddAddFragment=getView().findViewById(R.id.btnAddAddFragment);

        btnAddAddFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           addToFirestore();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayoutMain, new Choose_UserType());
                ft.commit();
            }
        });

    }

    private void addToFirestore() {
        String fname, lname, phoneNum1, birthd, email2;
        fname = etFirstN.getText().toString();
        lname = etLASTn.getText().toString();
        phoneNum1 = etPhoneNum.getText().toString();
        birthd = etBirthday.getText().toString();

        if (fname.trim().isEmpty() || lname.trim().isEmpty() || phoneNum1.trim().isEmpty() || birthd.trim().isEmpty() ) {
            Toast.makeText(getActivity(), "some data is missing or incorrect", Toast.LENGTH_SHORT).show();
            return;
        }
        User user=new User(fname,lname,phoneNum1,birthd,fbs.getAuth().getCurrentUser().getEmail());


        fbs.getFire().collection("Users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
}