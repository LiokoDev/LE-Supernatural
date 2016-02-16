package com.liokodev.supernaturalfanbase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.List;

/**
 * Created by Jake on 2/11/16.
 */
public class showAdapter extends RecyclerView.Adapter<showAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView actImage;
        TextView tTitle, tDesc, tUrl;

        ViewHolder(final View itemView) {
            super(itemView);


            actImage = (ImageView)itemView.findViewById(R.id.newsImage); // Need to change the name
            tTitle = (TextView)itemView.findViewById(R.id.TitleT); //
            //tDesc = (TextView)itemView.findViewById(R.id.DescT); //
            tUrl = (TextView)itemView.findViewById(R.id.photoUrl); // So we know what to parse.


        }

    }

    List<showData> showFeed;

    showAdapter(List<showData> showFeed) {
        this.showFeed = showFeed;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.showcard, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);

        if (MainActivity.dontLoad == 0) {
            //Load the animation from the xml file and set it to the row
            Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.push_in);

            animation.setDuration(390);
            v.startAnimation(animation);
            MainActivity.dontLoad = 1;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder ViewHolder, int i) {

        ViewHolder.tTitle.setText(showFeed.get(i).sName);
        //ViewHolder.tDesc.setText(showFeed.get(i).sShortDesc);
        ViewHolder.tUrl.setText(showFeed.get(i).sUrl);

        Ion.with(ViewHolder.actImage)
                //.smartSize(true)
                .placeholder(R.drawable.loader)
                //.error(R.drawable.error)
                .load(showFeed.get(i).sPhoto);


    }

    @Override
    public int getItemCount() {
        return showFeed.size();
    }
}
