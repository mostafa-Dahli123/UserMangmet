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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInFragment extends Fragment {
Button btnSP,btnFP;
    private EditText etUserName,etPassword;
    private Button btnLogin;
    private FireBaseServices fbs;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LogInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogInFragment newInstance(String param1, String param2) {
        LogInFragment fragment = new LogInFragment();
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
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        // connecting componens
        btnFP=getView().findViewById(R.id.btnFP);
        btnFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayoutMain, new ForgetPassword());
                ft.commit();
            }
        });
        btnSP=getView().findViewById(R.id.btnSP);
        btnSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayoutMain, new SignUpFragment());
                ft.commit();
            }
        });
        fbs = FireBaseServices.getInstance();
        etUserName = getView().findViewById(R.id.etUserNameLogIn);
        etPassword = getView().findViewById(R.id.etPassowrdLoginLogin);
        btnLogin = getView().findViewById(R.id.btnLoginLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Data Validation
                String username =etUserName.getText().toString();
                String password =etPassword.getText().toString();
                if (username.trim().isEmpty() && password.trim().isEmpty()){
                    Toast.makeText(getActivity(), "some fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Signup proce
                fbs.getAuth().signInWithEmailAndPassword(username, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // PUT YOUR CODE HERE
                            FragmentTransaction ft =getActivity().getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.frameLayoutMain, new Choose_UserType());
                            ft.commit();
                            //gotoAddUserFragment();

                        } else {
                            // PUT YOUR CODE HERE
                        }
                    }
                });
            }
        });
    }

    public void gotoAddUserFragment()
    {
        FragmentTransaction ft =getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new Choose_UserType());
        ft.commit();
    }

    public void gotoUserListFragment()
    {
        FragmentTransaction ft =getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new UserListFragment());
        ft.commit();
    }
}