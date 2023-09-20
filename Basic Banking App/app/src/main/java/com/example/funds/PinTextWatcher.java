package com.example.funds;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class PinTextWatcher implements TextWatcher {
    private EditText currentEditText;
    private EditText nextEditText;
    private EditText previousEditText;

    public PinTextWatcher(EditText currentEditText, EditText nextEditText, EditText previousEditText) {
        this.currentEditText = currentEditText;
        this.nextEditText = nextEditText;
        this.previousEditText = previousEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 1 && nextEditText != null) {
            currentEditText.clearFocus();
            nextEditText.requestFocus();
        }
    }
}
