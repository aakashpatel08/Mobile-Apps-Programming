package edu.uco.apatel19.p5aakashp;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ContactsFragment extends ListFragment {
    private ListSelectionListener mListener = null;

    public interface ListSelectionListener {
        void onListSelection(int index);
    }

    public void onListItemClick(ListView listView, View view, int pos, long id){
        getListView().setItemChecked(pos, true);
        mListener.onListSelection(pos);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(new ArrayAdapter<>(getActivity(), R.layout.contacts_fragment, ContactsActivity.getLastNameArray));

        try {
            mListener = (ListSelectionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement ListSelectionListener");
        }
    }
}
