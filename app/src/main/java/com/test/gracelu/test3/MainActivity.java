package com.test.gracelu.test3;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.constants.TransactionType;
import com.reimaginebanking.api.nessieandroidsdk.models.Account;
import com.reimaginebanking.api.nessieandroidsdk.models.Customer;
import com.reimaginebanking.api.nessieandroidsdk.models.Transfer;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NessieClient client = NessieClient.getInstance("");
        final String[] arr = {String.valueOf(R.id.d1), String.valueOf(R.id.d2)};
        client.CUSTOMER.getCustomer("581e61b7360f81f1045476d5", new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                Customer anita = (Customer) result;
                String cusid = anita.getId(); //Gets her customer number
                client.ACCOUNT.getCustomerAccounts(cusid, new NessieResultsListener() {

                    @Override
                    public void onSuccess(Object result) { //result is list of all accounts of anita
                        //Account acc = (Account) result;
                        result = (Account[]) result;
                        Account c = null;//checking
                        Account s = null;//saving
                        for (Account ob : (Account[]) result) {
                            Account temp = (Account) ob;
                            if (temp.getType().toString().equals("Checking")) {
                                c = (Account) ob;//this is just a pointer ugh

                            }
                            if (temp.getType().toString().equals("Savings")) {
                                s = (Account) ob; //ugh
                            }
                        }

                        int bal = c.getBalance();
                        int sumtosave = 2; // figure out later
                        String curr = "Dunkin Donuts: 1107 19th St NW, Washington, DC, 20036"; //Our current location
                        if (bal - sumtosave > 0 && getLoc(arr, curr)) { //If they have $ in account and in location
                            bal = bal- sumtosave;
                            Transfer trans = null;
                            //if (c.getId() != null && s.getId() != null)
                                 //trans = new Transfer("581e61b7360f81f1045476d5", "deposit", "2016-11-06", "pending", "balance", c.getId(), s.getId(), 2.0);

                            client.TRANSFER.createTransfer(c.getId(), trans, new NessieResultsListener() {
                                @Override
                                public void onSuccess(Object result) {
                                    Log.d("success", result.toString(), null);
                                }

                                @Override
                                public void onFailure(NessieError error) {
                                    Log.d("error", error.getMessage(), null);
                                }
                            });


                        }
                    }

                    @Override
                    public void onFailure(NessieError error) {
                        Log.d("error", error.getMessage(), null);
                    }
                });

            }

            @Override
            public void onFailure(NessieError error) {
                Log.d("error", error.getMessage(), null);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private boolean getLoc(String[] arr, String curr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(curr))
                return true;
        }
        return false;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        AppIndex.AppIndexApi.start(client2, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client2, getIndexApiAction());
        client2.disconnect();
    }
}
