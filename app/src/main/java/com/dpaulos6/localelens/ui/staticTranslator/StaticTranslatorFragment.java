package com.dpaulos6.localelens.ui.staticTranslator;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.dpaulos6.localelens.R;
import com.dpaulos6.localelens.databinding.FragmentStaticTranslatorBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StaticTranslatorFragment extends Fragment
{

  ActivityResultLauncher<Intent> fileLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
          result->{
            Intent res = result.getData();
            if(res != null && res.getData() != null) {
              Uri caminhoParaoFicheiro = res.getData();
              try {
                InputStream stream = requireActivity().getContentResolver().openInputStream(caminhoParaoFicheiro);
                Bitmap imagem = BitmapFactory.decodeStream(stream);

                ImageView img = requireActivity().findViewById(R.id.imgView);
                Glide.with(this).load(imagem).into(img);

              } catch (FileNotFoundException e)
              {
                Log.d("MDlog", "No pic");
              }
            }
          });

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

  public void pesquisarFicheiro(View view)
  {
    Intent inFile = new Intent(Intent.ACTION_PICK);
    inFile.setType("image/*");
    fileLauncher.launch(inFile);
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}