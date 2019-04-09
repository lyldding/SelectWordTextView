package com.lyldding.textselect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lyldding.selecttextview.SelectWordTextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    SelectWordTextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mTextView = this.findViewById(R.id.test);
        mTextView.setText(" News that the top diplomats from the US and Russia are to meet follows an unexpected phone conversation on Friday evening between President Barack Obama and President Vladimir Putin. The Kremlin initiated the call to discuss ways of stabilising the situation in Ukraine, a surprise move after weeks of escalating tensions between Russia and the West. It may be a sign of tentative progress towards a diplomatic solution, though the White House says Russia must first pull back the troops that have been deployed along the border with Ukraine.\n" +
                "A leading Ukrainian politician, Vitali Klitschko, has announced that he will not run for presidential in elections in May, but will instead support a prominent businessman and former government minister Petro Poroshenko. Mr Klitschko, a former world boxing champion, told a gathering of his Udar party that democratic forces, as he put it, needed to unify behind a single candidate.\n" +
                "“I've always said this has to be a candidate who enjoys the strongest public support with the greatest chance of victory. Today this candidate in my opinion is Petro Poroshenko.”");
        mTextView.setOnClickWordListener(new SelectWordTextView.OnClickWordListener() {
            @Override
            public void onClickWord(String word) {
                Toast.makeText(MainActivity.this, word, Toast.LENGTH_SHORT).show();
            }
        });
    }
}