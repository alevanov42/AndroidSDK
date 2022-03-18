package com.enaza.uz.payment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.enaza.uz.R;
import com.enaza.uz.payment.utils.PaycomSandBox;

import static com.enaza.uz.payment.PaymentActivity.EXTRA_AMOUNT;
import static com.enaza.uz.payment.PaymentActivity.EXTRA_ID;
import static com.enaza.uz.payment.PaymentActivity.EXTRA_LANG;
import static com.enaza.uz.payment.PaymentActivity.EXTRA_SAVE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String xAuth = "5a3bb098d9ffa237dc027290";
        Double sum = 5551.0;
        Boolean multyPaying = false;

        Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
        intent.putExtra(EXTRA_ID, xAuth); //Ваш ID мерчанта
        intent.putExtra(EXTRA_AMOUNT, sum); //Сумма оплаты
        intent.putExtra(EXTRA_SAVE, multyPaying); //Сохранить для многократной оплаты?
        intent.putExtra(EXTRA_LANG, "RU"); //Язык "RU" или "UZ"
        PaycomSandBox.setEnabled(true); //true для тестовой площадки, по умолчанию false
        startActivityForResult(intent, 0);
    }
}