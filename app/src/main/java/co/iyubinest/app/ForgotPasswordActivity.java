package co.iyubinest.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by cristiangomez on 12/8/17.
 */
public class ForgotPasswordActivity extends AppCompatActivity {

  public static Intent create(AppCompatActivity activity) {
    return new Intent(activity, ForgotPasswordActivity.class);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forgot_password);
  }
}
