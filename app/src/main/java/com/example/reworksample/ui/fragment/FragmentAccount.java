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

    private static final int RC_SIGN_IN = 69;

    public FragmentAccount() {
        // Required empty public constructor
    }

    private static FragmentAccount fragment;

    public static FragmentAccount getInstance() {
        if (fragment == null) {
            fragment = new FragmentAccount();
        }
        return fragment;
    }

    private FragmentAccountBinding binding;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
// ...


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Initialize Firebase Auth
        //regist Firebase
        FirebaseApp.initializeApp(getActivity());
        mAuth = FirebaseAuth.getInstance();

        configGGLogin();

        setView();
        setClick();
    }

    private void setClick() {
        binding.tvLoginWithGG.setOnClickListener(v -> {
            loginGG();
        });

        binding.tvLoginWithFb.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Tính năng đang phát triển", Toast.LENGTH_LONG).show();
        });


        binding.tvLogOut.setOnClickListener(v -> {
            mGoogleSignInClient.signOut();
            binding.llLogin.setVisibility(View.VISIBLE);
            binding.cardviewContainUser.setVisibility(View.GONE);
        });
    }

    private void setView() {
        // Check User logined to Set UI
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        } else {
            binding.cardviewContainUser.setVisibility(View.GONE);
            binding.llLogin.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_account, container, false);
        return binding.getRoot();
    }

    private void configGGLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id_1))
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
    }


    private void loginGG() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.e("DDVH", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.e("DDVH", "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        if (getActivity() == null) return;
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("DDVH", "signInWithCredential:failure", task.getException());
                            handleLoginFail();
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (getActivity() == null) return;
        binding.cardviewContainUser.setVisibility(View.VISIBLE);
        Glide.with(getActivity()).load(user.getPhotoUrl()).into(binding.imgLogo);
        binding.tvName.setText(user.getDisplayName());
        binding.tvEmail.setText(user.getEmail());
        binding.llLogin.setVisibility(View.GONE);
    }

    private void handleLoginFail() {
        Toast.makeText(getActivity(), "Đăng nhập không thành công !", Toast.LENGTH_SHORT).show();
    }

}