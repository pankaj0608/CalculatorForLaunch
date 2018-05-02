package whizkid.amaya.calculatorforlaunch;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

//https://www.journaldev.com/10416/android-listview-with-custom-adapter-example-tutorial

//implements View.OnClickListener
public class CalculatorCustomAdapterRecycler extends
        RecyclerView.Adapter<CalculatorCustomAdapterRecycler.ViewHolder> implements View.OnClickListener {

    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec

    //    private Context mContext;
//    private LayoutInflater mInflater;
    private ArrayList<HistoryTasks> mDataSource = new ArrayList<HistoryTasks>();
    private ArrayList<HistoryTasks> itemsPendingRemoval = new ArrayList<HistoryTasks>();

    private TextView editTextEquation;
    private AlertDialog alertDialogObject;
    private boolean undoOn = true;

    private Handler handler = new Handler(); // hanlder for running delayed runnables
    HashMap<HistoryTasks, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a removal if need be


    public void setBaseEssentials(TextView editTextEquation, AlertDialog alertDialogObject) {
        this.editTextEquation = editTextEquation;
        this.alertDialogObject = alertDialogObject;
    }

    @Override
    public void onClick(View v) {
        System.out.println("v " + v.getClass());
        System.out.println("v index " + mDataSource.indexOf(v));
        System.out.println("v equation " + ((TextView) v.findViewById(R.id.history_list_equation)).getText());
        System.out.println("v result " + ((TextView) v.findViewById(R.id.history_list_result)).getText());

        String strName = ((TextView) v.findViewById(R.id.history_list_equation)).getText().toString();
        editTextEquation.setText(Utils.correctEquation(strName));
        alertDialogObject.dismiss();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView textEquation;
        TextView textResult;
        Button undoButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            textEquation = (TextView) itemView.findViewById(R.id.history_list_equation);
            textResult = (TextView) itemView.findViewById(R.id.history_list_result);
            undoButton = (Button) itemView.findViewById(R.id.history_undo_button);

            itemView.setOnClickListener(CalculatorCustomAdapterRecycler.this);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CalculatorCustomAdapterRecycler(ArrayList<HistoryTasks> myDataset) {
        mDataSource = myDataset;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public CalculatorCustomAdapterRecycler.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View historyView = inflater.inflate(R.layout.row_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(historyView);

        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CalculatorCustomAdapterRecycler.ViewHolder viewHolder, int position) {

        final HistoryTasks item = mDataSource.get(position);

        // Get the data model based on position
        HistoryTasks historyTasks = mDataSource.get(position);

        // Set item views based on your views and data model
        TextView textViewEquation = viewHolder.textEquation;
        textViewEquation.setText(historyTasks.getEquation());

        TextView textViewResult = viewHolder.textResult;
        textViewResult.setText(Utils.colourMyText(historyTasks.getResult(), Utils.getPreferenceColor()));

        if (itemsPendingRemoval.contains(item)) {
            // we need to show the "undo" state of the row
            viewHolder.itemView.setBackgroundColor(Utils.getColorFromResourceId(R.color.pankaj_theme_dark_red));//Color.RED
            viewHolder.textEquation.setVisibility(View.GONE);
            viewHolder.textResult.setVisibility(View.GONE);
            viewHolder.undoButton.setVisibility(View.VISIBLE);
            viewHolder.undoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // user wants to undo the removal, let's cancel the pending task
                    Runnable pendingRemovalRunnable = pendingRunnables.get(item);
                    pendingRunnables.remove(item);
                    if (pendingRemovalRunnable != null)
                        handler.removeCallbacks(pendingRemovalRunnable);
                    itemsPendingRemoval.remove(item);
                    // this will rebind the row in "normal" state
                    notifyItemChanged(mDataSource.indexOf(item));
                }
            });
        } else {
            // we need to show the "normal" state
            viewHolder.itemView.setBackgroundColor(Color.WHITE);
            viewHolder.textEquation.setVisibility(View.VISIBLE);
            viewHolder.textResult.setVisibility(View.VISIBLE);
            viewHolder.undoButton.setVisibility(View.GONE);
            viewHolder.undoButton.setOnClickListener(null);
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSource.size();
    }


    public void remove(int position) {
        mDataSource.remove(position);
        Utils.removeHistorySingleData(position);
        notifyItemRemoved(position);

        if (Utils.getHistoryData() == null || Utils.getHistoryData().size() == 0) {
            alertDialogObject.getButton(AlertDialog.BUTTON_NEGATIVE).setVisibility(View.GONE);
            TextView listViewFillerText = alertDialogObject.findViewById(R.id.listViewFillerText);
            listViewFillerText.setText(R.string.no_history_data);
            listViewFillerText.setTextColor(Utils.getPreferenceColorFromColourResources());
        }
    }


    public void restoreItem(HistoryTasks item, int position) {
        mDataSource.add(position, item);
        notifyItemInserted(position);
    }


    public void setUndoOn(boolean undoOn) {
        this.undoOn = undoOn;
    }

    public boolean isUndoOn() {
        return undoOn;
    }

    public boolean isPendingRemoval(int position) {
        HistoryTasks item = mDataSource.get(position);
        return itemsPendingRemoval.contains(item);
    }

    public void pendingRemoval(int position) {
        final HistoryTasks item = mDataSource.get(position);
        if (!itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.add(item);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(mDataSource.indexOf(item));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(item, pendingRemovalRunnable);
        }
    }

}
