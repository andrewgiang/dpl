package com.detoritlabs.dpl.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.api.model.Author;
import com.detoritlabs.dpl.api.model.Book;
import com.detoritlabs.dpl.api.model.Cover;
import com.detoritlabs.dpl.fragment.CatalogFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by andrewgiang on 7/12/14.
 */
public class BookDetailActivity extends Activity {

    @InjectView(R.id.bookName)
    TextView bookName;

    @InjectView(R.id.imageCover)
    ImageView imageCover;

    @InjectView(R.id.image_placeholder)
    ImageView imagePlaceholder;

    @InjectView(R.id.authors)
    TextView authors;

    @InjectView(R.id.pageNumbers)
    TextView pagenumber;

    @InjectView(R.id.publish_date)
    TextView publish_date;
    private String isbn;

    @OnClick(R.id.catalog)
    public void launchCatalog(){
        Intent i = new Intent(this, CatalogActivity.class);
        i.putExtra(CatalogActivity.KEY_URL, CatalogFragment.SEARCH_URL+isbn);
        i.putExtra(CatalogActivity.KEY_TYPE, CatalogActivity.TYPE_SEARCH);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.inject(this);
        if (getIntent() != null) {
            Book book = getIntent().getParcelableExtra("BOOK");
            bookName.setText(book.getTitle());
            Cover cover = book.getCover();
            if (cover != null) {
                Picasso.with(this).load(cover.getLarge()).into(imageCover, new Callback() {
                    @Override
                    public void onSuccess() {
                        imagePlaceholder.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
            }
            String authorText = getAuthorText(book.getAuthors());
            isbn = getIntent().getStringExtra("ISBN");
            authors.setText(authorText);

            pagenumber.setText(String.valueOf(book.getNumberOfPages()));
            publish_date.setText(book.getPublishDate());

        }
    }

    public String getAuthorText(List<Author> authors) {
        if (authors != null) {
            List<String> names = new ArrayList<String>();
            for (Author author : authors) {
                names.add(author.getName());
            }
            return TextUtils.join(", ", names);
        }
        return "Unknown";
    }
}

