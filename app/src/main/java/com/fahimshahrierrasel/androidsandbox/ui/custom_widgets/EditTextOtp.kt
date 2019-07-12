package com.fahimshahrierrasel.androidsandbox.ui.custom_widgets

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.Nullable
import com.fahimshahrierrasel.androidsandbox.R

class EditTextOtp : LinearLayout, View.OnFocusChangeListener, TextWatcher, View.OnKeyListener {

    private var etDigit1: EditText? = null
    private var etDigit2: EditText? = null
    private var etDigit3: EditText? = null
    private var etDigit4: EditText? = null
    var currentFocused: EditText? = null
        private set

    var otp: String
        get() = makeOtp()
        set(otp) {
            if (otp.length != 4) return
            etDigit1!!.setText(otp[0].toString())
            etDigit2!!.setText(otp[1].toString())
            etDigit3!!.setText(otp[2].toString())
            etDigit4!!.setText(otp[3].toString())
        }

    val isValid: Boolean
        get() = makeOtp().length == 4

    constructor(context: Context) : super(context) {
        initialize(null)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        initialize(attrs)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initialize(attrs)
    }

    private fun initialize(attrs: AttributeSet?) {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.otp_view, this)

        etDigit1 = findViewById<EditText>(R.id.etDigit1)
        etDigit2 = findViewById<EditText>(R.id.etDigit2)
        etDigit3 = findViewById<EditText>(R.id.etDigit3)
        etDigit4 = findViewById<EditText>(R.id.etDigit4)

        initFocusListener()
        initTextChangeListener()
        initKeyListener()

    }

    private fun initFocusListener() {
        etDigit1!!.onFocusChangeListener = this
        etDigit2!!.onFocusChangeListener = this
        etDigit3!!.onFocusChangeListener = this
        etDigit4!!.onFocusChangeListener = this
    }

    private fun initTextChangeListener() {
        etDigit1!!.addTextChangedListener(this)
        etDigit2!!.addTextChangedListener(this)
        etDigit3!!.addTextChangedListener(this)
        etDigit4!!.addTextChangedListener(this)
    }

    private fun initKeyListener() {
        etDigit1!!.setOnKeyListener(this)
        etDigit2!!.setOnKeyListener(this)
        etDigit3!!.setOnKeyListener(this)
        etDigit4!!.setOnKeyListener(this)
    }

    override fun onFocusChange(v: View, hasFocus: Boolean) {
        currentFocused = v as EditText
        currentFocused!!.setSelection(currentFocused!!.text.length)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

        if (currentFocused!!.text.isNotEmpty() && currentFocused !== etDigit4) {

            currentFocused!!.focusSearch(View.FOCUS_RIGHT).requestFocus()

        } else if (currentFocused!!.text.isNotEmpty() && currentFocused === etDigit4) {

            val imm = context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            imm.hideSoftInputFromWindow(windowToken, 0)

        } else {
            val currentValue = currentFocused!!.text.toString()
            if (currentValue.isEmpty() && currentFocused!!.selectionStart <= 0) {
                currentFocused!!.focusSearch(View.FOCUS_LEFT).requestFocus()
            }

        }
    }

    override fun afterTextChanged(s: Editable) {

    }

    private fun makeOtp(): String {
        val sb = StringBuilder()
        sb.append(etDigit1!!.text.toString())
        sb.append(etDigit2!!.text.toString())
        sb.append(etDigit3!!.text.toString())
        sb.append(etDigit4!!.text.toString())
        return sb.toString()
    }

    override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {

        if (event.action == KeyEvent.ACTION_DOWN) {

            when (v.id) {
                R.id.etDigit1 ->

                    return isKeyDel(etDigit1, keyCode)

                R.id.etDigit2 ->

                    return isKeyDel(etDigit2, keyCode)

                R.id.etDigit3 ->

                    return isKeyDel(etDigit3, keyCode)

                R.id.etDigit4 ->

                    return isKeyDel(etDigit4, keyCode)

                else -> return false
            }

        }

        return false
    }

    private fun isKeyDel(etDigit: EditText?, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            etDigit!!.text = null
            return true
        }
        return false
    }
}