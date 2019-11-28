package Adapter;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2thiccc.R;

import java.util.ArrayList;
import java.util.List;

class profileViewHolder extends RecyclerView.ViewHolder {

    public profileViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}




class nutritionViewHolder extends RecyclerView.ViewHolder {

    public nutritionViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}





class fitnessViewHolder extends RecyclerView.ViewHolder {

    public fitnessViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}



public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int PROFILE = 0;
    public static final int NUTRITION = 1;
    public static final int FITNES = 2;
    int[] items;
    Context context;

    public MainAdapter(int[] list) {
        this.items = list;
    }

    @Override
    public int getItemViewType(int position) {
        return items[position];

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;



        switch(viewType){
            case PROFILE:
                view = inflater.inflate(R.layout.profile_widget,parent,false);
                return new profileViewHolder(view);

            case NUTRITION:
                view = inflater.inflate(R.layout.nutrition_widget,parent,false);
                return new nutritionViewHolder(view);


            case FITNES:
                view = inflater.inflate(R.layout.fitness_widget,parent,false);
                return new fitnessViewHolder(view);


            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch(holder.getItemViewType()){
            case PROFILE:
            profileViewHolder pViewHolder = (profileViewHolder)holder;
            pViewHolder.setIsRecyclable(false);
            break;
            case NUTRITION:
                nutritionViewHolder nViewHolder = (nutritionViewHolder)holder;
                nViewHolder.setIsRecyclable(false);
                break;
            case FITNES:
                fitnessViewHolder fViewHolder = (fitnessViewHolder) holder;
                fViewHolder.setIsRecyclable(false);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}
