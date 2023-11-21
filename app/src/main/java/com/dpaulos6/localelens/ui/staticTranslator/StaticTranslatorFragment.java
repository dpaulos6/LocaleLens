package com.dpaulos6.localelens.ui.staticTranslator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dpaulos6.localelens.databinding.FragmentStaticTranslatorBinding;

public class StaticTranslatorFragment extends Fragment
{

  private FragmentStaticTranslatorBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState)
  {
    StaticTranslatorViewModel staticTranslatorViewModel =
        new ViewModelProvider(this).get(StaticTranslatorViewModel.class);

    binding = FragmentStaticTranslatorBinding.inflate(inflater, container, false);
    View root = binding.getRoot();



    final TextView textView = binding.textStaticTranslator;
    staticTranslatorViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    return root;
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}