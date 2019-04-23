package hongkhanh.on_tap1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterListView extends BaseAdapter {
    ArrayList<Model> arrayList;
    Context context;

    public AdapterListView(ArrayList<Model> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
        if(view == null){
            view = View.inflate(context,R.layout.item_list_view,null);
            holder = new ViewHolder();
            holder.ivAvatar = view.findViewById(R.id.iv_avatar);
            holder.tvAge = view.findViewById(R.id.tv_age);
            holder.tvName = view.findViewById(R.id.tv_name);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        int image = context.getResources().getIdentifier(arrayList.get(i).getAvater(),"drawable",context.getPackageName());
        holder.ivAvatar.setImageResource(image);
        holder.tvName.setText(arrayList.get(i).getUssername());
        holder.tvAge.setText(arrayList.get(i).getAge());
        return view;
    }

    class ViewHolder{
        ImageView ivAvatar;
        TextView tvName, tvAge;
    }
}
