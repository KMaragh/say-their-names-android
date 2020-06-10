package com.blm.saytheirnames.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.blm.saytheirnames.R;
import com.blm.saytheirnames.adapters.HashtagAdapter;
import com.blm.saytheirnames.models.Donation;
import com.blm.saytheirnames.models.Hashtag;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import com.jgabrielfreitas.core.BlurImageView;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class DonationDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    String banner, outcome, title, desc;

    private BlurImageView blurImageView;
    private ImageView donationImage,close;
    private TextView donationTitle, subTitle, donationDesc;
    private Button donationButton;
    private View progress;
    private Toolbar toolbar;

    Donation donation;

    private RecyclerView hashTagRecycler;
    private HashtagAdapter hashtagAdapter;
    private ArrayList<Hashtag> hashtagList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_details);

        initView();
        getIntentData();
//        hashTags();
    }

    void initView() {
        blurImageView = findViewById(R.id.blurImageView);
        donationImage = findViewById(R.id.actual_image);
        donationTitle = findViewById(R.id.donation_title);
        subTitle = findViewById(R.id.sub_title);
        donationDesc = findViewById(R.id.donation_desc);
        hashTagRecycler = findViewById(R.id.hash_tag_recycler);
    }

    void getIntentData() {
        Intent intent = getIntent();

        banner = intent.getStringExtra("banner");
        outcome = intent.getStringExtra("outcome");
        title = intent.getStringExtra("title");
        desc = intent.getStringExtra("desc");

        Glide.with(this)
                .load(banner)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.blm2)
                        .error(R.drawable.blm2))
                .apply(bitmapTransform(new BlurTransformation(22, 5)))
                .into(blurImageView);

        Glide.with(this)
                .load(outcome)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.blm2)
                        .error(R.drawable.blm2))
                .into(donationImage);

        donationTitle.setText(title);
        donationDesc.setText(desc);
    }

//    void hashTags() {
//        hashtagList = new ArrayList<>();
//        hashtagAdapter = new HashtagAdapter((HashtagAdapter.HashtagListener) this, hashtagList);
//        hashTagRecycler.setAdapter(hashtagAdapter);
//    }
}
