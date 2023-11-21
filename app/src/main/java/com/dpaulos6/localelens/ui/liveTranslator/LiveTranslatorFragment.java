package com.dpaulos6.localelens.ui.liveTranslator;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dpaulos6.localelens.databinding.FragmentLiveTranslatorBinding;

public class LiveTranslatorFragment extends Fragment
{

  private FragmentLiveTranslatorBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState)
  {
    LiveTranslatorViewModel liveTranslatorViewModel =
        new ViewModelProvider(this).get(LiveTranslatorViewModel.class);

    binding = FragmentLiveTranslatorBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    // Launch camera on fragment start
    // Camera is currently working
    try {
      Intent in = new Intent();
      in.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
      startActivity(in);
    } catch (Exception e) {
      e.printStackTrace();
    }

    final TextView textView = binding.textLiveTranslator;
    liveTranslatorViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    return root;
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}