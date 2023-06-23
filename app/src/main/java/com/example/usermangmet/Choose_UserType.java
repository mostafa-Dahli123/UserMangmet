    package com.example.usermangmet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

    /**
 * A simple {@link Fragment} subclass.
 * Use the {@link Choose_UserType#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Choose_UserType extends Fragment implements AdapterView.OnItemSelectedListener {
Spinner spinner;
TextView textView1;
        Button btn6;

        FireBaseServices fbs;
        User user;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Choose_UserType() {
        // Required empty public constructor
    }
  public void onStart(){
        super.onStart();
        connectcomponents();
  }

        private void connectcomponents() {
        textView1=getView().findViewById(R.id.textView1);
        spinner = getView().findViewById(R.id.spinner1);
            btn6= getView().findViewById(R.id.btn6);
            fbs=FireBaseServices.getInstance();
            ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getActivity(), R.array.UserType, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinner.setAdapter(adapter);
            btn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text= spinner.getSelectedItem().toString();
                    if(text.equals("Student")){
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
                                        doc.getReference().update("teacher",false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                                ft.replace(R.id.frameLayoutMain, new UserListFragment(true,user));
                                                ft.commit();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });
                                    }
                                })
                                .addOnFailureListener(e ->{
                                    System.out.println("Error retrieving users:"+e.getMessage());

                                });

                    }else if (text.equals("Teacher")){
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
                                        doc.getReference().update("teacher",true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                                ft.replace(R.id.frameLayoutMain, new UserListFragment(false,user));
                                                ft.commit();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });
                                    }
                                })
                                .addOnFailureListener(e ->{
                                    System.out.println("Error retrieving users:"+e.getMessage());

                                });
                    }else {
                        Toast.makeText(getActivity(), "Choose Type", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

        /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Choose_UserType.
     */
    // TODO: Rename and change types and number of parameters
    public static Choose_UserType newInstance(String param1, String param2) {
        Choose_UserType fragment = new Choose_UserType();
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
        return inflater.inflate(R.layout.fragment_choose__user_type, container, false);
    }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String text= parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
            if(text.equals("Student")){
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayoutMain, new AfterStudent());
                ft.commit();
            }else if (text.equals("Teacher")){
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayoutMain, new AfterTeacher());
                ft.commit();
            }else {
                Toast.makeText(getActivity(), "Choose Type", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }