package com.test.gracelu.test3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static ImageButton FAB = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NessieClient client = NessieClient.getInstance("f1e4a8e47992316ea823dd7cae9507b1");
        //final String[] arr = {String.valueOf(R.id.d1), String.valueOf(R.id.d2)};
        final TextView warn = (TextView) findViewById(R.id.warn);
        final TextView message = (TextView) findViewById(R.id.message);
        final TextView balance = (TextView) findViewById(R.id.balb);

        //Database
        DBHandler db = new DBHandler(this, null, null, 1);
        //db.removeAll();
        Log.d("Insert: ", "Inserting...");
        db.addLocation(new Location("Dunkin Donuts", "1101 14th St NW", "Washington, DC", 20005));
        db.addLocation(new Location("Dockers", "475 Brannan St #330", "San Francisco, CA", 94107));

        //Reading all locations
        Log.d("Reading: ", "Reading all locations...");
        List<Location> locations = db.getAllLocations();

        init(locations);

//        client.CUSTOMER.getCustomer("585ca0180fa692b34a9b8f08", new NessieResultsListener() {
//            @Override
//            public void onSuccess(Object result) {
//                Customer anita = (Customer) result;
//                String cusid = anita.getId(); //Gets her customer number
//                client.ACCOUNT.getCustomerAccounts(cusid, new NessieResultsListener() {
//
//                    @Override
//                    public void onSuccess(Object result) { //result is list of all accounts of anita
//                        //Account acc = (Account) result;
//                        result = (Account[]) result;
//                        Account c = null;//checking
//                        Account s = null;//saving
//                        for (Account ob : (Account[]) result) {
//                            Account temp = (Account) ob;
//                            if (temp.getType().toString().equals("Checking")) {
//                                c = (Account) ob;//this is just a pointer ugh
//
//                            }
//                            if (temp.getType().toString().equals("Savings")) {
//                                s = (Account) ob; //ugh
//                            }
//                        }
//
//                        assert c != null;
//                        double bal = c.getBalance();
//                        int sumtosave = 2; // figure out later
//                        String curr = "Dunkin Donuts: 1107 19th St NW, Washington, DC, 20036"; //Our current location
//                        String currB = "Your checking balance $: " + bal;
//                        balance.setText(String.format("%s", currB));
//                        //balance.appendText("" +bal);
//                        //balance.setText("I am setting text at balance")
//                        if (bal - sumtosave > 0 && getLoc(arr, curr)) { //If they have $ in account and in location
//                            bal = bal- sumtosave;
//                            //Send Warning in App that in black-listed location
//                            warn.setText(getString(R.string.warning)); //Warning!
//
//                            Transfer.Builder temp = new Transfer.Builder();
//                            assert s != null;
//                            Transfer trans = temp.transactionDate("2016-11-06")
//                                    .medium(TransactionMedium.BALANCE)
//                                    .payeeId(s.getId())
//                                    .amount(2.0)
//                                    .description("Transferring money")
//                                    .build();
//
////                            Transfer trans1 = new Transfer(BATTERY_SERVICE)
////                            //if (c.getId() != null && s.getId() != null)
////                                 //trans = new Transfer("581e61b7360f81f1045476d5", "deposit", "2016-11-06", "pending", "balance", c.getId(), s.getId(), 2.0);
//
//                            client.TRANSFER.createTransfer(c.getId(), trans, new NessieResultsListener() {
//                                @Override
//                                public void onSuccess(Object result) {
//                                    Log.d("success", result.toString(), null);
//                                    message.setText(getString(R.string.success));
//                                }
//
//                                @Override
//                                public void onFailure(NessieError error) {
//                                    Log.d("error", error.getMessage(), null);
//                                    message.setText(getString(R.string.fail));
//                                }
//                            });
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(NessieError error) {
//                        Log.d("error", error.getMessage(), null);
//                    }
//                });
//
//            }
//
//            @Override
//            public void onFailure(NessieError error) {
//                Log.d("error", error.getMessage(), null);
//            }
//        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        FAB = (ImageButton) findViewById(R.id.floatingActionButton);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,AddLocActivity.class);
                startActivity(i);
            }
        });
    }

    private boolean getLoc(String[] arr, String curr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(curr))
                return true;
        }
        return false;
    }
    public void init(List<Location> locations){
        TableLayout tableLoc = (TableLayout) findViewById(R.id.tableLoc);

        for(Location location:locations){
            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView tv = new TextView(this);
            tv.setText(location.getName()+", " + location.getStreet() + ", " + location.getCity()
                + ", " + location.getZipcode());
            row.addView(tv);
            tableLoc.addView(row);
        }
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
