package com.enaza.uz.payment.api.task;

import android.os.AsyncTask;

import com.enaza.uz.payment.api.JsonParser;
import com.enaza.uz.payment.api.JsonRpcRequest;
import com.enaza.uz.payment.api.callback.ConfirmCodeCallback;

import org.json.JSONObject;

public class ConfirmCodeTask extends AsyncTask<Void, Void, String> {

    private String id;
    private String code;
    private String token;
    private ConfirmCodeCallback confirmCodeCallback;
    private boolean hasError;
    private JsonParser jsonParser;

    public ConfirmCodeTask(String id, String code, String token, ConfirmCodeCallback confirmCodeCallback) {
        this.id = id;
        this.code = code;
        this.token = token;
        this.confirmCodeCallback = confirmCodeCallback;
        this.jsonParser = new JsonParser();
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(Void... params) {
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest(id);

        JSONObject jsonObject = jsonParser.getCardsVerify(token, code);
        String result = jsonRpcRequest.callApiMethod(jsonObject, JsonRpcRequest.cardsCreateVerifyMethod);

        if (result == null) return null;
        if (jsonParser.checkError(result) != null) {
            hasError = true;
            return jsonParser.checkError(result);
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        if (s == null) {
//            showError(getString(R.string.tryAgainMessage));
            confirmCodeCallback.onError("");
        } else if (hasError) {
            confirmCodeCallback.onError(s);
        } else {
            confirmCodeCallback.onSuccess(jsonParser.getResult(s));
        }
    }
}
