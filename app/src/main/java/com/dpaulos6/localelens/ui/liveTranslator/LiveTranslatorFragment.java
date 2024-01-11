package com.dpaulos6.localelens.ui.liveTranslator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.dpaulos6.localelens.MainActivity;
import com.dpaulos6.localelens.R;
import com.dpaulos6.localelens.databinding.FragmentLiveTranslatorBinding;
import com.google.common.util.concurrent.ListenableFuture;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class LiveTranslatorFragment extends Fragment implements CameraBridgeViewBase.CvCameraViewFrame
{
  private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
  private FragmentLiveTranslatorBinding binding;
  private CameraBridgeViewBase mOpenCvCameraView;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState)
  {
    LiveTranslatorViewModel liveTranslatorViewModel =
        new ViewModelProvider(this).get(LiveTranslatorViewModel.class);

    binding = FragmentLiveTranslatorBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext());

    cameraProviderFuture.addListener(() -> {
      try {
        ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
        bindPreview(cameraProvider);
      } catch (ExecutionException | InterruptedException e) {}
    }, ContextCompat.getMainExecutor(requireContext()));

    return root;
  }

  void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
    Preview preview = new Preview.Builder()
        .build();

    CameraSelector cameraSelector = new CameraSelector.Builder()
        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
        .build();

    PreviewView previewView =((MainActivity) requireContext()).findViewById(R.id.previewView);
    preview.setSurfaceProvider(previewView.getSurfaceProvider());

    mOpenCvCameraView = new CameraBridgeViewBase(requireContext(), null);
    mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
    mOpenCvCameraView.setCvCameraViewListener(this);

    Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, preview);
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}