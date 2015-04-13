package ie.dit.android.dit;



import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    List<Contacts> contacts;

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView contactName;
        public TextView contactDepartment;
        public TextView contactEmail;
        public TextView contactPhoneNo;

        public ViewHolder(View itemView) {
            super(itemView);
            this.contactName = (TextView)itemView.findViewById(R.id.contactNameText);
            this.contactDepartment = (TextView)itemView.findViewById(R.id.contactDepartmentText);
            this.contactEmail = (TextView)itemView.findViewById(R.id.contactEmailText);
            this.contactPhoneNo = (TextView)itemView.findViewById(R.id.contactPhoneNoText);

        }
    }

    public ContactsAdapter(){  //List<Contacts> contacts
        super();

        //this.contacts = contacts;

        //TEST VALUES

        contacts = new ArrayList<>();
        Contacts c = new Contacts();
        c.setName("Name 1");
        c.setDepartment("Dep 1");
        c.setEmail("sdadad@yahooo.com");
        c.setLocation("Location 1");
        c.setPhoneNo("3232322323");

        contacts.add(c);




    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_contacts_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contacts singleContact = contacts.get(position);

        holder.contactName.setText(singleContact.getName());
        holder.contactDepartment.setText(singleContact.getDepartment());
        holder.contactEmail.setText(singleContact.getEmail());
        holder.contactPhoneNo.setText(singleContact.getPhoneNo());

        Log.d("DATA","Name: " + singleContact.getName());
        Log.d("DATA","Department: " + singleContact.getDepartment());
        Log.d("DATA","Email: " + singleContact.getEmail());
        Log.d("DATA","Phone: " + singleContact.getPhoneNo());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }




}
