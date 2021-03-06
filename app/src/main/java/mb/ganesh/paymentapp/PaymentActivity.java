package mb.ganesh.paymentapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.razorpay.Checkout;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity {

    private String KeyId = "rzp_test_NUARedGCgPlYjc";
    private String KeySecret = "NbbjnMUllUtABJaFxZG2Mc1c";

    MaterialButton buyNowBtn;
    MaterialCardView card1mo, card3mo, card12mo;
    MaterialCardView tempCard;
    String valueToPay = "101";
    int amount;
    boolean isCardSelected = false;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //init
        buyNowBtn = findViewById(R.id.buyNowBtnId);
        card1mo = findViewById(R.id.card1moId);
        card3mo = findViewById(R.id.card3moId);
        card12mo = findViewById(R.id.card12moId);

        //init values

        amount = Math.round(Float.parseFloat(valueToPay));
        buyNowBtn.setVisibility(View.GONE);



        //add click listener
        card1mo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempCard = card1mo;
                hideOtherStuff(tempCard, card3mo, card12mo);
                buyNowBtn.setVisibility(View.VISIBLE);
            }
        });

        card3mo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempCard = card3mo;
                hideOtherStuff(tempCard, card1mo, card12mo);
                buyNowBtn.setVisibility(View.VISIBLE);
            }
        });

        card12mo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempCard = card12mo;
                hideOtherStuff(tempCard, card1mo, card3mo);
                buyNowBtn.setVisibility(View.VISIBLE);
            }
        });


        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoader();
                rayzorPayInit(amount);

            }
        });
    }

    private void startLoader() {
        progress = new ProgressDialog(this);    //ProgressDialog
        progress.setTitle("Loading");
        progress.setMessage("Please wait");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.show();
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(7000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progress.dismiss();
            }
        }).start();
    }

    private void rayzorPayInit(int amount) {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_NUARedGCgPlYjc");
        checkout.setImage(R.drawable.ic_crown2);
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("name", "GilGal");
            jsonObject.put("description", "GilGal online payment");
            jsonObject.put("theme.color", "#185ADB");
            jsonObject.put("currency", "INR");
            jsonObject.put("amount", amount);
            jsonObject.put("prefill.contact", "9876543210");
            jsonObject.put("prefill.email", "gilgal.payment@gmail.com");
            checkout.open(PaymentActivity.this, jsonObject);


        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void hideOtherStuff(MaterialCardView clickedCard, MaterialCardView card1, MaterialCardView card2) {
        clickedCard.setStrokeColor(ContextCompat.getColor(PaymentActivity.this, R.color.mBlue));
        clickedCard.setStrokeWidth(3);
        card1.setStrokeWidth(0);
        card2.setStrokeWidth(0);

        isCardSelected = true;
    }
}


