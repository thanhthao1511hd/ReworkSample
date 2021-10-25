package com.example.reworksample.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.reworksample.R;
import com.example.reworksample.databinding.FragmentAccountBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class FragmentAccount extends Fragment {

    FragmentAccountBinding binding;
    private static final int RC_SIGN_In=10;
    private FirebaseAuth mAuth;
    private GoogleSignInClient googleSignInClient;


    public FragmentAccount() {
        // Required empty public constructor
    }

    public static FragmentAccount newInstance() {

        Bundle args = new Bundle();

        FragmentAccount fragment = new FragmentAccount();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FirebaseApp.initializeApp(getActivity());
        mAuth=FirebaseAuth.getInstance();
        configGGLogin();
        setView();
        setClick();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_In){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account=task.getResult(ApiException.class);
                Log.e("DDVH: ", "firebaseAuthWithGoogle" + account.getId());
                firebaseAuthGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.e("DDVH: ", "Google sign in failed", e);
            }
        }
    }

    private void loginFail()
    {
        Toast.makeText(getActivity(), "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
    }
    private void updateUI(FirebaseUser user)
    {
        if (getActivity()==null)
        {
            return;
        }
        else{
            binding.cardviewContainUser.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(user.getPhotoUrl()).into(binding.imgLogo);
            binding.tvName.setText(user.getDisplayName());
            binding.tvEmail.setText(user.getEmail());
            binding.llLogin.setVisibility(View.GONE);
        }
    }
    private void firebaseAuthGoogle(String idToken)
    {
        if (getActivity()==null)
        {
            return;
        }else{
            AuthCredential authCredential= GoogleAuthProvider.getCredential(idToken, null);
            mAuth.signInWithCredential(authCredential).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        FirebaseUser user=mAuth.getCurrentUser();
                        updateUI(user);
                    }else{
                        Log.e("DDVH: ", "SignInWithCredential: failure", task.getException());
                        loginFail();
                    }
                }
            });
        }
    }

    private void setView() {
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser!=null)
        {

        }
        else{
            binding.cardviewContainUser.setVisibility(View.GONE);
            binding.llLogin.setVisibility(View.VISIBLE);
        }
    }

    private void setClick() {
        binding.tvLoginWithGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginGG();
            }
        });
        binding.tvLoginWithFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void loginGG() {
        Intent intent=googleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_In);
    }

    private void configGGLogin() {
        GoogleSignInOptions ggSign=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id_1))
                .requestEmail().requestProfile().build();
        googleSignInClient= GoogleSignIn.getClient(getActivity(), ggSign);

    }


}