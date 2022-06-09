package com.enaza.uz.payment.api.callback;

import com.enaza.uz.payment.model.Result;

public interface ConfirmCodeCallback {
     void onSuccess(Result result);

     void onError(String error);
}
