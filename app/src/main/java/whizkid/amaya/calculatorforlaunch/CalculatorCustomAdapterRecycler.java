package whizkid.amaya.calculatorforlaunch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//https://www.journaldev.com/10416/android-listview-with-custom-adapter-example-tutorial

public class CalculatorCustomAdapterRecycler extends
        RecyclerView.Adapter<CalculatorCustomAdapterRecycler.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<HistoryTasks> mDataSource;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CalculatorCustomAdapterRecycler(ArrayList<HistoryTasks>  myDataset) {
        mDataSource = myDataset;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public CalculatorCustomAdapterRecycler.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataSource.get(position).getEquation());

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

//    // View lookup cache
//    private static class ViewHolder {
//        TextView textEquation;
//        TextView textResult;
////        TextView txtType;
////        TextView txtVersion;
//    }


//    public CalculatorCustomAdapterRecycler(Context context, ArrayList<HistoryTasks> items) {
//        super(context, R.layout.row_item, items);
//        mContext = context;
//        mDataSource = items;
//        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }

    // override other abstract methods here


//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//
//        // Get view for row item
//        // Get the data item for this position
//        HistoryTasks historyTasks = getItem(position);
//        // Check if an existing view is being reused, otherwise inflate the view
//        ViewHolder viewHolder; // view lookup cache stored in tag
//
//        final View result;
//
//        if (convertView == null) {
//
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.row_item, parent, false);
//            viewHolder.textEquation = (TextView) convertView.findViewById(R.id.history_list_equation);
//            viewHolder.textResult = (TextView) convertView.findViewById(R.id.history_list_result);
//
//            result = convertView;
//
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//            result = convertView;
//        }
//
////        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
////        result.startAnimation(animation);
////        lastPosition = position;
//
//        viewHolder.textEquation.setText(historyTasks.getEquation());
//        viewHolder.textResult.setText(Utils.colourMyText(historyTasks.getResult(), Utils.getPreferenceColor()));
//
////        viewHolder.txtType.setText(dataModel.getType());
////        viewHolder.txtVersion.setText(dataModel.getVersion_number());
////        viewHolder.info.setOnClickListener(this);
////        viewHolder.info.setTag(position);
//        // Return the completed view to render on screen
//        return convertView;
//    }
//

//    @Override
//    public int getCount() {
//        return mDataSource.size();
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public HistoryTasks getItem(int position) {
//        return mDataSource.get(position);
//    }


}
