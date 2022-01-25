package com.atsistemas.formacion2022.common

import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by Carlos Mateo Benito on 25/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
fun EditText.setText(text:String, watcher: TextWatcher){
    removeTextChangedListener(watcher)
    setText(text)
    setSelection(text.length)
    addTextChangedListener(watcher)
}