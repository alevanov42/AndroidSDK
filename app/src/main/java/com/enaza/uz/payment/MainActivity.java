package com.enaza.uz.payment;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.enaza.uz.R;
import com.enaza.uz.payment.api.callback.VerifyCardCallback;
import com.enaza.uz.payment.api.task.VerifyCardTask;
import com.enaza.uz.payment.model.Confirm;
import com.enaza.uz.payment.utils.PaycomSandBox;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PaycomSandBox.setEnabled(true);

        new VerifyCardTask(
                "6229d432f193c361ee0718bb",
                14990.0,
                "8600495473316477",
                "0399",
                "en",
                false, new VerifyCardCallback() {

            @Override
            public void onSuccess(Confirm confirm, String token) {

            }

            @Override
            public void onError(String error) {
                Log.e("asdasd", error);
            }
        }).execute();

    }
}