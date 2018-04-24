package whizkid.amaya.calculatorforlaunch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//https://www.journaldev.com/10416/android-listview-with-custom-adapter-example-tutorial

public class CalculatorCustomAdapter extends ArrayAdapter<HistoryTasks> {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<HistoryTasks> mDataSource;

    // View lookup cache
    private static class ViewHolder {
        TextView textEquation;
//        TextView txtType;
//        TextView txtVersion;
    }


    public CalculatorCustomAdapter(Context context, ArrayList<HistoryTasks> items) {
        super(context, R.layout.row_item, items);
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // override other abstract methods here


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get view for row item
        // Get the data item for this position
        HistoryTasks historyTasks = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.textEquation = (TextView) convertView.findViewById(R.id.history_list_equation);
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;

        viewHolder.textEquation.setText(historyTasks.getEquation());
//        viewHolder.txtType.setText(dataModel.getType());
//        viewHolder.txtVersion.setText(dataModel.getVersion_number());
//        viewHolder.info.setOnClickListener(this);
//        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }


    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public HistoryTasks getItem(int position) {
        return mDataSource.get(position);
    }
}
