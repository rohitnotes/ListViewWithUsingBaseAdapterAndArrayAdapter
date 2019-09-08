package both.base.array.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<ItemModel> {

    private LayoutInflater layoutInflater;
    private int lastPosition = -1;

    public CustomArrayAdapter(Context context, ArrayList<ItemModel> itemModelArrayList) {
        super(context, 0 , itemModelArrayList);
        layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemViewHolder itemViewHolder;

        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
              /*
            OR
            convertView = layoutInflater.inflate(R.layout.layout_list_item,null);
             */
            itemViewHolder = new ItemViewHolder(convertView);
            convertView.setTag(itemViewHolder);
        }
        else
        {
            itemViewHolder = (ItemViewHolder) convertView.getTag();
        }

        Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        convertView.startAnimation(animation);
        lastPosition = position;

        ItemModel rowItem = (ItemModel) getItem(position);
        ((ItemViewHolder)itemViewHolder).setData(rowItem);
        return convertView;
    }

    private class ItemViewHolder
    {
        TextView tvTitle, tvDesc;
        ImageView ivIcon;

        public ItemViewHolder(View item)
        {
            tvTitle = (TextView) item.findViewById(R.id.tvTitle);
            tvDesc  = (TextView) item.findViewById(R.id.tvDesc);
            ivIcon  = (ImageView) item.findViewById(R.id.ivIcon);
        }

        public void setData(ItemModel itemPosition)
        {
            tvTitle.setText(itemPosition.getTitle());
            tvDesc.setText(itemPosition.getDescription());
            ivIcon.setImageResource(itemPosition.getImgResId());
        }
    }
}
