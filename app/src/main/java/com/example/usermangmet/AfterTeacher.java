package com.example.usermangmet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AfterTeacher#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AfterTeacher extends Fragment {
    String subject;
FireBaseServices fbs;

Button btn12;
TextView name1,phone1,email1,birthday1;

    User user,user1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AfterTeacher(User user,String subject) {
        this.user1 = user;
        this.subject=subject;
    }

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
        name1=getView().findViewById(R.id.name1);
        email1=getView().findViewById(R.id.email1);
        birthday1=getView().findViewById(R.id.birthday1);
        phone1=getView().findViewById(R.id.phone1);


        name1.setText(user1.getFname()+" "+user1.getLname());
        email1.setText(user1.getEmail());
        birthday1.setText(user1.getBirthDay());
        phone1.setText(user1.getPhoneNum());

        btn12=getView().findViewById(R.id.btn12);
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS(user1.getPhoneNum(), "Hello, this is "+user.getFname()+ " "+ user.getLname()+" I need help with "+subject+" please contact me"+user.getPhoneNum());
                Toast.makeText(getContext(), "your help request has been sent ", Toast.LENGTH_SHORT).show();
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
    public void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            // SMS sent successfully
        } catch (Exception e) {
            // Failed to send SMS
            e.printStackTrace();
        }
    }

}