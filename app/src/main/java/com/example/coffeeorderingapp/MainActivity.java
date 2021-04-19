package com.example.coffeeorderingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeorderingapp.R;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    boolean whippedCream = false;
    boolean chocolateTopping = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //    Checkbox whipped cream
    public void checkWhippedCream(View view) {
        if (!whippedCream) whippedCream = true;
        else whippedCream = false;
        displayPrice(calculatePrice(quantity, 10));
        Log.v("Mainactivity", "Yes, function called");
    }

    // check chocolate topping
    public void checkChocolate(View view) {
        if (!chocolateTopping) chocolateTopping = true;
        else chocolateTopping = false;
        displayPrice(calculatePrice(quantity, 10));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText name = (EditText) findViewById(R.id.customer_name);
        String CustomerName = name.getText().toString();

        String message = createOrderSummary(quantity);
//        String[] to_mail=new String[]{"madu18cs@cmrit.ac.in"};
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, to_mail);
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order Details For " + CustomerName);
//        intent.putExtra(Intent.EXTRA_TEXT, message);
////        intent.setType("text/plain");
//        startActivity(Intent.createChooser(intent, "Choose an email app:"));
            displayMessage(message);
    }

    public void incrementOrder(View view) {
        quantity += 1;
        displayQuantity(quantity);
        displayPrice(calculatePrice(quantity, 10));
    }

    public void decrementOrder(View view) {
        if (quantity >= 1)
            quantity -= 1;
        displayQuantity(quantity);
        displayPrice(calculatePrice(quantity, 10));
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is used to calculate Price @10 per cup
     */

    private int calculatePrice(int n, int price) {
        if (whippedCream == true) {
            price += 5;
        }
        if (chocolateTopping) {
            price += 5;
        }

        return n * price;
    }

    private String createOrderSummary(int quantity) {
        EditText name = (EditText) findViewById(R.id.customer_name);
        String CustomerName = name.getText().toString();
        String msg = getString(R.string.Name)+" : "+ CustomerName + "\n"+ getString(R.string.Quantity)+" : "  + quantity+ "\n"+getString(R.string.Toppings) +"\n";
        if (whippedCream) msg += "\t\t\t\t"+getString(R.string.whipped_cream)+ " : "+getString(R.string.yes)+"\n";
        else msg += "\t\t\t\t"+getString(R.string.whipped_cream)+ " : "+getString(R.string.no)+"\n";

        if (chocolateTopping) msg += "\t\t\t\t"+getString(R.string.chocolate)+ " : " +getString(R.string.yes)+"\n";
        else msg += "\t\t\t\t"+getString(R.string.chocolate)+ " : " +getString(R.string.no)+"\n";

        msg += "\n"+getString(R.string.price)+": ₹" + calculatePrice(quantity, 10) + "\n"+getString(R.string.thankyou);

        return msg;
    }
}