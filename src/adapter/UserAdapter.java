package adapter;

import java.util.List;

import com.example.sales.control.R;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import model.User;

public class UserAdapter extends BaseAdapter {
	
	private Context context;
	private List<User> listUsers;
	
	public UserAdapter(Context ctx, List<User> users) {
		// TODO Auto-generated constructor stub
		this.context = ctx;
		this.listUsers = users;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listUsers.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listUsers.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		User user = listUsers.get(position);
		
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.users_list, null);
		}
		TextView txtName = (TextView) view.findViewById(R.id.user_list_name);
		txtName.setText(user.getName());
		return view;
	}

}
