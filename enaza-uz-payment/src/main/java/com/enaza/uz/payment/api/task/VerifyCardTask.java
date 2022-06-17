package com.enaza.uz.payment.api.task;

import android.os.AsyncTask;
import android.util.Log;

import com.enaza.uz.payment.api.JsonParser;
import com.enaza.uz.payment.api.JsonRpcRequest;
import com.enaza.uz.payment.api.callback.VerifyCardCallback;

import org.json.JSONObject;

/**
 * VerifyCardTask - call several cards methods api
 * and update activity
 */
public class VerifyCardTask extends AsyncTask<Void, Void, String> {

    private Double amount;
    private boolean hasError, save;
    private JsonParser jsonParser;
    private String id, number, expire, token, lang;
    private VerifyCardCallback verifyCardCallback;

    public VerifyCardTask(String id, Double amount, String number, String expire, String lang, Boolean isSave, VerifyCardCallback verifyCardCallback) {
        jsonParser = new JsonParser();
        this.id = id;
        this.amount = amount;
        this.number = number;
        this.expire = expire;
        this.save = isSave;
        this.lang = lang;
        this.verifyCardCallback = verifyCardCallback;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.e("asdiasbh", "asfoif");
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest(id, lang);

        JSONObject jsonObject = jsonParser.getCardsCreate(number, expire, amount, save);
        Log.e("asdeasd", jsonObject.toString());
        String result = jsonRpcRequest.callApiMethod(jsonObject, JsonRpcRequest.cardsCreateMethod);

        if (result == null) return null;
        if (jsonParser.checkError(result) != null) {
            hasError = true;
            return result;
        }


        token = jsonParser.getCardToken(result);
        jsonObject = jsonParser.getCardsVerifyCode(token);
        result = jsonRpcRequest.callApiMethod(jsonObject, JsonRpcRequest.cardsGetVerifyCodeMethod);

        return result;
    }

    @Override
    protected void onPostExecute(String json) {
        Log.e("asdfdsf", json);
        if (json == null) {
            verifyCardCallback.onError(""); //TODO добавить текст ошибки
        } else if (hasError) {
            verifyCardCallback.onError(json);
        } else {
            verifyCardCallback.onSuccess(jsonParser.getConfirm(json), token);
        }
    }
}