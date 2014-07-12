package com.detoritlabs.dpl.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.detoritlabs.dpl.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class LibraryFragment extends Fragment {


    @InjectView(R.id.submitEmailBody)
    EditText submitBody;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_send:
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts( "mailto", "askalibrarian@detroitpubliclibrary.org", null));
                intent.putExtra(Intent.EXTRA_TEXT, submitBody.getText());
                startActivity(Intent.createChooser(intent, "Ask a Librarian"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.library_activity_actions, menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_library, container, false);
        ButterKnife.inject(this, root);
        return root;
    }
}
