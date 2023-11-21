package com.dpaulos6.localelens.ui.liveTranslator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiveTranslatorViewModel extends ViewModel
{

  private final MutableLiveData<String> mText;

  public LiveTranslatorViewModel()
  {
    mText = new MutableLiveData<>();
    mText.setValue("This is live translator fragment");
  }

  public LiveData<String> getText()
  {
    return mText;
  }
}