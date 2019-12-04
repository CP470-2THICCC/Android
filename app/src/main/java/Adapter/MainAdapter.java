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




/**
 * @author Luka Sitas
 * Basic view holder for the RecyclerView Editor
 */
class editViewHolder extends RecyclerView.ViewHolder {

    public editViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}



/**@author Luka Sitas
 * Basic view holder for the RecyclerView Profile View
 * */
class profileViewHolder extends RecyclerView.ViewHolder {

    public profileViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}



/**@author Luka Sitas
 *
 * Basic view holder for the RecyclerView Nutrition View
 * */
class nutritionViewHolder extends RecyclerView.ViewHolder {

    public nutritionViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}




/**@author Luka Sitas
 * Basic view holder for the RecyclerView Fitness View
 * */
class fitnessViewHolder extends RecyclerView.ViewHolder {

    public fitnessViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}


/**@author Luka Sitas
 *
 */

/**
 * The main adapter for the RecyclerView
 * Extends the base RecyclerView.Adapter
 * */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    /**
     * Name of Activity
     * */
    protected static final  String ACTIVITY_NAME= "MainAdapter";
    /**
     * Profile constant val = 0
     * */
    public static final int PROFILE = 0;
    /**
     * Nutrition constant val = 1
     * */
    public static final int NUTRITION = 1;
    /**
     * Fitness constant val = 2
     * */
    public static final int FITNESS = 2;
    /**
     * Edit constant val = -1
     * */
    public static final int EDIT = -1;
    ArrayList<Integer> items = new ArrayList<>();
    Context context;

    /**
     * Constructor for the adapter
     * Requires an ArrayList<Integer>
     * Values must be values of constants.
     * */
    public MainAdapter(ArrayList<Integer> list) {

        this.items = list;
        Log.i(ACTIVITY_NAME,"in init");
        for(int i = 0;i < items.size();i++)
            Log.d(ACTIVITY_NAME,"item " + i + ": " + items.get(i));
    }

    /**
     * @return
     * Returns the view type (int) of the view at position in the list of views.
     * */
    @Override
    public int getItemViewType(int position) {
        Log.i(ACTIVITY_NAME,"in getItemViewType val:" + items.get(position));
        return items.get(position);

    }


    /**
     * @return
     * ViewHolder for the specified viewType
     * */
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

            case EDIT:
                Log.i(ACTIVITY_NAME,"creating Editor");
                view = inflater.inflate(R.layout.edit_widget,parent,false);
                return new editViewHolder(view);


            default:
                return null;
        }
    }


    /**
     * Binds ViewHolder to the RecyclerView
     * */
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

            case EDIT:
                Log.i(ACTIVITY_NAME,"binding Editor");
                editViewHolder eViewHolder = (editViewHolder) holder;
                eViewHolder.setIsRecyclable(false);
                break;

            default:
                break;
        }
    }



    /**
     * @param itemCode
     * Adds a new item to the RecyclerView in the position before the editor.
     * */
    public void addNewItem(int itemCode) {
        Log.i(ACTIVITY_NAME, "in addNewItem");
        if (itemCode <= FITNESS && itemCode >= PROFILE) {
            this.items.add(this.items.size() - 1, itemCode);

        }
    }
    /**
     * Removes the item before the editor.
     * */
    public void removeLastItem(){
        Log.i(ACTIVITY_NAME,"in removeLastItem");
        if(this.items.size()>1){
            this.items.remove(this.items.size()-2);
        }
    }

    /**
     * @return
     * Returns the number of items in the RecyclerView.
     * */
    @Override
    public int getItemCount() {
        Log.i(ACTIVITY_NAME,"in getItemCount");
        return items.size();
    }
}
