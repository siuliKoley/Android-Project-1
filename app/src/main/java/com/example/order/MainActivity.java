package com.example.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText quantity;
    private TextView price;
    private EditText name;
    private Button inc;
    private Button dec;
    private Button OrderSum;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity = (EditText) findViewById(R.id.quantity);
        price = (TextView) findViewById(R.id.price);
        Button b = (Button) findViewById(R.id.button);
        inc = (Button) findViewById(R.id.buttonInc);
        dec = (Button) findViewById(R.id.buttonDec);
        name = (EditText) findViewById(R.id.name);
        OrderSum = (Button) findViewById(R.id.summery);
        quantity.setText("1");
        CheckBox cream = (CheckBox) findViewById(R.id.cream);
        CheckBox choco = (CheckBox) findViewById(R.id.choco);
        price.setMovementMethod(new ScrollingMovementMethod());  //adding scroll properties.


        OrderSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // button view
                String value = quantity.getText().toString();
                String nam = name.getText().toString();
                if (value.length() == 0) {
                    Toast.makeText(MainActivity.this, "Enter the quantity!!!", Toast.LENGTH_LONG).show();
                } else if (nam.length() == 0) {
                    Toast.makeText(MainActivity.this, "Enter the name!!!", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        Double gv = (Double.valueOf(value) * 10);
                        Double op = 0.0;
                         result = "Name: " + nam + " \nQuantity: " + value;
                        if (cream.isChecked()) {
                            result += "\nAdd:Whipped Cream";
                            op += 4.0;
                        }
                        if (choco.isChecked()) {
                            result += "\nAdd:Chocolate";
                            op += 2.0;
                        }
                        op = op * Double.valueOf(value);
                        gv += op;
                        result += "\nTotal: $" + gv.toString() + " \n Thank You";



                        price.setText(result);
                    } catch (NumberFormatException e) {
                        price.setText("Exception");

                    }
                }



            }

        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Order for "+ name.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,"Order Details:\n "+ result);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

                String val = price.getText().toString();
                if (val.length() == 0) {
                    Toast.makeText(MainActivity.this, "Fill the above details first!!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Order placed.", Toast.LENGTH_LONG).show();
                    price.setText("");
                    name.setText("");
                    quantity.setText("1");
                }
            }
        });

        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = quantity.getText().toString();
                Double nquantity = Double.valueOf(value) + 1;
                quantity.setText(nquantity.toString());
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = quantity.getText().toString();
                Double nquantity = Double.valueOf(value);
                if (nquantity == 1) {
                    Toast.makeText(MainActivity.this, "quantity cannot be less than 1!!!", Toast.LENGTH_LONG).show();
                } else {
                    nquantity = nquantity - 1;
                    quantity.setText(nquantity.toString());
                }
            }
        });
    }
}