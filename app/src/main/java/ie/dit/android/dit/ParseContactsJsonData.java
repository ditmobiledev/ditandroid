package ie.dit.android.dit;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseContactsJsonData extends GetRawData {

    private static final String LOG_TAG = ParseContactsJsonData.class.getSimpleName();
    public static final String SERVER_URL = "http://collegboi.me/Parse/scrapContacts.php?firstName=d&lastName=d";
    List<Contacts> contacts;

    // Constructor
    public ParseContactsJsonData() {
        super(null);
        contacts = new ArrayList<>();
    }

    public void execute() {
        super.setJsonURL(SERVER_URL);
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        downloadJsonData.execute(SERVER_URL);
    }


    // Modify AsyncTask methods to make sure data can download before processing starts
    public class DownloadJsonData extends DownloadRawJsonData {
        protected void onPostExecute(String webData) {
            super.onPostExecute(webData);
            processResult();
        }

        protected String doInBackground(String... params) {
            String[] par = { SERVER_URL };
            return super.doInBackground(par);
        }
    }


    // Create a list of News objects.
    public void processResult() {
        if (getJsonDownloadStatus() != DownloadStatus.OK) {
            Log.e(LOG_TAG, "Error downloading raw file");
            return;
        }


        final String CONTACTS_ITEMS = "Contacts";
        final String CONTACTS_NAME = "name";
        final String CONTACTS_DEPARTMENT = "depart";
        final String CONTACTS_EMAIL = "email";
        final String CONTACTS_LOCATION = "location";
        final String CONTACTS_PHONE_NO = "extension";


        try {
            JSONObject jsonData = new JSONObject(getJsonData());
            JSONArray itemsArray = jsonData.getJSONArray(CONTACTS_ITEMS);

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject jsonContactsItem = itemsArray.getJSONObject(i);
                String name = jsonContactsItem.getString(CONTACTS_NAME);
                String department = jsonContactsItem.getString(CONTACTS_DEPARTMENT);
                String email = jsonContactsItem.getString(CONTACTS_EMAIL);
                String location = jsonContactsItem.getString(CONTACTS_LOCATION);
                String phoneNo = jsonContactsItem.getString(CONTACTS_PHONE_NO);
//

                Contacts contactsObject = new Contacts(name, department, email, location, phoneNo);
                this.contacts.add(contactsObject);
            }

            // Print contacts objects' data
            for (Contacts contact : contacts) {
                Log.v(LOG_TAG, contact.toString());
            }

        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed to parse JSON: " + e);
        }
    }

    public List<Contacts> getContacts() {
        return contacts;
    }
}
