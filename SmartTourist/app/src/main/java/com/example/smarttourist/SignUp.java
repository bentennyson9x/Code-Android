package com.example.smarttourist;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import java.util.HashMap;
        import java.util.Map;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText edtNameAccount, edtPasswordAccount, edtEmail, edtPhoneNumber, edtAddress,edtmiddlename,
            edtlastname,edtfirstname;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        AddView();
        Click();
    }

    private void Click() {
        btnSignUp.setOnClickListener(this);
    }

    private void AddView() {
        edtNameAccount = findViewById(R.id.signUpNameAccount);
        edtPasswordAccount = findViewById(R.id.signUpPassword);
        edtPhoneNumber = findViewById(R.id.signUpPhoneNumber);
        edtAddress = findViewById(R.id.signUpAddress);
        edtEmail = findViewById(R.id.signUpEmail);
        edtfirstname = findViewById(R.id.signUpFirstName);
        edtlastname = findViewById(R.id.signUpLastName);
        edtmiddlename = findViewById(R.id.signUpMiddleName);
        btnSignUp = findViewById(R.id.signUpBtnConfirm);
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-zA-z]+[a-z0-9]*@{1}\\w+mail.com$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHONE_NUMBER_REGEX =
            Pattern.compile("^0[987]{1}\\d{8}$", Pattern.CASE_INSENSITIVE);
    public static boolean validatePhoneNumber(String phoneNumber) {
        Matcher matcher = VALID_PHONE_NUMBER_REGEX .matcher(phoneNumber);
        return matcher.find();
    }
    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    @Override
    public void onClick(View view) {
        if (view == btnSignUp){
            String nameAccount = edtNameAccount.getText().toString().trim();
            String Pass = edtPasswordAccount.getText().toString().trim();
            String Phone = edtPhoneNumber.getText().toString().trim();
            String Address = edtAddress.getText().toString().trim();
            String Email = edtEmail.getText().toString().trim();
            if (nameAccount.equals("")==true||Pass.isEmpty()==true||
                    Phone.isEmpty()==true||Address.isEmpty()==true||Email.isEmpty()==true){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Notice: ");
                builder.setIcon(R.drawable.warning);
                builder.setMessage("Form can not be empty !! ");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
            else if (validateEmail(Email)==false){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Notice: ");
                builder.setIcon(R.drawable.warning);
                if (validatePhoneNumber(Phone)==false){
                    builder.setMessage("Invalid Email and Phone Number . Please try again ! ");
                }
                else {
                    builder.setMessage("Invalid Email. Please try again ! ");
                }
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
            else if (validatePhoneNumber(Phone)==false){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Notice: ");
                builder.setIcon(R.drawable.warning);
                builder.setMessage("Invalid Phone. Please try again ! ");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
            else {
                String url = "http://"+getString(R.string.IP)+"/QLCT/SignUp.php";
                Log.e("xem",url);
                AddDataBase(url);
            }
        }

    }

    private void AddDataBase(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("xem",response.toString());
                if (response.trim().equals("success")){
                    Toast.makeText(SignUp.this, "Sign up success !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUp.this,Login.class));
                }
                else {
                    Toast.makeText(SignUp.this, "Sign up fail ! \n"+response, Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("xem",error.toString());
                Toast.makeText(SignUp.this, "You may have been lost connection. Please check your internet and try again ! ", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String > params = new HashMap<>();
                params.put("idClient",edtNameAccount.getText().toString());
                params.put("password",edtPasswordAccount.getText().toString());
                params.put("email",edtEmail.getText().toString());
                params.put("phoneNumber",edtPhoneNumber.getText().toString());
                params.put("address",edtAddress.getText().toString());
                params.put("firstName",edtfirstname.getText().toString());
                params.put("middleName",edtmiddlename.getText().toString());
                params.put("lastName",edtlastname.getText().toString());
                StringBuilder fullName = new StringBuilder();
                fullName.append(edtfirstname.getText().toString());
                fullName.append(edtmiddlename.getText().toString());
                fullName.append(edtlastname.getText().toString());
                params.put("fullName",fullName.toString());
                StringBuffer
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
}
