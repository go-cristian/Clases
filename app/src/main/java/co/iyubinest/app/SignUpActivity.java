package co.iyubinest.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by cristiangomez on 12/8/17.
 */
public class SignUpActivity extends AppCompatActivity {

  EditText useranameField, emailField, passwordField, confirmPasswordField;

  Button signUpButton;

  public static Intent create(AppCompatActivity activity) {
    return new Intent(activity, SignUpActivity.class);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);

    useranameField = findViewById(R.id.singup_username);
    emailField = findViewById(R.id.signup_email);
    passwordField = findViewById(R.id.signup_password);
    confirmPasswordField = findViewById(R.id.signup_password_confirm);
    signUpButton = findViewById(R.id.singup_button);

    useranameField.addTextChangedListener(new FormValidWatcher());
    emailField.addTextChangedListener(new FormValidWatcher());
    passwordField.addTextChangedListener(new FormValidWatcher());
    confirmPasswordField.addTextChangedListener(new FormValidWatcher());
  }

  private void valid() {
    boolean enabled =
        isValid(useranameField) && isValid(emailField) && isValid(passwordField) && isValid(
            confirmPasswordField);
    signUpButton.setEnabled(enabled);
  }

  private boolean isValid(EditText text) {
    return !TextUtils.isEmpty(text.getText());
  }

  private class FormValidWatcher implements TextWatcher {

    @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      valid();
    }

    @Override public void afterTextChanged(Editable editable) {

    }
  }
}
