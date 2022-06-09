package com.enaza.uz.payment.api.callback;

import com.enaza.uz.payment.model.Confirm;

public interface VerifyCardCallback {
    void onSuccess(Confirm confirm, String token);

    void onError(String error);
}