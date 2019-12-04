package Adapter;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
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
    protected static final  String ACTIVITY_NAME= "MainAdapter";
    public static final int PROFILE = 0;
    public static final int NUTRITION = 1;
    public static final int FITNESS = 2;
    ArrayList<Integer> items = new ArrayList<>();
    Context context;

    public MainAdapter(ArrayList<Integer> list) {

        this.items = list;
        Log.i(ACTIVITY_NAME,"in init");
        for(int i = 0;i < items.size();i++)
            Log.d(ACTIVITY_NAME,"item " + i + ": " + items.get(i));
    }

    @Override
    public int getItemViewType(int position) {
        Log.i(ACTIVITY_NAME,"in getItemViewType val:" + items.get(position));
        return items.get(position);

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(ACTIVITY_NAME,"in onCreateViewHolder");
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;



        switch(viewType){
            case PROFILE:
                Log.i(ACTIVITY_NAME,"creating Profile");
                view = inflater.inflate(R.layout.profile_widget,parent,false);
                return new profileViewHolder(view);

            case NUTRITION:
                Log.i(ACTIVITY_NAME,"creating Nutrition");
                view = inflater.inflate(R.layout.nutrition_widget,parent,false);
                return new nutritionViewHolder(view);


            case FITNESS:
                Log.i(ACTIVITY_NAME,"creating Fitness");
                view = inflater.inflate(R.layout.fitness_widget,parent,false);
                return new fitnessViewHolder(view);


            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.i(ACTIVITY_NAME,"in onBindViewHolder");
        switch(holder.getItemViewType()){
            case PROFILE:
                Log.i(ACTIVITY_NAME,"binding Profile");
            profileViewHolder pViewHolder = (profileViewHolder)holder;
            pViewHolder.setIsRecyclable(false);
            break;
            case NUTRITION:
                Log.i(ACTIVITY_NAME,"binding Nutrition");
                nutritionViewHolder nViewHolder = (nutritionViewHolder)holder;
                nViewHolder.setIsRecyclable(false);
                break;
            case FITNESS:
                Log.i(ACTIVITY_NAME,"binding Fitness");
                fitnessViewHolder fViewHolder = (fitnessViewHolder) holder;
                fViewHolder.setIsRecyclable(false);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        Log.i(ACTIVITY_NAME,"in getItemCount");
        return items.size();
    }
}
