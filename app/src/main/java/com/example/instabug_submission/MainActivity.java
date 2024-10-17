package com.example.instabug_submission;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.instabug.bug.BugReporting;
import com.instabug.library.Instabug;
import com.instabug.library.invocation.InstabugInvocationEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        new Instabug.Builder(this.getApplication(), "34456fc8accdea850ff99134e31e73d7")
                .setInvocationEvents(  InstabugInvocationEvent.SHAKE)
                .build();
        BugReporting.setShakingThreshold(350);
        Button bugReportButton = findViewById(R.id.report_bug);
        bugReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Instabug.show();
            }
        });
    }
}