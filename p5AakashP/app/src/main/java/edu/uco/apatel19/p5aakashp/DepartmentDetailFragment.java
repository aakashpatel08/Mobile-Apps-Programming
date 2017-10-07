package edu.uco.apatel19.p5aakashp;

import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DepartmentDetailFragment extends DialogFragment implements AdapterView.OnItemClickListener {
    private ListView listview_department;
    private Department[] departments = {
            new Department("Biology","http://www.uco.edu/cms/biology/"),
            new Department("Engineering and Physics","http://www.uco.edu/cms/engineering/"),
            new Department("Funeral Services","http://www.uco.edu/cms/funeral/index.asp"),
            new Department("Mathematics and Statistics","http://www.math.uco.edu"),
            new Department("Nursing","http://www.uco.edu/cms/nursing/"),
            new Department("Chemistry","http://www.uco.edu/cms/chemistry/"),
            new Department("Computer Science","http://cs.uco.edu/www/"),
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_department, null, false);
        listview_department = (ListView) view.findViewById(R.id.Department_ListView);

        getDialog().getWindow().setTitle("Pick a Department");
        getDialog().getContext().setTheme(R.style.DialogTheme);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstance){
        super.onActivityCreated(savedInstance);

        ArrayAdapter<Department> adapter = new ArrayAdapter<>(getActivity(), R.layout.department_layout, R.id.Department_TextView, departments);
        listview_department.setAdapter(adapter);
        listview_department.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String website = departments[position].getWebsite();

        Intent myIntent = new Intent(Intent.ACTION_VIEW);
        myIntent.setData(Uri.parse(website));
        PendingIntent pIntent = PendingIntent.getActivity(super.getActivity(), 0, myIntent, 0);

        Notification n  = new Notification.Builder(super.getActivity())
                .setContentTitle(departments[position].getDepartment() + " selected.")
                .setContentText("You have been notified.")
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentIntent(pIntent)
                .setAutoCancel(true).build();


        NotificationManager notificationManager =
                (NotificationManager) super.getActivity().getSystemService(super.getActivity().NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }
}
