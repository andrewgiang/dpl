package com.detoritlabs.dpl.fragment;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.activity.LibraryActivity;
import com.detoritlabs.dpl.activity.LocationActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import android.net.Uri;

import java.util.List;

public class ContactFragment extends Fragment {

    static final String FACEBOOK_APP_URI = "fb://page/103971016308779";
    static final String FACEBOOK_WEB_URI = "https://www.facebook.com/detroitpubliclibrary";
    static final String TWITTER_WEB_URI = "https://twitter.com/detroitlibrary";
    static final String YOUTUBE_WEB_URI = "https://www.youtube.com/user/detroitpubliclibrary";
    static final String ONLINE_BOOK_URI = "http://www.detroit.lib.mi.us/specialservice/overdrive-ebooks-and-audiobooks";
    static final String PHONE_NUMBER = "(313) 930-3939";

    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }
    public ContactFragment() {
        // Required empty public constructor
    }

    private void openLink(String link) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }

    void openFacebook() {
        // older versions of Android (tested on 2.3.4) open facebook links in the browser without prompting the user, so we have to send a fb:// request assuming the user has the facebook app installed
        try {
            openLink(FACEBOOK_APP_URI);
        } catch (Exception e) {
            // app not found, let's open the browser
            openLink(FACEBOOK_WEB_URI);
        }
    }

    void openTwitter() {
        openLink(TWITTER_WEB_URI);
    }

    void openYoutube() {
        openLink(YOUTUBE_WEB_URI);
    }

    void openOnlineBooks(){
        openLink(ONLINE_BOOK_URI);
    }

   void openNumber(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        List<ResolveInfo> phoneApps = getActivity().getPackageManager().queryIntentActivities(intent, 0);
        if (!phoneApps.isEmpty()) {
            // User has an app installed that can handle a telephone number.
            startActivity(intent);
        } else {
            // User has no phone call apps installed. Let's display the phone number.
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Oops");
            builder.setMessage("Can't make calls on this device");
            builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            builder.create().show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.inject(this, root);
        return root;
    }

    @OnClick(R.id.facebookButton)
    public void onFacebookButtonClick(){
       openFacebook();
    }

    @OnClick(R.id.twitterButton)
    public void onTwitterButtonClick(){
        openTwitter();
    }

   @OnClick(R.id.youtubeButton)
   public void onYouTubeButtonClick(){
       openYoutube();
   }

   @OnClick(R.id.bookButton)
   public void onOverdriveButtonClick(){
       openOnlineBooks();
   }

   @OnClick(R.id.callButton)
   public void onCallButtonClick(){
       openNumber(PHONE_NUMBER);
   }

    @OnClick(R.id.locationButton)
    public void onLibraryButtonClick(){
        Intent intent = new Intent(getActivity(), LocationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.librarianButton)
    public void onLibrarianButtonClick(){
        Intent intent = new Intent(getActivity(), LibraryActivity.class);
        startActivity(intent);
    }

}
