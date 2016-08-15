package sunil.project3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvAdapter extends RecyclerView.Adapter<MainRvViewHolder>{
    public ArrayList<String> list;
    public MainRvAdapter(ArrayList<String> list) {
        this.list = list;
    }


    @Override
    public MainRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        MainRvViewHolder viewHolder = new MainRvViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MainRvViewHolder holder, int position) {
        holder.mTextView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
