package ua.kmakhno.iam.tipcalculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private EditText billEditText;
    private EditText tip10EditText;
    private EditText tip15EditText;
    private EditText tip20EditText;
    private EditText total10EditText;
    private EditText total15EditText;
    private EditText total20EditText;
    private EditText tipCustom;
    private EditText totalCustom;
    private TextView customTipTextView;
    private SeekBar seekBar;

    private double currentBill;
    private int customPercent;

    private static final String BILL_TOTAL = "BILL_TOTAL";
    private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            currentBill = 0.0;
            customPercent = 18;
        }else {
            currentBill = savedInstanceState.getDouble(BILL_TOTAL);
            customPercent = savedInstanceState.getInt(CUSTOM_PERCENT);
        }

        billEditText = (EditText)findViewById(R.id.billEditText);

        billEditText.addTextChangedListener(billEditTextWatcher);

        tip10EditText = (EditText)findViewById(R.id.tip10EditText);
        tip15EditText = (EditText)findViewById(R.id.tip15EditText);
        tip20EditText = (EditText)findViewById(R.id.tip20EditText);
        total10EditText = (EditText)findViewById(R.id.total10EditText);
        total15EditText = (EditText)findViewById(R.id.total15EditText);
        total20EditText = (EditText)findViewById(R.id.total20EditText);
        tipCustom = (EditText)findViewById(R.id.tipCustomEditText);
        totalCustom = (EditText)findViewById(R.id.totalCustomEditText);
        customTipTextView = (TextView)findViewById(R.id.customTipTextView);

        seekBar = (SeekBar)findViewById(R.id.customSeekBar);
        seekBar.setOnSeekBarChangeListener(this);




    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        customPercent = seekBar.getProgress();
        updateCustom();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        customPercent = seekBar.getProgress();
        updateCustom();
    }

    private void updateStandart(){

        double tenPercentTip = currentBill * .1;
        double tenPercentTotal = currentBill + tenPercentTip;

        tip10EditText.setText(String.format("%.02f", tenPercentTip));
        total10EditText.setText(String.format("%.02f", tenPercentTotal));

        double fifteenPercentTip = currentBill * .15;
        double fifteenPercentTotal = currentBill + fifteenPercentTip;

        tip15EditText.setText(String.format("%.02f", fifteenPercentTip));
        total15EditText.setText(String.format("%.02f", fifteenPercentTotal));

        double twentyPercentTip = currentBill * .2;
        double twentyPercentTotal = currentBill + twentyPercentTip;

        tip20EditText.setText(String.format("%.02f", twentyPercentTip));
        total20EditText.setText(String.format("%.02f", twentyPercentTotal));


    }

    private void updateCustom(){

        customTipTextView.setText(customPercent + " %");

        double customTipAmount =  currentBill * customPercent * .01;
        double customTotalAmount = customTipAmount + currentBill;

        tipCustom.setText(String.format("%.02f", customTipAmount));
        totalCustom.setText(String.format("%.02f", customTotalAmount));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble(BILL_TOTAL, currentBill);
        outState.putDouble(CUSTOM_PERCENT, customPercent);
    }

    private TextWatcher billEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {
                currentBill =  Double.parseDouble(s.toString());
            }catch (NumberFormatException e){

                currentBill = 0.0;
            }
            updateStandart();
            updateCustom();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
