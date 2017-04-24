package com.example.hppc.edified;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class CourseDescription extends AppCompatActivity {

    private static final String KEY_FILE_URI = "key_file_uri";
    private static final String KEY_DOWNLOAD_URL = "key_download_url";
    private Button courseBook, courseQuiz;
    private TextView category, descrip;
    private String TAG = "CourseDescription";
    private Uri mDownloadUrl = null;
    private FirebaseAuth mAuth;
    private Course crs;
    private Activity activity;

    private BroadcastReceiver mBroadcastReceiver;
    private ProgressDialog mProgressDialog;
    private Uri mFileUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        crs = intent.getExtras().getParcelable("Course");
        category = (TextView) findViewById(R.id.category);
        descrip = (TextView) findViewById(R.id.description);
        category.setText(crs.getCourseCategory());
        descrip.setText(crs.getCourseDesc());

        mAuth = FirebaseAuth.getInstance();

        activity = this;
        courseBook = (Button) findViewById(R.id.book);

        courseBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] permission = new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                };
                if (checkPermissions(permission)) {
                    beginDownload();
                }
            }
        });

        courseQuiz = (Button) findViewById(R.id.quiz);
        courseQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizIntent = new Intent(CourseDescription.this, CourseQuizActivity.class);
                quizIntent.putExtra("course", (Parcelable) crs);
                startActivity(quizIntent);
            }
        });

        if (savedInstanceState != null) {
            mFileUri = savedInstanceState.getParcelable(KEY_FILE_URI);
            mDownloadUrl = savedInstanceState.getParcelable(KEY_DOWNLOAD_URL);
        }

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive:" + intent);
                hideProgressDialog();

                switch (intent.getAction()) {
                    case DownloadService.DOWNLOAD_COMPLETED:
                        // Get number of bytes downloaded
                        long numBytes = intent.getLongExtra(DownloadService.EXTRA_BYTES_DOWNLOADED, 0);

                        // Alert success
                        showMessageDialog(getString(R.string.success), String.format(Locale.getDefault(),
                                "%d bytes downloaded from %s",
                                numBytes,
                                intent.getStringExtra(DownloadService.EXTRA_DOWNLOAD_PATH)));

                        String filename = intent.getStringExtra(DownloadService.EXTRA_FILE_PATH);

//                        File file = new File(Environment.getExternalStoragePublicDirectory("edified") , "book.pdf");

                        File pdfPath = new File(Environment.getExternalStorageDirectory(), "edified");
                        File pdfFile = new File(pdfPath, "book.pdf");

                        Uri uri = FileProvider.getUriForFile(CourseDescription.this, BuildConfig.APPLICATION_ID + ".provider", pdfFile);
                        try {
                            Intent intentBook = new Intent(Intent.ACTION_VIEW);
                            intentBook.setDataAndType(uri, "application/pdf");
                            intentBook.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            activity.startActivity(intentBook);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(activity, "No pdf viewer installed", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case DownloadService.DOWNLOAD_ERROR:
                        // Alert failure
                        showMessageDialog("Error", String.format(Locale.getDefault(),
                                "Failed to download from %s",
                                intent.getStringExtra(DownloadService.EXTRA_DOWNLOAD_PATH)));
                        break;
                }
            }
        };
    }

    private boolean checkPermissions(String[] permission) {
        int result;
        ArrayList<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permission) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                beginDownload();
            }
            return;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle out) {
        out.putParcelable(KEY_FILE_URI, mFileUri);
        out.putParcelable(KEY_DOWNLOAD_URL, mDownloadUrl);
    }

    private void beginDownload() {
        // Get path
        String path = crs.getCourseBook();

        // Kick off MyDownloadService to download the file
        Intent intent = new Intent(this, DownloadService.class)
                .putExtra(DownloadService.EXTRA_DOWNLOAD_PATH, path)
                .setAction(DownloadService.ACTION_DOWNLOAD);
        startService(intent);

        // Show loading spinner
        showProgressDialog(getString(R.string.progress_downloading));
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private void showMessageDialog(String title, String message) {
        AlertDialog ad = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .create();
        ad.show();
    }

    private void showProgressDialog(String caption) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.setMessage(caption);
        mProgressDialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI(mAuth.getCurrentUser());

        // Register receiver for uploads and downloads
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(mBroadcastReceiver, DownloadService.getIntentFilter());
    }

    @Override
    public void onStop() {
        super.onStop();

        // Unregister download receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }

    private void updateUI(FirebaseUser currentUser) {

    }
}
