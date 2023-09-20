package com.example.funds;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class PinKeyListener implements View.OnKeyListener{
    private EditText currentEditText;
    private EditText previousEditText;

    public PinKeyListener(EditText currentEditText, EditText previousEditText) {
        this.currentEditText = currentEditText;
        this.previousEditText = previousEditText;
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (previousEditText != null) {
                currentEditText.setText("");
                previousEditText.requestFocus();
                return true;
            }
        }
        return false;
    }
}
