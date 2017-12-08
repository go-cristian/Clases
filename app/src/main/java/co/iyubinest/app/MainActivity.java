package co.iyubinest.app;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

  private View mainView;

  private View initButton, singUpLink, forgotLink;

  private EditText usernameField;

  private EditText passwordField;

  private Api api;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mainView = findViewById(android.R.id.content);
    singUpLink = findViewById(R.id.login_singup_link);
    forgotLink = findViewById(R.id.login_forgot_link);
    initButton = findViewById(R.id.login_init);
    usernameField = findViewById(R.id.login_username);
    passwordField = findViewById(R.id.login_password);

    initButton.setOnClickListener(new LoginListener());
    singUpLink.setOnClickListener(new SignUpListener());
    forgotLink.setOnClickListener(new ForgotListener());
    usernameField.addTextChangedListener(new FormValidWatcher());
    passwordField.addTextChangedListener(new FormValidWatcher());

    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://demo8720912.mockable.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    api = retrofit.create(Api.class);
  }

  private void validate() {
    boolean enabled = isValid(usernameField) && isValid(passwordField);
    initButton.setEnabled(enabled);
  }

  private boolean isValid(EditText field) {
    return !TextUtils.isEmpty(field.getText());
  }

  interface Api {

    @POST("login") public Call<MessageBody> login();
  }

  class MessageBody {

    private final String msg;

    MessageBody(String msg) {
      this.msg = msg;
    }

    public String getMsg() {
      return msg;
    }
  }

  private class LoginListener implements View.OnClickListener {

    @Override public void onClick(View view) {
      api.login().enqueue(new LoginCallback());
    }
  }

  private class LoginCallback implements Callback<MessageBody> {

    @Override public void onResponse(Call<MessageBody> call, Response<MessageBody> response) {
      Snackbar.make(mainView, response.body().getMsg(), Snackbar.LENGTH_SHORT).show();
    }

    @Override public void onFailure(Call<MessageBody> call, Throwable t) {
      Snackbar.make(mainView, "Error", Snackbar.LENGTH_SHORT).show();
    }
  }

  private class SignUpListener implements View.OnClickListener {

    @Override public void onClick(View view) {
      startActivity(SignUpActivity.create(MainActivity.this));
      //finish();
    }
  }

  private class ForgotListener implements View.OnClickListener {

    @Override public void onClick(View view) {
      startActivity(ForgotPasswordActivity.create(MainActivity.this));
    }
  }

  private class FormValidWatcher implements TextWatcher {

    @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      validate();
    }

    @Override public void afterTextChanged(Editable editable) {

    }
  }
}
