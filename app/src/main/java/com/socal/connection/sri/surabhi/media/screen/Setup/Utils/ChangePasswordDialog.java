package com.socal.connection.sri.surabhi.media.screen.Setup.Utils;

import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.utils.Common;
import com.socal.connection.sri.surabhi.media.utils.License.SimpleCryptoException;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ChangePasswordDialog extends DialogFragment implements View.OnClickListener {
    @BindView(R.id.dialog_save_btn)
    public Button saveBtn;

    @BindView(R.id.dialog_cancel_btn)
    public Button cancelBtn;

    @BindView(R.id.old_password_edittext)
    public EditText oldPasswordEditText;

    @BindView(R.id.new_password_edittext)
    public EditText newPasswordEditText;

    @BindView(R.id.re_new_password_edittext)
    public EditText reNewPasswordEditText;

    @BindView(R.id.show_pass_checkbox)
    public CheckBox checkBoxView;

    private Unbinder unbinder;
    private SriSurabhiActivity mContext = SriSurabhiActivity.getInstance();
    private String adminPassword = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adminPassword = getArguments().getString(Common.SS_ADMIN_PASSWORD);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.setup_pass_change_dialog, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        getDialog().setTitle(getResources().getString(R.string.setup_enter_new_admin_password));
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        // Do something else
        saveBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        checkBoxView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                doCheckBox(b);
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.dialog_save_btn) {
            savePassword();
        } else if (view.getId() == R.id.dialog_cancel_btn) {
            dismiss();
        }
    }

    private void savePassword() {

        String oldPassword = oldPasswordEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();
        String reNewPassword = reNewPasswordEditText.getText().toString();

        if (oldPassword.equals(adminPassword) && newPassword.equals(reNewPassword)) {

            try {

                SetupFile setupFile = new SetupFile.SetupBuilder().setAdminPassword(reNewPassword).build();
                boolean isFileSave = setupFile.saveFile();
                mContext.setSetupFile(setupFile);

                if (isFileSave) {
                    Toast.makeText(getActivity(), getString(R.string.setup_admin_password_has_been_successfully_changed), Toast.LENGTH_SHORT).show();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (SimpleCryptoException e) {
                e.printStackTrace();
            }
        } else {
            if (!oldPassword.equals(adminPassword)) {
                Toast.makeText(getActivity(), getString(R.string.setup_old_password_is_wrong), Toast.LENGTH_SHORT).show();
                return;
            }
            if (!newPassword.equals(reNewPassword)) {
                Toast.makeText(getActivity(), getString(R.string.setup_re_enter_password_is_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void doCheckBox(boolean isChecked) {
        if (isChecked) {
            oldPasswordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            newPasswordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            reNewPasswordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            oldPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            newPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            reNewPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        oldPasswordEditText.setSelection(oldPasswordEditText.length());
        newPasswordEditText.setSelection(newPasswordEditText.length());
        reNewPasswordEditText.setSelection(reNewPasswordEditText.length());
    }
}
