package com.liokodev.supernaturalfanbase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

/**
 * Created by Jake on 2/11/16.
 */
public class showAdapter extends RecyclerView.Adapter<showAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {



        ViewHolder(final View itemView) {
            super(itemView);




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
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder ViewHolder, int i) {



    }

    @Override
    public int getItemCount() {
        return showFeed.size();
    }
}
