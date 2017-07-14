package pt.joaocruz.myrecipeschallenge.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pt.joaocruz.myrecipeschallenge.R;

/**
 * Created by jcruz on 14.07.17.
 */


public class LoginDialog extends DialogFragment {

    private LoginDialogListener listener;
    private EditText mEmail;
    private EditText mPassword;
    private Button mCancel;
    private Button mLogin;

    public LoginDialog listener(LoginDialogListener listener) {
        this.listener = listener;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_popup, container);
        mEmail = view.findViewById(R.id.email_et);
        mPassword = view.findViewById(R.id.password_et);
        mLogin = view.findViewById(R.id.submit_bt);
        mCancel = view.findViewById(R.id.cancel_bt);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null)
                    listener.onLoginSubmit(mEmail.getText().toString(), mPassword.getText().toString());
            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }

    public interface LoginDialogListener {
        void onLoginSubmit(String email, String password);
    }
}
