package edu.uco.apatel19.p5aakashp;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactDetailsFragment extends Fragment {
    private TextView nameView = null;
    private TextView emailView = null;
    private TextView phoneView = null;
    private int currentIndex = -1;
    private int detailsArrayLength;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nameView = (TextView) getActivity().findViewById(R.id.nameView);
        phoneView = (TextView) getActivity().findViewById(R.id.phoneView);
        emailView = (TextView) getActivity().findViewById(R.id.emailView);
        detailsArrayLength = ContactsActivity.contacts.length;

        emailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEmail();
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.contacts_details_fragment, container, false);
    }

    public int getShownIndex() {
        return currentIndex;
    }


    public void showDetailsAtIndex(int newIndex){
        if (newIndex >= 0 && newIndex <= detailsArrayLength){
            currentIndex = newIndex;
            nameView.setText("Name:\n" + ContactsActivity.contacts[currentIndex].getFirstName().toString() + " " +
                    ContactsActivity.contacts[currentIndex].getLastName().toString());
            nameView.setTextColor(Color.BLACK);
            phoneView.setText("Phone:\n" + ContactsActivity.contacts[currentIndex].getPhoneNumber().toString() + " ");
            phoneView.setTextColor(Color.BLACK);
            emailView.setText("Email:\n" + ContactsActivity.contacts[currentIndex].getEmail().toString() + " ");
            emailView.setTextColor(Color.BLACK);
        }
    }

    private void startEmail(){
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("message/rfc822");
        myIntent.putExtra(myIntent.EXTRA_EMAIL, new String[]{ContactsActivity.contacts[currentIndex].getEmail().toString()});
        myIntent.putExtra(myIntent.EXTRA_SUBJECT, "Announcement from Mobile Apps Class");
        myIntent.putExtra(myIntent.EXTRA_TEXT, "This is the body of the email. \n"
                + "This email is a test for my mobile apps programming class. \n"
                + "If I actually sent this email it was an accident.");
        try {
            startActivity(Intent.createChooser(myIntent, "Send mail"));
        }catch (android.content.ActivityNotFoundException ex){
        }
    }
}
