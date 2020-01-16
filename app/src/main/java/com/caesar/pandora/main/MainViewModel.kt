package com.caesar.pandora.main

import androidx.databinding.ObservableField
import com.caesarlib.fram.view.BaseView
import com.caesarlib.fram.viewmodel.BaseViewModel

class MainViewModel :BaseViewModel<BaseView>(){
    val mtabs = ObservableField<List<String>>()
    val mtabFonts = ObservableField<List<String>>()
}
