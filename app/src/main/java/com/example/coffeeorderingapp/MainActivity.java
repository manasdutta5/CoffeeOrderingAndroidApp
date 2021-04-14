package com.example.coffeeorderingapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    boolean chocolateTopping=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //    Checkbox whipped cream
    public void checkWhippedCream(View view){
        if(!whippedCream) whippedCream = true;
        else whippedCream = false;
        displayPrice(calculatePrice(quantity,10));
        Log.v("Mainactivity","Yes, function called");
    }
    // check chocolate topping
    public void checkChocolate(View view){
        if(!chocolateTopping) chocolateTopping = true;
        else chocolateTopping = false;
        displayPrice(calculatePrice(quantity,10));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayMessage(createOrderSummary(quantity));
    }

    public void incrementOrder(View view) {
        quantity += 1;
        displayQuantity(quantity);
        displayPrice(calculatePrice(quantity,10));
    }

    public void decrementOrder(View view) {
        if(quantity>=1)
            quantity -= 1;
        displayQuantity(quantity);
        displayPrice(calculatePrice(quantity,10));
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
     *  This method is used to calculate Price @10 per cup
     */

    private int calculatePrice(int n,int price) {
        if(whippedCream==true)
        {
            price+=5;
        }
        if(chocolateTopping)
        {
            price+=5;
        }

        return n*price;
    }

    private String createOrderSummary(int quantity)
    {
        String msg="Name: Momo\nQuantity: " + quantity +"\nToppings:\n";
        if(whippedCream) msg+="\t\t\t\tWhipped Cream: Yes\n";
        else msg+="\t\t\t\tWhipped Cream: No\n";

        if(chocolateTopping) msg+="\t\t\t\tChocolate: Yes\n";
        else msg+="\t\t\t\tChocolate: No\n";

        msg+="\nPrice: ₹" +calculatePrice(quantity,10) + "\nThank You.";
        return msg;
    }
}