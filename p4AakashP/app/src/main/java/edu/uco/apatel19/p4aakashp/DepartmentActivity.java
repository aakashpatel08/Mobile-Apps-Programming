package edu.uco.apatel19.p4aakashp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DepartmentActivity extends Activity {
    private ActionMode.Callback actionModeCallback;
    private ActionMode actionMode;
    Department[] departments = new Department[7];
    String[] departmentNames = new String[7];
    private ListView listView;
    int selectedDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        listView = (ListView) findViewById(R.id.Department_List_View);

        listView.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.item_view, departmentNames));


        departments[0] = new Department("Biology", "4059745017", "http://www.uco.edu/cms/biology/index.asp");
        departments[1] = new Department("Engineering and Physics", "4059742000", "http://www.uco.edu/cms/engineering/");
        departments[2] = new Department("Funeral Services", "4059745001", "http://www.uco.edu/cms/funeral/index.asp");
        departments[3] = new Department("Mathematics and Statistics", "4059745012", "http://www.math.uco.edu/");
        departments[4] = new Department("Nursing", "4059745000", "http://www.uco.edu/cms/nursing/index.asp");
        departments[5] = new Department("Chemistry", "4059745018", "http://www.uco.edu/cms/chemistry/index.asp");
        departments[6] = new Department("Computer Science", "4059745716", "http://cs.uco.edu/www/");
        for (int i = 0; i < 7; i++) {
            departmentNames[i] = this.departments[i].getName();
        }

        actionModeCallback = new ActionModeCallback();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDepartment = position;
                if (actionMode != null){
                    return false;
                }
                actionMode = startActionMode(actionModeCallback);
                return true;
            }
        });
    }

    private class ActionModeCallback implements ActionMode.Callback{

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.Call_Menu:
                    Intent myIntent = new Intent(Intent.ACTION_DIAL);
                    myIntent.setData(Uri.parse("tel:" + departments[selectedDepartment].getPhonenumber().toString()));
                    mode.finish();
                    startActivity(myIntent);
                    return true;
                case R.id.Website_Menu:
                    Intent myIntent2 = new Intent(Intent.ACTION_VIEW);
                    myIntent2.setData(Uri.parse(departments[selectedDepartment].getWebsite().toString()));
                    mode.finish();
                    startActivity(myIntent2);
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    }
}
