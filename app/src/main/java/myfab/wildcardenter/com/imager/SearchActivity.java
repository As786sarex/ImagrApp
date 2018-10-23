package myfab.wildcardenter.com.imager;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class SearchActivity extends AppCompatActivity {
    EditText editText;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText = findViewById(R.id.search_text);
        btn = findViewById(R.id.search_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm =
                        (ConnectivityManager) SearchActivity.this.getSystemService(SearchActivity.this.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = null;
                if (cm != null) {
                    activeNetwork = cm.getActiveNetworkInfo();
                }

                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if (!isConnected) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                    builder.setMessage("\nOops....Looks like you are not connected to the Internet." +
                            "\n\n").setTitle("Network Error!!").setCancelable(true)
                            .setIcon(R.mipmap.launcher_icon_init).show();
                } else {
                    if (editText.getText().toString().trim().equals("")) {
                        Toasty.warning(SearchActivity.this, "Field Required", Toast.LENGTH_SHORT, true).show();
                    } else {
                        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                        intent.putExtra("mylta", editText.getText().toString());
                        startActivity(intent);
                    }

                }
            }

        });
    }
}