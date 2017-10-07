package edu.uco.apatel19.p5aakashp;

import android.os.Bundle;
import android.app.Activity;

public class ContactsActivity extends Activity implements ContactsFragment.ListSelectionListener {
    public static contactsInfo[] contacts = new contactsInfo[25];
    public static String[] getLastNameArray = new String[25];
    private ContactDetailsFragment detailsFragment;
    boolean sorting = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        createContacts();
        sortContacts();

        for (int i = 0; i < contacts.length; i++){
            getLastNameArray[i] = contacts[i].getLastName();
        }

        detailsFragment = (ContactDetailsFragment) getFragmentManager().findFragmentById(R.id.Contact_Details);
    }

    @Override
    public void onListSelection(int index) {
        if (detailsFragment.getShownIndex() != index) {
            detailsFragment.showDetailsAtIndex(index);
        }
    }

//Create contacts information to be shown
    private void createContacts(){
        contacts[0] = new contactsInfo("Harry", "Hart", "(405)789-8634", "hhart@gmail.com");
        contacts[1] = new contactsInfo("Ruby", "Patterson", "(405)987-6321", "rpatterson@gmail.com");
        contacts[2] = new contactsInfo("Elaine", "Page", "(405)596-5324", "epage@gmail.com");
        contacts[3] = new contactsInfo("Catherine", "Campbell", "(405)753-9514", "ccampbell@gmail.com");
        contacts[4] = new contactsInfo("Gene", "Norris", "(405)846-8638", "gnorris@gmail.com");
        contacts[5] = new contactsInfo("Donald", "Stewart", "(405)346-8275", "dstewart@gmail.com");
        contacts[6] = new contactsInfo("Frederick", "Hunter", "(405)159-4682", "fhunter@gmail.com");
        contacts[7] = new contactsInfo("Eric", "Berry", "(405)436-7861", "eberry@yahoo.com");
        contacts[8] = new contactsInfo("James", "William", "(405)234-7592", "jwilliam@yahoo.com");
        contacts[9] = new contactsInfo("Francis", "Spencer", "(405)511-8899", "fspencer@yahoo.com");
        contacts[10] = new contactsInfo("Shane", "Bryant", "(405)321-4877", "sbryant@yahoo.com");
        contacts[11] = new contactsInfo("Travis", "Walker", "(405)5858-9871", "twalker@yahoo.com");
        contacts[12] = new contactsInfo("Rose", "Taylor", "(405)369-7845", "rtaylor@yahoo.com");
        contacts[13] = new contactsInfo("Ben", "Blake", "(405)258-8927", "bblake@yahoo.com");
        contacts[14] = new contactsInfo("Debra", "Collins", "(405)523-4682", "dcollins@live.com");
        contacts[15] = new contactsInfo("Cynthia", "Rice", "(405)789-8631", "crice@live.com");
        contacts[16] = new contactsInfo("Jackie", "Phelps", "(405)976-9511", "jphelps@live.com");
        contacts[17] = new contactsInfo("Ellen", "Bass", "(405)556-4848", "ebass@live.com");
        contacts[18] = new contactsInfo("Wallace", "Higgins", "(405)753-3121", "whiggins@live.com");
        contacts[19] = new contactsInfo("Oscar", "Perry", "(405)456-6578", "operry@live.com");
        contacts[20] = new contactsInfo("Morris", "Tyler", "(405)751-8135", "mtyler@live.com");
        contacts[21] = new contactsInfo("Derek", "Davidson", "(405)713-7779", "ddavidson@yahoo.com");
        contacts[22] = new contactsInfo("Carroll", "Lane", "(405)489-4873", "clane@yahoo.com");
        contacts[23] = new contactsInfo("Terry", "Holmes", "(405)861-2233", "tholmes@gmail.com");
        contacts[24] = new contactsInfo("Colin", "Holt", "(405)723-4869", "cholt@gmail.com");
    }

//Sort contacts by last name
    private void sortContacts(){
        while (sorting) {
            sorting = false;
            for (int i = 0; i < contacts.length - 1; i++) {
                if (contacts[i].compareTo(contacts[i + 1]) < 0) {
                    contactsInfo temp = contacts[i];
                    contacts[i] = contacts[i + 1];
                    contacts[i + 1] = temp;
                    sorting = true;
                }
            }
        }
    }
}
