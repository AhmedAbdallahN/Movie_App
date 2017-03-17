package com.example.ahmed.movie_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class mobileView extends Fragment {

    Movie movie;
    List<tralier> traliers;
    listAdapter traliersAdapter;
    List<review> reviews;
    ListView listView;
    ListView reviewList;
    reviewAdapter reviewAdapter;
    Database db;
    public static Boolean favorite;
    View page;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new Database(getContext());
        page = inflater.inflate(R.layout.fragment_mobile_view, container, false);
ImageView favorite = (ImageView) page.findViewById(R.id.favoriteImage);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new Database(getContext());
                ImageView favoriteImage = (ImageView) page.findViewById(R.id.favoriteImage);
                if (mobileView.favorite) {
                    db.deleteMovie(movie);
                    favoriteImage.setImageResource(R.drawable.hnd5a);
                    Toast toast = Toast.makeText(getContext(), "Unfavored", Toast.LENGTH_SHORT);
                    toast.show();
                    mobileView.favorite = false;
                    if (gridFragment.favorite) {
                        refresh x = (refresh) getActivity();
                        x.refresh();
                    }
                } else {
                    db.addMovie(movie);
                    favoriteImage.setImageResource(R.drawable.toecu);
                    Toast toast = Toast.makeText(getContext(), "Favorite", Toast.LENGTH_SHORT);
                    toast.show();
                    mobileView.favorite = true;
                    if (gridFragment.favorite) {
                        refresh x = (refresh) getActivity();
                        x.refresh();
                    }
                }

            }
        });
        return page;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!MainActivity.large && !MainActivity.xlarge){

            movie = (Movie) getArguments().getSerializable("movie");
            updateMovie(movie);
        }
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    public void updateMovie(Movie movie) {
        db = new Database(getContext());
        this.movie = movie;
        TextView yearView = (TextView) page.findViewById(R.id.yearView);
        TextView durationView = (TextView) page.findViewById(R.id.durationView);
        TextView descreptionView = (TextView) page.findViewById(R.id.descretionView);
        TextView rateView = (TextView) page.findViewById(R.id.rateView);
        ImageView imageView = (ImageView) page.findViewById(R.id.imageView2);
        TextView titleView = (TextView) page.findViewById(R.id.titleView);

        Picasso.with(getContext()).load(movie.getImageId()).into(imageView);
        yearView.setText("" + movie.getYear());
        rateView.setText(movie.getRate() + "/10");
        descreptionView.setText(movie.getDescription().toString());
        durationView.setText(movie.getDuration() + "min");
        titleView.setText(movie.getTitle().toString());


        listView = (ListView) page.findViewById(R.id.listView);

        backgroundTask task = new backgroundTask() {
            @Override
            protected void onPostExecute(String jsonResult) {
                jsonParsing parsing = new jsonParsing();
                traliers = parsing.traliersParsing(jsonResult);
                traliersAdapter = new listAdapter(getContext(), R.layout.list_item, traliers);
                listView.setAdapter(traliersAdapter);
            }
        };
        task.execute("https://api.themoviedb.org/3/movie/" + movie.getId() + "/videos?api_key=dc803b5dbdfccad4ec7f127673c2821b&language=en-US");
        listView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        setListViewHeightBasedOnChildren(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = "https://www.youtube.com/watch?v=" + traliers.get(position).getKey();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


        reviewList = (ListView) page.findViewById(R.id.reviewsList);
        backgroundTask getReviews = new backgroundTask() {
            @Override
            protected void onPostExecute(String jsonResult) {
                jsonParsing parsing = new jsonParsing();
                reviews = parsing.reviewsParsing(jsonResult);
                reviewAdapter = new reviewAdapter(getContext(), R.id.reviewsList, reviews);
                reviewList.setAdapter(reviewAdapter);

            }
        };
        getReviews.execute("https://api.themoviedb.org/3/movie/" + movie.getId() + "/reviews?api_key=dc803b5dbdfccad4ec7f127673c2821b&language=en-US");

        ImageView favoriteImage = (ImageView) page.findViewById(R.id.favoriteImage);
        if (db.checkFavorite(movie.getId())) {
            favoriteImage.setImageResource(R.drawable.toecu);
            favorite = true;
        } else {
            favorite = false;
            favoriteImage.setImageResource(R.drawable.hnd5a);
        }

    }

    public interface refresh{
        public void refresh();
    }

}
