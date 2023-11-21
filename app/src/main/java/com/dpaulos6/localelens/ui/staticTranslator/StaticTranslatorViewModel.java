package com.dpaulos6.localelens.ui.staticTranslator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StaticTranslatorViewModel extends ViewModel
{

  private final MutableLiveData<String> mText;

  public StaticTranslatorViewModel()
  {
    mText = new MutableLiveData<>();
    mText.setValue("This is static translator fragment");
  }

  public LiveData<String> getText()
  {
    return mText;
  }
}