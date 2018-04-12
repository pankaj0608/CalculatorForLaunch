package whizkid.amaya.calculatorforlaunch;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<RowItem> rowItems;

    public CustomAdapter(Context context, List<RowItem> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }

    private class ViewHolder {
        ImageView imageViewIcon;
        TextView textViewTitle;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        LayoutInflater layoutInflater =
                (LayoutInflater)
                        context.getSystemService(
                                Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, null);

            viewHolder = new ViewHolder();
            viewHolder.imageViewIcon =
                    (ImageView) convertView.findViewById(R.id.title);
            viewHolder.textViewTitle =
                    (TextView) convertView.findViewById(R.id.title);

            RowItem rowItem =  rowItems.get(position);
            viewHolder.imageViewIcon.setImageResource(rowItem.getIcon());
            viewHolder.textViewTitle.setText(rowItem.getTitle());

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
