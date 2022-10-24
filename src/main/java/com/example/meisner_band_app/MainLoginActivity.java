package com.example.meisner_band_app;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Dialog;
        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.GridView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class MainLoginActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText passText;
    private TextView textGreeting;
    private Button loginButton;
    private Button forgotPass;
    private Button sayHelloButton;
    public ArrayList<UserMap> userMaps = new ArrayList<>();
    EditText userName, userPass;
    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        nameText = findViewById(R.id.nameText);
        passText = findViewById(R.id.passText);
        loginButton = findViewById(R.id.login);
        textGreeting = findViewById(R.id.textGreeting);
        sayHelloButton = findViewById(R.id.buttonSayHello);
        forgotPass = findViewById(R.id.forgotPass);
        String loginTextStr = nameText.getText().toString();
        String passTextStr = passText.getText().toString();
        String nameTextStr = nameText.getText().toString();
        ArrayList<UserMap> list;
        list = new ArrayList<>();

        sqLiteHelper = new SQLiteHelper(this, "UserDB.sqlite", null, 2);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS USER (Id INTEGER PRIMARY KEY AUTOINCREMENT, userName VARCHAR, userPass VARCHAR)");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertUserData("placeholder", "placeholder");

                    // get all data from sqlite
                    Cursor cursor = MainLoginActivity.sqLiteHelper.getData("SELECT * FROM USER");
                    list.clear();
                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(0);
                        String userName = cursor.getString(1);
                        String userPass = cursor.getString(2);
                        list.add(new UserMap(userName, userPass));

                        if ((list.get(0).getUsername() == loginTextStr) && (list.get(0).getPassword() == passTextStr)) {
                            textGreeting.setText("Found existing account " + nameTextStr);
                            Intent intent = new Intent(MainLoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        if ((list.get(0).getUsername() == loginTextStr) && (list.get(0).getPassword() != passTextStr)) {
                            textGreeting.setText("Incorrect password! Please try again, or press Forgot Password");
                        }

                        if (list.get(0).getUsername() != loginTextStr) {
                            textGreeting.setText("Added new account " + nameTextStr + " Click LOGIN again to continue");
                            list.get(0).setUsername(loginTextStr);
                            list.get(0).setPassword(passTextStr);
                            System.out.println("username " + userName);
                            System.out.println("password " + userPass);
                        }
                    }

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((list.get(0).getUsername() == loginTextStr) && (list.get(0).getPassword() != passTextStr)) {
                    textGreeting.setText("Password successfully updated!");
                    list.get(0).setPassword(passTextStr);
                }
                return;
            }
        });

        addUser("placeholder", "placeholder");
        nameText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (nameText.length() < 1) {
                    sayHelloButton.setEnabled(false);
                } else if (nameText.length() > 0) {
                    sayHelloButton.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (nameText.length() < 1) {
                    sayHelloButton.setEnabled(false);
                } else if (nameText.length() > 0) {
                    sayHelloButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        if (nameText.getText().toString() != "") {
            sayHelloButton.setEnabled(true);
        } else {
            sayHelloButton.setEnabled(false);
        }
    }

    public void NewUser(View view) {
        String loginTextStr = nameText.getText().toString();
        String passTextStr = passText.getText().toString();
        String nameTextStr = nameText.getText().toString();

        System.out.println(loginTextStr); // successfully communicating with app
        for (UserMap userMaps : userMaps) {
            System.out.println("Checking userMaps");
            if (userMaps.getUsername().equals(loginTextStr)) {
                System.out.println("Found " + loginTextStr + " account!\n" +
                        "Logging In!");
                textGreeting.setText("Found existing account " + nameTextStr);
                Intent intent = new Intent(MainLoginActivity.this, MainActivity.class);
                startActivity(intent);

            } else {
                System.out.println("Did not find " + loginTextStr + " account\n" +
                        "Adding account to database now!");
                userMaps.setUsername(loginTextStr);
                userMaps.setPassword(passTextStr);
                textGreeting.setText("Added new account " + nameTextStr + " Click LOGIN again to continue");

            }
            System.out.println("userMaps checked");
        }
    }

    public void addUser(String x, String y) {
        System.out.println("Adding User!");
        userMaps.add(new UserMap(x, y));
    }

    public void SayHello(View view) {
        String nameTextStr = nameText.getText().toString();

        if (nameText == null) {
            textGreeting.setText("You must enter a name!");

        } else if (nameText != null) {
            System.out.println("nameText is not null!");
            textGreeting.setText("Hello! " + nameTextStr);
        }
        return;
    }



}
